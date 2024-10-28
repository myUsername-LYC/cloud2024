package com.code.cloud.payment9001;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "user")
@Component
@Data
public class Props {
//    @Value("${user.detail:default}")
    private String detail;
//    @Value("${user.password:default}")
    private String password;
}
