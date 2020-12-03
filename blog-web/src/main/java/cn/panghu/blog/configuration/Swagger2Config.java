package cn.panghu.blog.configuration;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author pang hu
 * @date 2020/5/16 18:30
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger.enable}")
    private boolean enableSwagger;
    @Value("${info.version}")
    private String version;

    private static List<Parameter> operationParameters = Lists.newArrayList();

    static{
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("X-AUTH-TOKEN").description("身份令牌")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        operationParameters.add(tokenPar.build());
    }

    @Bean
    public Docket createItpApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户中心")
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .globalOperationParameters(operationParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.panghu.blog"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .enableUrlTemplating(true)
                .tags(new Tag("info", "胖虎的个人博客API文档说明"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("互联网接口文档")
                .description("互联网 Swagger API 服务")
                .termsOfServiceUrl("http://swagger.io/")
                .contact(new Contact("胖虎", "127.0.0.1", "1013266928@qq.com"))
                .version(version)
                .license("")
                .licenseUrl("")
                .build();
    }

}
