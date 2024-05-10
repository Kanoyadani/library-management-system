# Sistema de Biblioteca

Este projeto implementa um sistema simples de biblioteca em Java, utilizando a arquitetura MVC e persistindo dados em um banco de dados H2 em memória. O sistema apresenta um menu na interface CLI (Command Line Interface) com as seguintes opções:

- Cadastrar Livro: Permite cadastrar novos livros na biblioteca, incluindo informações como título, autor, ISBN e editora.
- Listar Livros: Exibe uma lista de todos os livros cadastrados na biblioteca, com a opção de filtrar por título ou autor.
- Atualizar Livro: Permite atualizar informações de um livro já cadastrado, como título, autor, ISBN ou editora.
- Excluir Livro: Remove um livro da biblioteca.
- Emprestar Livro: Permite registrar o empréstimo de um livro para um determinado usuário, incluindo data de empréstimo e data prevista para devolução.
- Devolver Livro: Registra a devolução de um livro emprestado, atualizando a data de devolução no sistema.




## Tecnologias Utilizadas:

- **Linguagem de programação:** Java
- **Arquitetura:** MVC (Model-View-Controller)
- **Banco de dados:** H2 (em memória)
- **Gerenciamento de dependências:** Maven
- **Controle de versão:** Git e GitHub
- **Interface**: CLI (Command Line Interface)


## Conceitos Estudados
- Arquitetura MVC
- API JDBC do Java
- Git e GitHub
- Maven
- Gerenciador de pacotes
- Arquivo de configuração pom.xml
- Spring CLI
## Instruções para Uso
Clone o repositório:
```Bash
git clone: https://github.com/richardrso/library-management-system
```
Acesse o diretório do projeto:
```Bash
cd library-management-system
```
Compile o projeto:
```Bash
mvn compile
```
Execute a aplicação:
```Bash
mvn exec:java
```
