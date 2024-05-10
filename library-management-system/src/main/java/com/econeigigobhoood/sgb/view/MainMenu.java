package com.econeigigobhoood.sgb.view;

import java.util.Scanner;

import com.econeigigobhoood.sgb.controller.Misc;

import java.util.ArrayList;

public class MainMenu {
    private View view;
    private Scanner scannerInput;
    public ArrayList<String> listOptions = new ArrayList<String>();

    // Consturtor do sistema
    public MainMenu(Scanner scannerInput) {
        this.scannerInput = scannerInput;
    }

    // Setting View
    public void setView(View view) { this.view = view; }

    private void msgDefault(int timeSec) {
        Misc.clearScreen();
        Misc.text("ATENÇÃO -> Opção inválida\n");
        Misc.delay(timeSec);
        Misc.clearScreen();
    }

    private void helpMenu() {
        Misc.text("**** Sistema de Gestão de Biblioteca ****\n");
        Misc.text("=========== Tela de ajuda ===========\n\n");
        Misc.text(
                "Para utilizar o Sistema de Gestão de Livros será apresentado opções de cada tela presente ao usuário, cada opção tem um número correspondente");
        Misc.text(
                "sendo assim para selecionar a opção desejada basta digitar o número desta opção, por exemplo:\n");

        Misc.text("1 - Carregar arquivo");
        Misc.text("O número 1 representa o número da opção e Carregar arquivo é a descrição da opção.\n");

        Misc.text(
                "Após digitar a opção desejada basta pressionar a tecla ENTER que será carregado a tela correspondente da função escolhida.\n");
        Misc.text("Pressione qualquer tecla para retornar ao menu principal...");

        try {
            scannerInput.nextLine();
        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            System.exit(-1);
        }

        Misc.clearScreen();
        callMainMenu();
    }

    @SuppressWarnings("resource")
    private String baseMsg(String nameScreen, ArrayList<String> opSelector) {
        String op = "";

        Scanner scannerInput = new Scanner(System.in);
        Misc.text("**** Sistema de Gestão de Biblioteca ****\n");

        Misc.text("Selecione a opção desejada digitando o número correspondente:\n");

        Misc.text("=========== %s ===========\n", nameScreen);
        Misc.text("Dica: Para receber ajuda escreva AJUDAR.\n");

        for (int i = 0; i < opSelector.size(); i++) {
            Misc.text(opSelector.get(i));
        }

        try {
            Misc.text("Número da opção: ");
            op = scannerInput.nextLine();
            op = op.toUpperCase();

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scannerInput.nextLine();
            callMainMenu();
        }

        return op;
    }

    protected void baseMsgFunc(String nameScreen) {
        Misc.text("**** Sistema de Gestão de Biblioteca ****\n");

        Misc.text("=========== %s ===========\n\n", nameScreen);
    }

    // Menu inicial é este aqui.
    public void callMainMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Operações de cadastro de livros");
        listOptions.add("2 - Empréstimo de livros");
        listOptions.add("3 - Listar livros cadastrados");
        listOptions.add("4 - Sair do programa e MANTER Banco de Dados H2");
        listOptions.add("5 - Sair do programa e LIMPAR Banco de Dados H2");

        op = baseMsg("Menu principal", listOptions);
        switch (op) {
            case "1" -> { Misc.clearScreen(); bookRegMenu(); }
            case "2" -> { Misc.clearScreen(); bookBorrow(); }
            case "3" -> { Misc.clearScreen(); view.bookListing(); }
            case "4" -> { Misc.clearScreen(); Misc.text("Sistema fechado, até logo!\n\n"); System.exit(0); }
            case "5" -> { Misc.clearScreen(); view.deleteAll(); System.exit(0); }
            case "AJUDAR" -> { Misc.clearScreen(); helpMenu(); }
            default -> { msgDefault(3); callMainMenu(); }
        }
    }

    public void bookRegMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Cadastrar livros novos");
        listOptions.add("2 - Listar livros cadastrados");
        listOptions.add("3 - Buscar livro (ID)");
        listOptions.add("4 - Alterar cadastro de livros");
        listOptions.add("5 - Deletar livro (ID)");
        listOptions.add("6 - Voltar ao menu anterior");

        op = baseMsg("Cadastro de livros", listOptions);
        switch (op) {
            case "1" -> { Misc.clearScreen(); view.callBookRegister(); }
            case "2" -> { Misc.clearScreen(); view.bookListing(); }
            case "3" -> { Misc.clearScreen(); view.searchID(); }
            case "4" -> { Misc.clearScreen(); view.callUpdateBook(); }
            case "5" -> { Misc.clearScreen(); view.deleteBook(); }
            case "6" -> { Misc.clearScreen(); callMainMenu(); }
            default -> { msgDefault(3); bookBorrow(); }
        }
    }

    public void bookBorrow() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Emprestar livro");
        listOptions.add("2 - Devolver livro");
        listOptions.add("3 - Listar livros cadastrados");
        listOptions.add("4 - Voltar ao menu anterior");

        op = baseMsg("Empréstimo de livros", listOptions);
        switch (op) {
            case "1" -> { Misc.clearScreen(); view.lendBook(); }
            case "2" -> { Misc.clearScreen(); view.receiveLendBook(); }
            case "3" -> { Misc.clearScreen(); view.bookListing(); }
            case "4" -> { Misc.clearScreen(); callMainMenu(); }
            default -> { msgDefault(3); bookBorrow(); }
        }
    }
}