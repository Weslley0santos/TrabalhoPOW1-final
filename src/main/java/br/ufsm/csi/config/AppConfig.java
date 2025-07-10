// Ajuste o pacote para refletir a sua nova estrutura
package br.ufsm.csi.config;

// Adapte o import para o novo pacote do seu AutorizadorInterceptor
import br.ufsm.csi.security.AutorizadorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marca a classe como uma fonte de configuração para o Spring
public class AppConfig implements WebMvcConfigurer { // Implementa WebMvcConfigurer diretamente

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registra o seu interceptor
        registry.addInterceptor(new AutorizadorInterceptor());
    }


    @Bean
    public WebMvcConfigurer mvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new AutorizadorInterceptor());
            }
        };
    }

}