package fr.unilim.fr.spaceinvaders;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

public class DessinSpaceInvaders implements DessinJeu {

	@Override
	public void dessiner(BufferedImage image) {
		this.dessinerObjet(x,y,im);
	}
	
	private void dessinerObjet(int x, int y, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
	}

}
