package Mastermind.Donnees;

import Mastermind.Model.DonneeMaster;

/******************************MASTERMIND********************************/

/*************************************************************************
 * 
 * Pattern MVC - Classe permettant de controler et transférer les données
 * saisies par l'utilisateur associés au jeu Mastermind.
 * 
 * @author Delomez Matthieu
 *************************************************************************/

public class MasterControl {

	/**
	 * Modèle de données relatif au jeu Mastermind.
	 * 
	 * @see DonneeMaster
	 */
	private DonneeMaster modelMaster;

	/**
	 * Constructeur de la classe MasterControler.
	 * 
	 * @param modelMaster Modèle de données relatif au jeu.
	 */
	public MasterControl(DonneeMaster modelMaster) {
		this.modelMaster = modelMaster;
	}


	/**
	 * Méthode relative au mode Challenger qui permet de transférer la combinaison
	 * secrète de l'ordinateur au modèle.
	 * 
	 * @param propositionSecrete Combinaison secrète de l'ordinateur en mode challenger.
	 */
	public void setPropoSecreteCpuChallenger(String propoSecrete) {
		this.modelMaster.setPropoSecreteCpuChallenger(propoSecrete);
	}

	/**
	 * Méthode relative au mode Challenger qui permet de transférer la proposition
	 * du joueur au modèle.
	 * 
	 * @param propositionJoueur Proposition du joueur en mode challenger.
	 */
	public void setPropoManChallenger(String propoMan) {
		this.modelMaster.setPropoManChallenger(propoMan);
	}


	/**
	 * Méthode relative au mode Défenseur qui permet de transférer la combinaison
	 * secrète du joueur au modèle.
	 * 
	 * @param propositionSecrete Combinaison secrète du joueur en mode défenseur.
	 */
	public void setPropoSecreteManDefenseur(String propoSecrete) {
		this.modelMaster.setPropoSecreteManDefenseur(propoSecrete);
	}

	/**
	 * Méthode relative au mode Défenseur qui permet de transférer la réponse du
	 * joueur au modèle.
	 * 
	 * @param reponseJoueur Réponse du joueur en mode défenseur.
	 */
	public void setReponseManDefenseur(String reponseMan) {
		this.modelMaster.setPropoSecreteManDefenseur(reponseMan);
	}


	/**
	 * Méthode relative au mode Duel qui permet de transférer la combinaison secrète
	 * de l'ordinateur au modèle.
	 * 
	 * @param propositionSecrete Combinaison secrète de l'ordinateur en mode duel.
	 */
	public void setPropoSecreteCpuDuel(String propoSecrete) {
		this.modelMaster.setPropoSecreteCpuDuel(propoSecrete);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la combinaison secrète
	 * du joueur au modèle.
	 * 
	 * @param propositionSecrete Combinaison secrète du joueur en mode duel.
	 */
	public void setPropoSecreteManDuel(String propoSecrete) {
		this.modelMaster.setPropoSecreteManDuel(propoSecrete);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la proposition du
	 * joueur au modèle.
	 * 
	 * @param propositionJoueur Proposition du joueur en mode duel.
	 */
	public void setPropoManDuel(String propoMan) {
		this.modelMaster.setPropoManDuel(propoMan);
	}

	/**
	 * Méthode relative au mode Duel qui permet de transférer la réponse du joueur
	 * au modèle.
	 * 
	 * @param reponseJoueur Réponse du joueur en mode duel.
	 */
	public void setReponseManDuel(String reponseMan) {
		this.modelMaster.setReponseManDuel(reponseMan);
	}


	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le mode de
	 * jeu au modèle.
	 * 
	 * @param modeJeu Challenger = 0, Defenseur = 1, Duel = 2.
	 */
	public void setModeJeu(int modeJeu) {
		this.modelMaster.setModeJeu(modeJeu);

	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le nombre
	 * d'essais au modèle.
	 * 
	 * @param nbEssais Nombre d'essais.
	 */
	public void setNbEssai(int nbEssai) {
		this.modelMaster.setNbEssai(nbEssai);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le nombre de
	 * cases au modèle.
	 * 
	 * @param nbreCases Nombre de cases
	 */
	public void setNbCase(int nbCase) {
		this.modelMaster.setNbCase(nbCase);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le nombre de
	 * couleurs utilisables au modèle.
	 * 
	 * @param nbrCouleurs Nombre de couleurs utilisables
	 */
	public void setNbCouleur(int nbCouleur) {
		this.modelMaster.setNbCouleur(nbCouleur);
	}

	/**
	 * Méthode commune à tous les modes de jeu qui permet de transférer le choix du
	 * joueur en fin de partie au modèle.
	 * 
	 * @param choixFinPartie Choix du joueur en fin de partie.
	 */
	public void setChoixFinPartie(String choixFinPartie) {
		this.modelMaster.setChoixFinPartie(choixFinPartie);
	}
}