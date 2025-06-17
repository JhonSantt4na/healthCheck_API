# HealthCheck API – Sistema de Agendamento Médico

## 🧠 Visão Geral

A **HealthCheck API** é uma API REST construída com Java e Spring Boot, focada no agendamento de consultas médicas. O objetivo é proporcionar uma experiência real de desenvolvimento profissional, aplicando as melhores práticas de backend modernas.

---

## ✅ Tecnologias e Padrões Utilizados

- **Linguagem:** Java 21+
    
- **Framework:** Spring Boot
    
- **Camadas:** Controller → Service → Repository
    
- **DTO com `record`**
    
- **MapStruct para conversões**
    
- **Flyway para versionamento de banco**
    
- **MySQL (com Docker)**
    
- **Swagger para documentação da API**
    
- **Postman para testes**
    
- **Tratamento de exceções customizadas**
    
- **SpringDoc OpenAPI + Swagger UI**
    
- **Spring Security (JWT no futuro)**
    
- **Docker + Docker Compose**
    
- **Cors Configuration**
    
- **Padronização de logs com SLF4J**
    
- **Testes com JUnit 5**
    
- **Kubernetes (Futuramente)**
    

---

## 🔁 Fluxograma da Aplicação

```plaintext
[Usuário]
   |
   v
[Front/Postman]
   |
   v
[Controller - REST API]
   |
   v
[Service Layer]
   |
   v
[DTO ↔ MapStruct]
   |
   v
[Repository - JPA]
   |
   v
[MySQL via Docker]
```

---

## 🔧 Estrutura de Branches (Git)

- `main`: versão estável e pronta para deploy
    
- `develop`: desenvolvimento contínuo (integração de features)
    
- `feature/<nome-da-feature>`: desenvolvimento de novas funcionalidades
    
- `bugfix/<descrição-do-bug>`: correções específicas
    
- `hotfix/<ajuste-emergencial>`: correções emergenciais em produção
    

---

## 🗂️ Backlog Funcional (Resumo do MVP)

| ID  | Título                           | Descrição                                             | Prioridade |
| --- | -------------------------------- | ----------------------------------------------------- | ---------- |
| 1   | Cadastro de Paciente             | Criar endpoint para cadastrar novo paciente           | Alta       |
| 2   | Cadastro de Médico               | Criar endpoint para cadastrar novo médico             | Alta       |
| 3   | Listar Médicos por especialidade | Retornar lista filtrada de médicos por especialidade  | Alta       |
| 4   | Agendamento de Consulta          | Criar agendamento de consulta entre paciente e médico | Alta       |
| 5   | Cancelamento de Consulta         | Permitir cancelamento e salvar motivo                 | Média      |
| 6   | Consulta detalhada               | Obter dados detalhados de consulta                    | Média      |
| 7   | Swagger Documentation            | Expor todos os endpoints via Swagger UI               | Alta       |
| 8   | Versionamento de Banco           | Migrar estrutura com Flyway                           | Alta       |
| 9   | Deploy com Docker Compose        | Subir sistema e banco com Docker Compose              | Alta       |
| 10  | Testes com Postman               | Validar todos os endpoints no Postman                 | Alta       |
| 11  | Logs estruturados                | Adicionar logs com SLF4J em todos os fluxos           | Média      |
| 12  | Exceptions Personalizadas        | Criar tratamento de exceções com `@ControllerAdvice`  | Alta       |
| 13  | Configuração CORS                | Liberar acesso externo de forma controlada            | Média      |
| 14  | Health Check Endpoint            | Criar endpoint `/health` para status                  | Média      |

---

## 📋 Issues para GitHub

1. `#1` Setup inicial do projeto Spring Boot com dependências
    
2. `#2` Criar entidades: Paciente, Médico, Consulta
    
3. `#3` Criar DTOs com records
    
4. `#4` Implementar MapStruct para conversão entre DTO e Entity
    
5. `#5` Criar Camada de Service com regras de negócio
    
6. `#6` Criar Controllers e expor endpoints
    
7. `#7` Configurar CORS
    
8. `#8` Configurar Swagger (SpringDoc OpenAPI)
    
9. `#9` Configurar Docker e docker-compose.yml
    
10. `#10` Criar migrations com Flyway
    
11. `#11` Tratar exceções com `@ControllerAdvice`
    
12. `#12` Adicionar logs com SLF4J
    
13. `#13` Criar testes no Postman
    
14. `#14` Criar README.md com instruções de uso
    

---

## 🧱 Entidades

### 🧑 Médico (`Doctor`)

- id
    
- nome
    
- especialidade (enum)
    
- crm
    
- telefone
    
- ativo (boolean)
    

### 👤 Paciente (`Patient`)

- id
    
- nome
    
- cpf
    
- email
    
- telefone
    
- ativo (boolean)

- Plano de saude

Operadoras Brasileiras Comuns:
    UN (Unimed)
    AM (Amil)
    BR (Bradesco Saúde)
    SU (SulAmérica)

sus:
4. Exemplos de Números VÁLIDOS para Teste
   Tipo	Número do CNS
   Definitivo	703 6888 6073 0008
   Definitivo	131 6544 3387 0002
   Provisório	899 0000 1234 5678

   Observação: Remova os espaços antes de validar!

### 📅 Consulta (`Appointment`)

- id
    
- médico_id (FK)
    
- paciente_id (FK)
    
- data_hora
    
- status (enum: AGENDADA, CANCELADA)
    
- motivo_cancelamento (nullable)

---


✅ DTOs de Usuário (User) - para autenticação e segurança

    UserRegisterDTO – para cadastro de usuário.

    UserLoginDTO – para login.

    UserResponseDTO – para retornar dados públicos (como id, username, role).

    TokenResponseDTO – para retornar o JWT após login.

✅ DTOs de Paciente (Patient)

    PatientCreateDTO – para cadastro de novo paciente.

    PatientUpdateDTO – para atualização de dados.

    PatientResponseDTO – para exibir dados do paciente.

✅ DTOs de Médico (Doctor)

    DoctorCreateDTO – para cadastro de médico (incluindo especialidade e agenda).

    DoctorUpdateDTO – para atualizar dados (como disponibilidade).

    DoctorResponseDTO – para exibir dados do médico.

    DoctorAvailabilityDTO – para exibir os horários disponíveis para consulta.

✅ DTOs de Consulta (Appointment)

    AppointmentRequestDTO – para o paciente solicitar agendamento (escolher médico, data/hora).

    AppointmentApprovalDTO – para o médico aceitar ou recusar.

    AppointmentCancelDTO – para cancelar com antecedência (validando a regra de 2 dias).

    AppointmentResponseDTO – para retornar dados da consulta (incluindo status, médico, paciente).

    AppointmentReportDTO – usado pelo médico para gerar relatório (em PDF) após a consulta.



---

## 🛠️ Exemplo de Estrutura de Projeto

```
src/
├── main/
│   ├── java/com/healthcheck/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── mapper/
│   │   ├── repository/
│   │   ├── service/
│   │   └── config/
│   └── resources/
│       ├── application.properties
│       └── db/migration/V1__init.sql
├── test/
│   └── java/com/healthcheck/
│       └── ...
```

---

## 🐳 Docker e Docker Compose

**Dockerfile:**

```dockerfile
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/healthcheck-api.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**docker-compose.yml:**

```yaml
version: "3.8"
services:
  db:
    image: mysql:8
    container_name: mysql_db
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: healthcheck
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql

  api:
    build: .
    container_name: healthcheck_api
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/healthcheck
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root

volumes:
  db_data:
```

---

## 📂 Postman

- Criar uma collection `HealthCheckAPI`
    
- Incluir testes para:
    
    - POST /medicos
        
    - POST /pacientes
        
    - POST /consultas
        
    - DELETE /consultas/{id}
        
    - GET /consultas/{id}
        
    - GET /swagger-ui.html
        

---

## 📄 Swagger/OpenAPI

- URL: `/swagger-ui.html`
    
- Configurado via SpringDoc (`springdoc-openapi-ui`)
    
- Versão da API: v1.0.0
    
- Todos os endpoints documentados
    

---

## 📌 Considerações Finais

Este projeto será um grande diferencial no seu portfólio como Júnior ou Estagiário. Demonstra domínio sobre:

- Boas práticas
    
- Camadas bem definidas
    
- Clean Code
    
- Migrations e versionamento de banco
    
- Integração com ferramentas modernas
    
- Possibilidade futura de deploy em Kubernetes
    

---

<!---  Níveis de Log:

- audit: Para logs de auditoria (ex.: criação de `Doctor`).
- com.example: Para o pacote do projeto, com nível `DEBUG` para detalhes.
- root: Logger padrão com nível `INFO`.

logger.trace(): Detalhes muito específicos (raramente usado).
logger.debug(): Informações de depuração (ex.: parâmetros recebidos).
logger.info(): Eventos importantes (ex.: sucesso na criação de um Doctor).
logger.warn(): Situações anormais, mas não críticas (ex.: email duplicado).
logger.error(): Erros graves (ex.: falha inesperada).

Para depuração durante o desenvolvimento (nível DEBUG).
Para monitoramento em produção (níveis INFO, WARN, ERROR).
Para registrar erros que precisam de investigação (nível ERROR)

AUDID Apenas Info:

private static final Logger auditLogger = LoggerFactory.getLogger("audit");
private static final Logger logger = LoggerFactory.getLogger(Startup.class);
-->
