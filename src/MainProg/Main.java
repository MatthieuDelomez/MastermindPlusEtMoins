package MainProg;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ConsolePlusMoins.Menu.Menu;



/*************************MASTERMIND PLUS ET MOINS*************************

/*************************************************************************
 * Classe Main du programme.
 * 
 * @author Delomez Matthieu
 *************************************************************************/


public class Main {
	
	public static boolean argument;
	
	public static int modDev;
	
	/**
	 * Variable qui permet la gestion des logs d'erreurs.
	 */
	private static final Logger LOGGER = LogManager.getLogger();
	
	

	public static void main(String[] args) {
		
		
		/*
		 * Chargement du fichier config.properties
		 * 
		 * @param Properties prop
		 * @param InputStream input
		 * @param OutputStream output
		 */
		Properties prop = new Properties();
		
		InputStream input = null;
		
		OutputStream output = null;
		
		try {
			 input =  new FileInputStream("config.properties");
			 
			 /*
			  * On charge le fichier.
			  */
			 prop.load(input);
		
		} catch (IOException ex) {

			try {
				
				// Si erreur, nous créons un nouveau fichier de config.
				output = new FileOutputStream("config.properties");
				
				/*
				 * On définies les valeurs par défault.
				 */
				prop.setProperty("longueur", "10");
				prop.setProperty("couleurs", "10");
				prop.setProperty("coupsMax", "15");
				prop.setProperty("modeDeveloppeur", "0");
				
        		LOGGER.trace(" Execption Properties");

				
				/*
				 * On sauvegarde la config à la racine du projet.
				 */
				prop.store(output, null);
				
			} catch (IOException io) {

				io.printStackTrace();

			} finally {

				if (output != null) {

					try {

						output.close();

					} catch (IOException e) {

						e.printStackTrace();

					} finally {

						if (input != null) {

							try {

								input.close();

							} catch (IOException e) {

								e.printStackTrace();

							}

						}

					}

				}

			}

		}

		if (args.length > 0)
			
			modDev = Integer.parseInt(args[0]);
		
		else
			
			modDev = Integer.valueOf(prop.getProperty("modeDeveloppeur"));

		/*
		 * Création de l'objet Menu, pour de cette manière pouvoir choisir
		 * le mode de jeu que l'on désire.
		 */
		Menu menu = new Menu();


			}
		

			}
	