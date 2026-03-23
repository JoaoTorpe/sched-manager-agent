package com.torpe.mcp_client.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "telegram.api")
public class TelegramProperties {

    private String token;
    private String url;
    private String channel;

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

    public String getChannel() {return channel;}

    public void setChannel(String channel) {this.channel = channel;}
}
