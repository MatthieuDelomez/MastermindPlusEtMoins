package Mastermind;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/******************************MASTERMIND********************************/

/*************************************************************************
 * Classe relative la boite de dialogue qui permet d'avoir accès aux param
 *du Mastermind tels que le nombre d'essais, le nombre de cases et le nbr
 *de couleurs utilisables pour le Mastermind.
 * 
 * @author Delomez Matthieu
 *************************************************************************/


public class BoiteDialogueParametrage extends JDialog {
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * JPanel principal de la classe.
	 */
	private JPanel container = new JPanel();
	
	/**
	 * JPanel contenant le composant relatif au mode développeur commun aux jeux RecherchePlusMoins et Mastermind.
	 */
	private JPanel jpContainerModeDeveloppeur=new JPanel();
	
	
	/**
	 * JPanel qui contient les composants des paramètres du jeu.
	 */
	private JPanel containerMaster = new JPanel();
	
	
	/**
	 * JPanel contenant les JButton de validation ou de delete.
	 */
	private JPanel containerButton = new JPanel();
	
	
	
	/**
	 * JLabel informatif.
	 */
	private JLabel nbrEssai = new JLabel("Nombre d'essais :"),
	
	               nbrCase = new JLabel("Nombre de cases :"),
	               
	               nbrCouleur = new JLabel("Nombre de couleurs :");
	
	/**
	 * JComboBox contenant une plage de valeurs pour un paramètre donné.
	 */
	private JComboBox nbrEssaiCombo = new JComboBox(), 
			
			          nbrCaseCombo = new JComboBox(),
			          
			          nbrCouleurCombo = new JComboBox();
	
	/**
	 * JCheckBox permettant de sélectionner le mode développeur.
	 */
	private JCheckBox jcbModeDeveloppeur=new JCheckBox("Mode développeur");

	
	
	/**
	 * JButon de validation.
	 */
	private JButton boutonOk = new JButton("OK");
	
	
	/**
	 * JButton d'annulation.
	 */
	private JButton Annuler = new JButton("Annuler");
	
	
	/**
	 * Objet permettant de charger le fichier ressources/config.properties
	 * et de pouvoir enregistrer dans le fichier.
	 */
	private Properties prop;
	
	
	/**
	 * Flux d'entrée permettant de lire le fichier ressources/config.properties.
	 */
	private InputStream input;
	
	

	/**
	 * Flux de sortie permettant d'écrire dans le fichier ressources/config.properties.
	 */
	private OutputStream output;
	
	/**
	 * Variable de type String qui manipule le fichier ressources/config.properties
	 */
	private String  str_nbrEssai = "",
			    
			        str_nbrCase = "",
			        
			        str_nbrCouleur = "";
	
	
	/**
	 * Variable de type String, qui permet de pouvoir manipuler le fichier
	 * ressources/config.properties
	 */
	private String [] tab_nbrEssai, tab_nbrCase, tab_nbrCouleur;
	
	
	/**
	 *  Nombre de valeurs possibles dans le fichier ressources/config.properties pour le paramètre correspondant.
	 */
	private int NbrCouleurFichierConfig = 7;
	
	private int nbCase, nbEssai, nbCouleur;
	
	/**
	 * Paramètre relatif au jeu et Mastermind. Par défaut, le mode développeur est désactivé.
	 */
	private boolean modeDeveloppeurActive;

	
	
	
	/* *************************************************************************************************
	 * 
	 ******************************************CONSTRUCTEUR*********************************************
	 * 
	 **************************************************************************************************/
	
	
	public BoiteDialogueParametrage(JFrame parent, String title, boolean modal,
			
			int nbEssai, int nbCase, int nbCouleur) {
		
		    super(parent,title,modal);
		    this.nbCase = nbCase;
		    this.nbCouleur = nbCouleur;
		    this.nbEssai = nbEssai;
		    this.modeDeveloppeurActive = modeDeveloppeurActive;
		    this.setSize(600, 300);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		    this.initComponent();
		    this.showDialog(true);
		}
	
	
	

	/**
	 * Interface graphique de la boite de dialogue et pouvoir analyser les données du fichier
	 * ressources/config.properties et d'enregister les paramètres dans le même fichier.
	 */
	
	private void initComponent() {
		
		container.setPreferredSize(new Dimension(590,80));
		container.setBorder(BorderFactory.createTitledBorder("Mastermind"));
		container.add(nbrEssaiCombo);
		container.add(nbrCaseCombo);
		container.add(nbrCouleurCombo);
		
		container.add(nbrEssai);
		container.add(nbrCase);
		container.add(nbrCouleur);
		
		container.setPreferredSize(new Dimension(600,300));
		container.add(boutonOk);
		container.add(Annuler);
		
		this.setContentPane(container);
		
		
		/**
		 * Import des données du fichier config.properties.
		 */
		prop = new Properties();
		input = null;
		
		try {
			
			input = new FileInputStream("ressources/config.properties");
			prop.load(input);
			
			str_nbrEssai = prop.getProperty("param.nbEssai");
			tab_nbrEssai = str_nbrEssai.split(",");
			
			str_nbrCase = prop.getProperty("param.nbCase");
			tab_nbrCase = str_nbrCase.split(",");
			
			str_nbrCouleur = prop.getProperty("param.nbCouleur");
			tab_nbrCouleur = str_nbrCouleur.split(",");
		
			
			
			int i =0;
				
				nbrEssaiCombo.addItem(tab_nbrEssai[i]);
				nbrCouleurCombo.addItem(tab_nbrCouleur[i]);
				nbrCaseCombo.addItem(tab_nbrCase[i]);

		
			
			nbrEssaiCombo.setSelectedItem(prop.getProperty("param.nbEssaiActif"));
			nbrCaseCombo.setSelectedItem(prop.getProperty("param.nbCaseActif"));
			nbrCouleurCombo.setSelectedItem(prop.getProperty("param.nbCouleurActif"));
		}
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
			
			finally {
				
			
				if(input!=null) {
					
				
					try {
						
					
						input.close();
						
					} catch (IOException e) {
						
						e.printStackTrace();
						
					}}}
	
		if(modeDeveloppeurActive==false)
			
			jcbModeDeveloppeur.setSelected(false);
		else
			jcbModeDeveloppeur.setSelected(true);

		
		/**
		 *Développement du Listener/
		 */
		
		// Enregistrer les paramètres du joueur dans le fichier config.properties
		
		boutonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				prop = new Properties();
				input = null;
				output = null;
				
			
				try {
					
					input = new FileInputStream("ressources/config.properties");
					prop.load(input);
					
					// Traitement pour le jeu
					nbCase = Integer.valueOf((String)nbrCaseCombo.getSelectedItem());
					nbCouleur = Integer.valueOf((String)nbrCouleurCombo.getSelectedItem());
                    nbEssai = Integer.valueOf((String)nbrEssaiCombo.getSelectedItem());
                    
                    prop.setProperty("param.nbCaseActif", (String)nbrCaseCombo.getSelectedItem());
                    prop.setProperty("param.nbEssaiActif", (String)nbrEssaiCombo.getSelectedItem());
                    prop.setProperty("param.nbCouleurActif", (String)nbrCouleurCombo.getSelectedItem());
                    
                    output = new FileOutputStream("ressources/config.properties");
                    prop.store(output,  "Fichier de configuration config.properties");
                    
                    
				} catch (IOException e1) {
					
					e1.printStackTrace();
					
				} finally {
					
					if (input != null) {
						
						try {
							
							output.close();
							input.close();
							
						} catch (IOException e2) {
							
							e2.printStackTrace();
						}
					}}
				
			//Traitement pour l'option Mode Développeur
			if(jcbModeDeveloppeur.isSelected()) {
				
				modeDeveloppeurActive=true;
			}
			else
				modeDeveloppeurActive=false;

			showDialog(false);
		}

	});

				
				Annuler.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						showDialog(false);
					}
				});
			}
	
			
			/**
			 * Méthode qui servira à rendre visible la boite de dialogue.
			 * Affichage de type boolean qui permet d'indiquer si la boite
			 * sera visible ou non.
			 */
			private void showDialog(boolean affichage) {
				this.setVisible(affichage);
			}
			
			
			/* *************************************************************************************************
			 * 
			 ******************************************ACCESSEUR*********************************************
			 * 
			 **************************************************************************************************/
			
			
			/**
			 * Accesseur qui pourra récupérer le nombre de cases pour le jeu.
			 * 
			 */
			public int getNbrCase() {
				return nbCase;
			}
			
			/**
			 * Accesseur qui pourra récupérer le nombre d'essais pour le jeu.
			 * 
			 */
			public int getNbrEssai() {
				return nbEssai;
			}
			
			/**
			 * Accesseur qui pourra récupérer le nombre de couleurs pour le jeu.
			 * 
			 */
			public int getNbrCouleur() {
				return nbCouleur;
			}
			
			/**
			 * Accesseur permettant de récupérer la variable indiquant si le mode développeur est activé ou non.
			 * @return La variable indiquant si le mode développeur est activé ou non.
			 */
			public boolean getModeDeveloppeurActive() {
				return modeDeveloppeurActive;
			}
		}
		

		
		
	


