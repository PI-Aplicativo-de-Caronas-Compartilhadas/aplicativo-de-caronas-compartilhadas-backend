# 🚗 Rachou - Aplicativo de Caronas Compartilhadas

<p align="center">
  <img src="https://img.shields.io/badge/Java-Spring%20Boot-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Database-MySQL-blue?style=for-the-badge&logo=mysql" alt="MySQL">
</p>

---

## 📋 Sobre o Projeto

O **Rachou** é uma aplicação de carona compartilhada desenvolvida com o objetivo de conectar motoristas e passageiros que desejam compartilhar trajetos de forma prática, econômica e colaborativa. Ao dividir custos de combustível e pedágios, a plataforma promove uma alternativa inteligente e sustentável para a mobilidade urbana e rodoviária.

---

## 🛠️ Módulos de Dados Implementados (Models)

O ecossistema da aplicação foi completamente estruturado utilizando os relacionamentos do Spring Data JPA:

### 1. Gerenciamento de Usuários (`Usuario`)
Mapeia as credenciais, perfis e dados essenciais para o funcionamento seguro das caronas:
* `id` (Long) — Identificador único.
* `nome` (String) — Nome completo do participante.
* `email` (String) — E-mail exclusivo utilizado para login e contato.
* `telefone` (String) — Contato telefônico para comunicação direta.
* `senha` (String) — Senha cifrada no banco via BCrypt (mínimo de 12 caracteres).
* `foto` (String) — Link para a foto de perfil do caronista.
* `tipo` (String) — Define a categoria de permissão ou perfil do usuário.

### 2. Modalidades de Carona (`Modalidade`)
Categoriza os tipos de viagens oferecidas (ex: Viagem Municipal, Intermunicipal, Universitária):
* `id` (Long) — Identificador único.
* `nome` (String) — Nome descritivo da modalidade.
* `descricao` (String) — Detalhes sobre o propósito da categoria.
* *Relacionamento:* Uma Modalidade possui muitas (`@OneToMany`) Viagens.

### 3. Registro de Itinerários (`Viagem`)
Controla o fluxo ativo de trajetos oferecidos e buscados na plataforma:
* `id` (Long) — Identificador único.
* `origem` / `destino` (String) — Localidades de saída e chegada da carona.
* `preco` (BigDecimal) — Valor estipulado a ser rateado.
* `previsaoSaida` / `previsaoChegada` (LocalDateTime) — Cronograma do trajeto.
* `status` (String) — Estado atual do percurso.
* *Relacionamentos:* Muitas viagens pertencem a um (`@ManyToOne`) Usuário e a uma (`@ManyToOne`) Modalidade.

---

## 🛡️ Segurança & Autenticação (Spring Security + JWT)

A API conta com um controle rígido de segurança baseado em tokens **JWT (JSON Web Token)** de arquitetura *Stateless*.
* **Criptografia:** Todas as senhas são mascaradas no banco de dados com o algoritmo `BCryptPasswordEncoder`.
* **Filtros de Autenticação:** A classe `JwtAuthFilter` intercepta requisições HTTP, extrai o cabeçalho `Authorization: Bearer <token>` e valida a sessão do usuário.
* **Regras de Acesso:** Os endpoints de cadastro (`/usuarios/cadastrar`) e login (`/usuarios/logar`), além da documentação do Swagger, são públicos. Todas as demais rotas exigem o Token JWT ativo no Header.

---

## 🛣️ Catálogo de Rotas da API (Endpoints)

Abaixo estão listadas todas as rotas unificadas da aplicação gerenciadas pelos controladores REST:

### Módulo de Usuários e Autenticação (`UsuarioController`)
| Método HTTP | Endpoint | Resposta HTTP (Sucesso) | Protegido? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/usuarios/cadastrar` | `201 CREATED` | 🔓 Não | Cadastra um novo usuário criptografando a senha. |
| **POST** | `/usuarios/logar` | `200 OK` | 🔓 Não | Autentica as credenciais e retorna o Token JWT. |
| **GET** | `/usuarios` | `200 OK` | 🔒 Sim | Lista todos os usuários cadastrados. |
| **GET** | `/usuarios/{id}` | `200 OK` | 🔒 Sim | Busca detalhes de um usuário específico pelo ID. |
| **GET** | `/usuarios/nome/{nome}` | `200 OK` | 🔒 Sim | Filtra usuários por termos contidos no nome. |
| **PUT** | `/usuarios` | `200 OK` | 🔒 Sim | Atualiza as informações cadastrais existentes. |
| **DELETE** | `/usuarios/{id}` | `204 NO CONTENT` | 🔒 Sim | Exclui permanentemente uma conta da plataforma. |

### Módulo de Modalidades (`ModalidadeController`)
| Método HTTP | Endpoint | Resposta HTTP (Sucesso) | Protegido? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| **GET** | `/modalidades` | `200 OK` | 🔒 Sim | Lista todas as modalidades configuradas. |
| **GET** | `/modalidades/{id}` | `200 OK` | 🔒 Sim | Busca modalidade por ID específico. |
| **GET** | `/modalidades/nome/{nome}` | `200 OK` | 🔒 Sim | Filtra modalidades por nome. |
| **POST** | `/modalidades` | `201 CREATED` | 🔒 Sim | Registra uma nova categoria de carona. |
| **PUT** | `/modalidades` | `200 OK` | 🔒 Sim | Edita os dados de uma modalidade. |
| **DELETE** | `/modalidades/{id}` | `204 NO CONTENT` | 🔒 Sim | Remove uma modalidade do catálogo. |

### Módulo de Viagens (`ViagemController`)
| Método HTTP | Endpoint | Resposta HTTP (Sucesso) | Protegido? | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| **GET** | `/viagens` | `200 OK` | 🔒 Sim | Lista todas as caronas disponíveis na plataforma. |
| **GET** | `/viagens/{id}` | `200 OK` | 🔒 Sim | Recupera dados detalhados de uma viagem pelo ID. |
| **GET** | `/viagens/destino/{destino}` | `200 OK` | 🔒 Sim | Filtra rotas com base na cidade/local de destino. |
| **POST** | `/viagens` | `201 CREATED` | 🔒 Sim | Publica uma oferta de carona simulando horários. |
| **DELETE** | `/viagens/{id}` | `204 NO CONTENT` | 🔒 Sim | Cancela e remove uma viagem ativa do sistema. |

> 💡 **Nota de Regra de Negócio:** No cadastro de uma viagem (`POST /viagens`), a camada de serviço (`ViagemService`) injeta automaticamente a previsão de saída com base no horário atual do servidor e calcula de forma randômica uma estimativa realista de tempo e distância para preenchimento da previsão de chegada.

---

## 📖 Documentação Automatizada (Swagger/OpenAPI)

A API agora conta com documentação interativa integrada. Ao subir o servidor local, você pode testar todas as requisições e verificar os esquemas de dados diretamente pela interface gráfica.
* **Testes Autenticados:** A interface possui o botão **Authorize** configurado via `SwaggerConfig`. Basta inserir o token gerado no login (o prefixo `Bearer` é adicionado automaticamente pela especificação) para liberar os testes nos endpoints bloqueados.

---

## 🛠️ Ferramentas & Tecnologias Atualizadas

* **Linguagem Backend:** Java 21
* **Framework:** Spring Boot 3.5.14
  * Spring Data JPA
  * Spring Security (Autenticação Stateless e Proteção de Rotas)
  * Jakarta Validation
* **Mapeamento de Token:** io.jsonwebtoken (JJWT v0.12.6)
* **Documentação:** Springdoc OpenAPI Starter UI (v2.8.9)
* **Banco de Dados Relacional:** MySQL (Produção/Desenvolvimento) & Banco H2 (Escopo de Testes)
* **Validação de Endpoints / API Client:** Insomnia / Swagger UI

---

## 👥 Organização do Time (TASK 03)

O gerenciamento e a execução deste projeto seguem a metodologia ágil, com a equipe dividida nos seguintes papéis:

* 🔴 **Product Owner (PO):** [Lilian](https://github.com/lilianlacerda) — Responsável pela visão do produto, requisitos e gerenciamento do backlog.
* 🔵 **Scrum Master:** [Igor](https://github.com/yama-knz) — Facilitadora do time, remoção de impedimentos e processos ágeis.
* 🟢 **Equipe de Desenvolvimento (Devs):** [Fabriciana](https://github.com/fabricianalima) — Engenharia de Software / Backend
  * [Roberta](https://github.com/robertairds) — Engenharia de Software / Backend
* 💗 **QA / Tester:** [Vinicius](https://github.com/viniovicente99)  — Garantia de qualidade, mapeamento de cenários e testes de endpoints no Insomnia.


---
<p align="center">Desenvolvido com dedicação pelo time Rachou 🚗✨</p>
