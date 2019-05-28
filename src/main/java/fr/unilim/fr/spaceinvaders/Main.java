package fr.unilim.fr.spaceinvaders;

import fr.unilim.iut.spaceinvaders.model.*;
import fr.unilim.iut.spaceinvaders.moteurjeu.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		SpaceInvaders jeu = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR,Constante.ESPACEJEU_HAUTEUR);
		jeu.initialiserJeu();
		DessinSpaceInvaders aff = new DessinSpaceInvaders(jeu);

		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(jeu, aff);
		moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR,Constante.ESPACEJEU_HAUTEUR);
	}

}
