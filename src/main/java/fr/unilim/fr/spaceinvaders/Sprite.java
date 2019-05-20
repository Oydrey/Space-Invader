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

	public boolean estOrdoneeCouverte(int y) {
		return (ordoneeLaPlusBasse()<=y) && (y<=ordoneeLaPlusHaute());
	}

	public int ordoneeLaPlusHaute() {
		return this.origine.ordonnee();		
	}

	public int ordoneeLaPlusBasse() {
		return ordoneeLaPlusHaute()-this.dimension.hauteur()+1;
	}

	public boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche()<=x) && (x<=abscisseLaPlusADroite());
	}

	public int abscisseLaPlusADroite() {
		return this.origine.abscisse()+this.dimension.longueur()-1;
	}

	public int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}
	
	public void deplacerHorizontalementVers(Direction direction) {
		this.origine.changerAbscisse(this.origine.abscisse() + direction.valeur()*vitesse);
	}

	public void positionner(int x, int y) {
		this.origine.changerAbscisse(x);
		this.origine.changerOrdonnee(y);
	}
	
	public void deplacerVerticalementVers(Direction direction) {
		this.origine.changerOrdonnee(this.origine.ordonnee() + direction.valeur()*vitesse);
	}
	
	public Sprite detruire() {
		return null;
	}

}