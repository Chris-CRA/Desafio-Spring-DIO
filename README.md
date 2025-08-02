# 🎧 Spotify REST API - Projeto com Spring Framework

<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/1/19/Spotify_logo_without_text.svg" height="120" alt="Spotify Logo"/>
</p>

> Uma API REST construída com **Spring Framework** que simula operações com álbuns do Spotify.  
> O projeto aplica **Padrões de Projeto**, documentação com **Swagger** e autenticação simulada.

---

## 📌 Tecnologias Utilizadas

- ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
- ![Spring Framework](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
- ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
- ![Lombok](https://img.shields.io/badge/Lombok-red?style=for-the-badge&logo=java&logoColor=white)
- Maven, Spring Web, Spring Boot DevTools

---

## 🎯 Funcionalidades da API

- 🔍 Buscar álbum por ID (simulação + integração com a API do Spotify)
- ➕ Criar álbum (simulação)
- 🔄 Atualizar álbum
- ❌ Deletar álbum

Todos os endpoints são acessíveis e testáveis através do Swagger!

---

## 🔗 Acesse a Documentação Swagger

📎 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

> Inicie a aplicação localmente e acesse o link acima no seu navegador.

---


---

## 💡 Padrões de Projeto Aplicados

- **Facade** – Encapsula acesso à API externa simulada.
- **Service Layer** – Centraliza as regras de negócio.
- **Repository Pattern** – Organização da lógica de dados (mesmo em simulação).
- **Builder** (sugerido para estender): Pode ser aplicado na criação dos modelos.

---

## ▶️ Como Executar o Projeto

```bash
# Clone o repositório
git clone https://github.com/Chris-CRA/Desafio-Spring-DIO

# Acesse o diretório
cd spotify-rest-api

# Compile o projeto com Maven
mvn clean install

# Rode a aplicação
mvn spring-boot:run

🛡️ Autenticação (Simulada)
O projeto possui simulação de autenticação com base no application.properties.
Para produção, a integração real com o token OAuth 2.0 da API do Spotify pode ser implementada.

📣 Créditos
Projeto desenvolvido como estudo de Spring Framework + REST + Padrões de Projeto.
Inspirado na API oficial do Spotify.

<p align="center"><strong>💚 Let the music play with Spring! 💚</strong></p>
