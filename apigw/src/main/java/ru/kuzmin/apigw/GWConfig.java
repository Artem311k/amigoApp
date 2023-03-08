package ru.kuzmin.apigw;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

@RequiredArgsConstructor
@Configuration
public class GWConfig {

//    @Bean
//    public HttpClient  webClient() {
//        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
//    }
}
