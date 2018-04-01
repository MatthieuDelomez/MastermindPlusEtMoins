package ConsolePlusMoins.ClassFille;

import java.util.*;

import ConsolePlusMoins.ClassFille.Cpu.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ConsolePlusMoins.AbstractClass.Jeu;
import ConsolePlusMoins.AbstractClass.Joueur;

public class Mastermind extends Jeu {

	/*
	 * Importation des Loggers.
	 */
	private static final Logger LOGGER = LogManager.getLogger();

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
	ArrayList listeArray;

	/*
	 * Constructeur de la classe Mastermind qui va nous permettre de charger les
	 * param�tres du jeu via la classe Jeu et le fichier config.properties.
	 * 
	 * @See Jeu - LongueurCodeSecret, coupsMax.
	 */
	public Mastermind() {
		super("\n****Mastermind****");
		LOGGER.info("Chargement du jeu Mastermind");

		LongueurCodeSecret = Integer.valueOf(prop.getProperty("longueurMastermind"));
		coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		LOGGER.info("Longueur du code secret et nombre de coups sera charg�s via le fichier config.properties");
	}

	/*
	 * Cr�ation du mode Challenger.
	 * 
	 * @see Jeu
	 * 
	 * @See Humain
	 * 
	 * @param initCompteur
	 * 
	 * @param g�n�rerCodeSecret
	 * 
	 * @g�n�rerListeSoluce
	 */
	public void challenger() {

		LOGGER.info("Chargement du mode Challenger");
		System.out.println("Welcome To Challenger Mode");

		/*
		 * On charge les m�thodes de la classe m�re.
		 */
		initCompteur();
		genererNbrSecret();

		// Chargement du Joueur1.
		joueur1 = new Humain();

		/*
		 * Log de confirmation que le joueur � bien �t� charg�.
		 */
		LOGGER.info("Joueur 1 d�fini comme un humain");

		/*
		 * On effectue une boucle grace � un do , while qui d�fini les r�gles et le
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

		// Si les pions bien plac�s sont inf�rieur au Code secret et que
		// compteur est inf�rieur � coupsMax + 1
		// Invoquer la m�thode de fin de partie.
		while (pionBienPlace < nbrSecret.length() && compteur < coupsMax + 1);
		finPartie("Vous avez");

	}

	/*
	 * Cr�ation du mode Defenseur.
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
	 * @g�n�rer afficherScore
	 */
	public void defenseur() {

		LOGGER.info("Chargement du mode d�fenseur");
		System.out.println("Welcome To Defense Mode");

		initCompteur();
		genererListeSoluce();

		// Chargement du joueur1
		joueur1 = new Humain();

		/*
		 * Log de confirmation que le joueur1 � bien �t� charg�.
		 */
		LOGGER.info("Joueur 1 d�fini comme un humain");

		joueur2 = new Cpu();

		/*
		 * Log de confirmation que le joueur2 � bien �t� charg�.
		 */
		LOGGER.info("Joueur 1 d�fini comme un ordinateur");

		/*
		 * On charge la m�thode proposerNbr, pour ensuite pouvoir la d�finir en tant que
		 * NombreSecret.
		 */
		joueur1.proposerNbr();

		nbrSecret = Joueur.proposition;

		System.out.println("Le code secret est : " + nbrSecret);

		/*
		 * On effectue une boucle grace � un do , while qui d�fini les r�gles et le
		 * d�roulement du programme.
		 * 
		 * Un tour de boucle rajoute +1 au compteur.
		 * 
		 */
		do {

			devMode();
			resetSoluce();
			afficherScore();
			joueur2.parcourirListe(listeArray);

			System.out.println(Joueur.proposition);
			comparerNbr(joueur2);

			afficherScore();
			enregistrerScore();

			nettoyer();

			compteur++;
		}

		// Effectuer les conditions d'arrets de la boucle do
		// Si le bon resulat est inf�rieur au code secret et que le compteur
		// ne d�passe pas coupsMax + 1 invoquer la m�thode de fin de partie
		while (goodresult < nbrSecret.length() && compteur < coupsMax + 1);
		finPartie("L'ordinateur � :");

	}

	/*
	 * Cr�ation du mode Duel.
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

		// M�me m�thodde utilis�e dans le mode d�fenseur
		codeJoueur1 = nbrSecret;

		// On d�fini le joueur 1 comme un humain
		joueur1 = new Humain();

		/*
		 * Log de confirmation que le joueur1 � bien �t� charg�.
		 */
		LOGGER.info("Joueur 1 d�fini comme un humain");
		
		joueur1.proposerNbr();
		
		codeJoueur2 = Joueur.proposition;
		
		joueur2 = new Cpu();
		
		/*
		 * Log de confirmation que le joueur1 � bien �t� charg�.
		 */
		LOGGER.info("Joueur 2 d�fini comme un ordinateur");
		
		/*
		 * On effectue une boucle grace � un do , while qui d�fini les r�gles et le
		 * d�roulement du programme.
		 * 
		 * Un tour de boucle rajoute +1 au compteur.
		 * 
		 */
		do {
			
			resetSoluce();
			nbrSecret = codeJoueur1;
			devMode();
			System.out.println("A vous :");
			afficherScore();
			
			// On rajoute une codition if la proposition du Joueur 1 ou 2
			// est �gal au codeSecret alors
			// On ajoute +1 au compteur et on sort de la boucle
			if(String.valueOf(Joueur.proposition).equals(nbrSecret)) {
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
			afficherScore();
			joueur2.parcourirListe(listeArray);
			
			System.out.println(Joueur.proposition);
			
			comparerNbr(joueur2);
			afficherScore();
			enregistrerScore();
			
			nettoyer();
			compteur++;
		}
		
		// Effectuer les conditions d'arrets des deux boucles
		// si Code secret = codeJoueur1 alors on invoque la m�thode de fin de partie
		// Joueur 1 � gagne contre joueur2
		
		//Par contre si NbrSecret = codeJoueur2
		// Alors Joueur2 gagne contre joueur1
		while (!String.valueOf(Joueur.proposition).equals(nbrSecret) && compteur < coupsMax +1);
                        if(nbrSecret == codeJoueur1)
    	                finPartie("F�licitation vous avez gagn� ");
		
        else if(nbrSecret == codeJoueur2)
    	                finPartie("D�sol� mais vous avez perdu");

	}

	/*
	 * Cr�ation de la m�thode qui nous servira � g�n�rer une liste de solutions
	 * possibles en fonction des r�ponses que nous allons donner.
	 */
	public void genererListeSoluce() {

	// Nous faisons appel � l'objet listeSet
	listeSet = new HashSet();
	
	int NbrMaximumUtilises = nbrUtilises.length - 1;
	
	// Cr�ation d'un objet Random
    Random random = new Random();
	
	// On associe le tableau chiffreCodeSecret � la valeur de la variable LongueurCodeSecret
	int chiffreCodeSecret[] = new int[LongueurCodeSecret];
	
	/*
	 * Boucle do/while pour g�n�rer un chiffre al�atoire jusqu'a atteindre la longueur Max
	 */
	do {
		
		for(int i = 0; i < LongueurCodeSecret; i++){
			chiffreCodeSecret[i] = random.nextInt(NbrMaximumUtilises +1);
			nbrSecret += chiffreCodeSecret[i];
		}
		
		listeSet.add(nbrSecret);
		nbrSecret ="";
	    
	    }
	
	while (listeSet.size() < Math.pow(nbrUtilises.length, LongueurCodeSecret));
			
	
	listeArray = new ArrayList(listeSet);
	
	LOGGER.info("Les solutions possibles on �t� g�n�r�e");
	}

	public void comparerNbr(Joueur joueur) {

		// Nous mettons les propositions dans une variable de type String.
		String propositions = String.valueOf(joueur.proposition);

		for (int i = 0; i < LongueurCodeSecret; i++) {
			int codePropose = Character.getNumericValue(propositions.charAt(i));
			int chiffreCodeSecret = Character.getNumericValue(nbrSecret.charAt(i));

			/*
			 * Conditions de la m�thode de comparaison.
			 * 
			 * Si le code propos� est egal au codeSecret alors le pion est bien plac� Sinon
			 * pr�sent mais mal plac� Ou alors Null.
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
		System.out.println(pionPresentMalPlace + " sont pr�sent mais mal plac�s" + pionBienPlace

				+ "pion Bien plac�s");
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
		LOGGER.info("Solutions remis � zero");

	}

	/*
	 * M�thode qui consiste � �liminer tout les composant de la ArrayList qui ne
	 * correspond pas � la proposition faite par l'ordinateur.
	 */
	public void nettoyer() {

		listeArray.remove(Joueur.proposition);

		// Tant que la liste compl�te n'est pas parcourue on effectue des tours de
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

		LOGGER.info("Combinaisons mauvaise sont retir�es de la liste des solutions possibles");
	}
	
	public void enregistrerScore() {
		goodresult = pionBienPlace;
		badResult = pionPresentMalPlace;
	}
}
