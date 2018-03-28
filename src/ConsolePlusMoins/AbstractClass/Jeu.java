package ConsolePlusMoins.AbstractClass;

import java.io.FileInputStream;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;



/***************************MASTERMIND PLUS ET MOINS**********************

/*************************************************************************
 * 
 * Classe abstraite Jeu qui d�finit les m�thodes au bon fonctionnement du
 * jeu PlusOuMoins.
 * 
 * @author Delomez Matthieu
 *************************************************************************/


public abstract class Jeu {
	
	/*
	 * Joueur 1 qui correspond au joueur Humain.
	 */
	protected Joueur joueur1;
	
	/*
	 * Joueur 2 qui correspond au joueur Cpu.
	 */
	protected Joueur joueur2;
	
	/*
	 * Variable de type String contenant le nom du jeu.
	 */
	protected String nomDuJeu;
	
	/*
	 * Variable static correspondant au resulat donn�e.
	 */
	protected static String resultat;
	
	/*
	 * Tableau d'entier static correspondant aux nombres utilis�s.
	 */
	protected static int nbrUtilises[];
	
	/*
	 * Variable static de type int correspondant � la longueur du code Secret.
	 */
	public static int LongueurCodeSecret;
	
	/*
	 * Variable qui sert de compteur.
	 */
	public static int compteur;
	
	/*
	 * Variable static correspondant au coup maximum autoris�s.
	 */
	protected static int coupsMax;
	
	/*
	 * Variable de type String correspondant au code secret.
	 */
	public static String nbrSecret = "";
	
	/*
	 * Configuration du fichier config.properties.
	 */
	protected Properties prop = new Properties();
	
	/*
	 * Inportation du fichier config.properties.
	 */
	InputStream input = null;
	
	
	/* *************************************************************************************************
	 * 
	 *********************************CONSTRUCTEUR*************************************
	 * 
	 **************************************************************************************************/

	/*
	 * Constructeur de la classe Jeu.
	 * 
	 * @param nomDuJeu.
	 * @param LongueurCodeSecret
	 * @param nbrUtilises
	 * @param coupsMax
	 * @param input
	 */
	
	public Jeu(String nomDuJeu) {
		
		/*
		 * Chargement du fichier config.properties.
		 */
		try {
		     input = new FileInputStream("config.properties");
		     prop.load(input);
		     LongueurCodeSecret = Integer.valueOf(prop.getProperty("longueur"));
		     coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		     nbrUtilises = new int [Integer.valueOf(prop.getProperty("couleurs"))];
		     
		/*
		 * Boucle pour incr�menter un coup/tour � chaque tour de boucle.
		 */
		     for(int i =0; i < Integer.valueOf(prop.getProperty("couleurs")); i++) {
		    	 nbrUtilises[i] = i;
		     }
		     
		     
		}catch (IOException ex) {
			ex.printStackTrace();
			
		} finally {
			
			if(input != null) {
				try {
					input.close();
					
				}catch(IOException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		
		this.nomDuJeu = nomDuJeu;
		
		/*
		 * Variable correspondant au byte qui correspondra � la r�ponse qui sera appliqu�e au Scanner.
		 */
		byte choix = 0;
		
		
		System.out.println(afficherNom());

		do {

			System.out.println("\nChoisissez un mode de jeu :");

			System.out.println("\n1� Challenger \n2� D�fenseur \n3� Duel \n4� Quitter");



			Scanner sc = new Scanner(System.in);
			
			/*
			 * Construction des diff�rents modes de jeu.
			 * Si l'entr�e n'est pas un byte alors invoquer la classe Erreur.
			 * 
			 * @see Erreur
			 */
			if(!sc.hasNextByte()) {
				Erreur.erreurChoix();
				sc.next();
				continue;
			}
			
			choix = sc.nextByte();
			switch(choix) {
			
			case 1 :
				   this.challenger();
				   break;
				    
			case 2:
				   this.defenseur();
				   break;
				   
			case 3: 
				   this.duel();
				   break;
				   
			case 4 : 
				   break;
				   
				   
		    default: 
		    	   Erreur.erreurChoix();
			}
			
		} while(choix != 1 && choix !=2 && choix !=3 && choix !=4);
		
	}
	
	
	/*
	 * M�thodes relatives aux modes de jeu.
	 */
	public void challenger() {
		}
	
	public void defenseur() {
		}
	
	public void duel() {
	    }
	
	
	/*
	 * G�n�rer le code Secret.
	 */
	public void genererNbrSecret() {
		
		nbrSecret = "";
		Random random = new Random();
		int chiffreNbrSecret[] = new int [LongueurCodeSecret];
		
		/*
		 * Tour de boucle pour g�n�rer un chiffre al�atoirement pour atteindre la longueur
		 * qui est d�finie dans la variable LongueurCodeSecret.
		 */
		for(int i = 0; i < LongueurCodeSecret; i++) {
			chiffreNbrSecret[i] = random.nextInt(9+1);
			
			nbrSecret += chiffreNbrSecret[i];
			
		}
	}
	
	
	/*
	 * M�thode servant � retourner le nom du jeu qui � �t� selectionner par l'utilisateur.
	 */
	public String afficherNom() {
		return this.nomDuJeu;
	}
	
	
	/*
	 * Comparer la proposition du joueur au code secret.
	 */
	public void comparerNbr(Joueur joueur) {
		/*
		 * On met la valeur de la proposition dans un String.
		 */
	    String secretPropo = String.valueOf(Joueur.proposition);
	    resultat = "";
	    
	    for( int i = 0; i < LongueurCodeSecret; i++) {
	    	
	    	int chiffrePropo = Character.getNumericValue(secretPropo.charAt(i));
	    	int chiffreNbrSecret = Character.getNumericValue(nbrSecret.charAt(i));
	    	/*
	    	 * On g�re les r�ponse qui seront donn�es par le Cpu ou le Joueur.
	    	 * 
	    	 * 
	    	 */
	    	if(chiffrePropo > chiffreNbrSecret)
	    		resultat += "-";
	    	else if(chiffrePropo < chiffreNbrSecret)
	    		resultat += "+";
	    	else if(chiffrePropo == chiffreNbrSecret)
	    		resultat += "=";
	    	
	    }
	    
	    /*
	     * On affiche le resulat dans la console.
	     */
	    System.out.println("Resultat : " + resultat + "\n\n--------\n");
	}
	    
	    /*
	     * On g�re la fin de partie avec les crit�res pour gagner ou perdre.
	     * 
	     * @param compteur - Score
	     * @param coupsMax - coups maximum autoris�s.
	     */
	    public void finPartie(String vainqueur) {
	    if(compteur >= coupsMax && ! Joueur.proposition.equals(nbrSecret))
	    	System.out.println(

					"Vous avez atteint la limite de coups ===>" + coupsMax + "<=== ! Le nombre myst�re �tait : " + nbrSecret + ".");

	    else
	    	System.out.println(  vainqueur + " trouv� le code secret en " + (compteur - 1) + " coups !");
	}
	
	
	/*
	 * Initialiser le score via le compteur.
	 */
	public void initCompteur() {
	compteur = 1;
    }
	
	/*
	 * Affichage du compteur.
	 */
	public void afficherCompteur() {
		System.out.println("Coups n�" + (compteur));
}
	
	/*
	 * Gestion de la fin de partie.
	 */
	
	/*
	 * Initialisation du mode d�veloppeur.
	 */
	public void devMode() {
		if(Main.modDev ==1)
			
		{
			System.out.println("[Mode d�veloppeur] Le nombre myst�re est : "+nbrSecret);

		 
       }
	
	}
	
 }
