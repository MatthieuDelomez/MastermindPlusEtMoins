package Mastermind.Vue;

import java.awt.BorderLayout;





import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdz.model.*;
import com.sdz.control.*;
import com.sdz.vue.*;
import com.sdz.observation.Observable;
import com.sdz.observation.Observer;


 /*************************MASTERMIND******************************/

/** Classe Fenetre compos�e d'une barre de menu qui permet d'acc�der
 * aux fonctionnalit�s du jeux. Elle impl�mente l'interface Observer.
 * 
 * @ author Matthieu Delomez
 * @see Observer
 * 
 *******************************************************************/

public class Fenetre extends JFrame implements Observer {
	
	

	private static final long serialVersionUID = 1L;

	/**
	 * Barre du menu
	 * Fichier = nouveau + score + quitter.
	 * A propos = Regles + Credit.
	 * 
	 */
	private JMenuBar jmbmenu = new JMenuBar();
	
	/*
	 * Element de la barre du menu
	 */
	private JMenu jmFichier = new JMenu("Fichier"), jmInstruction = new JMenu("Instruction"),
			
			jmModeJeu = new JMenu("Mode de jeu"), jmParametre = new JMenu("Param�tres");
	
	/*
	 * Champ permettant d'acc�der � la fonctionnalit� correspondante de l'application.
	 */
	private JMenuItem jmiModeChallenger = new JMenuItem("Mode Challenger"),
			
			  effacer = new JMenuItem("Nouveau"),

              jmiModeDefenseur = new JMenuItem("Mode D�fenseur"),
	
              jmiModeDuel = new JMenuItem("Mode Duel"),
	
              jmiQuitter = new JMenuItem("Quitter"),
	
              jmiRegle = new JMenuItem("R�gles"),
	
              jmiParametre = new JMenuItem("Parametrages");
	
	
	private Dimension size;
	
	/**
	 * Image de la page d'accuei.
	 */
	private JLabel imageJeu = new JLabel(new ImageIcon("ressources/Mastermind.png"));
	
	
	/**
	 * Mod�le des donn�es correspondant au jeu Mastermind
	 * @see DonneeMaster
	 */
	private DonneeMaster donneeMaster;
	
	
	/**
	 * JPanel principal de la classe.
	 */
	private JPanel container = new JPanel();
	
	
	/**
	 * Objet li� au jeu correspondant.
	 * @see MasterChallenger
	 * @see MasterDefenseur
	 * @see MasterDuel
	 */
	private ModeChallenger modeChallenger;
	
	private ModeDefenseur modeDefenseur;
	
	private ModeDuel modeDuel;
	
	
	
	/**
	 * Boite de dialoge permettant de changer les param�tres du jeu.
	 * @see BoiteDialogueParametrage
	 */
	private BoiteDialogueParametrage jdparametrage;
	
	/**
	 * Flux d'entr�e qui va permettre de lire le fichier ressources/config.properties
	 */
	private InputStream input;
	
	/**
	 * Flux de sortie qui va permettre de charger le fichier ressources/config.properties
	 */
	private Properties prop;
	
	
	/**
	 * Variable permettant la gestion des logs d'erreurs.
	 */
	private static final Logger LOGGER = LogManager.getLogger(); // A recharger
	
	/**
	 * Param�tre du jeu.
	 */
	private int nbCase = 4, nbEssai = 10, nbCouleur = 10;
	
	/**
	 * Param�tre du mode d�veloppement
	 */
	private boolean modeDeveloppeurActive = false;
	
	private Observable model;


	
	
	/* *************************************************************************************************
	 * 
	 ******************************************CONSTRUCTEUR*********************************************
	 * 
	 **************************************************************************************************/
	
	/**
	 * Constructeur de la classe Fenetre.
	 * 
	 * @param donneeMaster
	 * @param donneeMaster2 
	 * @param string 
	 * @param modeDeveloppeurActiveConsole, param�tre boolean indiquant si le mode developpeur est actif ou non.
	 * @see DonneeMaster
	 */
	
	public Fenetre(Observable donneeMaster,boolean modeDeveloppeurActiveConsole) {
		
		LOGGER.trace("Instanciation de la fenetre principale");
		this.setTitle("Mastermind");
		this.setSize(1000, 740);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("ressoures/Mastermind.png").getImage());
		imageJeu.setPreferredSize(new Dimension(1000,740));
		
		container.setPreferredSize(new Dimension(600,637));
		container.add(imageJeu);
		container.setBackground(Color.BLACK);
		this.setContentPane(container);
		
		this.model = donneeMaster;
		this.model.addObserver(this);
		this.modeDeveloppeurActive = modeDeveloppeurActiveConsole;
		LOGGER.trace("Initialisation des mod�les de donn�es");
		
		// On r�cup�re les donn�es enregistr�es dans le fichier config.properties
		prop = new Properties();
		input = null;
		
		
		try {
			
			input = new FileInputStream("ressources/config.properties");
			prop.load(input);
			
			nbEssai = Integer.valueOf(prop.getProperty("param.nbEssaiActif"));
			nbCase = Integer.valueOf(prop.getProperty("param.nbCaseActif"));
			nbCouleur = Integer.valueOf(prop.getProperty("param.nbCouleurActif"));

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
			
			if(input!=null) {
				
				try {
					
					input.close();
					
					
				} catch (IOException e) {
					e.printStackTrace();
					
					
				}	
			}
		}
		
		this.initMenu();
		this.setVisible(true);
	}
		
		
		/**
		 * M�thode qui permet d'initialiser le menu de la fen�tre principale.	 
        */
		public void initMenu() {
			
			LOGGER.trace("Initalisation du menu");
			
			// D�finition des mn�mosiques
			jmFichier.setMnemonic('F');
			jmInstruction.setMnemonic('I');
			jmParametre.setMnemonic('P');
			jmModeJeu.setMnemonic('E');

			

			
			// D�finition des acc�l�rateurs
			jmiQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		    jmiParametre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		    effacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
		    
		    // Construction du menu
		  
			jmModeJeu.add(jmiModeChallenger);

			jmModeJeu.add(jmiModeDefenseur);

			jmModeJeu.add(jmiModeDuel);

			jmFichier.add(effacer);

			jmFichier.addSeparator();

			jmFichier.add(jmiQuitter);

			jmParametre.add(jmiParametre);

			jmInstruction.add(jmiRegle);

			jmbmenu.add(jmFichier);

			jmbmenu.add(jmParametre);

			jmbmenu.add(jmInstruction);
			
			jmbmenu.add(jmModeJeu);

			this.setJMenuBar(jmbmenu);


			
			// D�finition  des listeners.
			jmiModeChallenger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					container.removeAll();
			     	//container.setBackground(Color.YELLOW);
					
			
					modeChallenger=new ModeChallenger(nbCase,nbEssai,

							nbCouleur,modeDeveloppeurActive, new DonneeMaster());
					

					container.add(modeChallenger);
					container.revalidate();
					container.repaint();
					jmParametre.setEnabled(false);
					effacer.setEnabled(true);
					jmParametre.setEnabled(true);

			    	LOGGER.trace("On clik sur le bouton challenger");

					
					 initModel();
				}
			});
			
			jmiModeDefenseur.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					container.removeAll();
					modeDefenseur=new ModeDefenseur(nbCase,nbEssai,nbCouleur,modeDeveloppeurActive,new DonneeMaster());
					container.add(modeDefenseur);

					container.revalidate();
					container.repaint();
					jmParametre.setEnabled(false);
					effacer.setEnabled(true);
					jmParametre.setEnabled(true);

					
					initModel();
				}
			});
			
			
			
			 
		
			 
                  effacer.addActionListener(new ActionListener(){
                      public void actionPerformed(ActionEvent arg0){
                    	  
                    	    container.removeAll();
                    		jmParametre.setEnabled(true);
                	    	jmParametre.removeAll();
                	    	container.setBackground(Color.BLACK);
                	    	container.add(imageJeu);
                	    	container.revalidate();
                	    	container.repaint();
                	    	
                	    	initModel();
                	    	LOGGER.trace("Retour � l'accueil");
                            
                      }	    	
                  });

			
			jmiModeDuel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					container.removeAll();
					modeDuel = new ModeDuel(nbCase,nbEssai,

							nbCouleur,modeDeveloppeurActive,new DonneeMaster());
					container.add(modeDuel);

					container.revalidate();
					container.repaint();
					jmParametre.setEnabled(false);
					effacer.setEnabled(true);
					jmParametre.setEnabled(true);



					initModel();
				}
			});
			
			
			jmiParametre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jdparametrage = new BoiteDialogueParametrage(null,"Parm�tre des Jeux",true,
							
						nbEssai, nbCase, nbCouleur);
					
					nbEssai = jdparametrage.getNbrEssai();
					nbCase = jdparametrage.getNbrCase();
					nbCouleur = jdparametrage.getNbrCouleur();
					modeDeveloppeurActive = jdparametrage.getModeDeveloppeurActive();
					
					LOGGER.debug("Menu Param�tre - Nb essais :" + nbEssai);
					LOGGER.debug("Menu Param�tre - Nb cases :" + nbCase);
					LOGGER.debug("Menu Param�tre - Nb couleurs :" + nbCouleur);
					LOGGER.debug("Menu Param�tre - Etat du mode d�veloppeur  :" + modeDeveloppeurActive);

				}
			});
			
			
			jmiRegle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String instructionJeu = 
							
							"Le but de ce jeu est de d�couvrir la combinaison � 10 couleurs de l'adversaire (le d�fenseur)."
									+ "\nPour ce faire, l'attaquant fait une proposition. Le d�fenseur indique pour chaque proposition"
									+ "\nle nombre de couleurs de la proposition qui apparaissent � la bonne place (� l'aide de pions rouges)"
									+ "\net � la mauvaise place (� l'aide de pions blancs) dans la combinaison secr�te.Un mode duel o� "
									+ "\nattaquant et d�fenseur jouent tour � tour est �galement disponible."; 				
					JOptionPane.showMessageDialog(null, instructionJeu, "Instructions Mastermind", JOptionPane.INFORMATION_MESSAGE);
				
				}
			});
			
			
		
			jmiQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LOGGER.trace("Fin de l'appli");
					System.exit(0);
				}
			});
			
		}
		

		/* *************************************************************************************************
		 * 
		 ********************************IMPLEMENTATION DU PATTERN OBSERVER*********************************
		 * 
		 **************************************************************************************************/
		
		 /**
		* M�thode qui permet de r�initialiser les mod�les de donn�es relatifs aux jeux..
		*/
		    public void initModel() {
		    	this.model = new DonneeMaster();
		    	this.model.addObserver(this);
		    	LOGGER.trace("R�initialiser des mod�les de donn�es");
		    }
		
	/**
	 * Pattern Observer - M�thode non utilis�e dans la clase Fenetre.
	 */
		public void updateMaster(String reponse) {}
	 
		
	/**
	 * Pattern Observer - M�thode permettant de quitter l'appli.
	*/
	    public void quitterAppli() {
		LOGGER.trace("Find de l'appli");
		System.exit(0);
	}
	    
	    
		
	/**
    * Pattern Observer - M�thode pour revenir � la page d'accueil.
	*/
	    public void accueilObserver() {
	    	jmParametre.setEnabled(true);
	    	jmParametre.removeAll();
	    	container.setBackground(Color.BLACK);
	    	container.add(imageJeu);
	    	container.revalidate();
	    	
	    	container.repaint();
	    	LOGGER.trace("Retour � l'accueil");
	    	
	    }
	
		
	/**
	* Pattern Observer - M�thode non utilis�e dans la clase Fenetre.
    */
	    public void relancerPartie() {}


	}



	
	
