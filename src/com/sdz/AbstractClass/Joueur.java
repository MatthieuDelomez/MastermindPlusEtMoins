package com.sdz.AbstractClass;

import java.util.ArrayList;

/*
 * /**************************************************************************
 * 
 * Classe abstraite Joueur qui définit les méthodes relative aux classes Humain
 * et Cpu.
 * 
 * @author Delomez Matthieu
 *************************************************************************/

public abstract class Joueur {

	/*
	 * Instanciation des variables qui seront liés aux classes Humain et Cpu
	 */
	public static String proposition;

	protected static int LongProposition;

	public String resulatPrecedent = "";

	public void Joueur() {
	}

	/*
	 * Méthode qui consiste à proposer par un des deux joueurs.
	 */
	public void proposerNbr() {

	}

	/*
	 * Méthode qui va consister à proposer un nombre à partir de Solutions
	 * Probables.
	 */
	public void parcourirListe(ArrayList listeArray) {

	}

}
