package com.sdz.ClassFille;

import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdz.AbstractClass.Jeu;
import com.sdz.AbstractClass.Joueur;

/*
 * /*************************************************************************
 * 
 * Classe fille correspondant à l'ordinateur héritant de la classe mère Joueur.
 * 
 * @author Delomez Matthieu
 *************************************************************************/

public class Cpu extends Joueur {

	private static final Logger LOGGER = LogManager.getLogger();

	/*
	 * Variable de type String correspondant au tour précédent.
	 */
	String tourPrecedent = "";

	/*
	 * Constructeur de la classe Cpu ne retournant aucune valeur.
	 */
	public Cpu() {
	}

	/*
	 * Méthode pour mettre en place un algorithme pour que l'ordinateiur puisse nous
	 * proposer un nombre qui sera généré de facon aléatoire.
	 */
	public void proposerNbr() {

		System.out.println("Proposition du Cpu : ");

		if (Jeu.compteur == 1) {
			proposition = "";
			Random random = new Random();

			int chiffreNbrSecret[] = new int[Jeu.LongueurCodeSecret];

			/*
			 * On génére un chiffre de facon aléatoire pour ainsi atteindre la longueur
			 * définie dans la longueur du code secret.
			 */
			for (int i = 0; i < Jeu.LongueurCodeSecret; i++) {
				chiffreNbrSecret[i] = random.nextInt(9 + 1);
				proposition += chiffreNbrSecret[i];

			}

			tourPrecedent = proposition;
			System.out.println(proposition);

		} else {
			int chiffreNbrSecret[] = new int[Jeu.LongueurCodeSecret];

			for (int i = 0; i < Jeu.LongueurCodeSecret; i++) {

				if (resulatPrecedent.charAt(i) == '+') {

					chiffreNbrSecret[i] = Character.getNumericValue(tourPrecedent.charAt(i));
					chiffreNbrSecret[i]++;

				} else if (resulatPrecedent.charAt(i) == '-') {

					chiffreNbrSecret[i] = Character.getNumericValue(tourPrecedent.charAt(i));
					chiffreNbrSecret[i]--;

				} else if (resulatPrecedent.charAt(i) == '=')

					chiffreNbrSecret[i] = Character.getNumericValue(Jeu.nbrSecret.charAt(i));

				proposition += chiffreNbrSecret[i];
			}

			proposition = proposition.substring(Jeu.LongueurCodeSecret, proposition.length());

			tourPrecedent = proposition;

			System.out.println(proposition);

		}
	}

	/*
	 * Création de la méthode que nous utiliserons dans le jeu Mastermind.
	 * 
	 * Qui nous servira à pouvoir parcourir la listeArray pour pouvoir générer un
	 * code
	 * 
	 * de manière aléatoire.
	 */
	public void parcourirListe(ArrayList listeArray) {

		Random random = new Random();

		int index = random.nextInt(listeArray.size());

		proposition = (String) listeArray.get(index);

		LOGGER.info("Nombre proposé parmi la liste de solutions : " + proposition);

	}

}

