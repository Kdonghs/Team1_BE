package team1.be.seamless.util.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer").bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        Server server = new Server();
        server.setUrl("https://seamlessup.com");

        return new OpenAPI()
            .info(apiInfo())
            .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
            .security(Arrays.asList(securityRequirement))
            .addServersItem(server);
    }

    public Info apiInfo() {
        return new Info()
            .title("Seamless")
            .description("kakao tech camp step3<br>"
                + "<li>추가 작성</li>")
            .version("0.1");
    }
}
