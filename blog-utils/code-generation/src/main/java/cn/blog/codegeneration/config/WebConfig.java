package cn.blog.codegeneration.config;

/**
 * @author pang hu
 * @date 2020/10/18 12:21
 */
@Configuration
public class WebConfig {

    /**
     * 解决跨域调用问题
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .exposedHeaders(HttpHeaders.SET_COOKIE)
                        .maxAge(3600L);
            }
        };
    }


}

