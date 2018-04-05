package com.sdz.AbstractClass;

import java.util.ArrayList;

/*
 * /**************************************************************************
 * 
 * Classe abstraite Joueur qui d�finit les m�thodes relative aux classes Humain
 * et Cpu.
 * 
 * @author Delomez Matthieu
 *************************************************************************/

public abstract class Joueur {

	/*
	 * Instanciation des variables qui seront li�s aux classes Humain et Cpu
	 */
	public static String proposition;

	protected static int LongProposition;

	public String resulatPrecedent = "";

	public void Joueur() {
	}

	/*
	 * M�thode qui consiste � proposer par un des deux joueurs.
	 */
	public void proposerNbr() {

	}

	/*
	 * M�thode qui va consister � proposer un nombre � partir des Solutions
	 * Probables.
	 */
	public void parcourirListe(ArrayList listeArray) {

	}

}
