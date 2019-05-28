package fr.unilim.fr.spaceinvaders;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.fail;
import org.junit.Before;

import fr.unilim.fr.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.model.*;

public class EnvahisseurTest {

	private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
	    spaceinvaders = new SpaceInvaders(15, 10);
    }
	
	 @Test
		public void test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu() {
	    	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(6,3),1);
	    	assertEquals("" +
	    	        "...............\n" + 
	    	        "...............\n" +
	    	        "......EE.......\n" + 
	    	        "......EE.......\n" +
	    	        "...............\n" +
	    	        "...............\n" + 
	    	        "...............\n" +
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	 
	 @Test
		public void test_UnNouveauEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
			
			try {
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(9,2),new Position(7,9), 1);
				fail("Dépassement de l'envahisseur à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
			
			
			try {
				spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,4),new Position(7,1), 1);
				fail("Dépassement de l'envahisseur vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
				
		}
	 
	 @Test
		public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaDroite() {
			
	    	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(6,3), 3);
			spaceinvaders.deplacerEnvahisseurVersLaDroite();
			assertEquals("" + 
					"...............\n" + 
	    	        "...............\n" +
	    	        ".........EE....\n" + 
	    	        ".........EE....\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}
	 
	 @Test
		public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaGauche() {
			
	    	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(6,3), 3);
			spaceinvaders.deplacerEnvahisseurVersLaGauche();
			assertEquals("" + 
					"...............\n" + 
	    	        "...............\n" +
	    	        "...EE..........\n" + 
	    	        "...EE..........\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}
	 
	 @Test
		public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {
			
	    	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(13,3), 3);
			spaceinvaders.deplacerEnvahisseurVersLaDroite();
			assertEquals("" + 
					"...............\n" + 
	    	        "...............\n" +
	    	        ".............EE\n" + 
	    	        ".............EE\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}
	 
	 @Test
		public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {
			
	    	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(2,3), 3);
			spaceinvaders.deplacerEnvahisseurVersLaGauche();
			assertEquals("" + 
					"...............\n" + 
	    	        "...............\n" +
	    	        "EE.............\n" + 
	    	        "EE.............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" + 
	    	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		}
	 
	@Test
	    public void test_EnvahisseurDeplaceAutomatiquementLateralement_ChangeDirection() {

		   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(12,3), 3);

		   spaceinvaders.deplacerEnvahisseur();
		   
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "............EE.\n" + 
	       "............EE.\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	
}
