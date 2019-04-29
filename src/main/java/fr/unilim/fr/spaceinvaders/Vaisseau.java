package fr.unilim.fr.spaceinvaders;

public class Vaisseau {

	int x;
	int y;
	int longueur;
	int hauteur;

	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}
	
	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this.x = x;
		this.y = y;
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public boolean occupeLaPosition(int x, int y) {
	     return (estAbscisseCouverte(x) && estOrdoneeCouverte(y));
			
    }

	private boolean estOrdoneeCouverte(int y) {
		return (ordoneeLaPlusHaute()<=y) && (y<=ordonneLaPlusBasse());
	}

	private int ordonneLaPlusBasse() {
		return this.y;
	}

	private int ordoneeLaPlusHaute() {
		return ordonneLaPlusBasse()-this.hauteur+1;
	}

	private boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche()<=x) && (x<=abscisseLaPlusADroite());
	}

	public int abscisseLaPlusADroite() {
		return this.x+this.longueur-1;
	}

	public void seDeplacerVersLaDroite() {
		this.x = this.x + 1 ;
		
	}

	public int abscisseLaPlusAGauche() {
		return this.x;
	}

	public void seDeplacerVersLaGauche() {
		this.x = this.x - 1 ;
		
	}

	public void positionner(int x, int y) {
	    this.x = x;
	    this.y = y;
    }
	
}
