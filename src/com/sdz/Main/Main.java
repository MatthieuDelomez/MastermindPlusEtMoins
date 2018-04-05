package com.sdz.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdz.Menu.Menu;

/* 
* /*************************************************************************
* Classe Main du programme.
* 
* @see Menu
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
		 * 
		 * @param InputStream input
		 * 
		 * @param OutputStream output
		 */
		Properties prop = new Properties();

		InputStream input = null;

		OutputStream output = null;

		try {
			input = new FileInputStream("config.properties");

			/*
			 * On charge le fichier.
			 */
			prop.load(input);

		} catch (IOException ex) {

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

		if (args.length > 0)

			modDev = Integer.parseInt(args[0]);

		else

			modDev = Integer.valueOf(prop.getProperty("modeDeveloppeur"));

		/*
		 * Création de l'objet Menu, pour de cette maniére pouvoir choisir le mode de
		 * jeu que l'on désire.
		 */
		Menu menu = new Menu();

	}

}
