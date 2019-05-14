package fr.unilim.fr.spaceinvaders;

public abstract class Sprite {

	protected Position origine;
	protected Dimension dimension;
	protected int vitesse;

	public Sprite(Position origine, Dimension dimension, int vitesse) {
		super();
		this.origine = origine;
		this.dimension = dimension;
		this.vitesse = vitesse;
	}

	public Sprite() {
		super();
	}

	public boolean occupeLaPosition(int x, int y) {
	     return (estAbscisseCouverte(x) && estOrdoneeCouverte(y));
			
	}

	private boolean estOrdoneeCouverte(int y) {
		return (ordoneeLaPlusBasse()<=y) && (y<=ordoneeLaPlusHaute());
	}

	public int ordoneeLaPlusHaute() {
		return this.origine.ordonnee();		
	}

	public int ordoneeLaPlusBasse() {
		return ordoneeLaPlusHaute()-this.dimension.hauteur()+1;
	}

	private boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche()<=x) && (x<=abscisseLaPlusADroite());
	}

	public int abscisseLaPlusADroite() {
		return this.origine.abscisse()+this.dimension.longueur()-1;
	}

	public int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}

	public void seDeplacerVersLaDroite() {
	    this.origine.changerAbscisse(this.origine.abscisse()+vitesse);
	}

	public void seDeplacerVersLaGauche() {
		    this.origine.changerAbscisse(this.origine.abscisse()-vitesse);
	   }

	public void positionner(int x, int y) {
		  this.origine.changerAbscisse(x);
		  this.origine.changerOrdonnee(y);
	   }

}