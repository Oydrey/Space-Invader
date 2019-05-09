package fr.unilim.fr.spaceinvaders;

public class Vaisseau {

	Position origine;
	Dimension dimension;
	private int vitesse;

	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}
	
	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this(new Dimension(longueur, hauteur), new Position(x, y));
	}
	
	public Vaisseau(Dimension dimension, Position positionOrigine) {
	    this(dimension,positionOrigine,1);
    }
	
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		this.dimension = dimension;
		this.origine = positionOrigine;
		this.vitesse = vitesse;
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
