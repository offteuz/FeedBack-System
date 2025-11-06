package com.fiap.feedbacksystem.config;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@Configuration
public class SwaggerConfig {
    @GetMapping("/")
    public void redirectSwagger(HttpServletResponse response) {
        String url = "swagger-ui/index.html";
        response.setHeader("Location", url);
        response.setStatus(302);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FIAP - Tech Challenge - Fase 4 de Arquitetura e Desenvolvimento Java")
                        .description("API desenvolvida para o Tech Challenge da Fase 4 de 5.\n\n" +
                                "Esta aplicação simula uma plataforma de cursos on-line, onde os\n" +
                                "estudantes podem fornecer feedbacks e os administradores podem\n" +
                                "acompanhar rapidamente a satisfação dos alunos.")
                        .version("v1")
                        .contact(new Contact()
                                .name("FeedBack-System")
                                .email("contato@feedbacksystem.com")
                                .url("https://github.com/offteuz/FeedBack-System"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Técnica Completa")
                        .url("https://github.com/offteuz/FeedBack-System"))
                .addTagsItem(new Tag().name("Criar Usuário").description("Criação de usuários"))
                .addTagsItem(new Tag().name("Criar Feedback").description("Criação de feedbacks"))
                .addTagsItem(new Tag().name("Criar Aula").description("Criação de aulas"));
    }

    /**
     * Este bean roda DEPOIS que o Springdoc já escaneou os DTOs
     */
    @Bean
    public OpenApiCustomizer customApiPaths() {
        return openApi -> {
            openApi
                    // Usuário
                    .path("/api/usuarios", new PathItem().post(new Operation()
                                    .summary("Cria um novo usuário")
                                    .addTagsItem("Criar Usuário")
                                    .requestBody(new RequestBody()
                                            .description("Dados do usuário para criação")
                                            .required(true)
                                            .content(new Content().addMediaType("application/json", new MediaType()
                                                    .schema(new Schema().$ref("#/components/schemas/UsuarioRequestDTO")))))
                                    .responses(new ApiResponses()
                                            .addApiResponse("201", new ApiResponse().description("Usuário criado com sucesso")
                                                    .content(new Content().addMediaType("application/json", new MediaType()
                                                            .schema(new Schema().$ref("#/components/schemas/UsuarioResponseDTO")))))
                                            .addApiResponse("400", new ApiResponse().description("Requisição inválida"))
                                            .addApiResponse("403", new ApiResponse().description("Acesso negado"))
                                            .addApiResponse("500", new ApiResponse().description("Erro interno do servidor"))
                                    )
                            )
                    )
                    // Feedbacks
                    .path("/api/feedbacks", new PathItem().post(new Operation()
                                    .summary("Cria um novo feedback")
                                    .addTagsItem("Criar Feedback")
                                    .requestBody(new RequestBody()
                                            .description("Dados do feedback para criação")
                                            .required(true)
                                            .content(new Content().addMediaType("application/json", new MediaType()
                                                    .schema(new Schema().$ref("#/components/schemas/FeedbackRequestDTO")))))
                                    .responses(new ApiResponses()
                                            .addApiResponse("201", new ApiResponse().description("Feedback criado com sucesso")
                                                    .content(new Content().addMediaType("application/json", new MediaType()
                                                            .schema(new Schema().$ref("#/components/schemas/FeedbackResponseDTO")))))
                                            .addApiResponse("400", new ApiResponse().description("Requisição inválida"))
                                            .addApiResponse("403", new ApiResponse().description("Acesso negado"))
                                            .addApiResponse("500", new ApiResponse().description("Erro interno do servidor"))
                                    )
                            )
                    )
                    .path("/api/aulas", new PathItem().post(new Operation()
                                    .summary("Cria uma nova aula")
                                    .addTagsItem("Criar Aula")
                                    .requestBody(new RequestBody()
                                            .description("Dados da aula para criação")
                                            .required(true)
                                            .content(new Content().addMediaType("application/json", new MediaType()
                                                    .schema(new Schema().$ref("#/components/schemas/AulaRequestDTO")))))
                                    .responses(new ApiResponses()
                                            .addApiResponse("201", new ApiResponse().description("Aula criada com sucesso")
                                                    .content(new Content().addMediaType("application/json", new MediaType()
                                                            .schema(new Schema().$ref("#/components/schemas/AulaResponseDTO")))))
                                            .addApiResponse("400", new ApiResponse().description("Requisição inválida"))
                                            .addApiResponse("403", new ApiResponse().description("Acesso negado"))
                                            .addApiResponse("500", new ApiResponse().description("Erro interno do servidor"))
                                    )
                            )
                    );
        };
    }
}