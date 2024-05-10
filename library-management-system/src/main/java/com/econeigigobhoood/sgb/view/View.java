package com.econeigigobhoood.sgb.view;

import com.econeigigobhoood.sgb.controller.Controller;
import com.econeigigobhoood.sgb.controller.Misc;
import com.econeigigobhoood.sgb.model.Livro;

import java.util.List;
import java.util.Scanner;

public class View {
    private MainMenu mainMenu;
    private Controller controller;
    private Scanner scanner;

    // Puxar a instancia pelo construtor
    // Ação realizada no application
    public View(MainMenu mainMenu, Controller controller, Scanner scanner) {
        this.mainMenu = mainMenu;
        this.controller = controller;
        this.scanner = scanner;
    }
    
    public void callBookRegister() {
        // Se o arquivo tmp estiver vazio, significa que é um novo cadastro
        mainMenu.baseMsgFunc("Cadastrar livros novos");
        
        String op = "";

        Misc.text("Dados a serem preenchidos:\n*Nome do livro\n*Autor do livro\n*Quantidade de páginas\n\n");

        Misc.text("Nome do livro: ");
        String nomeLivro = scanner.nextLine();
        Misc.text("Autor do livro: ");
        String autorLivro = scanner.nextLine();
        Misc.text("Quantidade de páginas: ");
        int paginasLivro = scanner.nextInt();

        Misc.text("Livro a ser cadastrado: ");
        System.out.println("Nome do livro: " + nomeLivro);
        System.out.println("Autor do livro: " + autorLivro);
        System.out.println("Quantidade de páginas: " + paginasLivro);

        Misc.text("Esta correto o cadastro? (S/N): ");
        scanner.nextLine(); // Limpar buffer
        op = scanner.nextLine();
        op = op.toUpperCase();

        // Se condição for verdadeira os dados serão enviados
        if (op.equals("S")) {
            // Cadastrando livro no Banco de Dados
            Livro livro = new Livro(nomeLivro, autorLivro, paginasLivro);
            controller.insertarLivro(livro);
            Misc.text("Livro cadastrado com sucesso!");
            mainMenu.callMainMenu();
        } else if (op.equals("N")) {
            Misc.clearScreen();
            Misc.text("Operação cancelada!");
            callBookRegister();
        } else {
            Misc.clearScreen();
            Misc.text("Escolha invalida, operação cancelada!");
            callBookRegister();
        }
    }

    public void bookListing() {
        mainMenu.baseMsgFunc(" Listagem de livros cadastrados ");

        List<Livro> livros = controller.listaLivros();
        System.out.println("=== Livros Cadastrados ===");

        if (!livros.isEmpty()) {
            for (Livro livro : livros) {
                System.out.println("ID: " + livro.getIdlivro() + ", Nome: " + livro.getNome() + ", Status: " + livro.getStatus());
            }
        } else {
            System.out.println("Não há livros cadastrados.");
        }
        System.out.println("===========================\n");
        mainMenu.callMainMenu();
    }

    public void searchID() {
        mainMenu.baseMsgFunc(" Buscador de livro ");

        Misc.text("Insira o ID do livro a ser consultado: ");
        int id = scanner.nextInt();
        Livro livro = controller.buscaLivro(id);
        if(livro != null) {
            infoBook(livro);

            mainMenu.callMainMenu();
        } else {
            Misc.clearScreen();
            Misc.text("Livro não encontrado ou ID inexistente");

            mainMenu.callMainMenu();
        }
    }

    public void callUpdateBook() {
        mainMenu.baseMsgFunc("Alterar cadastro do livro");

        Misc.text("Insira o ID do livro a ser alterado: ");
        int id = scanner.nextInt();
        Livro livro = controller.buscaLivro(id);

        if (livro != null) {
            System.out.println(">>> Livro selecionado <<<");
            infoBook(livro);
            System.out.println(">>> Atualize os valores <<<");

            scanner.nextLine(); // Limpar buffer
            System.out.println("Digite o novo nome do livro: ");
            String nome = scanner.nextLine();
            livro.setNome(nome);
            System.out.println("Digite o novo autor do livro: ");
            String autor = scanner.nextLine();
            livro.setAutor(autor);
            System.out.println("Digite o novo número de páginas do livro: ");
            int numPaginas = scanner.nextInt();
            livro.setPaginas(numPaginas);
            controller.atualizarLivro(livro);
            System.out.println("Livro atualizado com sucesso!");

            mainMenu.callMainMenu();
        } else {
            System.out.println("Livro não encontrado!");

            mainMenu.callMainMenu();
        }
    }

    public void deleteBook() {
        mainMenu.baseMsgFunc("Deletar livro cadastrado");

        Misc.text("Insira o ID do livro a ser deletado: ");
        int id = scanner.nextInt();
        Livro livro = controller.buscaLivro(id);
        if(livro != null) {
            System.out.println(">>> Livro selecionado <<<");
            infoBook(livro);

            System.out.println("Deseja continuar? [S/N]: ");
            scanner.nextLine(); // Limpar buffer
            String op = scanner.nextLine();
            op = op.toUpperCase();
    
            if (op.equals("S")) {
                controller.excluiLivro(id);
                Misc.text("Livro deletado com sucesso!");

                mainMenu.callMainMenu();
            } else if (op.equals("N")) {
                Misc.clearScreen();
                Misc.text("Operação cancelada!");
                mainMenu.callMainMenu();
            } else {
                Misc.clearScreen();
                Misc.text("Escolha invalida, operação cancelada!");
                mainMenu.callMainMenu();
            }
        } else {
            Misc.clearScreen();
            Misc.text("Livro não encontrado ou ID inexistente");

            mainMenu.callMainMenu();
        }
    }

    public void lendBook() {
        mainMenu.baseMsgFunc("Emprestar livro");

        Misc.text("Insira o ID do livro a ser emprestado: ");
        int id = scanner.nextInt();
        Livro livro = controller.buscaLivro(id);
        if(livro != null) {
            System.out.println(">>> Livro selecionado <<<");
            infoBook(livro);

            String emprestado = livro.getStatus();
            if (!emprestado.equals("Emprestado")) {
                System.out.println("Este livro está disponível para ser emprestado!");

                System.out.println("Deseja continuar? [S/N]: ");
                scanner.nextLine(); // Limpar buffer
                String op = scanner.nextLine();
                op = op.toUpperCase();
        
                if (op.equals("S")) {
                    controller.emprestaLivro(id);
                    Misc.text("Livro emprestado com sucesso!");
    
                    mainMenu.callMainMenu();
                } else if (op.equals("N")) {
                    Misc.clearScreen();
                    Misc.text("Operação cancelada!");
                    mainMenu.callMainMenu();
                } else {
                    Misc.clearScreen();
                    Misc.text("Escolha invalida, operação cancelada!");
                    mainMenu.callMainMenu();
                }
            } else {
                System.out.println("Livro já está emprestado! Aguarde devolução.");
                mainMenu.callMainMenu();
            }
        } else {
            Misc.clearScreen();
            Misc.text("Livro não encontrado ou ID inexistente");

            mainMenu.callMainMenu();
        }
    }

    public void receiveLendBook() {
        mainMenu.baseMsgFunc("Devolver livro");

        Misc.text("Insira o ID do livro devolvido: ");
        int id = scanner.nextInt();
        Livro livro = controller.buscaLivro(id);
        if(livro != null) {
            System.out.println(">>> Livro selecionado <<<");
            infoBook(livro);

            String emprestado = livro.getStatus();
            if (emprestado.equals("Emprestado")) {
                System.out.println("Este livro está emprestado, deseja registrar a devolução? [S/N]: ");
                scanner.nextLine(); // Limpar buffer
                String op = scanner.nextLine();
                op = op.toUpperCase();
        
                if (op.equals("S")) {
                    controller.devolveLivro(id);
                    Misc.text("Livro devolvido com sucesso!");
    
                    mainMenu.callMainMenu();
                } else if (op.equals("N")) {
                    Misc.clearScreen();
                    Misc.text("Operação cancelada!");
                    mainMenu.callMainMenu();
                } else {
                    Misc.clearScreen();
                    Misc.text("Escolha invalida, operação cancelada!");
                    mainMenu.callMainMenu();
                }
            } else {
                System.out.println("Livro não está emprestado!");
                mainMenu.callMainMenu();
            }
        } else {
            Misc.clearScreen();
            Misc.text("Livro não encontrado ou ID inexistente");

            mainMenu.callMainMenu();
        }
    }

    public void deleteAll() {
        System.out.println("Essa operação deletará TODOS OS DADOS inseridos no banco de dados, tem certeza que deseja realizar essa operação? [S/N]: ");
        String op = scanner.nextLine();
        op = op.toUpperCase();

        if (op.equals("S")) {
            controller.limpaBancoH2();
            Misc.text("Sistema fechado e Banco de Dados limpo, até logo!\n\n");
        } else if (op.equals("N")) {
            Misc.clearScreen();
            Misc.text("Operação cancelada!");
            mainMenu.callMainMenu();
        } else {
            Misc.clearScreen();
            Misc.text("Escolha invalida, operação cancelada!");
            mainMenu.callMainMenu();
        }
    }

    public void infoBook(Livro livro) {
        System.out.println(">>> Detalhes do livro <<<");
        System.out.println("ID " + livro.getIdlivro());
        System.out.println("Nome: " + livro.getNome());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Número de páginas: " + livro.getPaginas());
        System.out.println("Status: " + livro.getStatus());
    }
}