package com.code.cloud.gateway.predicate;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * 通过继承AbstractRoutePredicateFactory自定义Predicate, 可以参考预定义好的Predicate
 *
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    public static final String PROTOCOL_KEY = "protocol";

    public MyRoutePredicateFactory() {
        super(Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("protocol");
    }

    public Predicate<ServerWebExchange> apply(final Config config) {
        return new GatewayPredicate() {
            public boolean test(ServerWebExchange serverWebExchange) {
                List<String> protocols = serverWebExchange.getRequest().getHeaders().get(PROTOCOL_KEY);
                if (protocols == null || protocols.isEmpty()) {
                    return false;
                }
                System.out.println("protocol = " + protocols.get(0));
                return protocols.get(0).equals(config.getProtocol());
            }

            public Object getConfig() {
                return config;
            }

            public String toString() {
                return String.format("My: %s", config.getProtocol());
            }
        };
    }

    public static class Config {
        private @NotNull String protocol;

        public Config() {
        }

        public String getProtocol() {
            return this.protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }
    }
}
