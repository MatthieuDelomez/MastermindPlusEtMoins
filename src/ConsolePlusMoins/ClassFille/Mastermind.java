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
	 * Nous allons cr�er une liste de collection Set, Sans doublon.
	 */
	HashSet listeSet;

	byte pionBienPlace = 0;
	byte pionPresentMalPlace = 0;

	/*
	 * Cr�ation des variables qui serviront � stocker le r�sultat.
	 */
	int goodresult = 0;
	int badResult = 0;

	/*
	 * Cr�ation d'une arrayList qui servira � supprimer les solutions pendant la
	 * partie.
	 */
	ArrayList listeArray01;

	/*
	 * Constructeur de la classe Mastermind qui va nous permettre de charger les
	 * param�tres du jeu via la classe Jeu et le fichier config.properties.
	 * 
	 * @See Jeu - LongueurCodeSecret, coupsMax.
	 */
	public Mastermind() {
		super("\n****Mastermind****");
		logger.info("Chargement du jeu Mastermind");

		LongueurCodeSecret = Integer.valueOf(prop.getProperty("longueurMastermind"));
		coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		logger.info("Longueur du code secret et nombre de coups sera charg�s via le fichier config.properties");
	}

	/*
	 * Cr�ation du mode Challenger.
	 * 
	 * 
	 */
	public void Challenger() {
		logger.info("Chargement du mode Challenger");
		System.out.println("Welcome To Challenger Mode");
	}
}
