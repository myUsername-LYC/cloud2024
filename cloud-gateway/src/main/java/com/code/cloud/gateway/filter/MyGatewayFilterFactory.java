package com.code.cloud.gateway.filter;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.GatewayToStringStyler;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriTemplate;
import reactor.core.publisher.Mono;

/**
 * 自定义网关过滤器
 *
 */
@Slf4j
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {
    public static final String TEMPLATE_KEY = "authentication";
    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    public MyGatewayFilterFactory() {
        super(Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enabled", "ignoreUrls");
    }

    public GatewayFilter apply(final Config config) {
        return new GatewayFilter() {
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                if (!config.isEnabled()) {
                    chain.filter(exchange.mutate().request(request).build());
                }

                if (config.getIgnoreUrls() != null && config.ignoreUrls.length > 0) {
                    RequestPath path = request.getPath();
                    for (String ignoreUrl : config.ignoreUrls) {
                        if (PATH_MATCHER.match(ignoreUrl, path.toString())) {
                            return chain.filter(exchange.mutate().request(request).build());
                        }
                    }
                }

                HttpHeaders headers = request.getHeaders();
                String authorization = headers.getFirst(TEMPLATE_KEY);
                //校验jwtToken的合法性
                ServerHttpResponse response = exchange.getResponse();
                try {
                    if (StringUtils.hasText(authorization)) {
                        return chain.filter(exchange);
                    }
                    throw new RuntimeException();
                } catch (RuntimeException ex) {
                    log.error("网关异常: [{}]", ex.getMessage());
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    //设置headers
                    HttpHeaders httpHeaders = response.getHeaders();
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.CACHE_CONTROL, "no-store, no-cache, max-age=0");
                    //设置body
                    JSONObject result = new JSONObject();
                    result.put("code", "401");
                    result.put("message", "未认证");
                    result.put("data", null);
                    result.put("timestamp", System.currentTimeMillis());
                    DataBuffer bodyDataBuffer = response.bufferFactory().wrap(result.toJSONBBytes());
                    return response.writeWith(Mono.just(bodyDataBuffer));
                }
            }

            public String toString() {
                return GatewayToStringStyler.filterToStringCreator(MyGatewayFilterFactory.this)
                        .append("enabled", config.isEnabled())
                        .append("ignoreUrls", config.ignoreUrls).toString();
            }
        };
    }

    @Data
    public static class Config {
        private boolean enabled;
        private String[] ignoreUrls;

        public Config() {
        }

    }
}
