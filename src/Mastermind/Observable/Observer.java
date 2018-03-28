package Mastermind.Observable;



/*************************MASTERMIND******************************/

/** Pattern Observer - Interface Observer.
*
* @ author Matthieu Delomez
* 
*******************************************************************/


public interface Observer {
	
	/**
	 * Méthode qui permet de mettre à jour la grille de jeu
	 * en fonction des propositions et des réponses de l'ordinateur
	 * suivant le mode de jeu choisi par l'utilisateur.
	 * 
	 * @param reponse - Correspond aux propositions et réponses
	 * de l'ordinateur.
	 */
	public void updateMaster(String reponse);
	
	/**
	 * Méthode qui permet de quitter l'appli.
	 * 
	 * @param quitterAppli
	 */
    public void quitterAppli();
    
    /**
     * Méthode permettant de retourner à la page d'accueil.
     * 
     * @param accueilObserver
     */
    public void accueilObserver();

    /**
     * Méthode pour relancer la partie.
     * 
     * @param relancerPartie
     */
    public void relancerPartie();
	


}