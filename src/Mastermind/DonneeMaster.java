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

/** Pattern MVC - Classe qui correspond au mod�le de donn�es du jeu.
 *  Le mod�le de donn�es va r�ceptionner les donn�es du controler,
 *  le analyser et mettre � jour les observateurs.
 *  Cette classe impl�mente l'interface Observable.
 * 
 * @ author Matthieu Delomez
 * @see Observable
 * 
 *******************************************************************/


public class DonneeMaster implements Observable {
	
	/**

	 * Appel � une ArrayList contenat la liste des observateurs.

	 */

	private ArrayList<Observer> listeObservateur = new ArrayList<Observer>();

	

	

	/**

	 * Appel � une liste chain�e contenant la liste de l'ensemble des combianaisons possibles.

	 */

	private LinkedList<String> listePossibilitees;

	

	

	/**

	 * Variable de type chaine de caract�res au mode challenger.

	 */

	private String propoSecreteCpuChallenger = "",

			

			       propoManChallenger = "",

	  

	               reponseCpuChallenger = "";

	

	

	/**

	 * Choix du joueur en fin de partie.

	 */

	private String choixFinPartie = "";

	

	



	/**

	 * Variable de type chaine de caract�res au mode d�fenseur.

	 */

	private String propoSecreteManDefenseur = "",

			

			       reponseManDefenseur = "",

			       

			       propoCpuDefenseur = "";

	

	



	/**

	 * Variable de type chaine de caract�res au mode duel.

	 */

	private String propoSecreteCpuDuel = "",

			

		           propoSecreteManDuel,

		       

		           propoManDuel = "",

		           

		           propoCpuDuel = "",

		           

		           reponseManDuel = "",

		           

		           reponseCpuDuel = "",

		           

		           affichage = "";

	

	

	/**

	 * Param�tre du jeu.

	 */

	private int nbEssai, nbCase, nbCouleur;

	

	

	/**

	 * Variable relative au mode de jeu : 0 - Challenger, 1 -D�fenseur, 2 - Duel.

	 */

	private int modeJeu;

	

	/**

	 * Variable permettant la gestion des logs d'�rreurs.

	 */

	private static final Logger LOGGER = LogManager.getLogger();

	

	

	/* *************************************************************************************************

	 * 

	 *********************************METHODE RELATIVE AU MODE CHALLENGER*************************************

	 * 

	 **************************************************************************************************/

	

	

	/**

	 * M�thode qui permet de r�cup�rer la combinaison secr�te du Cpu.

	 * 

	 * @param proposiSecret - Combinaison secr�te de l'ordi en challenger.

	 */

	public void setPropoSecreteCpuChallenger(String propoSecrete) {

		this.propoSecreteCpuChallenger = propoSecrete;

	}

	

	

	/**

	 * M�thode relative au mode Challenger qui permet de r�cup�rer la proposition du joueur.

	 * Suite � la proposition du joueur, l'ordinateur devra r�pondre.

	 * 

	 * @param propositionJoueur Proposition du joueur en mode challenger.

	 */

	public void setPropoManChallenger(String propoMan) {

		this.propoManChallenger = propoMan;

		this.analyseProposiManChallenger();

		this.updateObserver();

	}

	

	/**

	 * M�thode relative au mode Challenger qui permet d'analyser la proposition du joueur en la comparant

	 * � la combinaison secr�te de l'ordinateur.

	 */

	private void analyseProposiManChallenger() {

		



	//Analyse des boules bien plac�es (pions rouges) et mal plac�es (pions blancs). Pour faciliter le traitement, on va dire 

	//que pions rouges �quivaut � 1, pions blancs � 2 et emplacement vide � 3.

		

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

		

		//On r�ordonne le tableau d'entiers dans l'ordre num�rique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :

	   //pions rouges (si pr�sents), pions blancs (si pr�sents), et emplacement vide.

		

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

	 * M�thode relative au mode D�fenseur qui permet de r�cup�rer la combinaison secr�te du joueur.

	 * Apr�s r�cup�ration de la combinaison secr�te du joueur, l'ordinateur devra faire une proposition.

	 * 

	 * @param proposiSecret Combinaison secr�te du joueur en mode d�fenseur.

	 */

	public void setPropoSecreteManDefenseur(String propoSecrete) {

		this.propoSecreteManDefenseur = propoSecrete;

		

		LOGGER.debug("Jeu Mastermind en mode D�fenseur - Combinaison secr�te joueur Mod�le de donn�es :" +

		

				this.propoSecreteManDefenseur);

		

		this.initListePossibilitees();

		

		LOGGER.debug("Jeu Mastermind en mode D�fenseur - Taille de la liste cha�n�e :" +

				

				listePossibilitees.size());

		

		this.propoCpuDefenseur();

		this.updateObserver();

	}

	

	

	/**

	 * M�thode relative au mode D�fenseur qui permet de r�cup�rer la r�ponse du joueur.

	 * Suite � la r�ponse du joueur, l'ordinateur devra faire une proposition.

	 * 

	 * @param reponseMan - R�ponse du joueur en mode d�fenseur.

	 */

	public void setReponseManDefenseur(String reponseMan) {

		this.reponseManDefenseur = reponseMan;

		this.propoCpuDefenseur();

		this.updateObserver();

	}

	

	/**

	 * M�thode correspondant au mode D�fenseur, qui permet de programmer la proposition du Cpu

	 * qui sera adapt�e par rapport � la r�ponse du joueur.

	 * La premi�re proposition de la part de l'ordinateur sera le premier �l�ment de la liste chain�e

	 * qui comprendra toutes les possibilit�s.

	 * Suivant la reponse que fera le joueur, la liste sera r�duite au fur et � mesure.

	 * Le Cpu proposera � chaque fois le premier �l�ment de la liste.

	 */

	private void propoCpuDefenseur() {

		if(reponseManDefenseur.equals("")) {

			propoCpuDefenseur=listePossibilitees.getFirst();

			

			LOGGER.debug("Jeu Mastermind en mode D�fenseur - Proposition de l'ordinateur en mode d�fenseur :"+propoCpuDefenseur);

		}

		else {

			

			LOGGER.debug("Jeu Mastermind en mode D�fenseur - Modele de donn�es r�ponse du joueur :"+reponseManDefenseur);

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

			LOGGER.debug("Jeu Mastermind en mode D�fenseur - Taille liste cha�n�e r�actualis�e :"+listePossibilitees.size());

			LOGGER.debug("Jeu Mastermind en mode D�fenseur - Premier �l�ment r�actualis� :"+listePossibilitees.getFirst());

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

		 * M�thode relative au mode Duel qui permet de r�cup�rer la combinaison secr�te de l'ordinateur.

		 * 

		 * @param proposiSecrete - Combinaison secr�te de l'ordinateur en mode duel.

		 */

		public void setPropoSecreteCpuDuel(String propoSecrete) {

			this.propoSecreteCpuDuel = propoSecrete;

			

			LOGGER.debug("Jeu Mastermind en mode Duel - Combinaison Secr�te Ordinateur Mod�le de donn�es : "

			

					+ this.propoSecreteCpuDuel);

		}

		

		/**

		 * M�thode relative au mode Duel qui permet de r�cup�rer la combinaison secr�te du joueur.

		 * 

		 * @param proposiSecrete Combinaison secr�te du joueur en mode duel.

		 */

		public void setPropoSecreteManDuel(String propoSecrete) {

			this.propoSecreteManDuel=propoSecrete;

			

			LOGGER.debug("Jeu Mastermind en mode Duel - Proposition Secr�te Joueur Mod�le de donn�es :"

			

					+ this.propoSecreteManDuel);

			

			this.initListePossibilitees();

			

			LOGGER.debug("Jeu Mastermind en mode Duel - Taille de la liste cha�n�e :" 

			

					+ listePossibilitees.size());

		}

		

		

		/**

		 * M�thode relative au mode Duel qui permet de r�cup�rer la proposition du joueur.

		 * Suite � la proposition du joueur, l'ordinateur devra r�pondre et �galement

		 * faire une proposition.

		 * 

		 * @param proposiMan Proposition du joueur en mode duel.

		 */

		public void setPropoManDuel(String propoMan) {

			int verifReponseCpuDuel = 0;

			this.propoManDuel = propoMan;

			

			LOGGER.debug("Jeu Mastermind en mode Duel - Proposition Joueur Mod�le de donn�es :"

			

					+this.propoManDuel);

			

			this.analysePropoManDuel();

			affichage = reponseCpuDuel;

			LOGGER.debug("Jeu Mastermind en mode Duel - R�ponse Ordinateur Mode Duel :"

			

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

		 * M�thode relative au mode Duel qui permet de r�cup�rer la r�ponse du joueur.

		 * 

		 * @param reponseJoueur R�ponse du joueur en mode duel.

		 */

		public void setReponseManDuel(String reponseMan) {

			this.reponseManDuel = reponseMan;

		}

		

		

		/**

		 * M�thode relative au mode Duel qui permet d'analyser la proposition du joueur en la comparant

		 * � la combinaison secr�te de l'ordinateur.

		 */

		public void analysePropoManDuel() {

			

		

		//Analyse des boules bien plac�es (pions rouges) et mal plac�es (pions blancs).

		

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

	

	//On r�ordonne le tableau d'entiers dans l'ordre num�rique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :

	//pions rouges (si pr�sents), pions blancs (si pr�sents), et emplacement vide.

	

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

 * M�thode relative au mode Duel qui permet de d�terminer la proposition de l'ordinateur

 * en fonction de la r�ponse du joueur. La premi�re proposition de l'ordinateur correspondra

 * au premier �l�ment de la liste chain�e initiale qui comprend toutes les possibilit�s

 * vu que le joueur n'a pas encore r�pondu. Par la suite, suivant la r�ponse du joueur, la liste

 * chain�e sera r�duite au fur et � mesure jusqu'� ne comprendre qu'un seul �l�ment. 

 * L'ordinateur proposera � chaque fois le premier �l�ment de la liste chain�e.

 */



public void propoCpuDuel() {

	

	if(reponseManDuel.equals("")) {

		propoCpuDuel = listePossibilitees.getFirst();

		

		LOGGER.debug("Jeu Mastermind en mode Duel - Proposition Ordinateur Mode Duel :"

		

				+ propoCpuDuel);



	}

	

	else {

		

		LOGGER.debug("Jeu Mastermind en mode Duel - Modele de donn�es r�ponse du joueur :"

		

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

		LOGGER.debug("Jeu Mastermind en mode Duel - Taille liste cha�n�e r�actualis� :"+listePossibilitees.size());

		LOGGER.debug("Jeu Mastermind en mode Duel - Premier �l�ment r�actualis� :"+listePossibilitees.getFirst());

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

 * Mutateur commun � tous les modes de jeu qui permet de modifier le mode de jeu.

 * 

 * @param modeJeu Variable relative au mode de jeu : 0 - Challenger, 1 - D�fenseur, 2 - Duel.

 */

  public void setModeJeu(int modeJeu) {

	this.modeJeu = modeJeu;

}



/**

 * Mutateur commun � tous les modes de jeu qui permet de modifier le nombre d'essais.

 * 

 * @param nbEssai - Nombre d'essais.

 */

public void setNbEssai(int nbEssai) {

	this.nbEssai=nbEssai;

}



/**

 * Mutateur commun � tous les modes de jeu qui permet de modifier le nombre de cases.

 * 

 * @param nbreCases Nombre de cases.

 */

public void setNbCase(int nbCase) {

	this.nbCase = nbCase;

}



/**

 * Mutateur commun � tous les modes de jeu qui permet de modifier le nombre de couleurs utilisables.

 * 

 * @param nbCouleur - Nombre de couleurs utilisables.

 */

public void setNbCouleur(int nbCouleur) {

	this.nbCouleur=nbCouleur;

}



/**

 * M�thode commune � tous les modes de jeu qui permet de r�cup�rer le choix du joueur en fin de partie et

 * en fonction de cela, faire appel � la m�thode ad�quate correspondant au choix du joueur.

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

 * M�thode permettant d'initialiser la liste chain�e avec l'ensemble des combinaisons possibles.

 */

public void initListePossibilitees() {

	

	/*On cr�e un objet LinkedList avec l'ensemble des possibilit�s. Dans le cas o� on a 4 cases et 6 couleurs utilisables, 

	 l'objet LinkedList contiendra 1296 �l�ments. On s'assure bien que cette liste est initialis�e � chaque d�but de partie*/

	

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

 * Mise � jour des observateurs

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