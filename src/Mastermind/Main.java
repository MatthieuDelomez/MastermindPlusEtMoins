package Mastermind;

import com.sdz.model.*;
import com.sdz.vue.*;
import java.util.Scanner;

import com.sdz.observation.Observable;


/******************************MASTERMIND********************************/

/*************************************************************************
 * Classe Main du programme.
 * 
 * @author Delomez Matthieu
 *************************************************************************/


public class Main {
	
	/**
	 * Cette méthode permet de pouvoir instancier les modèles de données 
	 * relatif au jeu Mastermind mais aussi la fenêtre principal.
	 * Il est aussi possible de choisir via la console si le mode développeur
	 * est actif ou non.
	 * 
	 * @param args - Paramètre d'entrée au sein de la fonction Main
	 * @see Fenetre
	 */

	public static void main(String[] args) {
		
		// Paramètre du mode développeur via la console.
		Scanner sc = new Scanner(System.in);
		String strModeDeveloppeurActiveConsole = "";
		boolean modeDeveloppeurActiveConsole = false;
		
		do {
			
			System.out.println("Souhaitez vous activez le mode developpeur(O pour oui/ N pour non) ? :");
			strModeDeveloppeurActiveConsole = sc.nextLine();
		}
		
		while(!strModeDeveloppeurActiveConsole.equals("O")&&! strModeDeveloppeurActiveConsole.equals("N"));
		
		
		if(strModeDeveloppeurActiveConsole.equals("O"))
			modeDeveloppeurActiveConsole = true;
		
		else
			modeDeveloppeurActiveConsole = false;
		
		
		Observable Obs = new DonneeMaster();
		
		// Instanciation de la fenetre principale.
		  new Fenetre (Obs, modeDeveloppeurActiveConsole);
		
	}
	
}

	

