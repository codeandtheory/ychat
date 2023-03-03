package co.yml.ychat.jvm.config;

import co.yml.ychat.YChat;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YChatConfig {

    @Value("${apiKey}")
    private String apiKey;

    @Bean
    public YChat provideYChat() throws IOException {
        return YChat.create(apiKey);
    }
}
