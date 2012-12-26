package visu;

/**
 * @author  collet
 */
public interface Dirigeable extends Positionnable {

	public double getVitesseCourante(); // vitesse >= 0

	public void setVitesseCourante(double vitesseCourante);

	public double getDirectionCourante(); // direction en radians [0,2PI[

	public void setDirectionCourante(double directionCourante);
	
}
