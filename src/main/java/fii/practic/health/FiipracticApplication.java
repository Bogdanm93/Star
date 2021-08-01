package fii.practic.health;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fii.practic.health.boundry.controller.PurchaseController;
import fii.practic.health.boundry.controller.StockController;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class FiipracticApplication {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		SpringApplication.run(FiipracticApplication.class, args);

		Scanner in = new Scanner(System.in);
		boolean closeChat = false;
		String txt;
		while (closeChat == false) {
			System.out.println("WHAT IS YOUR COMMNAD?");
			txt = in.nextLine();
			String[] command = txt.split("\\s");

			if (command.length > 5 || command.length < 2) {
				System.out.println("COMANDA GRESITA, Mai incearca");
				continue;
			}
			String cm1 = command[0];
			String cm2 = command[1];

			// 1.
			if (cm1.equalsIgnoreCase("print") && cm2.equalsIgnoreCase("products") && command[2].equalsIgnoreCase("category")) {
				StockController.getProductsCatName(command[3]);
				System.out.println("COMANDA PLASA CU SUCCES");
				continue;
			}

			// 2.
			if (cm1.equalsIgnoreCase("print") && cm2.equalsIgnoreCase("products") && command[2].equalsIgnoreCase("all")) {
				StockController.printProductsAll();
				System.out.println("COMANDA PLASA CU SUCCES");
				continue;
			}
			
			// 3
			if(cm1.equalsIgnoreCase("print") && cm2.equalsIgnoreCase("products")){
				StockController.printProductsName(command[2]);
				continue;
			}
			
			// 4
			if(cm1.equalsIgnoreCase("print") && cm2.equalsIgnoreCase("categories")){
				StockController.printCategories();
				continue;
			}
			
			//5 
			if(cm1.equalsIgnoreCase("buy")){
				try {
					PurchaseController.BuyForUser(txt);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				continue;
			}

			System.out.println("Comanda dvs. este gresita sau nu exista!");
		}
		in.close();

	}
}
