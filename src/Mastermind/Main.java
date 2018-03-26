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
	 * Cette m�thode permet de pouvoir instancier les mod�les de donn�es 
	 * relatif au jeu Mastermind mais aussi la fen�tre principal.
	 * Il est aussi possible de choisir via la console si le mode d�veloppeur
	 * est actif ou non.
	 * 
	 * @param args - Param�tre d'entr�e au sein de la fonction Main
	 * @see Fenetre
	 */

	public static void main(String[] args) {
		
		// Param�tre du mode d�veloppeur via la console.
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

	

