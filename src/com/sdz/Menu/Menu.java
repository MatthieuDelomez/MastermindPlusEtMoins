package com.sdz.Menu;

import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import com.sdz.AbstractClass.Erreur;
import com.sdz.ClassFille.Mastermind;
import com.sdz.ClassFille.PlusMoins;


/*
 * /**************************************************************************
 * 
 * Classe correspondant au menu en mode console.
 * 
 * @author Delomez Matthieu
 *************************************************************************/

public class Menu {

	static int devMode;

	/*
	 * Chargement du fichier config.properties
	 * 
	 * @param Properties prop
	 * @param InputStream input
	 */
	Properties prop = new Properties();
	InputStream input = null;

	/*
	 * Constructeur de la classe Jeu.
	 * 
	 * @param byte choix - Correspond à la réponse enregistrée.
	 * @see Erreur
	 */
	public Menu() {
		
		

		byte choix = 0;
		System.out.println("Bien le Bonjour !");

		do {
			System.out.println("\n A quoi souhaitez-vous jouer ?");

			System.out.println("\n1. Plus ou moins \n2. Mastermind \n3. Quittez");

			Scanner sc = new Scanner(System.in);

			/*
			 * Si l'entrée saisie n'est pas un byte.
			 */
			if (!sc.hasNextByte()) {

				Erreur.erreurChoix();

				sc.next();

				continue;
			}

			choix = sc.nextByte();

			/*
			 * On initialise les choix possible via le menu crée.
			 */
			switch (choix) {

			case 1:
				PlusMoins plusmoins = new PlusMoins();

				choix = 0;

				break;

			case 2:
				
				Mastermind master = new Mastermind();
				
			    choix = 0;
			    
			    break;
			    

			case 3:

				System.out.println("A bientôt !");
				
				break;

			default:
				Erreur.erreurChoix();

			}
		} while (choix != 1 && choix != 2 && choix != 3 && choix != 4);
	}
}
