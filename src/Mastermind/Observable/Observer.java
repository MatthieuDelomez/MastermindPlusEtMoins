package Mastermind.Observable;



/*************************MASTERMIND******************************/

/** Pattern Observer - Interface Observer.
*
* @ author Matthieu Delomez
* 
*******************************************************************/


public interface Observer {
	
	/**
	 * M�thode qui permet de mettre � jour la grille de jeu
	 * en fonction des propositions et des r�ponses de l'ordinateur
	 * suivant le mode de jeu choisi par l'utilisateur.
	 * 
	 * @param reponse - Correspond aux propositions et r�ponses
	 * de l'ordinateur.
	 */
	public void updateMaster(String reponse);
	
	/**
	 * M�thode qui permet de quitter l'appli.
	 * 
	 * @param quitterAppli
	 */
    public void quitterAppli();
    
    /**
     * M�thode permettant de retourner � la page d'accueil.
     * 
     * @param accueilObserver
     */
    public void accueilObserver();

    /**
     * M�thode pour relancer la partie.
     * 
     * @param relancerPartie
     */
    public void relancerPartie();
	


}