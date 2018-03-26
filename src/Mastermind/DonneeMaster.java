package Mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.math.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdz.observation.Observable;
import com.sdz.observation.Observer;


/*************************MASTERMIND******************************/

/** Pattern MVC - Classe qui correspond au modèle de données du jeu.
 *  Le modèle de données va réceptionner les données du controler,
 *  le analyser et mettre à jour les observateurs.
 *  Cette classe implémente l'interface Observable.
 * 
 * @ author Matthieu Delomez
 * @see Observable
 * 
 *******************************************************************/


public class DonneeMaster implements Observable {
	
	/**

	 * Appel à une ArrayList contenat la liste des observateurs.

	 */

	private ArrayList<Observer> listeObservateur = new ArrayList<Observer>();

	

	

	/**

	 * Appel à une liste chainée contenant la liste de l'ensemble des combianaisons possibles.

	 */

	private LinkedList<String> listePossibilitees;

	

	

	/**

	 * Variable de type chaine de caractères au mode challenger.

	 */

	private String propoSecreteCpuChallenger = "",

			

			       propoManChallenger = "",

	  

	               reponseCpuChallenger = "";

	

	

	/**

	 * Choix du joueur en fin de partie.

	 */

	private String choixFinPartie = "";

	

	



	/**

	 * Variable de type chaine de caractères au mode défenseur.

	 */

	private String propoSecreteManDefenseur = "",

			

			       reponseManDefenseur = "",

			       

			       propoCpuDefenseur = "";

	

	



	/**

	 * Variable de type chaine de caractères au mode duel.

	 */

	private String propoSecreteCpuDuel = "",

			

		           propoSecreteManDuel,

		       

		           propoManDuel = "",

		           

		           propoCpuDuel = "",

		           

		           reponseManDuel = "",

		           

		           reponseCpuDuel = "",

		           

		           affichage = "";

	

	

	/**

	 * Paramètre du jeu.

	 */

	private int nbEssai, nbCase, nbCouleur;

	

	

	/**

	 * Variable relative au mode de jeu : 0 - Challenger, 1 -Défenseur, 2 - Duel.

	 */

	private int modeJeu;

	

	/**

	 * Variable permettant la gestion des logs d'érreurs.

	 */

	private static final Logger LOGGER = LogManager.getLogger();

	

	

	/* *************************************************************************************************

	 * 

	 *********************************METHODE RELATIVE AU MODE CHALLENGER*************************************

	 * 

	 **************************************************************************************************/

	

	

	/**

	 * Méthode qui permet de récupérer la combinaison secrète du Cpu.

	 * 

	 * @param proposiSecret - Combinaison secrète de l'ordi en challenger.

	 */

	public void setPropoSecreteCpuChallenger(String propoSecrete) {

		this.propoSecreteCpuChallenger = propoSecrete;

	}

	

	

	/**

	 * Méthode relative au mode Challenger qui permet de récupérer la proposition du joueur.

	 * Suite à la proposition du joueur, l'ordinateur devra répondre.

	 * 

	 * @param propositionJoueur Proposition du joueur en mode challenger.

	 */

	public void setPropoManChallenger(String propoMan) {

		this.propoManChallenger = propoMan;

		this.analyseProposiManChallenger();

		this.updateObserver();

	}

	

	/**

	 * Méthode relative au mode Challenger qui permet d'analyser la proposition du joueur en la comparant

	 * à la combinaison secrète de l'ordinateur.

	 */

	private void analyseProposiManChallenger() {

		



	//Analyse des boules bien placées (pions rouges) et mal placées (pions blancs). Pour faciliter le traitement, on va dire 

	//que pions rouges équivaut à 1, pions blancs à 2 et emplacement vide à 3.

		

		int[] tabReponse = new int[this.nbCase];

		char []tabAnalyse = new char[this.nbCase];

		

		tabAnalyse = propoSecreteCpuChallenger.toCharArray();

		

		for(int i = 0; i < this.nbCase; i++) {

			tabReponse[i] = 3;

		}

		

		reponseCpuChallenger = "";

		

		for(int i = 0; i < this.nbCase; i++){

			if(this.propoManChallenger.charAt(i) == tabAnalyse[i]) {

				tabReponse[i] = 1;

				tabAnalyse[i] = ' ';

			}

		}

		

		for( int i = 0; i < this.nbCase; i++) {

			for(int j = 0; j < this.nbCase; j++) {

				if(this.propoManChallenger.charAt(i) == tabAnalyse[j] && tabReponse[i] != 1) {

					tabReponse[i] = 2;

					tabAnalyse[j] = ' ';

					break;

				}

			}

		}

		

		//On réordonne le tableau d'entiers dans l'ordre numérique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :

	   //pions rouges (si présents), pions blancs (si présents), et emplacement vide.

		

		Arrays.sort(tabReponse);

		

		for(int i = 0; i < this.nbCase; i++) {

			if(tabReponse[i] == 1)

				

				reponseCpuChallenger += "R";

			

			else if(tabReponse[i] ==2)

				

				reponseCpuChallenger += "B";

			

			else

				

				reponseCpuChallenger += "V";

		}

	}

	

	

	/* *************************************************************************************************

	 * 

	 *********************************METHODE RELATIVE AU MODE DEFENSEUR*************************************

	 * 

	 **************************************************************************************************/

	

	

	/**

	 * Méthode relative au mode Défenseur qui permet de récupérer la combinaison secrète du joueur.

	 * Après récupération de la combinaison secrète du joueur, l'ordinateur devra faire une proposition.

	 * 

	 * @param proposiSecret Combinaison secrète du joueur en mode défenseur.

	 */

	public void setPropoSecreteManDefenseur(String propoSecrete) {

		this.propoSecreteManDefenseur = propoSecrete;

		

		LOGGER.debug("Jeu Mastermind en mode Défenseur - Combinaison secrète joueur Modèle de données :" +

		

				this.propoSecreteManDefenseur);

		

		this.initListePossibilitees();

		

		LOGGER.debug("Jeu Mastermind en mode Défenseur - Taille de la liste chaînée :" +

				

				listePossibilitees.size());

		

		this.propoCpuDefenseur();

		this.updateObserver();

	}

	

	

	/**

	 * Méthode relative au mode Défenseur qui permet de récupérer la réponse du joueur.

	 * Suite à la réponse du joueur, l'ordinateur devra faire une proposition.

	 * 

	 * @param reponseMan - Réponse du joueur en mode défenseur.

	 */

	public void setReponseManDefenseur(String reponseMan) {

		this.reponseManDefenseur = reponseMan;

		this.propoCpuDefenseur();

		this.updateObserver();

	}

	

	/**

	 * Méthode correspondant au mode Défenseur, qui permet de programmer la proposition du Cpu

	 * qui sera adaptée par rapport à la réponse du joueur.

	 * La première proposition de la part de l'ordinateur sera le premier élément de la liste chainée

	 * qui comprendra toutes les possibilités.

	 * Suivant la reponse que fera le joueur, la liste sera réduite au fur et à mesure.

	 * Le Cpu proposera à chaque fois le premier élément de la liste.

	 */

	private void propoCpuDefenseur() {

		if(reponseManDefenseur.equals("")) {

			propoCpuDefenseur=listePossibilitees.getFirst();

			

			LOGGER.debug("Jeu Mastermind en mode Défenseur - Proposition de l'ordinateur en mode défenseur :"+propoCpuDefenseur);

		}

		else {

			

			LOGGER.debug("Jeu Mastermind en mode Défenseur - Modele de données réponse du joueur :"+reponseManDefenseur);

			Iterator<String> itParcoursListe=listePossibilitees.iterator();

			String premierElementListe=this.listePossibilitees.getFirst();

			

			while(itParcoursListe.hasNext()) {

				

				String strElementListe=itParcoursListe.next();

				String resultatComparaison="";

				int[] tabComparaison=new int[this.nbCase];

				char []tabAnalyseListe=new char[this.nbCase];

				tabAnalyseListe=strElementListe.toCharArray();

				

				for (int i=0;i<this.nbCase;i++) {

					tabComparaison[i]=3;

				}



				for (int i=0;i<this.nbCase;i++) {

					if(premierElementListe.charAt(i)==tabAnalyseListe[i]) {

						tabComparaison[i]=1;

						tabAnalyseListe[i]=' ';

					}

				}

				for (int i=0;i<this.nbCase;i++) {

					for(int j=0;j<this.nbCase;j++) {

						if(premierElementListe.charAt(i)==tabAnalyseListe[j]&&tabComparaison[i]!=1) {

							tabComparaison[i]=2;

							tabAnalyseListe[j]=' ';

							break;

						}

					}

				}



				Arrays.sort(tabComparaison);

				for (int i=0;i<this.nbCase;i++) {

					if(tabComparaison[i]==1)

						resultatComparaison+="R";

					else if(tabComparaison[i]==2)

						resultatComparaison+="B";

					else

						resultatComparaison+="V";

				}

				if(!resultatComparaison.equals(reponseManDefenseur)) {

					itParcoursListe.remove();

				}



			}

			LOGGER.debug("Jeu Mastermind en mode Défenseur - Taille liste chaînée réactualisée :"+listePossibilitees.size());

			LOGGER.debug("Jeu Mastermind en mode Défenseur - Premier élément réactualisé :"+listePossibilitees.getFirst());

			reponseManDefenseur="";

			propoCpuDefenseur=listePossibilitees.getFirst();

		}

	}

		

		

		/* *************************************************************************************************

		 * 

		 *********************************METHODE RELATIVE AU MODE DUEL*************************************

		 * 

		 **************************************************************************************************/

		

		

		/**

		 * Méthode relative au mode Duel qui permet de récupérer la combinaison secrète de l'ordinateur.

		 * 

		 * @param proposiSecrete - Combinaison secrète de l'ordinateur en mode duel.

		 */

		public void setPropoSecreteCpuDuel(String propoSecrete) {

			this.propoSecreteCpuDuel = propoSecrete;

			

			LOGGER.debug("Jeu Mastermind en mode Duel - Combinaison Secrète Ordinateur Modèle de données : "

			

					+ this.propoSecreteCpuDuel);

		}

		

		/**

		 * Méthode relative au mode Duel qui permet de récupérer la combinaison secrète du joueur.

		 * 

		 * @param proposiSecrete Combinaison secrète du joueur en mode duel.

		 */

		public void setPropoSecreteManDuel(String propoSecrete) {

			this.propoSecreteManDuel=propoSecrete;

			

			LOGGER.debug("Jeu Mastermind en mode Duel - Proposition Secrète Joueur Modèle de données :"

			

					+ this.propoSecreteManDuel);

			

			this.initListePossibilitees();

			

			LOGGER.debug("Jeu Mastermind en mode Duel - Taille de la liste chaînée :" 

			

					+ listePossibilitees.size());

		}

		

		

		/**

		 * Méthode relative au mode Duel qui permet de récupérer la proposition du joueur.

		 * Suite à la proposition du joueur, l'ordinateur devra répondre et également

		 * faire une proposition.

		 * 

		 * @param proposiMan Proposition du joueur en mode duel.

		 */

		public void setPropoManDuel(String propoMan) {

			int verifReponseCpuDuel = 0;

			this.propoManDuel = propoMan;

			

			LOGGER.debug("Jeu Mastermind en mode Duel - Proposition Joueur Modèle de données :"

			

					+this.propoManDuel);

			

			this.analysePropoManDuel();

			affichage = reponseCpuDuel;

			LOGGER.debug("Jeu Mastermind en mode Duel - Réponse Ordinateur Mode Duel :"

			

					+affichage);

			

			this.updateObserver();

			

			

			for(int i = 0; i < this.nbCase; i++) {

				if(reponseCpuDuel.charAt(i)== 'R') {

					verifReponseCpuDuel++;

				}

			}



			if(verifReponseCpuDuel!=this.nbCase) {

				this.propoCpuDuel();

				affichage=propoCpuDuel;

				this.updateObserver();



		}

	}

		

		/**

		 * Méthode relative au mode Duel qui permet de récupérer la réponse du joueur.

		 * 

		 * @param reponseJoueur Réponse du joueur en mode duel.

		 */

		public void setReponseManDuel(String reponseMan) {

			this.reponseManDuel = reponseMan;

		}

		

		

		/**

		 * Méthode relative au mode Duel qui permet d'analyser la proposition du joueur en la comparant

		 * à la combinaison secrète de l'ordinateur.

		 */

		public void analysePropoManDuel() {

			

		

		//Analyse des boules bien placées (pions rouges) et mal placées (pions blancs).

		

				int[] tabReponse = new int[this.nbCase];

				char []tabAnalyse = new char[this.nbCase];

				

				tabAnalyse=propoSecreteCpuDuel.toCharArray();

				

				for (int i=0;i<this.nbCase;i++) {

					tabReponse[i]=3;

				}

				

				

				reponseCpuDuel = "";

		

				

				for (int i=0;i<this.nbCase;i++) {

					

				

					if(this.propoManDuel.charAt(i)==tabAnalyse[i]) {

						tabReponse[i]=1;

						tabAnalyse[i]=' ';

					}

	}



	for (int i=0;i<this.nbCase;i++) {

		

		for(int j=0;j<this.nbCase;j++) {

			

			if(this.propoManDuel.charAt(i)==tabAnalyse[j]&&tabReponse[i]!=1) {

				tabReponse[i]=2;

				tabAnalyse[j]=' ';

				break;

			}

		}

	}

	

	//On réordonne le tableau d'entiers dans l'ordre numérique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :

	//pions rouges (si présents), pions blancs (si présents), et emplacement vide.

	

			Arrays.sort(tabReponse);



			for (int i=0; i<this.nbCase ;i++) {

				

				if(tabReponse[i]==1)

					

					reponseCpuDuel+="R";

				

				else if(tabReponse[i]==2)

					

					reponseCpuDuel+="B";

				

				else

					

					reponseCpuDuel+="V";

			}

		}





/**

 * Méthode relative au mode Duel qui permet de déterminer la proposition de l'ordinateur

 * en fonction de la réponse du joueur. La première proposition de l'ordinateur correspondra

 * au premier élément de la liste chainée initiale qui comprend toutes les possibilités

 * vu que le joueur n'a pas encore répondu. Par la suite, suivant la réponse du joueur, la liste

 * chainée sera réduite au fur et à mesure jusqu'à ne comprendre qu'un seul élément. 

 * L'ordinateur proposera à chaque fois le premier élément de la liste chainée.

 */



public void propoCpuDuel() {

	

	if(reponseManDuel.equals("")) {

		propoCpuDuel = listePossibilitees.getFirst();

		

		LOGGER.debug("Jeu Mastermind en mode Duel - Proposition Ordinateur Mode Duel :"

		

				+ propoCpuDuel);



	}

	

	else {

		

		LOGGER.debug("Jeu Mastermind en mode Duel - Modele de données réponse du joueur :"

		

				+reponseManDuel);

		

		Iterator<String> itParcoursListe=listePossibilitees.iterator();

		String premierElementListe=this.listePossibilitees.getFirst();

		

		while(itParcoursListe.hasNext()) {

			

		

			String strElementListe=itParcoursListe.next();

			String resultatComparaison="";

			

			int[] tabComparaison=new int[this.nbCase];

			char []tabAnalyseListe=new char[this.nbCase];

			tabAnalyseListe=strElementListe.toCharArray();

			

			for (int i=0;i<this.nbCase;i++) {

				tabComparaison[i]=3;

			}

			

			for (int i=0;i<this.nbCase;i++) {

				if(premierElementListe.charAt(i)==tabAnalyseListe[i]) {

					tabComparaison[i]=1;

					tabAnalyseListe[i]=' ';

				}

			}

			for (int i=0;i<this.nbCase;i++) {

				for(int j=0;j<this.nbCase;j++) {

					

					if(premierElementListe.charAt(i)==tabAnalyseListe[j]&&tabComparaison[i]!=1) {

						tabComparaison[i]=2;

						tabAnalyseListe[j]=' ';

						break;

					}

				}

			}



			Arrays.sort(tabComparaison);

			for (int i=0;i<this.nbCase;i++) {

				if(tabComparaison[i]==1)

					

					resultatComparaison+="R";

				else if(tabComparaison[i]==2)

					

					resultatComparaison+="B";

				else

					resultatComparaison+="V";

			}

			if(!resultatComparaison.equals(reponseManDuel)) {

				itParcoursListe.remove();

			}



		}

		LOGGER.debug("Jeu Mastermind en mode Duel - Taille liste chaînée réactualisé :"+listePossibilitees.size());

		LOGGER.debug("Jeu Mastermind en mode Duel - Premier élément réactualisé :"+listePossibilitees.getFirst());

		reponseManDuel="";

		propoCpuDuel=listePossibilitees.getFirst();

	}

}





/* *************************************************************************************************

 * 

 *********************************METHODE RELATIVE A TOUS MODE DE JEU*************************************

 * 

 **************************************************************************************************/





 /**

 * Mutateur commun à tous les modes de jeu qui permet de modifier le mode de jeu.

 * 

 * @param modeJeu Variable relative au mode de jeu : 0 - Challenger, 1 - Défenseur, 2 - Duel.

 */

  public void setModeJeu(int modeJeu) {

	this.modeJeu = modeJeu;

}



/**

 * Mutateur commun à tous les modes de jeu qui permet de modifier le nombre d'essais.

 * 

 * @param nbEssai - Nombre d'essais.

 */

public void setNbEssai(int nbEssai) {

	this.nbEssai=nbEssai;

}



/**

 * Mutateur commun à tous les modes de jeu qui permet de modifier le nombre de cases.

 * 

 * @param nbreCases Nombre de cases.

 */

public void setNbCase(int nbCase) {

	this.nbCase = nbCase;

}



/**

 * Mutateur commun à tous les modes de jeu qui permet de modifier le nombre de couleurs utilisables.

 * 

 * @param nbCouleur - Nombre de couleurs utilisables.

 */

public void setNbCouleur(int nbCouleur) {

	this.nbCouleur=nbCouleur;

}



/**

 * Méthode commune à tous les modes de jeu qui permet de récupérer le choix du joueur en fin de partie et

 * en fonction de cela, faire appel à la méthode adéquate correspondant au choix du joueur.

 * 

 * @param choixFinDePartie Choix du joueur en fin de partie.

 */

public void setChoixFinPartie(String choixFinPartie) {

	this.choixFinPartie=choixFinPartie;

	

	if(this.choixFinPartie.equals("Quitter l'application"))

		this.quitterAppli();

	

	else if(this.choixFinPartie.equals("Lancer un autre jeu"))

		this.accueilObserver();

	

	else {

		this.relancerPartie();

	}

}



/**

 * Méthode permettant d'initialiser la liste chainée avec l'ensemble des combinaisons possibles.

 */

public void initListePossibilitees() {

	

	/*On crée un objet LinkedList avec l'ensemble des possibilités. Dans le cas où on a 4 cases et 6 couleurs utilisables, 

	 l'objet LinkedList contiendra 1296 éléments. On s'assure bien que cette liste est initialisée à chaque début de partie*/

	

	listePossibilitees=new LinkedList<String>();

	



	

	if(nbCase==4) {

		for(int i=0;i<this.nbCouleur;i++) {

			for(int j=0;j<this.nbCouleur;j++) {

				for(int k=0;k<this.nbCouleur;k++) {

					for(int l=0;l<this.nbCouleur;l++) {

						listePossibilitees.add(String.valueOf(i)+String.valueOf(j)+String.valueOf(k)+String.valueOf(l));

					}



				}

			}

		}

	}

	else if(nbCase==5) {

		for(int i=0;i<this.nbCouleur;i++) {

			for(int j=0;j<this.nbCouleur;j++) {

				for(int k=0;k<this.nbCouleur;k++) {

					for(int l=0;l<this.nbCouleur;l++) {

						for(int m=0;m<this.nbCouleur;m++) {

							listePossibilitees.add(String.valueOf(i)+String.valueOf(j)+String.valueOf(k)+String.valueOf(l)+String.valueOf(m));

						}

					}



				}

			}

		}

	}

	else {

		for(int i = 0 ; i<this.nbCouleur; i++) {

			for(int j = 0; j<this.nbCouleur; j++) {

				for(int k = 0; k < this.nbCouleur; k++) {

					for(int l = 0; l< this.nbCouleur; l++) {

						for(int m = 0; m <this.nbCouleur; m++) {

							for(int n = 0; n < this.nbCouleur ; n++) {

								listePossibilitees.add(String.valueOf(i)+String.valueOf(j)+String.valueOf(k)+String.valueOf(l)+String.valueOf(m)+String.valueOf(n));

							}		

						}

					}

				}

			}

		}

	}

}



/* ********************************

 * Mise à jour des observateurs

 **********************************/



public void addObserver(Observer obs) {

	listeObservateur.add(obs);

}



public void updateObserver() {

	for(Observer obs:listeObservateur) {

		if(modeJeu==0)

			obs.updateMaster(reponseCpuChallenger);

		else if(modeJeu==1)

			obs.updateMaster(propoCpuDefenseur);

		else

			obs.updateMaster(affichage);

	}

}



public void delObserver() {

	listeObservateur =new ArrayList<Observer>();

}



public void quitterAppli() {

	for (Observer obs : listeObservateur) {

		obs.quitterAppli();

	}

}



public void accueilObserver() {

	for (Observer obs : listeObservateur) {

		obs.accueilObserver();

	}

}



public void relancerPartie() {

	for (Observer obs : listeObservateur) {

		this.propoSecreteCpuDuel="";

		this.propoSecreteManDuel="";

		this.reponseManDuel="";

		this.propoCpuDuel="";

		obs.relancerPartie();

	}

}

  }