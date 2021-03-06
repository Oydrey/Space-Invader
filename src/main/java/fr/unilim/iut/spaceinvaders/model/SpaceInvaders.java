package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.fr.spaceinvaders.utils.*;
import fr.unilim.iut.spaceinvaders.moteurjeu.*;


public class SpaceInvaders implements Jeu {

	int longueur;
    int hauteur;
    Vaisseau vaisseau;
    Missile missile;
    Envahisseur envahisseur;
    
    @Override
	public void evoluer(Commande commandeUser) {
		if (commandeUser.droite) {
			this.deplacerVaisseauVersLaDroite();
		}
		if (commandeUser.gauche) {
			this.deplacerVaisseauVersLaGauche();
		}
		if (commandeUser.tir && !this.aUnMissile()) {
			this.tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR,Constante.MISSILE_HAUTEUR), Constante.MISSILE_VITESSE);
		}
		if (this.aUnMissile()) {
			this.deplacerMissile();
		}
		if (this.aUnEnvahisseur()) {
			this.deplacerEnvahisseur();
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
		
		Position positionEnvahisseur = new Position(this.longueur/2,Constante.ENVAHISSEUR_HAUTEUR_APPARITION);
		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
	 }
    
    public Vaisseau getVaisseau() {
    	return this.vaisseau;
    }
    
    public Envahisseur getEnvahisseur() {
    	return this.envahisseur;
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
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}
	
	public boolean aUnMissile() {
		return missile!=null;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return vaisseau!=null;
	}
	
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	private boolean aUnEnvahisseur() {
		return envahisseur!=null;
	}

	private boolean estDansEspaceDeJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		 if (vaisseau.abscisseLaPlusADroite()< (longueur-1)) {
			 vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			 if (!estDansEspaceDeJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordoneeLaPlusHaute())) {
					vaisseau.positionner(longueur - vaisseau.dimension.longueur(), vaisseau.ordoneeLaPlusHaute());
				}
		 }
	}

	public void deplacerVaisseauVersLaGauche() {
		if (vaisseau.abscisseLaPlusAGauche()> 0) {
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
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

	public void tirerUnMissile(Dimension dimension, int vitesse) {
		this.missile = this.vaisseau.tirerUnMissile(dimension,vitesse);	
	}
	
	public Missile retournerMissile() {
		return this.missile;
	}

	public void deplacerMissile() {
		this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		if (missile.ordoneeLaPlusHaute()<this.hauteur-this.hauteur) {
			this.missile = (Missile) this.missile.detruire();
		}
	}

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceDeJeu(x, y))
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");
		
		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();
		
		if (!estDansEspaceDeJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceDeJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

		envahisseur = new Envahisseur(dimension,position,vitesse);
	}

	public void deplacerEnvahisseurVersLaDroite() {
		if (envahisseur.abscisseLaPlusADroite()< (longueur-1)) {
			 envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			 if (!estDansEspaceDeJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordoneeLaPlusHaute())) {
				 envahisseur.positionner(longueur - envahisseur.dimension.longueur(), envahisseur.ordoneeLaPlusHaute());
				 envahisseur.changerDirection(Direction.GAUCHE);
			}
		 }
	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (envahisseur.abscisseLaPlusAGauche()> 0) {
			envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
			if (!estDansEspaceDeJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordoneeLaPlusHaute())) {
				envahisseur.positionner(0, envahisseur.ordoneeLaPlusHaute());
				envahisseur.changerDirection(Direction.DROITE);
			}
		}
		
	}

	public void deplacerEnvahisseur() {
		Direction directionEnvahisseur = this.envahisseur.getDirectionActuelle();
		switch (directionEnvahisseur) {
		case GAUCHE:
			this.deplacerEnvahisseurVersLaGauche();
		case DROITE:
			this.deplacerEnvahisseurVersLaDroite();
		default:
			break;
		}
	}


}
