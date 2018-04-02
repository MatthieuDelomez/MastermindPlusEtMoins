package com.sdz.AbstractClass;

/************************MASTERMIND PLUS ET MOINS*************************

/*************************************************************************
 * 
 * Classe abstraite servant à avertir l'utilisateur si une mauvaise saisie
 * ou manipulation à été faite.
 * 
 * @author Delomez Matthieu
 *************************************************************************/

public abstract class Erreur {
	
	/*
	 * Méthode servant à afficher si une entrée clavier ne correspond pas 
	 * à un choix proposé.
	 */
	public static void erreurChoix() {
		System.out.println("\nVeuillez choisir parmi les propositions.");
        }
	
	public static void erreurNbr() {
		System.out.println("\nVeuillez entrer un nombre à " + Jeu.LongueurCodeSecret + " chiffres.");

	}
	

}


