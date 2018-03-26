



/***************************MASTERMIND PLUS ET MOINS***********************

/**************************************************************************
 * 
 * Classe fille correspondant aux réglages desdifférents modes de jeu 
 * héritant de la classe Jeu.
 * 
 * @author Delomez Matthieu
 *************************************************************************/


public class PlusMoins extends Jeu{

	public PlusMoins() {
		
		super("\n  *******PLUS OU MOINS******");
		
		LongueurCodeSecret = Integer.valueOf(prop.getProperty("longueur"));
		
		coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		
	}
	
	
	/* *************************************************************************************************
	 * 
	 ***************************METHODE RELATIVE AU MODE CHALLENGER**************************************
	 * 
	 **************************************************************************************************/

	
	public void challenger() {
		
		System.out.println("********MODE CHALLENGER********");
		
		initCompteur();
		
		genererNbrSecret();
		
		joueur1 = new Humain();
		
		
		/*
		 * On effectue une boucle do{ tant que le code secret n'a pas été trouvé.
		 */
		do {
			devMode();
			
			afficherCompteur();
			
			joueur1.proposerNbr();
			
			comparerNbr(joueur1);
			
			compteur++;
			

		
		} while (!String.valueOf(Joueur.proposition).equals(nbrSecret) && compteur < coupsMax +1);
		
		System.out.println("Le code secret est :" + nbrSecret + ".\n");

		
		finPartie("Vous avez ==>");
	}
	
	
	/* *************************************************************************************************
	 * 
	 ***************************METHODE RELATIVE AU MODE DEFENSEUR**************************************
	 * 
	 **************************************************************************************************/

	
	public void defenseur() {
		
		System.out.println("********MODE DEFENSEUR********");
		

		initCompteur();
		
		
		
		joueur1 = new Humain();
		
		joueur2 = new Cpu();
		
		
		/*
		 * L'utilisateur est invité à entrer une combinaison à deviner.
		 */
		joueur1.proposerNbr();
		
		nbrSecret = Joueur.proposition;
		
		System.out.println("Le code secret est :" + nbrSecret + ".\n");
		
		do {
			

			
			devMode();
			
			afficherCompteur();
			
			joueur2.proposerNbr();
			
			comparerNbr(joueur2);
			
			
			joueur2.resulatPrecedent = resultat;
			
			compteur++;
			
		} while (!String.valueOf(Joueur.proposition).equals(nbrSecret) && compteur < coupsMax +1);
		
		finPartie("L'ordinateur à ");
		

	}
	
	/* *************************************************************************************************
	 * 
	 ***************************METHODE RELATIVE AU MODE DUEL**************************************
	 * 
	 **************************************************************************************************/

	
	public void duel() {
		
		System.out.println("********MODE DUEL********");
		
		String combinaisonJoueur1, combinaisonJoueur2;
		
		initCompteur();
		
		genererNbrSecret();
		
		/*
		 * On stocke le nombre à deviner par le joueur dans une variable.
		 */
		combinaisonJoueur1 = nbrSecret;
		
		joueur1 = new Humain();
		
		/*
		 * L'utilisateur doit proposer une combinaison à deviner pour l'ordinateur.
		 */
		joueur1.proposerNbr();
		
		/*
		 * On initialise l'ordinateur et on stocke la combinaison du cpu.
		 */
		combinaisonJoueur2 = Joueur.proposition;
		
		joueur2 = new Cpu();
		
		/*
		 * On initialise les paramètres du Joueur grace un un bloc do{ .
		 */
		do {
			
			nbrSecret = combinaisonJoueur1;
			
			devMode();
			
			afficherCompteur();
			
			System.out.println("A vous :");
			
			joueur1.proposerNbr();
			
			comparerNbr(joueur1);
			
			if(String.valueOf(Joueur.proposition).equals(nbrSecret)) {
				
				compteur++;
				
				break;
			}
			
			/*
			 * Nous faisons de même avec l'ordinateur .
			 */
			nbrSecret = combinaisonJoueur2;
			
			devMode();
			
			System.out.println("A l'ordinateur :");
			
			joueur2.proposerNbr();
			
			comparerNbr(joueur2);
			
			joueur2.resulatPrecedent = resultat;
			
			compteur++;
			
		} while (!String.valueOf(Joueur.proposition).equals(nbrSecret));
		
		if(nbrSecret == combinaisonJoueur1)
			
			finPartie("Vous avez");
		
		else if(nbrSecret == combinaisonJoueur2)
			finPartie("L'ordinateur a");
			
		}

	}


