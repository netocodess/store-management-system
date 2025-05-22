
<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![Java][java-shield]](https://www.java.com)
[![Spring Boot][spring-boot-shield]](https://spring.io/projects/spring-boot)
[![RabbitMQ][rabbitmq-shield]](https://www.rabbitmq.com/)
[![Docker][docker-shield]](https://www.docker.com/)
[![MySQL][mysql-shield]](https://www.mysql.com/)
[![JWT][jwt-shield]](https://jwt.io/)
[![Swagger][swagger-shield]](https://swagger.io/)



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/netocodess/store-management-system">
    <img src="assets/logo.png" alt="Logo" width="230" height="150">
  </a>

  <h3 align="center">Projeto Loja Online</h3>

  <p align="center">
    Uma simulação de loja online, com funcionalidades como cadastro de vendedores, controle de estoque, compras, envio de e-mails e rastreamento de transportadora.
  </p>
</div>

<!-- TABLE OF CONTENTS -->


---

<!-- SOBRE O PROJETO -->
## Sobre o Projeto

Este projeto simula uma loja online, abrangendo todo o processo de vendas, desde o cadastro de vendedores, controle de estoque, até o envio de compras para a transportadora e rastreamento do pedido.

Principais funcionalidades:
- **Cadastro de Vendedores:** Permite o registro e gestão de vendedores que irão vender produtos na loja.
- **Controle de Estoque:** Monitora a quantidade de produtos disponíveis e impede a venda de itens fora de estoque.
- **Compra de Produtos:** Permite aos clientes realizar compras e efetuar o pagamento.
- **Envio de E-mail:** Envia um e-mail de confirmação após a compra ser realizada.
- **Rastreio de Envio:** Envia o código de rastreio para a transportadora e gera um link de rastreamento.


### Tecnologias Usadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:
- [![Java][java-shield]](https://www.java.com)
- [![Spring Boot][spring-boot-shield]](https://spring.io/projects/spring-boot)
- [![RabbitMQ][rabbitmq-shield]](https://www.rabbitmq.com/)
- [![Docker][docker-shield]](https://www.docker.com/)
- [![MySQL][mysql-shield]](https://www.mysql.com/)
- [![JWT][jwt-shield]](https://jwt.io/)
- [![Swagger][swagger-shield]](https://swagger.io/)


---

<!-- COMO INSTALAR -->
## Como Instalar

Siga os passos abaixo para rodar o projeto localmente.

### Pré-requisitos

Você precisará do seguinte para rodar o projeto:
- [Java 21](https://www.java.com)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [RabbitMQ](https://www.rabbitmq.com/)
- [MySQL](https://www.mysql.com/)
- [Docker](https://www.docker.com/)
- [JWT](https://jwt.io/)
- [Swagger](https://swagger.io/)

Instale o [Docker](https://www.docker.com/) e rode o RabbitMQ localmente ou use um serviço externo.

### Instalação

1. Clone o repositório

   ```bash
   git clone https://github.com/your-username/online-store.git
   cd online-store
   ```

2. Configure as variáveis de ambiente no arquivo `application.yml` com as credenciais do MySQL e RabbitMQ.

3. Como este projeto é dividido em microserviços, cada um roda em uma porta diferente:

Seller Service: porta 8080

Orders Service: porta 8081

Notifications Service: porta 8082

Product Service: porta 8087

Cashback Service: porta 8086

Para que as filas, exchanges, bindings e routing keys do RabbitMQ sejam criadas corretamente, na primeira vez que rodar o projeto, execute os microserviços na seguinte ordem:

Seller Service (porta 8080)

Orders Service (porta 8081)

Product Service (porta 8087)

Cashback Service (porta 8086)

Notifications Service (porta 8082)

Execute cada microserviço com o comando abaixo, adaptando o diretório e a porta conforme necessário:

```bash
./mvnw spring-boot:run

```

Acesse cada serviço nas URLs:

Seller: http://localhost:8080

Orders: http://localhost:8081

Notifications: http://localhost:8082

Cashback: http://localhost:8086

Product: http://localhost:8087


---

<!-- USABILIDADE -->
## Usabilidade

O projeto é uma simulação de loja online, e as principais funcionalidades incluem:
- Cadastro de produtos e vendedores.
- Controle de estoque com verificação de disponibilidade.
- Processo de compras, incluindo a geração de código de rastreio.
- Envio de e-mails para confirmação de compra.

### Testando a API

Você pode testar a API utilizando o [Swagger](https://swagger.io/) para explorar os endpoints de forma interativa.


---

<!-- ROADMAP -->
## Roadmap

- [x] Cadastro de vendedores
- [x] Cadastro de produtos e controle de estoque
- [x] Processo de compra e pagamento
- [ ] Envio de e-mails de confirmação
- [x] Implementação de integração com transportadoras (código de rastreio)
- [ ] Melhoria na interface de usuário
- [ ] Testes automatizados com integração CI/CD

---

<!-- CONTRIBUIÇÕES -->
## Contribuições

Contribuições são bem-vindas! Se você deseja sugerir melhorias ou adicionar novas funcionalidades, siga os seguintes passos:

1. Faça um fork do repositório
2. Crie uma nova branch (`git checkout -b feature/NovaFuncionalidade`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova funcionalidade'`)
4. Envie para a branch do seu fork (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request


<p align="right">
  <a href="#readme-top" style="font-family: Arial, sans-serif; font-size: 16px; color: #28a745; text-decoration: none; font-weight: bold; transition: color 0.3s ease; padding: 5px;">
    Voltar ao topo
  </a>
</p>
<style>
  a:hover {
    color: #218838; 
  }
</style>




<!-- MARKDOWN LINKS & IMAGES -->
[java-shield]: https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white
[spring-boot-shield]: https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
[rabbitmq-shield]: https://img.shields.io/badge/RabbitMQ-61DAFB?style=for-the-badge&logo=rabbitmq&logoColor=white
[docker-shield]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white
[mysql-shield]: https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white
[jwt-shield]: https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white
[swagger-shield]: https://img.shields.io/badge/Swagger-85ea2d?style=for-the-badge&logo=swagger&logoColor=black

