package fr.unilim.fr.spaceinvaders;

public class Vaisseau {

	Position origine;
	Dimension dimension;

	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}
	
	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this.origine = new Position (x,y);
		this.dimension = new Dimension(longueur, hauteur);
	}

	public boolean occupeLaPosition(int x, int y) {
	     return (estAbscisseCouverte(x) && estOrdoneeCouverte(y));
			
    }

	private boolean estOrdoneeCouverte(int y) {
		return (ordoneeLaPlusBasse()<=y) && (y<=ordonneLaPlusHaute());
	}

	private int ordonneLaPlusHaute() {
		return ordonneLaPlusHaute()-this.dimension.hauteur()+1;
	}

	private int ordoneeLaPlusBasse() {
		return this.origine.ordonnee();		
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
	    this.origine.changerAbscisse(this.origine.abscisse()+1);
    }


   public void seDeplacerVersLaGauche() {
	    this.origine.changerAbscisse(this.origine.abscisse()-1);
   }


   public void positionner(int x, int y) {
	  this.origine.changerAbscisse(x);
	  this.origine.changerOrdonnee(y);
   }
	
}
