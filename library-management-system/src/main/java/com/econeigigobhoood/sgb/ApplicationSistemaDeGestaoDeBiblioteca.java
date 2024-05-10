package com.econeigigobhoood.sgb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.econeigigobhoood.sgb.config.BDConfig;
import com.econeigigobhoood.sgb.controller.Controller;
import com.econeigigobhoood.sgb.view.MainMenu;
import com.econeigigobhoood.sgb.view.View;

import java.util.Scanner;

@SpringBootApplication
public class ApplicationSistemaDeGestaoDeBiblioteca implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationSistemaDeGestaoDeBiblioteca.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Inicializando bonitinho
		Controller controller = new Controller();
		
		// A mesma praça, o mesmo banco, a mesma inicialização no mesmo jardim (Inicializando banco)
		BDConfig.criarTabelaLivros();
		
		// Inicialização da CLI
		Scanner scanner = new Scanner(System.in);
		MainMenu sistema = new MainMenu(scanner);

		// Inicializando view com instancia de sistema e settando view no sistema
		View view = new View(sistema, controller, scanner);
		sistema.setView(view);

		// E comeeeeeça a partida (inicializando sistema)
		sistema.callMainMenu();
	}
}