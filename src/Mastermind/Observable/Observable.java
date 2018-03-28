package Mastermind.Observable;



/*************************MASTERMIND******************************/

/** Pattern Observer - Interface Observable.
* 
* @ author Matthieu Delomez
* 
*******************************************************************/


public interface Observable {
	
	/**
	 * M�thode permettant d'ajouter des observater � une liste d'observateur.
	 * Une classe qui inpl�mente l'interface Observer.
	 * 
	 * @param obs - Un objet de type Observer.
	 */
	public void addObserver(Observer obs);
	
	/**
	 * M�thode qui permet de mettre � jour la liste des observateurs.
	 */
	public void updateObserver();
	
	/**
	 * M�thode qui permet de r�initialiser la liste des bservateurs.
	 */
	public void delObserver();
	
	/**
	 * M�thode qui permet de quitter l'appli.
	 */
	public void quitterAppli();
	
	/**
	 * M�thode qui permet de retourner � la page d'accueil.
	 */
	public void accueilObserver();
	
	/**
	 * M�thode permettant de relancer le jeu.
	 */
	public void relancerPartie();


	

}