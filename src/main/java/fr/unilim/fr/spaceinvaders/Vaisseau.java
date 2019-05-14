package fr.unilim.fr.spaceinvaders;

public class Vaisseau extends Sprite {
	
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		super(positionOrigine,dimension,vitesse);
	}

	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		
		Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);

		return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.dimension.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordoneeLaPlusBasse() - 1;
		return new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
	}
	
}
