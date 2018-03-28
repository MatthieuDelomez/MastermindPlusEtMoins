package Mastermind.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * Classe qui correspond au mode de jeu Challnger. Elle impl�mente l'interface
 * Observer.
 * 
 * @ author Matthieu Delomez
 * @see Observer
 * 
 *******************************************************************/

public class ModeChallenger extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	/**
	 * Param�tre du jeu.
	 */
	private int nbCase, nbEssai, nbCouleur;

	/**
	 * Variable qui permet d'effectuer des controles.
	 */
	private int ligne = 0,

			colonne = 1,

			verifCombiSecrete = 0;

	/**
	 * Param�tre de type boolean indiquant si le mode d�veloppeur est activ�.
	 */
	private boolean modeDeveloppeurActive;

	/**
	 * GridLayout correspondant � l'ensemble de la grille du jeu.
	 */
	private GridLayout gl;

	/**
	 * GridLayout correspondant � la derni�re colonne de la grille du jeu.
	 */
	private GridLayout glSolution;

	/**
	 * JPanel utilis� pour r�aliser l'interface graphique.
	 */
	private JPanel containerGrilleJeu = new JPanel(),

			containerBoutonEffacer_Valider = new JPanel(),

			containerButonCouleur = new JPanel(),

			containerSolutionCombiSecreteCpu = new JPanel();

	/**
	 * Tableau de JPanel utilis� pour l'interface graphique.
	 */
	private JPanel[] containerSolution;

	/**
	 * Image utilis� pour r�aliser l'interface graphique.
	 */
	private ImageIcon imgEmplacementVide = new ImageIcon("images/EmplacementVide.png");

	private ImageIcon imgEmplacementVideSolution = new ImageIcon("images/EmplacementVideSolution.png");

	private ImageIcon imgPionRougeSolution = new ImageIcon("images/PionRougeSolution.png");

	private ImageIcon imgPionBlancSolution = new ImageIcon("images/PionBlancSolution.png");

	private ImageIcon imgEmplacementVideSolutionCombiSecreteCpu = new ImageIcon(
			"images/EmplacementVideSolutionCombiSecreteCpu.png");

	private ImageIcon imgCouleurVerte = new ImageIcon("images/CouleurVerte.png");

	private ImageIcon imgCouleurBleu = new ImageIcon("images/CouleurBleu.png"),

			imgCouleurOrange = new ImageIcon("images/CouleurOrange.png");

	private ImageIcon CouleurRouge = new ImageIcon("images/CouleurRouge.png");

	private ImageIcon imgCouleurJaune = new ImageIcon("images/CouleurJaune.png");

	private ImageIcon imgCouleurViolet = new ImageIcon("images/CouleurViolet.png");

	private ImageIcon imgCouleurGris = new ImageIcon("images/CouleurGris.png");

	private ImageIcon imgCouleurBleuFonce = new ImageIcon("images/CouleurBleuFonce.png");

	private ImageIcon imgCouleurNoir = new ImageIcon("images/CouleurNoir.png");

	private ImageIcon imgCouleurMarron = new ImageIcon("images/CouleurMarron.png");

	/**
	 * Tableau de JLabel � deux dimensions.
	 */
	private JLabel[][] tabJLabelGrilleJeu;

	/**
	 * Tableau de JLabel � 1 dimension.
	 */
	private JLabel[] tabJLabelSolution, tabJLabelSolutionCombiSecreteCpu;

	/**
	 * JLabel de type informatif.
	 */
	private JLabel premiereInstruction = new JLabel("La combi secr�te a �t� g�n�r�e par le Cpu."),

			labelSolution = new JLabel("Solution :");

	/**
	 * Bouton utilis� pour l'interface.
	 */
	private JButton bEffacer = new JButton("Effacer la ligne"),

			bValider = new JButton("Valider"),

			bCouleurVerte = new JButton(imgCouleurVerte),

			bCouleurBleu = new JButton(imgCouleurBleu),

			bCouleurOrange = new JButton(imgCouleurOrange),

			bCouleurRouge = new JButton(CouleurRouge),

			bCouleurJaune = new JButton(imgCouleurJaune),

			bCouleurViolet = new JButton(imgCouleurViolet),

			bCouleurGris = new JButton(imgCouleurGris),

			bCouleurBleuFonce = new JButton(imgCouleurBleuFonce),

			bCouleurNoir = new JButton(imgCouleurNoir),

			bCouleurMarron = new JButton(imgCouleurMarron);

	/**
	 * Typographie.
	 */
	private Font police = new Font("Arial", Font.PLAIN, 14),

			policeSolution = new Font("Arial", Font.BOLD, 14);

	/**
	 * Combinaison secrete g�n�r�e par le Cpu.
	 */
	private String combiSecreteCpu = "";

	/**
	 * Proposition du joueur en mode challenger.
	 */
	private String propoManChallenger = "";

	/**
	 * Mod�le de donn�es relatif au jeu.
	 * 
	 * @see DonneesMaster
	 */
	private DonneeMaster modelMaster;

	/**
	 * Mod�le de donn�es relatif au jeu.
	 * 
	 * @see MasterControl
	 */
	private MasterControl controlerMaster;

	/**
	 * Boite de dialogue qui permet de faire un choix en fin de partie.
	 * 
	 * @see BoiteDialogueEndOfTheGame
	 */
	private BoiteDialogueEndOfTheGame jdFinPartie;

	/**
	 * Variable de type boolean qui permet d'indiquer la fin de partie.
	 */
	private boolean finPartie = false;

	/**
	 * Variable qui permet la gestion des logs d'erreurs.
	 */
	private static final Logger LOGGER = LogManager.getLogger();


	/**
	 * Constructeur de la classe ModeChallenger.
	 * 
	 * @param nbCase Nombre de cases du jeu Mastermind.
	 * @param nbEssai Nombre d'essais du jeu Mastermind.
	 * @param nbCouleur Nombre de couleurs utilisables du jeu Mastermind.
	 * @param modeDeveloppeurActive Param�tre de type bool�en indiquant 
	 * si le mode d�veloppeur est activ� ou non.
	 * @param modelMaster Mod�le de donn�es correspondant au jeu Mastermind.
	 * @see DonneesMaster
	 */
	public ModeChallenger(int nbCase, int nbEssai, int nbCouleur, boolean modeDeveloppeurActive,
			DonneeMaster modelMaster) {

		LOGGER.trace("Instanciation du jeu en mode Challenger");
		this.setPreferredSize(new Dimension(1000, 740));
		this.setBackground(Color.WHITE);
		this.nbCase = nbCase;
		this.nbEssai = nbEssai;
		this.nbCouleur = nbCouleur;
		this.modeDeveloppeurActive = modeDeveloppeurActive;
		this.modelMaster = modelMaster;
		this.controlerMaster = new MasterControl(this.modelMaster);
		this.generationCombiSecreteCpu();
		premiereInstruction.setPreferredSize(new Dimension(100, 40));
		premiereInstruction.setHorizontalAlignment(JLabel.CENTER);
		premiereInstruction.setFont(police);

		bValider.setEnabled(false);
		containerBoutonEffacer_Valider.setPreferredSize(new Dimension(1000, 40));
		containerBoutonEffacer_Valider.setBackground(Color.WHITE);
		containerBoutonEffacer_Valider.add(bValider);
		containerBoutonEffacer_Valider.add(bEffacer);

		bCouleurBleu.setPreferredSize(new Dimension(29, 29));
		bCouleurJaune.setPreferredSize(new Dimension(29, 29));
		bCouleurOrange.setPreferredSize(new Dimension(29, 29));
		bCouleurRouge.setPreferredSize(new Dimension(29, 29));
		bCouleurVerte.setPreferredSize(new Dimension(29, 29));
		bCouleurViolet.setPreferredSize(new Dimension(29, 29));
		bCouleurGris.setPreferredSize(new Dimension(29, 29));
		bCouleurBleuFonce.setPreferredSize(new Dimension(29, 29));
		bCouleurMarron.setPreferredSize(new Dimension(29, 29));
		bCouleurNoir.setPreferredSize(new Dimension(29, 29));

		LOGGER.trace("Bouton Dimension ");

		containerButonCouleur.setPreferredSize(new Dimension(1000, 40));
		containerButonCouleur.setBackground(Color.WHITE);
		containerButonCouleur.add(bCouleurBleu);
		containerButonCouleur.add(bCouleurJaune);
		containerButonCouleur.add(bCouleurOrange);
		containerButonCouleur.add(bCouleurRouge);

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
			containerButonCouleur.add(bCouleurVerte);
			break;

		case 6:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVerte);
			containerButonCouleur.add(bCouleurViolet);
			break;
		case 7:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVerte);
			containerButonCouleur.add(bCouleurViolet);
			containerButonCouleur.add(bCouleurGris);
			break;

		case 8:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVerte);
			containerButonCouleur.add(bCouleurViolet);
			containerButonCouleur.add(bCouleurGris);
			containerButonCouleur.add(bCouleurBleuFonce);
			break;

		case 9:
			containerButonCouleur.add(bCouleurBleu);
			containerButonCouleur.add(bCouleurJaune);
			containerButonCouleur.add(bCouleurOrange);
			containerButonCouleur.add(bCouleurRouge);
			containerButonCouleur.add(bCouleurVerte);
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
			containerButonCouleur.add(bCouleurVerte);
			containerButonCouleur.add(bCouleurViolet);
			containerButonCouleur.add(bCouleurGris);
			containerButonCouleur.add(bCouleurBleuFonce);
			containerButonCouleur.add(bCouleurMarron);
			containerButonCouleur.add(bCouleurNoir);
			break;

		default:
			LOGGER.error(
					"Jeu Mastermind en mode Challenger - Erreur lors de la mise en place de l'IHM pour les boutons li�s aux couleurs");
		}

		LOGGER.trace("Bouton Charg� ");

		labelSolution.setFont(policeSolution);
		containerSolutionCombiSecreteCpu.setPreferredSize(new Dimension(100, 40));
		containerSolutionCombiSecreteCpu.setBackground(Color.WHITE);
		containerSolutionCombiSecreteCpu.add(labelSolution);
		tabJLabelSolutionCombiSecreteCpu = new JLabel[this.nbCase];
		if (modeDeveloppeurActive == false) {

			for (int i = 0; i < this.nbCase; i++) {

				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel();
				tabJLabelSolutionCombiSecreteCpu[i].setPreferredSize(new Dimension(290, 290));
				tabJLabelSolutionCombiSecreteCpu[i].setIcon(imgEmplacementVideSolutionCombiSecreteCpu);
				containerSolutionCombiSecreteCpu.add(tabJLabelSolutionCombiSecreteCpu[i]);
			}
		} else {

			this.affichageSolution();
		}

		this.add(premiereInstruction);
		this.add(containerBoutonEffacer_Valider);
		this.add(containerButonCouleur);
		this.initialisationGrilleJeu();
		this.add(containerGrilleJeu);
		this.add(containerSolutionCombiSecreteCpu);

		this.modelMaster.addObserver(this);

		// D�finition des listeners

		bCouleurBleu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, imgCouleurBleu, "0");
				colonne++;
			}
		});

		bCouleurJaune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, imgCouleurJaune, "1");
				colonne++;
			}
		});

		bCouleurOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, imgCouleurOrange, "2");
				colonne++;
			}
		});

		bCouleurRouge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, CouleurRouge, "3");
				colonne++;
			}
		});

		bCouleurVerte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, imgCouleurVerte, "4");
				colonne++;
			}
		});

		bCouleurViolet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateGrilleJeu(ligne, colonne, imgCouleurViolet, "5");
				colonne++;
			}
		});

		bCouleurGris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, imgCouleurGris, "6");
				colonne++;
			}
		});

		bCouleurBleuFonce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, imgCouleurBleuFonce, "7");
				colonne++;
			}
		});

		bCouleurMarron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, imgCouleurMarron, "8");
				colonne++;
			}
		});

		bCouleurNoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateGrilleJeu(ligne, colonne, imgCouleurNoir, "9");
				colonne++;
			}
		});

		bValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				controlerMaster.setPropoManChallenger(propoManChallenger);
				if (!finPartie) {
					propoManChallenger = "";
					ligne++;
					colonne = 1;
					bValider.setEnabled(false);
				}

				else
					finPartie = false;
			}
		});

		bEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				effacerLigneGrilleJeu(ligne, colonne, imgEmplacementVide);
				colonne = 1;
			}
		});
	}

	/**
	 * M�thode permettant d'initialiser la grille de jeu.
	 */
	private void initialisationGrilleJeu() {

		LOGGER.trace("Initialisation de la Grille de jeu");

		gl = new GridLayout(this.nbEssai, this.nbCase + 2);
		containerGrilleJeu.setLayout(gl);
		containerGrilleJeu.setPreferredSize(new Dimension(30 * (this.nbCase + 2), 29 * this.nbEssai));
		tabJLabelGrilleJeu = new JLabel[this.nbEssai][this.nbCase + 1];

		if (this.nbCase == 4)
			glSolution = new GridLayout(this.nbCase / 2, this.nbCase / 2);

		else if (this.nbCase == 5)
			glSolution = new GridLayout(this.nbCase / 2, this.nbCase / 2 + 1);

		else
			glSolution = new GridLayout(this.nbCase / 2 - 1, this.nbCase / 2);

		tabJLabelSolution = new JLabel[this.nbCase];
		containerSolution = new JPanel[this.nbEssai];

		/*
		 * La grille de jeu est un JPanel organis� en GridLayout compos� d'un tableau de
		 * JLabel et d'un tableau de JPanel organis� �galement en GridLayout.
		 */
		for (int i = 0; i < this.nbEssai; i++) {

			for (int j = 0; j <= this.nbCase; j++) {

				if (j == 0) {

					tabJLabelGrilleJeu[i][j] = new JLabel(String.valueOf(i + 1));
					tabJLabelGrilleJeu[i][j].setOpaque(true);
					tabJLabelGrilleJeu[i][j].setBackground(Color.LIGHT_GRAY);
					tabJLabelGrilleJeu[i][j].setForeground(Color.YELLOW);
					tabJLabelGrilleJeu[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
					tabJLabelGrilleJeu[i][j].setHorizontalAlignment(JLabel.CENTER);
				} else {
					tabJLabelGrilleJeu[i][j] = new JLabel(imgEmplacementVide);
				}
			}
		}

		for (int i = 0; i < this.nbEssai; i++) {

			containerSolution[i] = new JPanel();
			containerSolution[i].removeAll();
			containerSolution[i].setPreferredSize(new Dimension(30, 29));
			containerSolution[i].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			containerSolution[i].setLayout(glSolution);

			for (int k = 0; k < this.nbCase; k++) {

				tabJLabelSolution[k] = new JLabel(imgEmplacementVideSolution);
				containerSolution[i].add(tabJLabelSolution[k]);

			}

			containerSolution[i].revalidate();
			containerSolution[i].repaint();
		}

		// L'organisation en GridLayout impose un remplissage ligne par ligne
		containerGrilleJeu.removeAll();

		for (int i = 0; i < this.nbEssai; i++) {

			for (int j = 0; j < this.nbCase + 1; j++) {

				containerGrilleJeu.add(tabJLabelGrilleJeu[i][j]);
			}
			containerGrilleJeu.add(containerSolution[i]);
		}

		containerGrilleJeu.revalidate();
		containerGrilleJeu.repaint();

	}

	/**
	 * M�thode permettant de mettre � jour la grille de jeu selon la proposition du
	 * joueur.
	 * 
	 * @param lig Ligne de la grille de jeu.
	 * @param col Colonne de la grille de jeu.
	 * @param couleurChoisie Couleur choisie par le joueur.
	 * @param codeCouleur Code couleur associ� � une couleur. Exemple : Bleu :"0", Jaune
	 */
	
	private void updateGrilleJeu(int lig, int col, ImageIcon couleurChoisie, String codeCouleur) {
		if (colonne <= this.nbCase) {

			LOGGER.debug("Mode challenger - MAJ de la grille de jeu"); // = Log non pris en compte

			tabJLabelGrilleJeu[lig][col] = new JLabel(couleurChoisie);
			propoManChallenger += codeCouleur;
			tabJLabelGrilleJeu[lig][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

			// L'organisation en GridLayout impose un remplissage ligne par ligne
			containerGrilleJeu.removeAll();

			for (int i = 0; i < this.nbEssai; i++) {

				for (int j = 0; j < this.nbCase + 1; j++) {
					containerGrilleJeu.add(tabJLabelGrilleJeu[i][j]);
				}

				containerGrilleJeu.add(containerSolution[i]);
			}

			containerGrilleJeu.revalidate();
			containerGrilleJeu.repaint();
			if (colonne == this.nbCase) {
				bValider.setEnabled(true);
			}
		}

		if (colonne > this.nbCase) {
			JOptionPane.showMessageDialog(null,
					"La ligne est compl�te. Vous pouvez soit valider, soit effacer la ligne", "Message Informatif",
					JOptionPane.WARNING_MESSAGE);
			colonne = this.nbCase;
		}
	}

	/**
	 * M�thode permettant d'effacer une ligne de la grille de jeu.
	 * 
	 * @param lig Ligne de la grille de jeu.
	 * @param col Colonne de la grille de jeu.
	 * @param emplacementVide Image correspondant � un emplacement vide.
	 */
	private void effacerLigneGrilleJeu(int lig, int col, ImageIcon emplacementVide) {

		for (int i = 1; i < col; i++) {

			tabJLabelGrilleJeu[lig][i] = new JLabel(emplacementVide);
		}

		propoManChallenger = "";

		// L'organisation en GridLayout impose un remplissage ligne par ligne
		containerGrilleJeu.removeAll();

		for (int i = 0; i < this.nbEssai; i++) {

			for (int j = 0; j < this.nbCase + 1; j++) {

				containerGrilleJeu.add(tabJLabelGrilleJeu[i][j]);
			}
			containerGrilleJeu.add(containerSolution[i]);
		}
		containerGrilleJeu.revalidate();
		containerGrilleJeu.repaint();
		bValider.setEnabled(false);

	}

	/**
	 * G�n�ration de la combinaison secr�te par l'ordinateur. La combinaison secr�te
	 * est une combinaison de chiffres cast�s en String, chaque chiffre
	 * correspondant � une couleur.
	 */
	private void generationCombiSecreteCpu() {

		int nbreAleatoire;
		for (int i = 0; i < this.nbCase; i++) {

			nbreAleatoire = (int) (Math.random() * nbCouleur);
			combiSecreteCpu += String.valueOf(nbreAleatoire);
		}

		LOGGER.debug("Jeu Mastermind en mode Challenger - G�n�ration de la combinaison secr�te:" + combiSecreteCpu);
		controlerMaster.setModeJeu(0);
		controlerMaster.setNbEssai(this.nbEssai);
		controlerMaster.setNbCase(this.nbCase);
		controlerMaster.setPropoSecreteCpuChallenger(combiSecreteCpu);
	}


	/**
	 * Pattern Observer - M�thode non utilis�e dans cette classe.
	 */
	public void quitterAppli() {
	}

	/**
	 * Pattern Observer - M�thode non utilis�e dans cette classe.
	 */
	public void accueilObserver() {
	}

	/**
	 * Pattern Observer - M�thode permettant de mettre � jour la grille de jeu selon
	 * la r�ponse de l'ordinateur.
	 * 
	 * @param reponse
	 *            R�ponse de l'ordinateur.
	 */
	public void updateMaster(String reponse) {

		/*
		 * Pour une ligne donn�e, on met � jour le JPanel containerSolution en suivant
		 * les �tapes habituelles pour un JPanel : On supprime les anciens composants,
		 * on ajoute les nouveaux, on revalide et on fait appel � la m�thode repaint()
		 */

		containerSolution[ligne].removeAll();

		for (int i = 0; i < this.nbCase; i++) {

			if (reponse.charAt(i) == 'R') {

				tabJLabelSolution[i] = new JLabel(imgPionRougeSolution);
				containerSolution[ligne].add(tabJLabelSolution[i]);
			}

			else if (reponse.charAt(i) == 'B') {

				tabJLabelSolution[i] = new JLabel(imgPionBlancSolution);
				containerSolution[ligne].add(tabJLabelSolution[i]);

			}

			else {
				tabJLabelSolution[i] = new JLabel(imgEmplacementVideSolution);
				containerSolution[ligne].add(tabJLabelSolution[i]);
			}
		}

		containerSolution[ligne].revalidate();
		containerSolution[ligne].repaint();

		// L'organisation en GridLayout impose un remplissage ligne par ligne
		containerGrilleJeu.removeAll();

		LOGGER.debug("Remplissage ligne par ligne");

		for (int i = 0; i < this.nbEssai; i++) {

			for (int j = 0; j < this.nbCase + 1; j++) {

				containerGrilleJeu.add(tabJLabelGrilleJeu[i][j]);
			}
			containerGrilleJeu.add(containerSolution[i]);
		}

		containerGrilleJeu.revalidate();
		containerGrilleJeu.repaint();
		this.gestionFinPartie(reponse);
	}

	/**
	 * Pattern Observer - M�thode permettant de relancer le m�me jeu :
	 * R�initialisation de l'IHM de la grille de jeu et de la solution en bas de
	 * page ainsi que toutes les variables et reg�n�ration d'une nouvelle
	 * combinaison secr�te.
	 */
	public void relancerPartie() {

		LOGGER.trace("Jeu Mastermind en mode Challenger - Partie relanc�e");
		// R�initialisation de l'IHM : on refait appel � la fonction
		// InitialisationGrilleJeu() et on r�initialise la solution en bas de page

		this.initialisationGrilleJeu();
		if (modeDeveloppeurActive == false) {
			containerSolutionCombiSecreteCpu.removeAll();
			containerSolutionCombiSecreteCpu.add(labelSolution);
			for (int i = 0; i < this.nbCase; i++) {
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel();
				tabJLabelSolutionCombiSecreteCpu[i].setPreferredSize(new Dimension(29, 29));
				tabJLabelSolutionCombiSecreteCpu[i].setIcon(imgEmplacementVideSolutionCombiSecreteCpu);
				containerSolutionCombiSecreteCpu.add(tabJLabelSolutionCombiSecreteCpu[i]);
			}
			containerSolutionCombiSecreteCpu.revalidate();
			containerSolutionCombiSecreteCpu.repaint();
		}

		ligne = 0;
		colonne = 1;
		verifCombiSecrete = 0;
		bValider.setEnabled(false);
		propoManChallenger = "";
		combiSecreteCpu = "";

		this.generationCombiSecreteCpu();
		if (modeDeveloppeurActive == true)
			this.affichageSolution();
	}

	/**
	 * Gestion de la fin de la partie en fonction de la r�ponse de l'ordinateur.
	 * 
	 * @param reponse R�ponse de l'ordinateur.
	 */
	private void gestionFinPartie(String reponse) {

		verifCombiSecrete = 0;

		for (int i = 0; i < reponse.length(); i++) {

			if (reponse.charAt(i) == 'R')
				verifCombiSecrete++;
		}

		// En cas de victoire
		if (verifCombiSecrete == nbCase) {

			JOptionPane.showMessageDialog(null,
					"F�licitations!!! Vous avez trouv� la combinaison secr�te en moins de " + nbEssai + " essais.",
					"Fin de Partie", JOptionPane.INFORMATION_MESSAGE);
			this.affichageSolution();
		}

		// En cas de d�fa�te
		if (ligne == nbEssai - 1 && verifCombiSecrete != nbCase) {

			this.affichageSolution();
			JOptionPane.showMessageDialog(null,
					"Vous avez perdu! Pour information, la combinaison secr�te est affich�e en bas de la page.",
					"Fin de Partie", JOptionPane.INFORMATION_MESSAGE);
		}

		// En cas de d�fa�te ou de victoire
		if (ligne == nbEssai - 1 || verifCombiSecrete == nbCase) {

			LOGGER.trace("Jeu Mastermind en mode Challenger - Fin de partie");
			finPartie = true;
			jdFinPartie = new BoiteDialogueEndOfTheGame(null, "Fin de Partie", true);
			controlerMaster.setChoixFinPartie(jdFinPartie.getChoixFinDePartie());
		}

	}

	/**
	 * M�thode permettant d'afficher la combinaison secr�te g�n�r�e par
	 * l'ordinateur.
	 */
	private void affichageSolution() {

		containerSolutionCombiSecreteCpu.removeAll();
		containerSolutionCombiSecreteCpu.add(labelSolution);

		for (int i = 0; i < nbCase; i++) {

			switch (combiSecreteCpu.charAt(i)) {
			case '0':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurBleu);
				break;
			case '1':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurJaune);
				break;
			case '2':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurOrange);
				break;
			case '3':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(CouleurRouge);
				break;
			case '4':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurVerte);
				break;
			case '5':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurViolet);
				break;
			case '6':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurGris);
				break;
			case '7':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurBleuFonce);
				break;
			case '8':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurMarron);
				break;
			case '9':
				tabJLabelSolutionCombiSecreteCpu[i] = new JLabel(imgCouleurNoir);
				break;
			default:
				LOGGER.error(
						"Jeu Mastermind en mode Challenger - Erreur de correspondance entre la combinaison secr�te et les couleurs");
			}

			tabJLabelSolutionCombiSecreteCpu[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			containerSolutionCombiSecreteCpu.add(tabJLabelSolutionCombiSecreteCpu[i]);
		}
		containerSolutionCombiSecreteCpu.revalidate();
		containerSolutionCombiSecreteCpu.repaint();
	}

}
