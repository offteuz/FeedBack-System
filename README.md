
# 📊 Feedback System – Azure Functions
Sistema de processamento e análise de feedbacks acadêmicos utilizando Azure Functions, MySQL em nuvem e arquitetura serverless.
O projeto realiza coleta, consolidação, análise semanal e notificação automática de feedbacks críticos.

### 📌 Objetivo do Projeto
Este projeto tem como objetivo:

- Processar feedbacks armazenados em banco de dados MySQL
- Expor APIs serverless via Azure Functions
- Gerar relatórios consolidados e semanais
- Identificar feedbacks críticos e urgentes
- Executar notificações automáticas com base em regras de negócio

### 🧱 Arquitetura Geral
    Cliente (Browser / HTTP)
            v
    Azure Function App (Java 17)
            v
    Azure Database for MySQL
            v
    Application Insights (Logs e Monitoramento)

## ☁️ Ambiente Azure
### 📦 Grupo de Recursos
- **Nome:** feedback-system-rg 
- **Região:** Canada

#### 🔹Azure Function App
- **Nome:** feedback-system-functions
- **Runtime:** Java 17
- **Modelo:** Serverless
- **Triggers:**
  - **HTTP Trigger**
  - **Timer Trigger**

#### 🔹 Banco de Dados
- **Serviço:** Azure Database for MySQL (Flexible Server)
- **Servidor:** feedback-system-server
- **Banco:** feedbacksystemdb

#### 🔹 Observabilidade
- **Application Insights:** ai-feedback-system

### 🔐 Variáveis de Ambiente
Configuradas em Function App → Configuration → Application settings:
    
    DB_URL=jdbc:mysql://feedback-system-server.mysql.database.azure.com:3306/feedbacksystemdb?useSSL=true&serverTimezone=UTC
    DB_USER=admin@feedback-system-server
    DB_PASSWORD=********

Essas variáveis são acessadas no código via:

    System.getenv("DB_URL");
    System.getenv("DB_USER");
    System.getenv("DB_PASSWORD");


### 🧠 Funcionalidades Implementadas
#### ✔️ Relatório Geral por Tipo
- Filtra feedbacks por tipo (ex: URGENTE)
- Retorna dados completos com usuário e aula

#### ✔️ Relatório Semanal Consolidado
- Total de feedbacks
- Total de feedbacks urgentes
- Média das notas
- Estrutura preparada para agrupamento por dia

#### ✔️ Notificação Automática
- Executada via Timer Trigger
- Identifica feedbacks críticos
- Simula envio de notificação para administradores

### 🔌 Endpoints Disponíveis
#### 📍 Relatório Geral
    GET /api/relatorio?tipo=URGENTE

#### 📍 Relatório Semanal
    GET /api/relatorioSemanal

#### ⏱️ Notificação Automática
    Executada automaticamente a cada minuto (Timer Trigger)

### 🗂️ Estrutura do Projeto
    src/main/java/com/fiap/functions
    │
    ├── controller
    │   ├── RelatorioFunction.java
    │   ├── RelatorioSemanalFunction.java
    │   └── NotificacaoFunction.java
    │
    ├── service
    │   ├── RelatorioService.java
    │   └── NotificacaoFeedbackService.java
    │
    ├── repository
    │   ├── DatabaseConnection.java
    │   ├── FeedbackRepository.java
    │   ├── RelatorioRepository.java
    │   └── RelatorioSemanalRepository.java
    │
    └── dto
    ├── FeedbackDTO.java
    ├── RelatorioDTO.java
    └── RelatorioSemanalDTO.java

### 🛠️ Build e Deploy
#### 🔹 Build Local
    ./mvnw clean package

#### 🔹 Deploy para Azure
    ./mvnw azure-functions:deploy

### 🧪 Testes
As consultas SQL foram testadas diretamente no banco MySQL em nuvem para validação dos resultados antes do deploy.

### 📚 Tecnologias Utilizadas
- Java 17
- Azure Functions
- Azure Database for MySQL
- Application Insights
- Maven 
- JDBC
- SQL

### ⚠️ Observações Importantes
- A aplicação utiliza JDBC puro, sem frameworks ORM
- As Azure Functions dependem das variáveis de ambiente corretamente configuradas 
- Logs e exceções são enviados ao Application Insights 
- O ambiente é totalmente serverless

### 👨‍🎓 Projeto Acadêmico
Projeto desenvolvido para fins acadêmicos – FIAP – Fase 4, com foco em:
- Cloud Computing
- Serverless
- Integração com banco de dados em nuvem 
- Monitoramento e observabilidade

## 👨‍💻 Autores
- Francisco Aurizelio de Sousa ([GitHub](https://github.com/faurizel))
- Lucas Herculano Amaro ([GitHub](https://github.com/LucasHerculanoAmaro))
- Matheus Jesus de Souza ([GitHub](https://github.com/offteuz))
- Matteus Santos de Abreu ([GitHub](https://github.com/Nexusf1re))
- Vitor Silva França ([GitHub](https://github.com/vitor-silva-franca))
