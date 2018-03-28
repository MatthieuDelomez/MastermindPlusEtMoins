package Mastermind.Observable;



/*************************MASTERMIND******************************/

/** Pattern Observer - Interface Observable.
* 
* @ author Matthieu Delomez
* 
*******************************************************************/


public interface Observable {
	
	/**
	 * Méthode permettant d'ajouter des observater à une liste d'observateur.
	 * Une classe qui inplémente l'interface Observer.
	 * 
	 * @param obs - Un objet de type Observer.
	 */
	public void addObserver(Observer obs);
	
	/**
	 * Méthode qui permet de mettre à jour la liste des observateurs.
	 */
	public void updateObserver();
	
	/**
	 * Méthode qui permet de réinitialiser la liste des bservateurs.
	 */
	public void delObserver();
	
	/**
	 * Méthode qui permet de quitter l'appli.
	 */
	public void quitterAppli();
	
	/**
	 * Méthode qui permet de retourner à la page d'accueil.
	 */
	public void accueilObserver();
	
	/**
	 * Méthode permettant de relancer le jeu.
	 */
	public void relancerPartie();


	

}