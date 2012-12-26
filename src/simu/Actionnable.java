package simu;

public interface Actionnable {
	
	// modifie les paramètres de vitesse et de direction.
    public void calculeDeplacementAFaire();

   // modifie la position en fonction de vitesse et direction courantes
    public  void effectueDeplacement();
	
}
