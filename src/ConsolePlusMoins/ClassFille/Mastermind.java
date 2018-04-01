package ConsolePlusMoins.ClassFille;

import java.util.*;
import java.util.logging.Logger;

import ConsolePlusMoins.AbstractClass.Jeu;

public class Mastermind extends Jeu {

	/*
	 * Importation des Loggers.
	 */
	private static Logger logger = Logger.getLogger(Main.class);

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
	 * Création d'une arrayList qui servira à supprimer les solutions pendant la
	 * partie.
	 */
	ArrayList listeArray01;

	/*
	 * Constructeur de la classe Mastermind qui va nous permettre de charger les
	 * paramètres du jeu via la classe Jeu et le fichier config.properties.
	 * 
	 * @See Jeu - LongueurCodeSecret, coupsMax.
	 */
	public Mastermind() {
		super("\n****Mastermind****");
		logger.info("Chargement du jeu Mastermind");

		LongueurCodeSecret = Integer.valueOf(prop.getProperty("longueurMastermind"));
		coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		logger.info("Longueur du code secret et nombre de coups sera chargés via le fichier config.properties");
	}

	/*
	 * Création du mode Challenger.
	 * 
	 * 
	 */
	public void Challenger() {
		logger.info("Chargement du mode Challenger");
		System.out.println("Welcome To Challenger Mode");
	}
}
