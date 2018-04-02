package com.sdz.AbstractClass;

/************************MASTERMIND PLUS ET MOINS*************************

/*************************************************************************
 * 
 * Classe abstraite servant � avertir l'utilisateur si une mauvaise saisie
 * ou manipulation � �t� faite.
 * 
 * @author Delomez Matthieu
 *************************************************************************/

public abstract class Erreur {
	
	/*
	 * M�thode servant � afficher si une entr�e clavier ne correspond pas 
	 * � un choix propos�.
	 */
	public static void erreurChoix() {
		System.out.println("\nVeuillez choisir parmi les propositions.");
        }
	
	public static void erreurNbr() {
		System.out.println("\nVeuillez entrer un nombre � " + Jeu.LongueurCodeSecret + " chiffres.");

	}
	

}


