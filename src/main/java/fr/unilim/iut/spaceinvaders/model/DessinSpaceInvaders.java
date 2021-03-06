package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

public class DessinSpaceInvaders implements DessinJeu {
	
	private SpaceInvaders jeu;

	public DessinSpaceInvaders(SpaceInvaders j) {
		this.jeu = j;
	}
	
	private void dessinerVaisseau(int x, int y,int dx, int dy, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.GRAY);
		crayon.fillRect(x,y,dx,dy);
	}
	
	private void dessinerMissile(int x, int y, int dx, int dy, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.BLUE);
		crayon.fillRect(x, y, dx, dy);
	}
	
	private void dessinerEnvahisseur(int x, int y, int dx, int dy, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.GREEN);
		crayon.fillOval(x, y, dx, dy);
	}
	
	@Override
	public void dessiner(BufferedImage image) {
		SpaceInvaders j = (SpaceInvaders) jeu;
		Vaisseau vaisseau = j.getVaisseau();
		Envahisseur envahisseur = j.getEnvahisseur();
		
		this.dessinerVaisseau(vaisseau.origine.abscisse(),
				vaisseau.ordoneeLaPlusBasse(),
				vaisseau.dimension.longueur(),
				vaisseau.dimension.hauteur(),image);
		
		if (j.aUnMissile()) {
			Missile missile = j.retournerMissile();
			this.dessinerMissile(missile.origine.abscisse(), 
					missile.ordoneeLaPlusBasse(),
					missile.dimension.longueur(),
					missile.dimension.hauteur(),image);
		}
		
		this.dessinerEnvahisseur(envahisseur.origine.abscisse(), 
				envahisseur.ordoneeLaPlusBasse(), 
				envahisseur.dimension.longueur(), 
				envahisseur.dimension.hauteur(), image);
		
	}

}
