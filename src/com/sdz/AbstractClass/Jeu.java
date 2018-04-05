package com.sdz.AbstractClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import com.sdz.Main.Main;

/**
 * /*************************************************************************
 * 
 * Classe abstraite Jeu qui définit les méthodes au bon fonctionnement du jeu
 * PlusOuMoins et Mastermind.
 * 
 * @author Delomez Matthieu
 *************************************************************************/

public abstract class Jeu {

	/*
	 * Joueur 1 qui correspond au joueur Humain.
	 */
	protected Joueur joueur1;

	/*
	 * Joueur 2 qui correspond au joueur Cpu.
	 */
	protected Joueur joueur2;

	/*
	 * Variable de type String contenant le nom du jeu.
	 */
	protected String nomDuJeu;

	/*
	 * Variable static correspondant au resulat donnée.
	 */
	protected static String resultat;

	/*
	 * Tableau d'entier static correspondant aux nombres utilisés.
	 */
	protected static int nbrUtilises[];

	/*
	 * Variable static de type int correspondant à la longueur du code Secret qui sera configurée grâce au fichier config.properties.
	 */
	public static int LongueurCodeSecret;

	/*
	 * Variable qui sert de compteur.
	 */
	public static int compteur;

	/*
	 * Variable static correspondant au coup maximum autorisés et qui fera référence à la configuration du fichier config.properties.
	 */
	protected static int coupsMax;

	/*
	 * Variable de type String correspondant au code secret.
	 */
	public static String nbrSecret = "";

	/*
	 * Configuration du fichier config.properties.
	 */
	protected Properties prop = new Properties();

	/*
	 * Inportation du fichier config.properties.
	 */
	InputStream input = null;

	/*
	 * Constructeur de la classe Jeu.
	 * 
	 * @param nomDuJeu.
	 * 
	 * @param LongueurCodeSecret
	 * 
	 * @param nbrUtilises
	 * 
	 * @param coupsMax
	 * 
	 * @param input
	 */

	public Jeu(String nomDuJeu) {

		/*
		 * Chargement du fichier config.properties.
		 */
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			LongueurCodeSecret = Integer.valueOf(prop.getProperty("longueur"));
			coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
			nbrUtilises = new int[Integer.valueOf(prop.getProperty("couleurs"))];

			/*
			 * Boucle pour incrémenter un coup/tour à chaque tour de boucle.
			 */
			for (int i = 0; i < Integer.valueOf(prop.getProperty("couleurs")); i++) {
				nbrUtilises[i] = i;
			}

		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {

			if (input != null) {
				try {
					input.close();

				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}

		this.nomDuJeu = nomDuJeu;

		/*
		 * Variable correspondant au byte qui correspondra à la réponse qui sera
		 * appliquée au Scanner.
		 */
		byte choix = 0;

		System.out.println(afficherNom());

		do {

			System.out.println("\n********** Choisissez un mode de jeu : **********");

			System.out.println("\n1 *** Challenger. \n2 *** Défenseur. \n3 *** Duel. \n4 *** Quitter.");

			Scanner sc = new Scanner(System.in);

			/*
			 * Construction des différents modes de jeu. Si l'entrée n'est pas un byte alors
			 * invoquer la classe Erreur.
			 * 
			 * @see Erreur
			 */
			if (!sc.hasNextByte()) {
				Erreur.erreurChoix();
				sc.next();
				continue;
			}

			choix = sc.nextByte();
			switch (choix) {

			case 1:
				this.challenger();
				break;

			case 2:
				this.defenseur();
				break;

			case 3:
				this.duel();
				break;

			case 4:
				break;

			default:
				Erreur.erreurChoix();
			}

		} while (choix != 1 && choix != 2 && choix != 3 && choix != 4);

	}

	/*
	 * Méthodes relatives aux modes de jeu.
	 */
	public void challenger() {
	}

	public void defenseur() {
	}

	public void duel() {
	}

	/*
	 * Générer le code Secret.
	 */
	public void genererNbrSecret() {

		nbrSecret = "";
		Random random = new Random();
		int chiffreNbrSecret[] = new int[LongueurCodeSecret];

		/*
		 * Tour de boucle pour générer un chiffre aléatoirement pour atteindre la
		 * longueur qui est définie dans la variable LongueurCodeSecret.
		 */
		for (int i = 0; i < LongueurCodeSecret; i++) {
			chiffreNbrSecret[i] = random.nextInt(9 + 1);

			nbrSecret += chiffreNbrSecret[i];

		}
	}

	/*
	 * Méthode servant à retourner le nom du jeu qui à été selectionner par
	 * l'utilisateur.
	 */
	public String afficherNom() {
		return this.nomDuJeu;
	}

	/*
	 * Comparer la proposition du joueur au code secret.
	 */
	public void comparerNbr(Joueur joueur) {
		/*
		 * On met la valeur de la proposition dans un String.
		 */
		String secretPropo = String.valueOf(Joueur.proposition);
		resultat = "";

		for (int i = 0; i < LongueurCodeSecret; i++) {

			int chiffrePropo = Character.getNumericValue(secretPropo.charAt(i));
			int chiffreNbrSecret = Character.getNumericValue(nbrSecret.charAt(i));
			/*
			 * On gère les réponse qui seront données par le Cpu ou le Joueur.
			 * 
			 * 
			 */
			if (chiffrePropo > chiffreNbrSecret)
				resultat += "-";
			else if (chiffrePropo < chiffreNbrSecret)
				resultat += "+";
			else if (chiffrePropo == chiffreNbrSecret)
				resultat += "=";

		}

		/*
		 * On affiche le resulat dans la console.
		 */
		System.out.println("Resultat : " + resultat + "\n\n--------\n");
	}

	/*
	 * On gère la fin de partie avec les critères pour gagner ou perdre.
	 * 
	 * @param compteur - Score
	 * 
	 * @param coupsMax - coup maximum autorisés.
	 */
	public void finPartie(String vainqueur) {
		if (compteur >= coupsMax && !Joueur.proposition.equals(nbrSecret))
			System.out.println(

					"Vous avez atteint la limite de coups ===>" + coupsMax + "<=== ! Le nombre mystère était : "
							+ nbrSecret + ".");

		else
			System.out.println("*** " + vainqueur + " trouvé le code secret en ==> " + (compteur - 1) + " <== coups !!! ***");
	}

	/*
	 * Initialiser le score via le compteur.
	 */
	public void initCompteur() {
		compteur = 1;
	}

	/*
	 * Affichage du compteur.
	 */
	public void afficherCompteur() {
		System.out.println("Coups n°" + (compteur));
	}


	/*
	 * Initialisation du mode devMode.
	 */
	public void devMode() {
		if (Main.modDev == 1)

		{
			System.out.println("[Mode développeur] Le nombre mystère est : " + nbrSecret);

		}

	}

}
