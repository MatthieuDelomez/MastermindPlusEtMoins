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

import Mastermind.Donnees.MasterControl;
import Mastermind.Model.DonneeMaster;
import Mastermind.Observable.Observer;

    /*************************MASTERMIND******************************/
/**
 * Classe qui correspond au mode de jeu Duel. Elle implémente l'interface
 * Observer.
 * 
 * @ author Matthieu Delomez
 * @see Observer
 * 
 *******************************************************************/

public class ModeDuel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/*
	 * Parametre du jeu.
	 */
	private int nbCase, nbEssai, nbCouleur = 6;

	/*
	 * Parametre du jeu.
	 */
	private int ligne = 0,

			colonne = 1,

			colonneCombiSecrete = 0,

			remplissageSoluce = 0,

			verifCombiSecrete = 0;

	/*
	 * Tableau d'entier utilisé pour enregistrer la reponse du joueur.
	 */
	private int[] tabReponseMan;

	/*
	 * GridLayout correspondant a l'ensemble de la grille de jeu.
	 */
	private GridLayout gl;

	/*
	 * GridLayout correspondant a la derniere colonne de la grille de jeu.
	 */
	private GridLayout glSoluce;

	/*
	 * JPanel utilisé pour réaliser l'interface graphique.
	 */
	private JPanel containerGrilleJeuGauche = new JPanel(),

			containerReponseMan = new JPanel(),

			containerButonCouleur = new JPanel(),

			containerSoluceCombiSecreteMan = new JPanel(),

			containerSoluceCombiSecreteCpu = new JPanel(),

			containerPartieGauche = new JPanel(),

			containerPartieDroite = new JPanel(),

			containerGrilleJeuDroite = new JPanel();

	/*
	 * Tableau de JPanel utilisé pour l'interface.
	 */
	private JPanel[] containerSoluceGauche, containerSoluceDroite;

	/**
	 * Image utilisée pour réaliser l'interface graphique.
	 */

	private ImageIcon imgEmplacementVide = new ImageIcon("images/MastermindEmplacementVide.png"),

			imgEmplacementVideSolution = new ImageIcon("images/EmplacementVideSolution.png"),

			imgCouleurVerte = new ImageIcon("images/CouleurVerte.png"),
			imgCouleurBleu = new ImageIcon("images/CouleurBleu.png"),

			imgCouleurOrange = new ImageIcon("images/CouleurOrange.png"),
			imgCouleurRouge = new ImageIcon("images/CouleurRouge.png"),

			imgCouleurJaune = new ImageIcon("images/CouleurJaune.png"),
			imgCouleurViolet = new ImageIcon("images/CouleurViolet.png"),

			imgPionRougeSolution = new ImageIcon("images/PionRougeSolution.png"),

			imgPionBlancSolution = new ImageIcon("images/PionBlancSolution.png"),

			imgEmplacementVideSolutionCombiSecreteMan = new ImageIcon(
					"images/EmplacementVideSolutionCombiSecreteCpu.png"),

			imgCouleurGris = new ImageIcon("images/CouleurGris.png"),
			imgCouleurBleuFonce = new ImageIcon("images/CouleurBleuFonce.png"),

			imgCouleurNoir = new ImageIcon("images/CouleurNoir.png"),
			imgCouleurMarron = new ImageIcon("images/CouleurMarron.png"),

			imgEmplacementVideSolutionCombiSecreteCpu = new ImageIcon(
					"images/MastermindEmplacementVideSolutionCombiSecreteCpu.png");

	/**
	 * Tableau de JLabel à deux dimension.
	 */
	private JLabel[][] tab2DimLabelGrilleJeuGauche,

			tab2DimLabelGrilleJeuDroite;

	/**
	 * Tableau à une dimension.
	 */
	private JLabel[] tabLabelSoluceGauche, tabLabelSoluceCombiSecreteMan,

			tabLabelSoluceCombiSecreteCpu, tabLabelSoluceDroite;

	/*
	 * JLabel de type informatif.
	 */
	private JLabel labelpremiereInstruction = new JLabel("La combinaison secrète à été générée par le Cpu."),

			labeldeuxiemeInstruction = new JLabel("Veuillez choisir une combinaison secrète."),

			labelreponseMan = new JLabel("Votre réponse :"),

			labelcombiSecreteMan = new JLabel("Combinaison secrète du joueur :"),

			labelcombiSecreteCpu = new JLabel("Combinaison Secrete de l'ordinateur :");

	/*
	 * JButton utilisé pour l'interface.
	 */
	private JButton bValiderCombiSecreteEtPropoMan = new JButton("Valider"),

			bEffacerCombiSecreteEtPropoMan = new JButton("Effacer"),

			bCouleurVert = new JButton(imgCouleurVerte),

			bCouleurMarron = new JButton(imgCouleurMarron),

			bCouleurBleu = new JButton(imgCouleurBleu),

			bCouleurBleuFonce = new JButton(imgCouleurBleuFonce),

			bCouleurRouge = new JButton(imgCouleurRouge),

			bCouleurOrange = new JButton(imgCouleurOrange),

			bCouleurViolet = new JButton(imgCouleurViolet),

			bCouleurGris = new JButton(imgCouleurGris),

			bCouleurNoir = new JButton(imgCouleurNoir),

			bCouleurJaune = new JButton(imgCouleurJaune),

			bEffacerReponseMan = new JButton("Effacer"),

			bValiderReponseMan = new JButton("Valider"),

			bPionBlancSolution = new JButton(imgPionBlancSolution),

			bPionRougeSolution = new JButton(imgPionBlancSolution);

	/*
	 * Police d'écriture : nom de la police, style et taille.
	 */
	private Font police = new Font("Arial", Font.PLAIN, 14),

			policeSolution = new Font("Arial", Font.BOLD, 14);

	/**
	 * Combinaison secrète du joueur en mode duel.
	 */

	private String propoSecreteManDuel = "";

	/**
	 * Proposition de l'ordinateur en mode duel.
	 */

	private String propoCpuDuel = "";

	/**
	 * Réponse du joueur en mode duel.
	 */

	private String reponseManDuel = "";

	/**
	 * Réponse attendue par le joueur.
	 */

	private String reponseAttendue = "";

	/**
	 * Combinaison secrète générée par l'ordinateur.
	 */

	private String combiSecreteCpu = "";

	/**
	 * Proposition du joueur en mode duel.
	 */

	private String propoManDuel = "";

	/*
	 * Controler relatif au jeu.
	 * 
	 * @see MasterControl.
	 */
	private MasterControl controlMaster;

	/*
	 * Controler relatif au jeu.
	 * 
	 * @see DonneeMaster.
	 */
	private DonneeMaster modelMaster;

	/*
	 * Boite de dialogue permettant d'effectuer un choix en fin de partie.
	 * 
	 * @see BoiteDialogueEndOfTheGame
	 */
	private BoiteDialogueEndOfTheGame jdFinPartie;

	/*
	 * Boolean qui indique la fin de la partie.
	 */
	private boolean boolFinPartie = false;

	/*
	 * Boolean qui indique si la combinaison secrete du joueur à été validé.
	 */
	private boolean combiSecreteValide = false;

	/*
	 * Boolean qui permet de selectionner la grille de jeu à MAJ.
	 */
	private boolean MajAffichageDuel = false;

	/*
	 * Boolean pour le mode développeur
	 */
	private boolean modeDeveloppeurActive;

	/*
	 * Gestion des logs d'erreurs.
	 */
	private static final Logger LOGGER = LogManager.getLogger();


	/**
	 * Constructeur de la classe MastermindModeChallenger.
	 * 
	 * @param nbCase Nombre de cases du jeu.
	 * @param nbEssai Nombre d'essais du jeu.
	 * @param nbCouleur Nombre de couleurs utilisables du jeu.
	 * @param modeDeveloppeurActive Paramètre de type booléen indiquant 
	 * si le mode développeur est activé ou non.
	 * @param modelMaster Modèle de données correspondant au jeu.
	 * @see DonneeMaster
	 */
	public ModeDuel(int nbCase, int nbCouleur, int nbEssai, boolean modeDeveloppeurActive, DonneeMaster modelMaster) {

		LOGGER.trace("Instanciation du jeu Mastermind en mode Duel");
		this.setPreferredSize(new Dimension(1000, 740));
		this.setBackground(Color.WHITE);
		this.nbCase = nbCase;
		this.nbCouleur = nbCouleur;
		this.nbEssai = nbEssai;
		this.modeDeveloppeurActive = modeDeveloppeurActive;
		this.modelMaster = modelMaster;
		this.controlMaster = new MasterControl(this.modelMaster);
		this.generationCombiSecreteCpu();

		/*
		 * Tableau pour enregistrer la réponse du joueur.
		 */
		tabReponseMan = new int[this.nbCase];
		for (int i = 0; i < this.nbCase; i++) {
			tabReponseMan[i] = 3;
			reponseManDuel += "V";
		}

		/*
		 * Interface graphique.
		 */
		labelpremiereInstruction.setPreferredSize(new Dimension(1000, 30));
		labelpremiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		labelpremiereInstruction.setFont(police);

		labeldeuxiemeInstruction.setPreferredSize(new Dimension(1000, 30));
		labeldeuxiemeInstruction.setHorizontalAlignment(JLabel.CENTER);
		labeldeuxiemeInstruction.setFont(police);

		bCouleurBleu.setPreferredSize(new Dimension(29, 29));
		bCouleurBleuFonce.setPreferredSize(new Dimension(29, 29));
		bCouleurVert.setPreferredSize(new Dimension(29, 29));
		bCouleurJaune.setPreferredSize(new Dimension(29, 29));
		bCouleurGris.setPreferredSize(new Dimension(29, 29));
		bCouleurMarron.setPreferredSize(new Dimension(29, 29));
		bCouleurNoir.setPreferredSize(new Dimension(29, 29));
		bCouleurOrange.setPreferredSize(new Dimension(29, 29));
		bCouleurViolet.setPreferredSize(new Dimension(29, 29));
		bCouleurRouge.setPreferredSize(new Dimension(29, 29));

		bValiderCombiSecreteEtPropoMan.setEnabled(false);
		containerButonCouleur.setPreferredSize(new Dimension(1000, 40));
		containerButonCouleur.setBackground(Color.PINK);

		switch (this.nbCouleur) {

		case 4:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			break;

		case 5:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVert);
			break;

		case 6:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVert);
			containerButonCouleur.add(bCouleurViolet);

			break;

		case 7:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVert);
			containerButonCouleur.add(bCouleurViolet);
			containerButonCouleur.add(bCouleurGris);
			break;

		case 8:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVert);
			containerButonCouleur.add(bCouleurViolet);
			containerButonCouleur.add(bCouleurGris);
			containerButonCouleur.add(bCouleurBleuFonce);
			break;

		case 9:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVert);
			containerButonCouleur.add(bCouleurViolet);
			containerButonCouleur.add(bCouleurGris);
			containerButonCouleur.add(bCouleurBleuFonce);
			containerButonCouleur.add(bCouleurMarron);
			break;

		case 10:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVert);
			containerButonCouleur.add(bCouleurViolet);
			containerButonCouleur.add(bCouleurGris);
			containerButonCouleur.add(bCouleurBleuFonce);
			containerButonCouleur.add(bCouleurMarron);
			containerButonCouleur.add(bCouleurNoir);
			break;

		default:
			LOGGER.error("Jeu en mode Duel - Erreur l'ors de la mise en place de l'Ihmn pour les boutons");
		}

		containerButonCouleur.add(bValiderCombiSecreteEtPropoMan);
		containerButonCouleur.add(bEffacerCombiSecreteEtPropoMan);

		labelreponseMan.setHorizontalAlignment(JLabel.CENTER);
		labelreponseMan.setFont(police);
		bPionRougeSolution.setPreferredSize(new Dimension(14, 14));
		bPionRougeSolution.setEnabled(false);
		bPionBlancSolution.setPreferredSize(new Dimension(14, 14));
		bPionBlancSolution.setEnabled(false);
		bValiderReponseMan.setEnabled(false);
		bEffacerReponseMan.setEnabled(false);
		containerReponseMan.setPreferredSize(new Dimension(1000, 40));
		containerReponseMan.setBackground(Color.WHITE);
		containerReponseMan.add(labelreponseMan);
		containerReponseMan.add(bPionRougeSolution);
		containerReponseMan.add(bPionBlancSolution);
		containerReponseMan.add(bValiderReponseMan);
		containerReponseMan.add(bEffacerReponseMan);

		/*
		 * Mise en place de l'IHM pour la combinaison secrète du Cpu.
		 */
		labelcombiSecreteCpu.setFont(policeSolution);
		containerSoluceCombiSecreteCpu.setPreferredSize(new Dimension(470, 40));
		containerSoluceCombiSecreteCpu.setBackground(Color.ORANGE);
		containerSoluceCombiSecreteCpu.add(labelcombiSecreteCpu);

		tabLabelSoluceCombiSecreteCpu = new JLabel[this.nbCase];
		if (this.modeDeveloppeurActive == false) {
			for (int i = 0; i < this.nbCase; i++) {
				tabLabelSoluceCombiSecreteCpu[i] = new JLabel();
				tabLabelSoluceCombiSecreteCpu[i].setPreferredSize(new Dimension(29, 29));
				tabLabelSoluceCombiSecreteCpu[i].setIcon(imgEmplacementVideSolutionCombiSecreteCpu);
				containerSoluceCombiSecreteCpu.add(tabLabelSoluceCombiSecreteCpu[i]);

			}

		}

		else {
			this.affichageSoluce();
		}

		/*
		 * Mise en place de l'Ihm pour la combinaison secrete du joueur.
		 */
		labelcombiSecreteMan.setFont(policeSolution);
		containerSoluceCombiSecreteMan.setPreferredSize(new Dimension(470, 40));
		containerSoluceCombiSecreteMan.setBackground(Color.GRAY);
		containerSoluceCombiSecreteMan.add(labelcombiSecreteMan);
		tabLabelSoluceCombiSecreteMan = new JLabel[this.nbCase];
		for (int i = 0; i < this.nbCase; i++) {
			tabLabelSoluceCombiSecreteMan[i] = new JLabel();
			tabLabelSoluceCombiSecreteMan[i].setPreferredSize(new Dimension(29, 29));
			tabLabelSoluceCombiSecreteMan[i].setIcon(imgEmplacementVideSolutionCombiSecreteMan);
			containerSoluceCombiSecreteMan.add(tabLabelSoluceCombiSecreteMan[i]);
		}

		this.initialisationGrilleJeuDroite();
		this.initialisationGrilleJeuGauche();

		/*
		 * Ajustement de la taille des JPanel principaux en fonction de la grille.
		 */
		if (this.nbEssai == 5) {
			containerPartieGauche.setPreferredSize(new Dimension(480, 220));
			containerPartieDroite.setPreferredSize(new Dimension(480, 220));

		}

		else if (this.nbEssai == 10) {
			containerPartieGauche.setPreferredSize(new Dimension(480, 370));
			containerPartieDroite.setPreferredSize(new Dimension(480, 370));

		}

		else {
			containerPartieGauche.setPreferredSize(new Dimension(480, 510));
			containerPartieDroite.setPreferredSize(new Dimension(480, 510));

		}

		/*
		 * JPanel de gauche prendra en compte les propositions du joueur et les réponses
		 * du Cpu ainsi que la combinaison secrete du Cpu.
		 */
		containerPartieGauche.setBackground(Color.WHITE);
		containerPartieGauche.setBorder(BorderFactory.createTitledBorder("Propositions Joueur"));
		containerPartieGauche.add(containerGrilleJeuGauche);
		containerPartieGauche.add(containerSoluceCombiSecreteCpu);

		/*
		 * JPanel de droite prendra en compte les propositions du Cpu et les réponses du
		 * joueur ainsi que la combinaison secrete du joueur.
		 */
		containerPartieDroite.setBackground(Color.WHITE);
		containerPartieDroite.setBorder(BorderFactory.createTitledBorder("Propositions Cpu"));
		containerPartieDroite.add(containerGrilleJeuDroite);
		containerPartieDroite.add(containerSoluceCombiSecreteMan);

		this.add(labelpremiereInstruction);
		this.add(labeldeuxiemeInstruction);
		this.add(containerButonCouleur);
		this.add(containerReponseMan);
		this.add(containerPartieGauche);
		this.add(containerPartieDroite);

		this.modelMaster.addObserver(this);

		/*
		 * Définition des Listeners.
		 */
		bCouleurBleu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurBleu, "0");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurBleu, "0");
					colonne++;

				}
			}
		});

		bCouleurJaune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurJaune, "1");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurJaune, "1");
					colonne++;

				}
			}
		});

		bCouleurOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurOrange, "2");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurOrange, "2");
					colonne++;

				}
			}
		});

		bCouleurRouge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurRouge, "3");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurRouge, "3");
					colonne++;

				}
			}
		});

		bCouleurVert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurVerte, "4");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurVerte, "4");
					colonne++;

				}
			}
		});

		bCouleurViolet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurViolet, "5");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurViolet, "5");
					colonne++;

				}
			}
		});

		bCouleurGris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurGris, "6");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurGris, "6");
					colonne++;

				}
			}
		});

		bCouleurBleuFonce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurBleuFonce, "7");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurBleuFonce, "7");
					colonne++;

				}
			}
		});

		bCouleurMarron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurMarron, "8");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurMarron, "8");
					colonne++;

				}
			}
		});

		bCouleurNoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					updateCombiSecrete(colonneCombiSecrete, imgCouleurNoir, "9");
					colonneCombiSecrete++;
				} else {
					affichagePropoMan(ligne, colonne, imgCouleurNoir, "9");
					colonne++;

				}
			}
		});

		bValiderCombiSecreteEtPropoMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					bValiderCombiSecreteEtPropoMan.setEnabled(false);
					combiSecreteValide = true;
					JOptionPane.showMessageDialog(null,
							"C'est à vous de commencer en effectuant une première proposition!", "Proposition Joueur",
							JOptionPane.INFORMATION_MESSAGE);
					modelMaster.setPropoSecreteManDuel(propoSecreteManDuel);
				} else {
					bValiderCombiSecreteEtPropoMan.setEnabled(false);
					bValiderCombiSecreteEtPropoMan.setEnabled(false);
					bCouleurBleu.setEnabled(false);
					bCouleurJaune.setEnabled(false);
					bCouleurOrange.setEnabled(false);
					bCouleurRouge.setEnabled(false);
					bCouleurVert.setEnabled(false);
					bCouleurViolet.setEnabled(false);
					bCouleurGris.setEnabled(false);
					bCouleurBleuFonce.setEnabled(false);
					bCouleurMarron.setEnabled(false);
					bCouleurNoir.setEnabled(false);
					bPionRougeSolution.setEnabled(true);
					bPionBlancSolution.setEnabled(true);
					bValiderReponseMan.setEnabled(true);
					bEffacerReponseMan.setEnabled(true);
					colonne = 1;
					controlMaster.setPropoManDuel(propoManDuel);
					propoManDuel = "";
					if (boolFinPartie) {
						boolFinPartie = false;
					}
				}
			}
		});

		bEffacerCombiSecreteEtPropoMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!combiSecreteValide) {
					effacerCombiSecrete(colonneCombiSecrete, imgEmplacementVideSolutionCombiSecreteMan);
				}

				else {
					effacerPropoMan(ligne, colonne, imgEmplacementVide);
					colonne = 1;

				}

			}

		});

		bPionRougeSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichageReponseMan(imgPionRougeSolution, 1);
			}
		});

		bPionBlancSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichageReponseMan(imgPionBlancSolution, 2);
			}
		});

		bValiderReponseMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOGGER.debug("Jeu en mode duel - réponse du joueur en mode duel"

						+ reponseManDuel);

				calculReponseAttendue();

				LOGGER.debug("Jeu en mode duel - reponse attendue :" + reponseAttendue);
				if (!reponseManDuel.equals(reponseAttendue)) {
					JOptionPane.showMessageDialog(null, "Votre réponse est foireuse. Retente ta chance",

							"Message pour votre vie",

							JOptionPane.WARNING_MESSAGE);
					effacerReponseMan();
				}

				else {

					ligne++;
					gestionFinPartie(reponseManDuel, 'J');
					if (!boolFinPartie) {
						controlMaster.setReponseManDuel(reponseManDuel);
						remplissageSoluce = 0;
						reponseManDuel = "";
						bCouleurBleu.setEnabled(true);
						bCouleurBleuFonce.setEnabled(true);
						bCouleurJaune.setEnabled(true);
						bCouleurVert.setEnabled(true);
						bCouleurViolet.setEnabled(true);
						bCouleurRouge.setEnabled(true);
						bCouleurOrange.setEnabled(true);
						bCouleurNoir.setEnabled(true);
						bCouleurGris.setEnabled(true);
						bCouleurMarron.setEnabled(true);

						bEffacerCombiSecreteEtPropoMan.setEnabled(true);
						bPionRougeSolution.setEnabled(false);
						bPionBlancSolution.setEnabled(false);
						bValiderReponseMan.setEnabled(false);
						bEffacerReponseMan.setEnabled(false);

						for (int i = 0; i < nbCase; i++) {
							tabReponseMan[i] = 3;
							reponseManDuel += "V";
						}

					} else

						boolFinPartie = false;
				}

			}

		});

		bEffacerReponseMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				effacerReponseMan();
			}

		});

	}

	/*
	 * Méthode pour initialiser la grille de gauche.
	 */
	private void initialisationGrilleJeuGauche() {
		gl = new GridLayout(this.nbEssai, this.nbCase + 2);
		containerGrilleJeuGauche.setLayout(gl);
		containerGrilleJeuGauche.setPreferredSize(new Dimension(30 * (this.nbCase + 2),

				29 * this.nbEssai));

		containerGrilleJeuGauche.setBackground(Color.WHITE);
		tab2DimLabelGrilleJeuGauche = new JLabel[this.nbEssai][this.nbCase + 1];

		if (this.nbCase == 4)
			glSoluce = new GridLayout(this.nbCase / 2, this.nbCase / 2);

		else if (this.nbCase == 5)
			glSoluce = new GridLayout(this.nbCase / 2, this.nbCase / 2 + 1);

		else
			glSoluce = new GridLayout(this.nbCase / 2 - 1, this.nbCase / 2);

		tabLabelSoluceGauche = new JLabel[this.nbCase];
		containerSoluceGauche = new JPanel[this.nbEssai];

		/*
		 * La grille est organisé en GridLayout composé d'un tableau de JLabel et
		 * JPanel.
		 */
		for (int i = 0; i < this.nbEssai; i++) {
			for (int j = 0; j <= this.nbCase; j++) {
				if (j == 0) {
					tab2DimLabelGrilleJeuGauche[i][j] = new JLabel(String.valueOf(i + 1));
					tab2DimLabelGrilleJeuGauche[i][j].setOpaque(true);
					tab2DimLabelGrilleJeuGauche[i][j].setBackground(Color.LIGHT_GRAY);
					tab2DimLabelGrilleJeuGauche[i][j].setForeground(Color.BLACK);
					tab2DimLabelGrilleJeuGauche[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					tab2DimLabelGrilleJeuGauche[i][j].setHorizontalAlignment(JLabel.CENTER);
				} else {
					tab2DimLabelGrilleJeuGauche[i][j] = new JLabel(imgEmplacementVide);
				}
			}
		}

		for (int i = 0; i < this.nbEssai; i++) {
			containerSoluceGauche[i] = new JPanel();
			containerSoluceGauche[i].removeAll();
			containerSoluceGauche[i].setPreferredSize(new Dimension(30, 29));
			containerSoluceGauche[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			containerSoluceGauche[i].setLayout(glSoluce);
			for (int k = 0; k < this.nbCase; k++) {
				tabLabelSoluceGauche[k] = new JLabel(imgEmplacementVideSolution);
				containerSoluceGauche[i].add(tabLabelSoluceGauche[k]);
			}

			containerSoluceGauche[i].revalidate();
			containerSoluceGauche[i].repaint();
		}

		/*
		 * Remplissage ligne par ligne du GridLayout
		 */
		containerGrilleJeuGauche.removeAll();
		for (int i = 0; i < this.nbEssai; i++) {
			for (int j = 0; j < this.nbCase + 1; j++) {
				containerGrilleJeuGauche.add(tab2DimLabelGrilleJeuGauche[i][j]);

			}

			containerGrilleJeuGauche.add(containerSoluceGauche[i]);

		}

		containerGrilleJeuGauche.revalidate();
		containerGrilleJeuGauche.repaint();

	}

	/*
	 * Méthode pour initialiser la grille de gauche.
	 */
	private void initialisationGrilleJeuDroite() {
		gl = new GridLayout(this.nbEssai, this.nbCase + 2);
		containerGrilleJeuDroite.setLayout(gl);
		containerGrilleJeuDroite.setPreferredSize(new Dimension(30 * (this.nbCase + 2),

				29 * this.nbEssai));

		containerGrilleJeuDroite.setBackground(Color.WHITE);
		tab2DimLabelGrilleJeuDroite = new JLabel[this.nbEssai][this.nbCase + 1];

		if (this.nbCase == 4)
			glSoluce = new GridLayout(this.nbCase / 2, this.nbCase / 2);

		else if (this.nbCase == 5)
			glSoluce = new GridLayout(this.nbCase / 2, this.nbCase / 2 + 1);

		else
			glSoluce = new GridLayout(this.nbCase / 2 - 1, this.nbCase / 2);

		tabLabelSoluceDroite = new JLabel[this.nbCase];
		containerSoluceDroite = new JPanel[this.nbEssai];

		/*
		 * La grille est organisé en GridLayout composé d'un tableau de JLabel et
		 * JPanel.
		 */
		for (int i = 0; i < this.nbEssai; i++) {
			for (int j = 0; j <= this.nbCase; j++) {
				if (j == 0) {
					tab2DimLabelGrilleJeuDroite[i][j] = new JLabel(String.valueOf(i + 1));
					tab2DimLabelGrilleJeuDroite[i][j].setOpaque(true);
					tab2DimLabelGrilleJeuDroite[i][j].setBackground(Color.LIGHT_GRAY);
					tab2DimLabelGrilleJeuDroite[i][j].setForeground(Color.BLACK);
					tab2DimLabelGrilleJeuDroite[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					tab2DimLabelGrilleJeuDroite[i][j].setHorizontalAlignment(JLabel.CENTER);
				} else {
					tab2DimLabelGrilleJeuDroite[i][j] = new JLabel(imgEmplacementVide);
				}
			}
		}

		for (int i = 0; i < this.nbEssai; i++) {
			containerSoluceDroite[i] = new JPanel();
			containerSoluceDroite[i].removeAll();
			containerSoluceDroite[i].setPreferredSize(new Dimension(30, 29));
			containerSoluceDroite[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			containerSoluceDroite[i].setLayout(glSoluce);
			for (int k = 0; k < this.nbCase; k++) {
				tabLabelSoluceDroite[k] = new JLabel(imgEmplacementVideSolution);
				containerSoluceDroite[i].add(tabLabelSoluceDroite[k]);
			}

			containerSoluceDroite[i].revalidate();
			containerSoluceDroite[i].repaint();
		}

		/*
		 * Remplissage ligne par ligne du GridLayout
		 */
		containerGrilleJeuDroite.removeAll();
		for (int i = 0; i < this.nbEssai; i++) {
			for (int j = 0; j < this.nbCase + 1; j++) {
				containerGrilleJeuDroite.add(tab2DimLabelGrilleJeuDroite[i][j]);

			}

			containerGrilleJeuDroite.add(containerSoluceDroite[i]);

		}

		containerGrilleJeuDroite.revalidate();
		containerGrilleJeuDroite.repaint();

	}

	/**
	 * Gestion de la combinaison Secrete du Cpu. C'est une combinaison de chiffres
	 * castés en String, chaque chiffre = couleur.
	 */
	private void generationCombiSecreteCpu() {

		int nbreAleatoire;
		for (int i = 0; i < this.nbCase; i++) {

			nbreAleatoire = (int) (Math.random() * nbCouleur);
			combiSecreteCpu += String.valueOf(nbreAleatoire);
		}

		LOGGER.debug("Jeu Mastermind en mode Duel - Génération de la combinaison secrète:" + combiSecreteCpu);
		controlMaster.setModeJeu(2);
		controlMaster.setNbEssai(this.nbEssai);
		controlMaster.setNbCase(this.nbCase);
		controlMaster.setNbCouleur(this.nbCouleur);
		controlMaster.setPropoSecreteCpuDuel(combiSecreteCpu);
	}

	/*
	 * Méthode pour MAJ la comninaison secrete choisie par le joueur.
	 * 
	 * @param col - Colonne de la combinaison secrete
	 * @param couleurChoisie - Couleur choisie par le joueur
	 * @param codeCouleur - Nombre egal à une couleur
	 */
	private void updateCombiSecrete(int col, ImageIcon couleurChoisie, String codeCouleur) {

		if (colonneCombiSecrete < this.nbCase) {
			propoSecreteManDuel += codeCouleur;
			tabLabelSoluceCombiSecreteMan[col] = new JLabel(couleurChoisie);
			tabLabelSoluceCombiSecreteMan[col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			containerSoluceCombiSecreteMan.removeAll();
			containerSoluceCombiSecreteMan.add(labelcombiSecreteMan);

			for (int i = 0; i < this.nbCase; i++) {
				containerSoluceCombiSecreteMan.add(tabLabelSoluceCombiSecreteMan[i]);
			}
			containerSoluceCombiSecreteMan.revalidate();
			containerSoluceCombiSecreteMan.repaint();
			if (colonneCombiSecrete == this.nbCase - 1)
				bValiderCombiSecreteEtPropoMan.setEnabled(true);
		}

		else if (colonneCombiSecrete == this.nbCase) {
			JOptionPane.showMessageDialog(null,
					"La ligne est complète. Vous pouvez soit valider, soit effacer la ligne",

					"Message Informatif",

					JOptionPane.WARNING_MESSAGE);
		}

	}

	/*
	 * Méthode pour effacer la combinaison secrète choisie par le joueur.
	 * 
	 * @param col - Colonne de la combinaison secrète.
	 * @param emplacementVide - Image de l'emplacement vide.
	 */
	private void effacerCombiSecrete(int col, ImageIcon emplacementVide) {
		for (int i = 0; i < col; i++) {
			tabLabelSoluceCombiSecreteMan[i] = new JLabel(emplacementVide);
		}
		containerSoluceCombiSecreteMan.removeAll();
		containerSoluceCombiSecreteMan.add(labelcombiSecreteMan);
		for (int i = 0; i < this.nbCase; i++) {
			containerSoluceCombiSecreteMan.add(tabLabelSoluceCombiSecreteMan[i]);
		}
		containerSoluceCombiSecreteMan.revalidate();
		containerSoluceCombiSecreteMan.repaint();
		bValiderCombiSecreteEtPropoMan.setEnabled(false);
		propoSecreteManDuel = "";
	}

	/*
	 * Méthode permettant de MAJ la grille de jeu de gauche suite aux propositions
	 * de joueur.
	 * 
	 * @param lig - Ligne de la grille de jeu de gauche.
	 * @param col - Colonne de la grille de gauche.
	 * @param couleurChoisie - Couleur choisie par le joueur.
	 * @param codeCouleur - Nombre egal à une couleur.
	 */
	private void affichagePropoMan(int lig, int col, ImageIcon couleurChoisie, String codeCouleur) {
		if (colonne <= this.nbCase) {
			tab2DimLabelGrilleJeuGauche[lig][col] = new JLabel(couleurChoisie);
			propoManDuel += codeCouleur;
			tab2DimLabelGrilleJeuGauche[lig][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			// L'organisation en GridLayout impose un remplissage ligne par ligne

			containerGrilleJeuGauche.removeAll();
			for (int i = 0; i < this.nbEssai; i++) {
				for (int j = 0; j < this.nbCase + 1; j++) {
					containerGrilleJeuGauche.add(tab2DimLabelGrilleJeuGauche[i][j]);
				}
				containerGrilleJeuGauche.add(containerSoluceGauche[i]);
			}
			containerGrilleJeuGauche.revalidate();
			containerGrilleJeuGauche.repaint();
			if (colonne == this.nbCase) {
				bValiderCombiSecreteEtPropoMan.setEnabled(true);
			}
		}

		if (colonne > this.nbCase) {
			JOptionPane.showMessageDialog(null,
					"La ligne est complète. Vous pouvez soit valider, soit effacer la ligne", "Message Informatif",
					JOptionPane.WARNING_MESSAGE);
			colonne = this.nbCase;
		}
	}

	/*
	 * Méthode pour effacer une ligne de la grille du jeu de gauche.
	 * 
	 * @param lig - Ligne de la grille gauche.
	 * @param col - Colonne de la grille de gauche.
	 * @param emplacementVide - Image d'un emplacement vide.
	 */
	private void effacerPropoMan(int lig, int col, ImageIcon emplacementVide) {
		for (int i = 1; i < col; i++) {
			tab2DimLabelGrilleJeuGauche[i][lig] = new JLabel(emplacementVide);
		}

		propoManDuel = "";

		// Remplissage du GridLayout ligne/Ligne.
		containerGrilleJeuGauche.removeAll();
		for (int i = 0; i < this.nbEssai; i++) {
			for (int j = 0; j < this.nbCase + 1; j++) {
				containerGrilleJeuGauche.add(tab2DimLabelGrilleJeuGauche[i][j]);

			}

			containerGrilleJeuGauche.add(containerSoluceGauche[i]);
		}
		containerGrilleJeuGauche.revalidate();
		containerGrilleJeuGauche.repaint();
		bValiderCombiSecreteEtPropoMan.setEnabled(false);
	}

	/*
	 * Méthode pour afficher la réponse du joueur sur la grille de droite.
	 * 
	 * @param couleurChoisieReponse - Couleur choisie par le joueur (Blanc/Rouge).
	 * @param codeCouleurReponse - Couleur associé à un chiffre.
	 */
	private void affichageReponseMan(ImageIcon couleurChoisieReponse, int codeCouleurReponse) {
		if (remplissageSoluce < this.nbCase) {
			containerSoluceDroite[ligne].removeAll();
			reponseManDuel = "";
			tabReponseMan[remplissageSoluce] = codeCouleurReponse;

			for (int i = 0; i < remplissageSoluce; i++) {
				containerSoluceDroite[ligne].add(tabLabelSoluceDroite[i]);
			}

			if (codeCouleurReponse == 1) {
				tabLabelSoluceDroite[remplissageSoluce] = new JLabel(couleurChoisieReponse);
				containerSoluceDroite[ligne].add(tabLabelSoluceDroite[remplissageSoluce]);
				remplissageSoluce++;

			}

			else if (codeCouleurReponse == 2) {
				tabLabelSoluceDroite[remplissageSoluce] = new JLabel(imgPionBlancSolution);
				containerSoluceDroite[ligne].add(tabLabelSoluceDroite[remplissageSoluce]);
				remplissageSoluce++;

			}

			for (int i = remplissageSoluce; i < this.nbCase; i++) {
				tabLabelSoluceDroite[remplissageSoluce] = new JLabel(imgEmplacementVideSolution);
				containerSoluceDroite[ligne].add(tabLabelSoluceDroite[i]);
			}

			containerSoluceDroite[ligne].revalidate();
			containerSoluceDroite[ligne].repaint();

			/*
			 * On réordonne le tableau d'entiers dans l'ordre numérique puis on effectue la
			 * correspondance avec les couleurs dans l'ordre suivant : pions rouges (si
			 * présents), pions blancs (si présents), et emplacement vide
			 */
			Arrays.sort(tabReponseMan);

			for (int i = 0; i < this.nbCase; i++) {
				if (tabReponseMan[i] == 1)
					reponseManDuel += 'R';

				else if (tabReponseMan[i] == 2)
					reponseManDuel += 'B';

				else
					reponseManDuel += 'R';
			}

		} else {
			JOptionPane.showMessageDialog(null,
					"Vous avez bien renseignés tous les pions. Vous pouvez soit valider votre réponse, soit effacer.",
					"Message Informatif",

					JOptionPane.WARNING_MESSAGE);
		}

	}

	/*
	 * Méthode pour effacer la réponse du joueur sur la grille de droite.
	 */
	private void effacerReponseMan() {
		containerSoluceDroite[ligne].removeAll();
		containerSoluceDroite[ligne].revalidate();
		for (int i = 0; i < nbCase; i++) {
			tabLabelSoluceDroite[i] = new JLabel(imgEmplacementVideSolution);
			containerSoluceDroite[ligne].add(tabLabelSoluceDroite[i]);
		}

		containerSoluceDroite[ligne].repaint();
		remplissageSoluce = 0;
		reponseManDuel = "";
		for (int i = 0; i < nbCase; i++) {
			tabReponseMan[i] = 3;
			reponseManDuel += "V";
		}
	}

	/*
	 * Méthode pour déterminer la réponse attendue par le joueur.
	 */
	private void calculReponseAttendue() {

		int[] tabReponseAttendue = new int[this.nbCase];
		char[] tabAnalyse = new char[this.nbCase];

		tabAnalyse = this.propoSecreteManDuel.toCharArray();
		for (int i = 0; i < this.nbCase; i++) {
			tabReponseAttendue[i] = 3;

		}
		reponseAttendue = "";

		for (int i = 0; i < this.nbCase; i++) {
			if (this.propoCpuDuel.charAt(i) == tabAnalyse[i]) {
				tabReponseAttendue[i] = 1;
				tabAnalyse[i] = ' ';

			}
		}

		for (int i = 0; i < this.nbCase; i++) {
			for (int j = 0; j < this.nbCase; j++) {
				if (this.propoCpuDuel.charAt(i) == tabAnalyse[j] && tabReponseAttendue[i] != 1) {
					tabReponseAttendue[i] = 2;
					tabAnalyse[j] = ' ';
					break;
				}
			}
		}

		Arrays.sort(tabReponseAttendue);

		for (int i = 0; i < this.nbCase; i++) {
			if (tabReponseAttendue[i] == 1)
				reponseAttendue += "R";

			else if (tabReponseAttendue[i] == 2)
				reponseAttendue += "B";

			else
				reponseAttendue += "V";
		}
	}

	/*
	 * Méthode pour afficher la combinaison secrète générée par le Cpu.
	 */
	private void affichageSoluce() {
		containerSoluceCombiSecreteCpu.removeAll();
		containerSoluceCombiSecreteCpu.add(labelcombiSecreteCpu);
		for (int i = 0; i < this.nbCase; i++) {
			switch (combiSecreteCpu.charAt(i)) {

			case '0':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurBleu);

				break;

			case '1':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurJaune);

				break;

			case '2':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurOrange);

				break;

			case '3':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurRouge);

				break;

			case '4':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurVerte);

				break;

			case '5':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurViolet);

				break;

			case '6':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurGris);

				break;

			case '7':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurBleuFonce);

				break;

			case '8':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurMarron);

				break;

			case '9':

				tabLabelSoluceCombiSecreteCpu[i] = new JLabel(imgCouleurNoir);

				break;

			default:

				LOGGER.error(
						"Jeu Mastermind en mode Duel - Erreur de correspondance entre la combinaison secrète de l'ordinateur et les couleurs");

			}
			tabLabelSoluceCombiSecreteCpu[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			containerSoluceCombiSecreteCpu.add(tabLabelSoluceCombiSecreteCpu[i]);
		}
		containerSoluceCombiSecreteCpu.revalidate();
		containerSoluceCombiSecreteCpu.repaint();
	}

	/*
	 * Gestion de la fin de partie en fonction de la réponse du joueur/Cpu.
	 * 
	 * @param reponse - Reponser du joueur ou du Cpu.
	 * @param identiteJoueur - Identité de celui qui répond : joueur ou Cpu.
	 */
	private void gestionFinPartie(String reponse, char identiteJoueur) {
		verifCombiSecrete = 0;
		for (int i = 0; i < reponse.length(); i++) {
			if (reponse.charAt(i) == 'R')
				verifCombiSecrete++;
		}

		/*
		 * Pour victoire.
		 */
		if (verifCombiSecrete == nbCase && identiteJoueur == 'J') {
			this.affichageSoluce();
			JOptionPane.showMessageDialog(null,
					"Vous avez perdu. L'ordinateur a trouvé en premier votre combinaison secrète. "

							+ "\nPour information, la combinaison secrète de l'ordinateur est affichée.",
					"Fin de Partie", JOptionPane.INFORMATION_MESSAGE);
		}

		/*
		 * Pour match nul.
		 */
		if (ligne == nbEssai && verifCombiSecrete != nbCase) {
			this.affichageSoluce();
			JOptionPane.showMessageDialog(null,
					"Match nul. Le nombre d'essai maximal a été dépassé.\nPour information, la combinaison secrète de l'ordinateur est "

							+ "affichée.",
					"Fin de Partie",

					JOptionPane.INFORMATION_MESSAGE);
		}

		/*
		 * Pour defaite et match nul.
		 */
		if (ligne == nbEssai || verifCombiSecrete == nbCase) {
			LOGGER.trace("jeu en mode duel - fin de partie");
			boolFinPartie = true;
			jdFinPartie = new BoiteDialogueEndOfTheGame(null, "Fin de Partie", true);
			controlMaster.setChoixFinPartie(jdFinPartie.getChoixFinDePartie());
		}

	}



	/*
	 * Méthode non utilisée dans cette classe.
	 */
	public void quitterAppli() {
	}

	/*
	 * Méthode non utilisée dans cette classe.
	 */
	public void accueilObserver() {
	}

	/*
	 * Méthode pour MAJ la grille de jeu gauche avec les réponses du Cpu. Mais
	 * également la grille de droite avec les proposition du Cpu.
	 * 
	 * @param affichage - Variable correspondant aux proposition et réponses du Cpu.
	 */
	public void updateMaster(String affichage) {

		// MAj de la grille de jeu de gauche avec les reponse du Cpu.

		if (!MajAffichageDuel) {

			/*
			 * Pour une ligne donnée, on met à jour le JPanel jpContainerSolutionGauche en
			 * suivant les étapes habituelles pour un JPanel :
			 * 
			 * On supprime les anciens composants, on ajoute les nouveaux, on revalide et on
			 * fait appel à la méthode repaint()
			 */

			containerSoluceGauche[ligne].removeAll();

			for (int i = 0; i < this.nbCase; i++) {

				if (affichage.charAt(i) == 'R') {
					tabLabelSoluceGauche[i] = new JLabel(imgPionRougeSolution);
					containerSoluceGauche[ligne].add(tabLabelSoluceGauche[i]);

				}

				else if (affichage.charAt(i) == 'B') {
					tabLabelSoluceGauche[i] = new JLabel(imgPionBlancSolution);
					containerSoluceGauche[ligne].add(tabLabelSoluceGauche[i]);

				}

				else {
					tabLabelSoluceGauche[i] = new JLabel(imgEmplacementVideSolution);
					containerSoluceGauche[ligne].add(tabLabelSoluceGauche[i]);
				}

			}

			containerSoluceGauche[ligne].revalidate();
			containerSoluceGauche[ligne].repaint();
			MajAffichageDuel = true;
			this.gestionFinPartie(affichage, 'O');
		}

		/*
		 * Sinon, MAJ le grille de droite avec les propositions du Cpu.
		 */
		else {
			this.propoCpuDuel = affichage;
			// On convertit la proposition du Cpu en JLabel avc les images adèquates.

			containerGrilleJeuDroite.removeAll();
			for (int i = 0; i < nbCase; i++) {
				switch (affichage.charAt(i)) {

				case '0':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurBleu);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '1':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurJaune);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '2':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurOrange);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '3':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurRouge);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '4':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurVerte);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '5':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurViolet);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '6':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurGris);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '7':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurBleuFonce);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '8':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurMarron);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				case '9':

					tab2DimLabelGrilleJeuDroite[ligne][i + 1] = new JLabel(imgCouleurNoir);

					tab2DimLabelGrilleJeuDroite[ligne][i + 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

					break;

				default:

					LOGGER.error(
							"Jeu Mastermind en mode Duel - Erreur de correspondance entre la proposition de l'ordinateur et les couleurs");

				}

			}
			for (int i = 0; i < this.nbEssai; i++) {
				for (int j = 0; j < this.nbCase + 1; j++) {
					containerGrilleJeuDroite.add(tab2DimLabelGrilleJeuDroite[i][j]);

				}
				containerGrilleJeuDroite.add(containerSoluceDroite[i]);

			}

			containerGrilleJeuDroite.revalidate();
			containerGrilleJeuDroite.repaint();
			MajAffichageDuel = false;
		}

	}

	/*
	 * Méthode pour relancer le jeu, réinitialisation de l'IHM des grilles de jeu
	 * ainsi que les combinaisons et variables.
	 */
	public void relancerPartie() {
		LOGGER.trace("Jeu en mode duel - Partie Relancée");

		// Réinitialisation des grilles de jeu
		this.initialisationGrilleJeuGauche();
		this.initialisationGrilleJeuDroite();

		// Réinitialisation de la combinaison secrete du Cpu en mode développeur inactif
		if (this.modeDeveloppeurActive == false) {
			containerSoluceCombiSecreteCpu.removeAll();
			containerSoluceCombiSecreteCpu.add(labelcombiSecreteCpu);

			for (int i = 0; i < this.nbCase; i++) {
				tabLabelSoluceCombiSecreteCpu[i] = new JLabel();
				tabLabelSoluceCombiSecreteCpu[i].setPreferredSize(new Dimension(29, 29));
				tabLabelSoluceCombiSecreteCpu[i].setIcon(imgEmplacementVideSolutionCombiSecreteCpu);
				containerSoluceCombiSecreteMan.add(tabLabelSoluceCombiSecreteMan[i]);
			}

			containerSoluceCombiSecreteMan.revalidate();
			containerSoluceCombiSecreteMan.repaint();

			bCouleurBleu.setEnabled(true);

			bCouleurJaune.setEnabled(true);

			bCouleurOrange.setEnabled(true);

			bCouleurRouge.setEnabled(true);

			bCouleurVert.setEnabled(true);

			bCouleurViolet.setEnabled(true);

			bCouleurGris.setEnabled(true);

			bCouleurBleuFonce.setEnabled(true);

			bCouleurMarron.setEnabled(true);

			bCouleurNoir.setEnabled(true);

			bValiderCombiSecreteEtPropoMan.setEnabled(false);

			bEffacerCombiSecreteEtPropoMan.setEnabled(true);

			bPionRougeSolution.setEnabled(false);

			bPionBlancSolution.setEnabled(false);

			bValiderReponseMan.setEnabled(false);

			bEffacerReponseMan.setEnabled(false);

			reponseManDuel = "";

			for (int i = 0; i < this.nbCase; i++) {

				tabReponseMan[i] = 3;

				reponseManDuel += "V";
			}

			ligne = 0;
			colonne = 1;
			remplissageSoluce = 0;
			reponseAttendue = "";
			combiSecreteCpu = "";
			propoSecreteManDuel = "";
			propoCpuDuel = "";
			propoManDuel = "";
			combiSecreteValide = false;
			MajAffichageDuel = false;
			this.generationCombiSecreteCpu();

			// Réinitialisation de la combinaison secrete du Cpu lorsque le mode développeur
			// est actif.
			if (modeDeveloppeurActive == true)
				this.affichageSoluce();

		}

	}

}
