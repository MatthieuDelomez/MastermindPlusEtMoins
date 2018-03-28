package ConsolePlusMoins.ClassFille;



import java.util.ArrayList;

import java.util.Random;

import ConsolePlusMoins.AbstractClass.Jeu;
import ConsolePlusMoins.AbstractClass.Joueur;





/***************************MASTERMIND PLUS ET MOINS**********************

/*************************************************************************
 * 
 * Classe fille correspondant à l'ordinateur héritant de la classe mére
 * Joueur.
 * 
 * @author Delomez Matthieu
 *************************************************************************/


public class Cpu extends Joueur {
	
	/*
	 * Variable de type String correspondant au tour précédent.
	 */
	String tourPrecedent = "";
	
	/*
	 * Constructeur de la classe Cpu ne retournant aucune valeur.
	 */
	public Cpu()
	{}
	
	/*
	 * Méthode pour mettre en place un algorithme pour que l'ordinateiur puisse nous
	 * proposer un nombre qui sera généré de facon aléatoire.
	 * 
	 */
	public void proposerNbr() {
		System.out.println("Proposition du Cpu : ");
		
		if(Jeu.compteur == 1) {
			proposition = "";
			Random random = new Random();
			
			int chiffreNbrSecret[] = new int [Jeu.LongueurCodeSecret];
			
			/*
			 * On génére un chiffre de facon aléatoire pour ainsi atteindre
			 * la longueur définie dans la longueur du code secret.
			 */
			for( int i = 0; i < Jeu.LongueurCodeSecret; i++) {
				chiffreNbrSecret[i] = random.nextInt(9+1);
				proposition += chiffreNbrSecret[i];
				
			}
			
			tourPrecedent = proposition;
			System.out.println(proposition);
		
		}else {
			   int chiffreNbrSecret[] = new int [Jeu.LongueurCodeSecret];
			   
			   for(int i = 0; i < Jeu.LongueurCodeSecret; i++) {
				   
				   if(resulatPrecedent.charAt(i) == '+') {
					   
					   chiffreNbrSecret[i] = Character.getNumericValue(tourPrecedent.charAt(i));
					   chiffreNbrSecret[i]++;
				   
				   } else if (resulatPrecedent.charAt(i) == '-') {
					   
					   chiffreNbrSecret[i] = Character.getNumericValue(tourPrecedent.charAt(i));
					   chiffreNbrSecret[i]--;
			   
				   } else if (resulatPrecedent.charAt(i) == '=')

						chiffreNbrSecret[i] = Character.getNumericValue(Jeu.nbrSecret.charAt(i));

				   proposition += chiffreNbrSecret[i];
			   }
			   
			   proposition = proposition.substring(Jeu.LongueurCodeSecret, proposition.length());
			
			   tourPrecedent = proposition;
			   
			   System.out.println(proposition);

		}
		
	}
	
}
	

