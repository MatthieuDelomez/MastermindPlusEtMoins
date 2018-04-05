package com.sdz.ClassFille;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdz.AbstractClass.Jeu;
import com.sdz.AbstractClass.Joueur;

public class Mastermind extends Jeu {

	/*
	 * Importation des Loggers.
	 */
	private static final Logger LOGGER = LogManager.getLogger();

	/*
	 * Nous allons créer une liste de collection Set, Sans doublon.
	 */
	HashSet listeSet;

	byte pionBienPlace = 0;
	byte pionPresentMalPlace = 0;

	/*
	 * Création des variables qui serviront à stocker le résultat.
	 */
	int goodresult = 0;
	int badResult = 0;

	/*
	 * Création d'une arrayList qui servira à suprimer les solutions pendant la
	 * partie.
	 */
	ArrayList listeArray;

	/*
	 * Constructeur de la classe Mastermind qui va nous permettre de charger les
	 * paramtres du jeu via la classe Jeu et le fichier config.properties.
	 * 
	 * @See Jeu - LongueurCodeSecret, coupsMax.
	 */
	public Mastermind() {
		super("\n****Mastermind****");
		LOGGER.info("Chargement du jeu Mastermind");

		LongueurCodeSecret = Integer.valueOf(prop.getProperty("longueur"));
		coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		LOGGER.info("Longueur du code secret et nombre de coups sera chargés via le fichier config.properties");
	}

	/*
	 * Création du mode Challenger.
	 * 
	 * @see Jeu
	 * 
	 * @See Humain
	 * 
	 * @param initCompteur
	 * 
	 * @param générerCodeSecret
	 * 
	 * @param générerListeSoluce
	 */
	public void challenger() {

		LOGGER.info("Chargement du mode Challenger");
		System.out.println("Welcome To Challenger Mode");

		/*
		 * On charge les méthodes de la classe mère.
		 * 
		 * @param initCompteur
		 * 
		 * @param genererNbrSecret
		 */
		initCompteur();
		genererNbrSecret();

		// Chargement du Joueur1.
		joueur1 = new Humain();

		/*
		 * Log de confirmation que le joueur à bien été chargé.
		 */
		LOGGER.info("Joueur 1 defini comme un humain");

		/*
		 * On effectue une boucle grace à un do , while qui défini les règles et le
		 * d�roulement du programme.
		 * 
		 * Un tour de boucle rajoute +1 au compteur.
		 */
		do {
			devMode();
			resetSoluce();
			afficherCompteur();

			joueur1.proposerNbr();
			comparerNbr(joueur1);
			afficherScore();
			compteur++;
		}

		// Si les pions bien placés sont inférieur au Code secret et que
		// compteur est inférieur à coupsMax + 1
		// Invoquer la méthode de fin de partie.
		while (pionBienPlace < nbrSecret.length() && compteur < coupsMax + 1);
		finPartie("Vous avez");

	}

	/*
	 * Création du mode Defenseur.
	 * 
	 * @see Jeu
	 * 
	 * @See Humain
	 * 
	 * @See Cpu
	 * 
	 * @param genererListeSoluce
	 * 
	 * @param devMode
	 * 
	 * @param proposerNbr
	 * 
	 * @param afficherScore
	 */
	public void defenseur() {

		LOGGER.info("Chargement du mode défenseur");
		System.out.println("Welcome To Defense Mode");

		initCompteur();
		genererListeSoluce();

		// Chargement du joueur1
		joueur1 = new Humain();

		/*
		 * Log de confirmation que le joueur1 à bien été chargé.
		 */
		LOGGER.info("Joueur 1 défini comme un humain");

		joueur2 = new Cpu();

		/*
		 * Log de confirmation que le joueur2 à bien été chargé.
		 */
		LOGGER.info("Joueur 1 défini comme un ordinateur");

		/*
		 * On charge la méthode proposerNbr, pour ensuite pouvoir la définir en tant que
		 * NombreSecret.
		 */
		joueur1.proposerNbr();

		nbrSecret = Joueur.proposition;

		System.out.println("Le code secret est : " + nbrSecret);

		/*
		 * On effectue une boucle grace à un do , while qui défini les règles et le
		 * déroulement du programme.
		 * 
		 * Un tour de boucle rajoute +1 au compteur.
		 * 
		 */
		do {

			devMode();
			resetSoluce();
			afficherScore();
			// On fait appel à la méthode parcourirListe qui se trouve dans le classe
			// Joueur.
			joueur2.parcourirListe(listeArray);

			System.out.println(Joueur.proposition);
			comparerNbr(joueur2);

			joueur2.resulatPrecedent = resultat;

			afficherScore();
			enregistrerScore();

			nettoyer();

			compteur++;
		}

		// Effectuer les conditions d'arrets de la boucle do
		// Si le bon resulat est inférieur au code secret et que le compteur
		// ne dépasse pas coupsMax + 1 invoquer la méthode de fin de partie
		while (!String.valueOf(Joueur.proposition).equals(nbrSecret) && compteur < coupsMax + 1);
		finPartie("L'ordinateur � :");

	}

	/*
	 * Création du mode Duel.
	 * 
	 * @see Jeu
	 * 
	 * @See Humain
	 * 
	 * @See Cpu
	 * 
	 * @param genererListeSoluce
	 * 
	 * @param devMode
	 * 
	 * @param proposerNbr
	 * 
	 * @param
	 * 
	 * @param afficherScore
	 */
	public void duel() {

		LOGGER.info("Chargement du mode duel");
		System.out.println("Welcome To Duel Mode");

		String codeJoueur1, codeJoueur2;

		initCompteur();

		genererListeSoluce();

		genererNbrSecret();

		// Même méthodde utilisée dans le mode défenseur
		codeJoueur1 = nbrSecret;

		// On défini le joueur 1 comme un humain
		joueur1 = new Humain();

		/*
		 * Log de confirmation que le joueur1 à bien été chargé.
		 */
		LOGGER.info("Joueur 1 défini comme un humain");

		joueur1.proposerNbr();

		codeJoueur2 = Joueur.proposition;

		joueur2 = new Cpu();

		/*
		 * Log de confirmation que le joueur1 à bien été chargé.
		 */
		LOGGER.info("Joueur 2 défini comme un ordinateur");

		/*
		 * On effectue une boucle grace à un do , while qui défini les règles et le
		 * déroulement du programme.
		 * 
		 * Un tour de boucle rajoute +1 au compteur.
		 * 
		 */
		do {

			resetSoluce();
			nbrSecret = codeJoueur1;
			devMode();
			afficherCompteur();

			System.out.println("A vous :");
			joueur1.proposerNbr();
			comparerNbr(joueur1);
			afficherScore();

			// On rajoute une codition if la proposition du Joueur 1 ou 2
			// est égal au codeSecret alors
			// On ajoute +1 au compteur et on sort de la boucle
			if (String.valueOf(Joueur.proposition).equals(nbrSecret)) {
				compteur++;
				break;
			}

			/*
			 * On programme le comportement du Joueur2.
			 */
			nbrSecret = codeJoueur2;
			devMode();
			System.out.println("A l'ordinateur :");

			resetSoluce();
			joueur2.parcourirListe(listeArray);

			System.out.println(Joueur.proposition);

			comparerNbr(joueur2);
			joueur2.resulatPrecedent = resultat;

			afficherScore();
			enregistrerScore();

			nettoyer();
			compteur++;
		}

		// Effectuer les conditions d'arrets des deux boucles
		// si Code secret = codeJoueur1 alors on invoque la methode de fin de partie
		// Joueur 1 gagne contre joueur2

		// Par contre si NbrSecret = codeJoueur2
		// Alors Joueur2 gagne contre joueur1
		while (!String.valueOf(Joueur.proposition).equals(nbrSecret) && compteur < coupsMax + 1);
		if (nbrSecret == codeJoueur1)
			finPartie("Félicitation vous avez gagné ");

		else if (nbrSecret == codeJoueur2)
			finPartie("Désolé mais vous avez perdu");

	}

	/*
	 * Création de la méthode qui nous servira à générer une liste de solutions
	 * possibles en fonction des r�ponses que nous allons donner.
	 */
	public void genererListeSoluce() {
		
		// Nous faisons appel à l'objet listeSet

		listeSet = new HashSet();
		
		double chiffreCodeSecret = LongueurCodeSecret;
		double NbrMaximumUtilises = nbrUtilises.length - 1;

		
		nbrSecret = "";
		
		
		for(int i = 0; i < Math.pow(nbrUtilises.length, LongueurCodeSecret); i++) {
			
			nbrSecret = String.format("%1$" + LongueurCodeSecret + "s", i).replace(' ', '0');
			
			System.out.println("-i " + nbrSecret);
			listeSet.add(nbrSecret);
			listeArray=new ArrayList(listeSet);


		}
		

	LOGGER.info("Les solutions possibles ont étaient génerées");

	


	}

	public void comparerNbr(Joueur joueur) {

		// Nous mettons les propositions dans une variable de type String.
		String propositions = String.valueOf(joueur.proposition);

		for (int i = 0; i < LongueurCodeSecret; i++) {
			int codePropose = Character.getNumericValue(propositions.charAt(i));
			int chiffreCodeSecret = Character.getNumericValue(nbrSecret.charAt(i));

			/*
			 * Conditions de la méthode de comparaison.
			 * 
			 * Si le code proposé est egal au codeSecret alors le pion est bien placé Sinon
			 * pr�sent mais mal placé Ou alors Null.
			 */
			if (codePropose == chiffreCodeSecret)
				pionBienPlace++;

			else if (nbrSecret.contains(String.valueOf(codePropose)))
				pionPresentMalPlace++;

			else
				;
		}

		LOGGER.info("Comparaison de la proposition de " + joueur + " ( " + joueur.proposition + ") avec le code secret"
				+ nbrSecret);

	}

	public void afficherScore() {

		if (pionPresentMalPlace >= nbrSecret.length())
			pionPresentMalPlace -= pionBienPlace;

		if (pionPresentMalPlace < 0)
			pionPresentMalPlace = 0;

		if (pionBienPlace == nbrSecret.length())
			pionPresentMalPlace = 0;

		System.out.println(pionPresentMalPlace + " sont présent mais mal placés " + pionBienPlace

				+ "pion Bien placés");
	}

	public void genererCodeSecret() {

		Random random = new Random();
		int index = random.nextInt(listeArray.size());
		nbrSecret = (String) listeArray.get(index);

		LOGGER.info("Code Secret choisi parmi la liste de solutions");

	}

	public void resetSoluce() {

		pionPresentMalPlace = 0;
		pionBienPlace = 0;
		LOGGER.info("Solutions remis à zero");

	}

	/*
	 * Méthode qui consiste à éliminer tout les composant de la ArrayList qui ne
	 * correspond pas à la proposition faite par l'ordinateur.
	 */
	public void nettoyer() {

		listeArray.remove(Joueur.proposition);

		// Tant que la liste complète n'est pas parcourue on effectue des tours de
		// boucles
		for (int i = 0; i < listeArray.size(); i++) {

			String sNbr = String.valueOf(listeArray.get(i));

			for (int n = 0; n < LongueurCodeSecret; n++) {
				int chiffreCodeSecret = Character.getNumericValue(nbrSecret.charAt(n));
				int chiffreSoluceProbable = Character.getNumericValue(sNbr.charAt(n));

				if (chiffreSoluceProbable == chiffreCodeSecret)
					pionBienPlace++;

				else if (nbrSecret.contains(String.valueOf(chiffreSoluceProbable)))
					pionPresentMalPlace++;

				else
					;
			}

			if (pionBienPlace <= goodresult) {
				listeArray.remove(listeArray.get(i));
				resetSoluce();

				if (!listeArray.contains(nbrSecret))
					listeArray.add(nbrSecret);
			}

			else
				resetSoluce();
		}

		LOGGER.info("Combinaisons mauvaise sont retirées de la liste des solutions possibles");
	}

	public void enregistrerScore() {
		goodresult = pionBienPlace;
		badResult = pionPresentMalPlace;
	}
}
