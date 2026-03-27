# 🛒 E-commerce API - Desafio de Capacitação Java 2026

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

## 📄 Sobre o Projeto

Uma API RESTful robusta desenvolvida para simular o backend completo de um e-commerce. Este projeto foi construído como parte do desafio de capacitação em Java, evoluindo desde o cadastro básico de produtos até um motor de vendas completo com travas de segurança e regras de negócio avançadas.

O sistema gerencia o fluxo completo de uma loja virtual, garantindo a integridade dos dados financeiros e de estoque. A arquitetura foi pensada para resolver problemas reais de e-commerce, como flutuação de preços durante a compra, vendas sem estoque e avaliações falsas.

## ✨ Funcionalidades Implementadas

- **CRUD de Produtos:** Gerenciamento completo de itens da loja.
- **Categorias Hierárquicas:** Estrutura de árvore (ex: Eletrônicos > Smartphones).
- **Histórico de Preços Automático:** Auditoria contínua (`@PreUpdate`) que arquiva o valor antigo sempre que um produto sofre reajuste.
- **Documentação Viva:** Interface Swagger completa para testes e exploração da API.
- **Carrinho de Compras Inteligente:** Implementação da regra de "Preço Congelado". O cliente paga o valor do momento em que o item foi adicionado ao carrinho, imune a aumentos de preço posteriores.
- **Livro-Caixa de Estoque:** Sistema de transações (Entrada/Saída) que atua como barreira intransponível: o sistema bloqueia (Erro 500) qualquer tentativa de venda se o saldo do produto for zero.
- **Motor de Checkout:** Conversão de carrinhos finalizados em Pedidos oficiais, calculando dinamicamente o valor total.
- **Cupons de Desconto:** Aplicação de promoções percentuais diretamente no fechamento do pedido.
- **Sistema de Reviews Anti-Fraude:** Regra de negócio rigorosa que varre o histórico do cliente e só permite que ele avalie (1 a 5 estrelas) um produto se houver um pedido pago contendo aquele item exato.
- **Testes Automatizados:** Cobertura de regras de negócio críticas (cálculo de descontos e fechamento de pedidos) utilizando JUnit 5 e Mockito.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 24
- **Framework:** Spring Boot 3.5.11
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** Oracle Database
- **Testes:** JUnit 5, Mockito
- **Documentação:** Springdoc OpenAPI (Swagger UI)
- **Ferramentas:** Lombok, Maven

## 🚀 Como Executar

### Pré-requisitos
Certifique-se de ter o Java e o Maven instalados em sua máquina, além de acesso a um banco de dados Oracle.

### Passo a Passo

1. **Clone o repositório:**
   ```
   git clone https://github.com/lucasscarmona/jp40-capacitacao-2026.git

2. **Configure o Banco de Dados:**
   Configure as credenciais do seu banco de dados Oracle no arquivo `src/main/resources/application.yml` (ou `.properties`).

3. **Execute a aplicação:**
   Rode a aplicação na sua IDE (IntelliJ/Eclipse) ou via linha de comando com o Maven.

4. **Acesse a documentação:**
   Com o projeto rodando, acesse a documentação e teste os endpoints através do Swagger no seu navegador:
   http://localhost:9090/swagger-ui/index.html

## 👨‍💻 Autor

Feito com dedicação por **Lucas Carmona Fernandes** 👋

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/lucasscarmona/)
