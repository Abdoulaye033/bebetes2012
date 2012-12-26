package bebetes;

import java.awt.Color;
import java.awt.Graphics;

import simu.Actionnable;
import visu.Champ;
import visu.Dessinable;
import visu.PerceptionAble;

public abstract class Bebete extends PerceptionAble implements Actionnable, Dessinable {

	public static double champDeVue = (Math.PI / 4); // En radians
	static int longueurDeVue = 20; // nb de pixel pour la longueur du champ de vision

	protected double x, y;                 // position à l'écran
	protected double vitesseCourante;      // vitesse en pixels par second (float pour conserver la précision du calcul de vitesse)
	protected double directionCourante;  // en radians [0 - 2 PI[
	protected Color couleur;            // Couleur de remplissage


	protected double prochaineVitesse;      // vitesse en pixels par second (float pour conserver la précision du calcul de vitesse)
	protected double prochaineDirection;  // en radians [0 - 2 PI[
	protected Champ champ;     // Le champ

	/* Implémentation de Positionnable */

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public Champ getChamp() {
		return champ;
	}

	/* Implémentation de Dirigeable */

	@Override
	public double getVitesseCourante() {
		return vitesseCourante;
	}

	@Override
	public void setVitesseCourante(double vitesseCourante) {
		this.vitesseCourante = vitesseCourante;
		this.prochaineVitesse = vitesseCourante;
	}

	@Override
	public double getDirectionCourante() {
		return directionCourante;
	}

	@Override
	public void setDirectionCourante(double directionCourante) {
		this.directionCourante = directionCourante;
		this.prochaineDirection = directionCourante;

	}


	/* Implémentation de Dessinable */

	@Override
	public Color getCouleur() {
		return couleur;
	}

	@Override
	public void seDessine(Graphics g) {
		int x = (int) this.x;
		int y = (int) this.y;
		
		int CDVDegres = (int) Math.toDegrees(champDeVue);
		g.setColor(couleur);
		g.fillArc(x, y, TAILLEGRAPHIQUE, TAILLEGRAPHIQUE, -(int) Math
				.toDegrees(directionCourante)
				- (CDVDegres / 2), CDVDegres);


	}

	/* Implémentation de Actionnable */

	@Override
	public abstract void calculeDeplacementAFaire()  ;

	@Override
	public abstract void effectueDeplacement() ;


	/* Implémentation de PerceptionAble */

	@Override
	public  double getChampDeVue() {
		// TODO Auto-generated method stub
		return champDeVue;
	}

	@Override
	public  int getLongueurDeVue() {
		// TODO Auto-generated method stub
		return longueurDeVue;
	}


	/* changer la longueur et le champ de vue est "static",
	   alors que les consulter se fait par des fonctions membres */

	public static void setLongueurDeVue(int lDV) { 
		longueurDeVue = lDV;
	}

	public static void setChampDeVue(int cDV) { 
		champDeVue = cDV;
	}


}

