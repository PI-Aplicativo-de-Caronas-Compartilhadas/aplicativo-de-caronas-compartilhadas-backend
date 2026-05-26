# 🚗 Rachou - Aplicativo de Caronas Compartilhadas

<p align="center">
  <img src="https://img.shields.io/badge/Status-M%C3%B3dulo%20Pronto-brightgreen?style=for-the-badge" alt="Status">
  <img src="https://img.shields.io/badge/Java-Spring%20Boot-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Database-MySQL-blue?style=for-the-badge&logo=mysql" alt="MySQL">
</p>

---

## 📋 Sobre o Projeto

O **Rachou** é uma aplicação de carona compartilhada desenvolvida com o objetivo de conectar motoristas e passageiros que desejam compartilhar trajetos de forma prática, econômica e colaborativa. Ao dividir custos de combustível e pedágios, a plataforma promove uma alternativa inteligente e sustentável para a mobilidade urbana e rodoviária.

---

## 🛠️ Etapa Concluída: Módulo de Gerenciamento de Usuários

Nesta primeira etapa do desenvolvimento (Desafio 1 Backend), estruturamos com sucesso o microsserviço responsável pelo **cadastro e armazenamento das informações básicas** dos usuários da plataforma.

### 🏢 Estrutura da Entidade (`Usuario`)
A classe model mapeia as credenciais e dados essenciais para o funcionamento seguro das caronas:
* `id` (Long) — Identificador único gerado automaticamente pelo banco de dados.
* `nome` (String) — Nome completo para identificação no perfil.
* `email` (String) — E-mail exclusivo utilizado para login e contato.
* `telefone` (String) — Contato telefônico para comunicação direta entre caronistas.
* `senha` (String) — Senha de acesso (com validação de segurança de 12 a 50 caracteres).

---

## 🛣️ Rotas da API Desenvolvidas (`UsuarioController`)

A API REST gerencia os dados através dos seguintes endpoints mapeados e prontos para consumo:

| Método HTTP | Endpoint | Método na Controller | Resposta HTTP (Sucesso) | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| **GET** | `/usuarios` | `buscarTodos()` | `200 OK` | Lista todos os usuários cadastrados no sistema. |
| **GET** | `/usuarios/{id}` | `buscarPorId(...)` | `200 OK` | Busca os detalhes de um usuário específico pelo ID. |
| **GET** | `/usuarios/nome/{nome}` | `buscarPorNome(...)` | `200 OK` | Filtra usuários por parte do nome (Ignore Case). |
| **POST** | `/usuarios` | `post(...)` | `201 CREATED` | Cadastra um novo participante na plataforma. |
| **PUT** | `/usuarios` | `put(...)` | `200 OK` | Atualiza os dados cadastrais de um usuário existente. |
| **DELETE** | `/usuarios/{id}` | `delete(...)` | `204 NO CONTENT` | Remove permanentemente uma conta do sistema. |

---

## 🚀 Ferramentas & Tecnologias Utilizadas

* **Linguagem Backend:** Java 17+
* **Framework:** Spring Boot 3.x (Spring Data JPA, Jakarta Validation)
* **Ambiente de Desenvolvimento:** Spring Tool Suite (STS)
* **Banco de Dados Relacional:** MySQL
* **Validação de Endpoints / API Client:** Insomnia

---

## 👥 Organização do Time (TASK 02)

O gerenciamento e a execução deste projeto seguem a metodologia ágil, com a equipe dividida nos seguintes papéis:

* 🔴 **Product Owner (PO):** [Roberta](https://github.com/robertairds) — Responsável pela visão do produto, requisitos e gerenciamento do backlog.
* 🔵 **Scrum Master:** [Lilian](https://github.com/lilianlacerda) — Facilitadora do time, remoção de impedimentos e processos ágeis.
* 🟢 **Equipe de Desenvolvimento (Devs):** * [Igor](https://github.com/yama-knz) — Engenharia de Software / Backend
  * [Vinicius](https://github.com/viniovicente99) — Engenharia de Software / Backend
* 💗 **QA / Tester:** [Fabriciana](https://github.com/fabricianalima) — Garantia de qualidade, mapeamento de cenários e testes de endpoints no Insomnia.

---
<p align="center">Desenvolvido com dedicação pelo time Rachou 🚗✨</p>
