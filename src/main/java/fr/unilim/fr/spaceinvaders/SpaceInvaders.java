package fr.unilim.fr.spaceinvaders;

import fr.unilim.fr.spaceinvaders.utils.*;
import fr.unilim.iut.spaceinvaders.moteurjeu.*;


public class SpaceInvaders implements Jeu {

	int longueur;
    int hauteur;
    Vaisseau vaisseau;
    
    @Override
	public void evoluer(Commande commandeUser) {
		if (commandeUser.droite) {
			this.deplacerVaisseauVersLaDroite();
		}
		if (commandeUser.gauche) {
			this.deplacerVaisseauVersLaGauche();
		}
	}

	@Override
	public boolean etreFini() {
		return false;
	}

    public SpaceInvaders(int longueur, int hauteur) {
	   this.longueur = longueur;
	   this.hauteur = hauteur;
    }
    
    public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
	 }
    
    public Vaisseau getVaisseau() {
    	return this.vaisseau;
    }

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_DE_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
		     marque=Constante.MARQUE_VAISSEAU;
		else
			marque=Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return vaisseau!=null;
	}

	private boolean estDansEspaceDeJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		 if (vaisseau.abscisseLaPlusADroite()< (longueur-1)) {
			 vaisseau.seDeplacerVersLaDroite();
			 if (!estDansEspaceDeJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordoneeLaPlusHaute())) {
					vaisseau.positionner(longueur - vaisseau.dimension.longueur(), vaisseau.ordoneeLaPlusHaute());
				}
		 }
	}

	public void deplacerVaisseauVersLaGauche() {
		if (vaisseau.abscisseLaPlusAGauche()> 0) {
			vaisseau.seDeplacerVersLaGauche();
			if (!estDansEspaceDeJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordoneeLaPlusHaute())) {
				vaisseau.positionner(0, vaisseau.ordoneeLaPlusHaute());
			}
		}
		
	}
	
	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceDeJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();
		
		if (!estDansEspaceDeJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceDeJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension,position,vitesse);
	}

}
