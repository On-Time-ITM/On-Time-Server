package org.itm.ontime.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(apiInfo())
        .servers(listOf(Server().url("/")))
        .components(
            Components()
                .addSecuritySchemes(
                    "bearer-key",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        )
        .addSecurityItem(
            SecurityRequirement().addList("bearer-key")
        )

    private fun apiInfo() = Info()
        .title("OnTime API Documentation")
        .description("This is API documents of OnTime Service.")
        .version("1.0")
        .contact(
            Contact()
                .name("OnTime Team")
                .email("dev.saeyeon@gmail.com")
        )

}