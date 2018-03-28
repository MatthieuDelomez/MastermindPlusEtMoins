package Mastermind.Vue;

import java.awt.Color;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdz.control.*;
import com.sdz.model.*;
import com.sdz.observation.*;
import com.sdz.vue.*;


/******************************MASTERMIND********************************/

/*************************************************************************
 * Classe relative au mode d�fenseur du jeu.
 *Cette classe impl�mente l'interface Observer.
 * 
 * @author Delomez Matthieu
 *************************************************************************/


public class ModeDefenseur extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;

	/*
	 * Param�tre du jeu.
	 */
	private int nbCase, nbEssai, nbCouleur = 6;
	
	/*
	 * Variable pour les controles.
	 */
	private int ligne = 0, colonne =1, colonneCombiSecrete = 0, remplissageSoluce = 0, verifCombiSecrete = 0;
	
	/*
	 * Tableau d'entier utilis� pour enregistrer la r�ponse du joueur.
	 */
	private int [] tabReponseMan;
	
	/*
	 * GridLayout correspondant � l'ensemble de la grille du jeu.
	 */
	private GridLayout gl;
	
	/*
	 * GridLayout qui correspond � la derni�re colonne de la grille.
	 */
	private GridLayout glSoluce;
	
	/*
	 * Jpanel pour l'interface graphique.
	 */
	private JPanel containerGrilleJeu = new JPanel(),
			
			       containerBoutonCouleur = new JPanel(),
			       
			       containerReponseMan = new JPanel(), 
			       
			       containerSoluceCombiSecreteMan = new JPanel();
	
	
	/*
	 * Tableau de Jpanel utilis� pour l'interface graphique.		       
	 */
	private JPanel[] containerSoluce;
	
	/*
	 * Image pour l'interface.
	 */
	private ImageIcon imgEmplacementVide = new ImageIcon("images/EmplacementVide.png"),
			
			imgEmplacementVideSolution = new ImageIcon("images/EmplacementVideSolution.png"),
			
			imgCouleurVerte = new ImageIcon("images/CouleurVerte.png"),
			
			imgCouleurJaune = new ImageIcon("images/CouleurJaune.png"),
			
			imgCouleurBleu = new ImageIcon("images/CouleurBleu.png"),

			imgCouleurBleuFonce = new ImageIcon("images/CouleurBleuFonce.png"),
			
			imgCouleurGris = new ImageIcon("images/CouleurGris.png"),
			
			imgCouleurMarron = new ImageIcon("images/CouleurMarron.png"),
			
			imgCouleurNoir = new ImageIcon("images/CouleurNoir.png"),
			
			imgCouleurOrange = new ImageIcon("images/CouleurOrange.png"),

			imgCouleurRouge = new ImageIcon("images/CouleurRouge.png"),
			
			imgCouleurViolet = new ImageIcon("images/CouleurViolet.png"),
			
			imgPionRougeSoluce = new ImageIcon("images/PionRougeSolution.png"),
			
			imgPionBlancSoluce = new ImageIcon("images/PionBlancSolution.png"),
			
			imgEmplacementVideSoluceCombiSecreteMan = new ImageIcon("images/EmplacementVideSolutionCombiSecreteCpu.png");
	
	
	/*
	 * Tableau JLabel � deux dimensions.
	 */
	private JLabel [][] tab2DimenGrilleJeu;
	
	/*
	 * Tableau de JLabel � une dimension.
	 */
	private JLabel [] tabSoluce, tabSoluceCombiSecreteMan;
	
	/*
	 * JLabel de type informatif.
	 */
	private JLabel premiereInstruction = new JLabel("Veuillez choisir une combinaison secrete."),
			
			ReponseMan = new JLabel("Votre r�ponse : "),
			
			Soluce = new JLabel("Combinaison Secr�te :");
	
	/*
	 * JButton utilis� pour l'interface graphique.
	 */
	private JButton effacerCombiSecrete = new JButton("Effacer"),
			
			validerCombiSecrete = new JButton("valider"),
			
			boutonCouleurVerte = new JButton(imgCouleurVerte),
			
			boutonCouleurBleuFonce = new JButton(imgCouleurBleuFonce),

			boutonCouleurBleu = new JButton(imgCouleurBleu),

			boutonCouleurJaune = new JButton(imgCouleurJaune),

			boutonCouleurMarron = new JButton(imgCouleurMarron),

			boutonCouleurOrange = new JButton(imgCouleurOrange),

			boutonCouleurRouge = new JButton(imgCouleurRouge),

			boutonCouleurViolet = new JButton(imgCouleurViolet),

			boutonCouleurGris = new JButton(imgCouleurGris),

			boutonCouleurNoir = new JButton(imgCouleurNoir),
			
			validerReponseMan = new JButton("Valider"),
			
			effacerReponseMan = new JButton("Effacer"),
			
			pionRougeSoluce = new JButton(imgPionRougeSoluce),
			
			pionBlancSoluce = new JButton(imgPionBlancSoluce);
			
			
	/*
	 * Typographie.
	 */
	private Font police = new Font("Courrier", Font.ROMAN_BASELINE, 14),
			
			policeSoluce = new Font("Arial", Font.BOLD, 16);
	
	/*
	 * Combinaison secr�te du joueur en mode d�fenseur.
	 */
	private String propoSecreteManDefenseur = "";
	
	/*
	 * Combinaison secr�te du Cpu en mode d�fenseur.
	 */
	private String propoCpuDefenseur = "";
	
	/*
	 * R�ponse du joueur en mode d�fenseur.
	 */
	private String reponseManDefenseur = "";
	
	/*
	 * R�ponse attendue par le joueur.
	 */
	private String reponseManAttendue = "";
	
	/*
	 * Mod�le de donn�es relatif au jeu.
	 * 
	 * @see DonneeMaster
	 */
	private DonneeMaster modelMaster;

	/*
	 * Mod�le de donn�es relatif au jeu.
	 * 
	 * @see MasterControl
	 */
	private MasterControl controlMaster;
	
	/*
	 * Boite de dialogue en fin de partie.
	 * 
	 * @see BoiteDialogueEndOfTheGame
	 */
	private BoiteDialogueEndOfTheGame finPartie;
	
	/*
	 * Variable de type boolean qui indique la fin de partie.
	 */
	private boolean booleanfinPartie = false;
	
	/*
	 * Boolean pour le mode d�veloppeur
	 */
	private boolean modeDeveloppeurActive;
	
	
	/*
	 * Gestion des logs d'erreurs.
	 */
	private static final Logger LOGGER = LogManager.getLogger();
	
	
	/* *************************************************************************************************
	 * 
	 ******************************************CONSTRUCTEUR*********************************************
	 * 
	 **************************************************************************************************/
	
	
	/*
	 * Constructeur de la classe ModeDefenseur.
	 * 
	 * @param nbCase - nombre de cases du jeu.
	 * @param nbEssai - nombre d'Essais du jeu.
	 * @param nbCouleur - nombre de couleurs du jeu.
	 * @param modelMaster - correspond aux donn�es du jeu.
	 * @ see DonneeMaster
	 */
	public ModeDefenseur(int nbCase, int nbEssai, int nbCouleur, boolean modeDeveloppeurActive, DonneeMaster modelMaster ) {
		
		LOGGER.trace("Instanciation du jeu en mode D�fenseur");
		this.setPreferredSize(new Dimension(1000,740));
		this.setBackground(Color.WHITE);
		this.nbCase = nbCase;
		this.nbCouleur = nbCouleur;
		this.nbEssai = nbEssai;
		this.modeDeveloppeurActive = modeDeveloppeurActive;
		this.modelMaster = modelMaster;
		this.controlMaster = new MasterControl(this.modelMaster);
		
		
		/*
		 * Tableau qui enregistrera la r�ponse du joueur.
		 */
		tabReponseMan = new int[this.nbCase];
		for (int i = 0; i < this.nbCase; i++) {
			
			tabReponseMan[i] = 3;
			reponseManDefenseur += "V";
		}
		
		
		premiereInstruction.setPreferredSize(new Dimension (1000,40));
		premiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		premiereInstruction.setFont(police);
		
		boutonCouleurBleu.setPreferredSize(new Dimension (29,29));
		boutonCouleurBleuFonce.setPreferredSize(new Dimension (29,29));
		boutonCouleurRouge.setPreferredSize(new Dimension (29,29));
		boutonCouleurViolet.setPreferredSize(new Dimension (29,29));
		boutonCouleurVerte.setPreferredSize(new Dimension (29,29));
		boutonCouleurGris.setPreferredSize(new Dimension (29,29));
		boutonCouleurMarron.setPreferredSize(new Dimension (29,29));
		boutonCouleurNoir.setPreferredSize(new Dimension (29,29));
		boutonCouleurOrange.setPreferredSize(new Dimension (29,29));
		boutonCouleurJaune.setPreferredSize(new Dimension (29,29));

		validerCombiSecrete.setEnabled(false);
		containerBoutonCouleur.setPreferredSize(new Dimension(1000,40));
		containerBoutonCouleur.setBackground(Color.WHITE);
		
		switch(this.nbCouleur) {
		
		case 4:
			containerBoutonCouleur.add(boutonCouleurBleu);
			containerBoutonCouleur.add(boutonCouleurJaune);
			containerBoutonCouleur.add(boutonCouleurOrange);
			containerBoutonCouleur.add(boutonCouleurRouge);
			break;
			
		case 5:
			containerBoutonCouleur.add(boutonCouleurBleu);
			containerBoutonCouleur.add(boutonCouleurJaune);
			containerBoutonCouleur.add(boutonCouleurOrange);
			containerBoutonCouleur.add(boutonCouleurRouge);
			containerBoutonCouleur.add(boutonCouleurVerte);
			break;
			
		case 6:
			containerBoutonCouleur.add(boutonCouleurBleu);
			containerBoutonCouleur.add(boutonCouleurJaune);
			containerBoutonCouleur.add(boutonCouleurOrange);
			containerBoutonCouleur.add(boutonCouleurRouge);
			containerBoutonCouleur.add(boutonCouleurVerte);
			containerBoutonCouleur.add(boutonCouleurViolet);
			break;
			
		case 7:
			containerBoutonCouleur.add(boutonCouleurBleu);
			containerBoutonCouleur.add(boutonCouleurJaune);
			containerBoutonCouleur.add(boutonCouleurOrange);
			containerBoutonCouleur.add(boutonCouleurRouge);
			containerBoutonCouleur.add(boutonCouleurVerte);
			containerBoutonCouleur.add(boutonCouleurViolet);
			containerBoutonCouleur.add(boutonCouleurGris);
			break;
			
		case 8:
			containerBoutonCouleur.add(boutonCouleurBleu);
			containerBoutonCouleur.add(boutonCouleurJaune);
			containerBoutonCouleur.add(boutonCouleurOrange);
			containerBoutonCouleur.add(boutonCouleurRouge);
			containerBoutonCouleur.add(boutonCouleurVerte);
			containerBoutonCouleur.add(boutonCouleurViolet);
			containerBoutonCouleur.add(boutonCouleurGris);
			containerBoutonCouleur.add(boutonCouleurBleuFonce);
			break;
			
		case 9:
			containerBoutonCouleur.add(boutonCouleurBleu);
			containerBoutonCouleur.add(boutonCouleurJaune);
			containerBoutonCouleur.add(boutonCouleurOrange);
			containerBoutonCouleur.add(boutonCouleurRouge);
			containerBoutonCouleur.add(boutonCouleurVerte);
			containerBoutonCouleur.add(boutonCouleurViolet);
			containerBoutonCouleur.add(boutonCouleurGris);
			containerBoutonCouleur.add(boutonCouleurBleuFonce);
			containerBoutonCouleur.add(boutonCouleurMarron);
			break;
			
		case 10:
			containerBoutonCouleur.add(boutonCouleurBleu);
			containerBoutonCouleur.add(boutonCouleurJaune);
			containerBoutonCouleur.add(boutonCouleurOrange);
			containerBoutonCouleur.add(boutonCouleurRouge);
			containerBoutonCouleur.add(boutonCouleurVerte);
			containerBoutonCouleur.add(boutonCouleurViolet);
			containerBoutonCouleur.add(boutonCouleurGris);
			containerBoutonCouleur.add(boutonCouleurBleuFonce);
			containerBoutonCouleur.add(boutonCouleurMarron);
			containerBoutonCouleur.add(boutonCouleurNoir);
			break;
			
			
	    default : 
	    	LOGGER.error("Jeu en mode Defenseur - Erreur lors de la mise en place de l'IHM pour les boutons li�s aux couleurs");
	    	
		}
		
		containerBoutonCouleur.add(validerCombiSecrete);
		containerBoutonCouleur.add(effacerCombiSecrete);
		
		ReponseMan.setHorizontalAlignment(JLabel.CENTER);
		ReponseMan.setFont(police);
		
		pionRougeSoluce.setPreferredSize(new Dimension(14,14));
		pionRougeSoluce.setEnabled(false);
		pionBlancSoluce.setPreferredSize(new Dimension(14,14));
		pionBlancSoluce.setEnabled(false);
		
		validerReponseMan.setEnabled(false);
		effacerReponseMan.setEnabled(false);
		
		containerReponseMan.setPreferredSize(new Dimension(1000,40));
		containerReponseMan.setBackground(Color.WHITE);
		
		containerReponseMan.add(ReponseMan);
		containerReponseMan.add(pionBlancSoluce);
		containerReponseMan.add(pionRougeSoluce);
		containerReponseMan.add(validerReponseMan);
		containerReponseMan.add(effacerReponseMan);
		
		
		Soluce.setFont(policeSoluce);
		containerSoluceCombiSecreteMan.setPreferredSize(new Dimension(1000,40));
		containerSoluceCombiSecreteMan.setBackground(Color.WHITE);
		containerSoluceCombiSecreteMan.add(Soluce);
		tabSoluceCombiSecreteMan = new JLabel[this.nbCase];
		
		for (int i = 0; i < this.nbCase; i++) {
			tabSoluceCombiSecreteMan[i] = new JLabel();
			tabSoluceCombiSecreteMan[i].setPreferredSize(new Dimension(29,29));
			tabSoluceCombiSecreteMan[i].setIcon(imgEmplacementVideSoluceCombiSecreteMan);
			
			containerSoluceCombiSecreteMan.add(tabSoluceCombiSecreteMan[i]);
			
		}
		
		this.add(premiereInstruction);
		this.add(containerBoutonCouleur);
		this.add(containerReponseMan);
		this.initialisationGrilleJeu();
		this.add(containerGrilleJeu);
		this.add(containerSoluceCombiSecreteMan);
		
		this.modelMaster.addObserver(this);
		
		
		/*
		 * D�finition des listeners
		 */
		
		boutonCouleurBleu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurBleu, "0");
				colonneCombiSecrete++;
			
	
			
		}
	});
		
		boutonCouleurJaune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurJaune, "1");
				colonneCombiSecrete++;
				
			}
			
		});
		
		boutonCouleurOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurOrange, "2");
				colonneCombiSecrete++;
			}
			
		});
		
		boutonCouleurBleuFonce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurBleuFonce, "3");
				colonneCombiSecrete++;
			}
			
		});
		
		boutonCouleurRouge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurRouge, "4");
				colonneCombiSecrete++;
			}
			
		});
		
		boutonCouleurNoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurNoir, "5");
				colonneCombiSecrete++;
			}
			
		});
		
		boutonCouleurViolet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurViolet, "6");
				colonneCombiSecrete++;
			}
			
		});
		
		boutonCouleurVerte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurVerte, "7");
				colonneCombiSecrete++;
			}
			
		});
		
		boutonCouleurGris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurGris, "8");
				colonneCombiSecrete++;
			}
			
		});
		
		boutonCouleurMarron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCombiSecrete(colonneCombiSecrete, imgCouleurMarron, "9");
				colonneCombiSecrete++;
			}
			
		});
		
		validerCombiSecrete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boutonCouleurBleu.setEnabled(false);
				boutonCouleurBleuFonce.setEnabled(false);
				boutonCouleurVerte.setEnabled(false);
				boutonCouleurViolet.setEnabled(false);
				boutonCouleurRouge.setEnabled(false);
				boutonCouleurGris.setEnabled(false);
				boutonCouleurNoir.setEnabled(false);
				boutonCouleurOrange.setEnabled(false);
				boutonCouleurMarron.setEnabled(false);
				boutonCouleurJaune.setEnabled(false);
				
				validerCombiSecrete.setEnabled(false);
				effacerCombiSecrete.setEnabled(false);
				
				pionRougeSoluce.setEnabled(true);
				pionBlancSoluce.setEnabled(true);
				validerReponseMan.setEnabled(true);
				effacerReponseMan.setEnabled(true);
				
				controlMaster.setNbEssai(nbEssai);
				controlMaster.setNbCase(nbCase);
				controlMaster.setNbCouleur(nbCouleur);
				controlMaster.setModeJeu(1);
				modelMaster.setPropoSecreteManDefenseur(propoSecreteManDefenseur);	



			}
			
		});
		
		effacerCombiSecrete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				effacerCombiSecrete(colonneCombiSecrete, imgEmplacementVideSoluceCombiSecreteMan);
				colonneCombiSecrete = 0;
			}
			
		});
		
		pionRougeSoluce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichageReponseMan(imgPionRougeSoluce, 1);
			}
			
		});
		
		pionBlancSoluce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichageReponseMan(imgPionBlancSoluce, 2);
			}
			
		});
		
		validerReponseMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LOGGER.debug("Jeu en mode Defenseur - Reponse du Joueur : " + reponseManDefenseur);
				calculReponseAttendue();
			    LOGGER.debug("Jeu en mode Defenseur - Reponse attendue : " + reponseManAttendue);
				
				if( !reponseManDefenseur.equals(reponseManAttendue)) {
					
					JOptionPane.showMessageDialog(null, "Attention : Mauvaise r�ponse. R�essayez !",
							
						"Message d'avertissement", JOptionPane.WARNING_MESSAGE);
					
					effacerReponseMan();
				}
				
				else {
					
					ligne++;
					gestionFinPartie(reponseManDefenseur);
					if(!booleanfinPartie) {
						
						controlMaster.setReponseManDefenseur(reponseManDefenseur);
						remplissageSoluce = 0;
						reponseManDefenseur = "";
						  boutonCouleurBleu.setEnabled(true);
						  boutonCouleurBleuFonce.setEnabled(true);
						  boutonCouleurJaune.setEnabled(true);
						  boutonCouleurVerte.setEnabled(true);
	    	              boutonCouleurViolet.setEnabled(true);
	    	              boutonCouleurRouge.setEnabled(true);
	    	              boutonCouleurOrange.setEnabled(true);
	    	              boutonCouleurNoir.setEnabled(true);
	    	              boutonCouleurGris.setEnabled(true);
	    	              boutonCouleurMarron.setEnabled(true);
	    	              
	    	            //  boutonEffacerCombiSecreteEtPropoMan.setEnabled(true);
	    	              pionRougeSoluce.setEnabled(false);
	    	              pionBlancSoluce.setEnabled(false);
	    	              validerReponseMan.setEnabled(false);
	    	              effacerReponseMan.setEnabled(false);
			
						for( int i = 0; i < nbCase; i++) {
							tabReponseMan[i] = 3;
							reponseManDefenseur += "V";
						}
					}
					
					else
						booleanfinPartie = false;
				}

			}
			
		});
		
		effacerReponseMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				effacerReponseMan();
			}
			
		});
		
	}
	
	
	/*
	 * M�thode permettant d'initialiser la grille de jeu.
	 */
	private void initialisationGrilleJeu() {
		
		
		 
 		LOGGER.trace("Initialisation de la Grille de jeu");

   		gl=new GridLayout(this.nbEssai,this.nbCase+2);
   		containerGrilleJeu.setLayout(gl);
   		containerGrilleJeu.setPreferredSize(new Dimension(30*(this.nbCase+2),29*this.nbEssai));
   		tab2DimenGrilleJeu=new JLabel[this.nbEssai][this.nbCase+1];
   		
   		
   		if(this.nbCase == 4)
   			glSoluce=new GridLayout(this.nbCase/2,this.nbCase/2);
   		
   		else if(this.nbCase == 5)
   			glSoluce=new GridLayout(this.nbCase/2,this.nbCase/2+1);
   		
   		else
   			glSoluce=new GridLayout(this.nbCase/2-1,this.nbCase/2);
   		

   		tabSoluce= new JLabel[this.nbCase];
   		containerSoluce=new JPanel[this.nbEssai];

   		
   		/*
   		 * La grille de jeu est un JPanel organis� en GridLayout compos� d'un tableau de JLabel et d'un tableau de JPanel organis� 
   		 * �galement en GridLayout.
   		 * 
   		 */
   		for (int i = 0; i < this.nbEssai; i++) {
   			
   			for (int j = 0; j <= this.nbCase; j++) {
   				
   				if(j == 0) {
   					
   					tab2DimenGrilleJeu[i][j]=new JLabel(String.valueOf(i+1));
   					tab2DimenGrilleJeu[i][j].setOpaque(true);
   					tab2DimenGrilleJeu[i][j].setBackground(Color.LIGHT_GRAY);
   					tab2DimenGrilleJeu[i][j].setForeground(Color.YELLOW);
   					tab2DimenGrilleJeu[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
   					tab2DimenGrilleJeu[i][j].setHorizontalAlignment(JLabel.CENTER);		
   				}
   				else {
   					tab2DimenGrilleJeu[i][j]=new JLabel(imgEmplacementVide);
   				}	
   			}
   		}

   		
   		for (int i = 0; i< this.nbEssai; i++) {
   			
				containerSoluce[i]=new JPanel();
				containerSoluce[i].removeAll();
				containerSoluce[i].setPreferredSize(new Dimension(30,29));
				containerSoluce[i].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
				containerSoluce[i].setLayout(glSoluce);
   			
   			
   			for (int k = 0; k < this.nbCase; k++) {
   				
   				tabSoluce[k]=new JLabel(imgEmplacementVideSolution);
   				containerSoluce[i].add(tabSoluce[k]);
   				
   			}
   			
   			
   			containerSoluce[i].revalidate();
   			containerSoluce[i].repaint();
   		}

   		
   		//L'organisation en GridLayout impose un remplissage ligne par ligne
   		containerGrilleJeu.removeAll();
   		
 		for (int i=0;i<this.nbEssai;i++) {
 			
 			for (int j=0;j<this.nbCase+1;j++) {
   				
   				containerGrilleJeu.add(tab2DimenGrilleJeu[i][j]);
   			}
   			containerGrilleJeu.add(containerSoluce[i]);
   		}
   		
   		
   		containerGrilleJeu.revalidate();
   		containerGrilleJeu.repaint();

   	

 	}
	
	/*
	 * M�thode qui met � jour la combinaison secr�te chois par le joueur.
	 * 
	 * @param col - Colonne de la combinaison
	 * @param couleurChoisie - Couleur choisie par le joueur
	 * @param - codeCouleur - Code 	associ� � une couleur : Expml Bleu = "0"...
	 */
	private void updateCombiSecrete(int col, ImageIcon couleurChoisie, String codeCouleur) {
		if(colonneCombiSecrete < this.nbCase) {
			propoSecreteManDefenseur += codeCouleur;
			tabSoluceCombiSecreteMan[col] = new JLabel(couleurChoisie);
			tabSoluceCombiSecreteMan[col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			containerSoluceCombiSecreteMan.removeAll();
			containerSoluceCombiSecreteMan.add(Soluce);
			
			for (int i =0; i < this.nbCase; i++) {
				containerSoluceCombiSecreteMan.add(tabSoluceCombiSecreteMan[i]);
				
			}
			
			containerSoluceCombiSecreteMan.revalidate();
			containerSoluceCombiSecreteMan.repaint();
			
			if(colonneCombiSecrete ==this.nbCase -1)
				validerCombiSecrete.setEnabled(true);
		}
		
		else if(colonneCombiSecrete == this.nbCase) {
			JOptionPane.showMessageDialog(null, "La ligne est compl�te. Vous pouvez valider, ou effacer la lige", 
					
			"Message informatif",	JOptionPane.WARNING_MESSAGE);
			
			colonneCombiSecrete = this.nbCase-1;
		}
	}
		
		/*
		 * M�thode qui efface la combinaison secrete choisie par le joueur.
		 * 
		 * @param col - Colonne de la combinaison secr�te
		 * @param emplacementVide - Img de lemplacement vide
		 */
		private void effacerCombiSecrete(int col, ImageIcon emplacementVide) {
		for (int i = 0; i < col; i++) {
			tabSoluceCombiSecreteMan[i] = new JLabel(emplacementVide);
		}
		
		containerSoluceCombiSecreteMan.removeAll();
		containerSoluceCombiSecreteMan.add(Soluce);
		
		for (int i =0; i < this.nbCase; i++) {
			containerSoluceCombiSecreteMan.add(tabSoluceCombiSecreteMan[i]);
			
		}
		
		containerSoluceCombiSecreteMan.revalidate();
		containerSoluceCombiSecreteMan.repaint();
		validerCombiSecrete.setEnabled(false);
		propoSecreteManDefenseur = "";
		
		}
		
		
		
		
		/* *************************************************************************************************
		 * 
		 ********************************IMPLEMENTATION DU PATTERN OBSERVER*********************************
		 * 
		 **************************************************************************************************/
		
		
		/*
		 * Pattern Observer - M�thode non utilis� dans cette classe.
		 */
		public void quitterAppli() {}
		
		/*
		 * Pattern Observer - M�thode non utilis� dans cette classe.
		 */
		public void accueilObserver() {}
		
		/*
		 * Pattern Observer - M�thode pour MAJ la grille de jeu selon la proposition du Cpu.
		 * 
		 * @param proposiCpu - Proposition de l'ordi
		 */
		public void updateMaster(String propoCpu) {
			this.propoCpuDefenseur = propoCpu;
			/*
			 * Nous devons convertir la proposition du Cpu en JLabel avc les img correspondantes sur le grille.
			 */
			containerGrilleJeu.removeAll();
			for (int i =0; i < this.nbCase; i++) {
				switch(propoCpuDefenseur.charAt(i)) {
				
				case '0' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurBleu);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '1' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurJaune);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '2' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurBleu);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '3' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurRouge);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '4' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurVerte);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '5' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurViolet);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '6' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurGris);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '7' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurBleuFonce);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '8' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurMarron);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				case '9' :
					tab2DimenGrilleJeu[ligne][i+1] = new JLabel(imgCouleurNoir);
					tab2DimenGrilleJeu[ligne][i+1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					break;
					
				default :
					LOGGER.error("Jeu en mode D�fenseur - Erreur de correspondance entre la proposition du cpu et les couleurs");
				}
			}
			
			for (int i = 0; i < this.nbEssai; i++) {
				for (int j = 0; j < this.nbCase +1; j++) {
					containerGrilleJeu.add(tab2DimenGrilleJeu[i][j]);
				}
				
				containerGrilleJeu.add(containerSoluce[i]);
			}
			
			containerGrilleJeu.revalidate();
			containerGrilleJeu.repaint();
			
		}
		
		/*
		 * Patter Observer - M�thode pour relancer le m�me jeu : R�initialisation de l'IHM et de la grille de jeu
		 * et la combinaison secr�te en bas de page + les variables.
		 */
		public void relancerPartie() {
			
		
			LOGGER.trace("Jeu en mode D�fenseur - Partie Relanc�e");
			this.initialisationGrilleJeu();
			containerSoluceCombiSecreteMan.removeAll();
			containerSoluceCombiSecreteMan.add(Soluce);
			
			for (int i = 0; i < this.nbCase; i++) {
				tabSoluceCombiSecreteMan[i] = new JLabel();
				tabSoluceCombiSecreteMan[i].setPreferredSize(new Dimension(29,29));
				tabSoluceCombiSecreteMan[i].setIcon(imgEmplacementVideSoluceCombiSecreteMan);
				containerSoluceCombiSecreteMan.add(tabSoluceCombiSecreteMan[i]);
				
			}
			
			containerSoluceCombiSecreteMan.revalidate();
			containerSoluceCombiSecreteMan.repaint();
			boutonCouleurBleu.setEnabled(true);
			boutonCouleurJaune.setEnabled(true);
			boutonCouleurOrange.setEnabled(true);
			boutonCouleurRouge.setEnabled(true);
			boutonCouleurVerte.setEnabled(true);
			boutonCouleurViolet.setEnabled(true);
			boutonCouleurGris.setEnabled(true);
			boutonCouleurBleuFonce.setEnabled(true);
			boutonCouleurMarron.setEnabled(true);
			boutonCouleurNoir.setEnabled(true);
			
			validerCombiSecrete.setEnabled(false);
			effacerCombiSecrete.setEnabled(true);
			
			pionRougeSoluce.setEnabled(false);
			pionBlancSoluce.setEnabled(false);
			
			validerReponseMan.setEnabled(false);
			effacerReponseMan.setEnabled(false);
			
			reponseManDefenseur = "";
			
			for (int i =0; i < this.nbCase; i++) {
				tabReponseMan[i] = 3;
				reponseManDefenseur += "V";
			}
			
			ligne = 0;
			remplissageSoluce = 0;
			verifCombiSecrete = 0;
			reponseManAttendue = "";
			propoSecreteManDefenseur ="";
			propoCpuDefenseur = "";
			
		}
		
		/*
		 * Gestion de la fin de partie en fonction de la r�ponse du joueur.
		 * 
		 * @param reponse - R�ponse du joueur.
		 */
		private void gestionFinPartie(String reponse) {
			verifCombiSecrete = 0;
			for (int i =0; i < reponse.length(); i++) {
				if(reponse.charAt(i) == 'R')
					verifCombiSecrete++;
			}
			
			// D�faite
			if(verifCombiSecrete == nbCase) {
				JOptionPane.showMessageDialog(null, "Vous avez perdu. Le Cpu � trouv� la combinaison secr�te"
						
						+ "en moins de" + nbEssai+ " essais.",
						
						"Fin de partie", JOptionPane.INFORMATION_MESSAGE);	
			}
			
			// Victoire
						if(ligne == nbEssai && verifCombiSecrete != nbCase) {
							JOptionPane.showMessageDialog(null, "Vous avez gagn�! Le Cpu n'a pas trouv� la combinaison secr�te"
									
									+ "en moins de" + nbEssai+ " essais.",
									
									"Fin de partie", JOptionPane.INFORMATION_MESSAGE);	
			}
						
		   // Defaite ou victoire
		   if (ligne == nbEssai || verifCombiSecrete ==nbCase) {
			   LOGGER.trace("Jeu en mode Defenseur - Fin de Partie");
			   booleanfinPartie = true;
			   finPartie = new BoiteDialogueEndOfTheGame(null, "Fin de Partie", true);
			   controlMaster.setChoixFinPartie(finPartie.getChoixFinDePartie());
		   }				
			
		}
		
		/**
		 * M�thode qui permet d'effacer la r�ponse du joueur.
		 */
		private void effacerReponseMan() {
			containerSoluce[ligne].removeAll();
			containerSoluce[ligne].revalidate();
			
			for (int i =0; i < nbCase; i++) {
				tabSoluce[i] = new JLabel(imgEmplacementVideSolution);
				containerSoluce[ligne].add(tabSoluce[i]);
				
			}
			
			containerSoluce[ligne].repaint();
			remplissageSoluce = 0;
			reponseManDefenseur = "";
			
			for (int i =0; i < nbCase;i++) {
				tabReponseMan[i] = 3;
				reponseManDefenseur += "V";
			}
		}
		
		/**
		 * M�thode pour afficher la r�ponse du joueur.
		 * 
		 * @param couleurChoisieReponse - Couleur choisie par le joueur pour r�pondre : rouge ou blanc.
		 * @param codeCouleurReponse - Code couleur associ� � une couleur.
		 */
		private void affichageReponseMan(ImageIcon couleurChoisieReponse, int codeCouleurReponse) {
			if(remplissageSoluce < this.nbCase) {
				containerSoluce[ligne].removeAll();
				reponseManDefenseur ="";
				tabReponseMan[remplissageSoluce] = codeCouleurReponse;
				
				for (int i =0; i < remplissageSoluce; i++) {
					containerSoluce[ligne].add(tabSoluce[i]);
				}
				
				if(codeCouleurReponse ==1) {
					tabSoluce[remplissageSoluce] = new JLabel(couleurChoisieReponse);
					containerSoluce[ligne].add(tabSoluce[remplissageSoluce]);
					remplissageSoluce++;
				}
				
				else if(codeCouleurReponse ==2) {
					tabSoluce[remplissageSoluce] = new JLabel(imgPionBlancSoluce);
					containerSoluce[ligne].add(tabSoluce[remplissageSoluce]);
					remplissageSoluce++;
				}
				
				for(int i = remplissageSoluce; i < this.nbCase; i++) {
					tabSoluce[i] = new JLabel(imgEmplacementVideSolution);
					containerSoluce[ligne].add(tabSoluce[i]);
				}
				containerSoluce[ligne].revalidate();
				containerSoluce[ligne].repaint();
				
				/**
				 * On r�ordonne le tablea d'entiers dans l'ordre num�rique puis on affectue la correspondance avec les couleurs dans
				 * l'ordre suivant : - Pions rouge (si pr�sents)
				 *                   - Pions blanc (si pr�sent)
				 *                   - Emplacement vide
				 */
				Arrays.sort(tabReponseMan);
				
				for (int i = 0; i < this.nbCase; i++) {
					if(tabReponseMan[i] ==1)
						reponseManDefenseur += "R";
					
					else if(tabReponseMan[i] ==2)
						reponseManDefenseur += "B";
					
					else
						reponseManDefenseur += "V";
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Vous avez bien renseign�s tous les pions. Vous pouvez soit valider votre r�ponse, soit effacer.", "Message Informatif",
						
						JOptionPane.WARNING_MESSAGE);
			}
		}
		
		/*
		 * M�thode qui permet de d�terminer la r�ponse attendue par le joueur.
		 */
		private void calculReponseAttendue() {
			
			//Analyse des boules bien plac�es (pions rouges) et mal plac�es (pions blancs). Pour faciliter le traitement, on va dire 
			//que pions rouges �quivaut � 1, pions blancs � 2 et emplacement vide � 3.
		
		    int [] tabReponseAttendue = new int[this.nbCase];
		    char[] tabAnalyse = new char[this.nbCase];
		    
		    tabAnalyse = this.propoSecreteManDefenseur.toCharArray();
		    
		    for (int i = 0; i < this.nbCase; i++) {
		    	tabReponseAttendue[i] = 3;
		    }
		    
		    reponseManAttendue = "";
		    
		    for (int i = 0; i < this.nbCase; i++) {
		    	if(this.propoCpuDefenseur.charAt(i) == tabAnalyse[i])  {
		    		tabReponseAttendue[i] =1;
		    		tabAnalyse[i] = ' ';		    		
		    	}
		    }
		    
		    for (int i =0; i <this.nbCase; i++) {
		    	for (int j =0; j <this.nbCase; j++)
		    		if(this.propoCpuDefenseur.charAt(i)==tabAnalyse[j]&&tabReponseAttendue[i]!=1) {
						tabReponseAttendue[i]=2;
						tabAnalyse[j]=' ';
						break;
		    	
		    }
		  }
		
		
		
		//On r�ordonne le tableau d'entiers dans l'ordre num�rique puis on effectue la correspondance avec les couleurs dans l'ordre suivant :
		//pions rouges (si pr�sents), pions blancs (si pr�sents), et emplacement vide
		
		Arrays.sort(tabReponseAttendue);
		
		for (int i = 0; i < this.nbCase; i++) {
			if(tabReponseAttendue[i] == 1)
				reponseManAttendue +="R";
			
			else if(tabReponseAttendue[i]==2)
				reponseManAttendue+="B";
			
			else
				reponseManAttendue+="V";
		}
}

}


		
		


	


		





			




		
	
	





