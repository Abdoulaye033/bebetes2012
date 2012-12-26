package bebetes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import simu.Actionnable;
import simu.Simulateur;
import visu.Dessinable;
import visu.Positionnable;
import visu.VisualisateurAnime;


public class ChampDeBebetes extends VisualisateurAnime {



	public static final double vitesseMax = 10f;

	protected Simulateur simu; // Mariage de convenance !!!

	public ChampDeBebetes(int largeur, int hauteur, int nb) {
		super(largeur,hauteur);
		setPreferredSize(new Dimension(largeur, hauteur));
		List <? extends Bebete> lb = fabriqueBebetes(nb);
		// Initialisation du mariage de convenance avec le simulateur
		simu = new Simulateur(50,lb);
		setDessinables(lb);
	}

	/* Redéfinitions pour synchroniser la gestion des 2 threads */

	public void demarre() {
		// on démarre d'abord la simulation
		simu.demarre();
		super.demarre();
	}

	public void arrete() {
		// on arrête d'abord la visualisation
		super.arrete();
		simu.arrete();
	}

	public ArrayList<? extends Bebete> fabriqueBebetes(int nb) {
		ArrayList<BebeteEmergente> nouvBebetes = new ArrayList<BebeteEmergente>();
		Random generateur = new Random();
		// unicité des couleurs des bébêtes, juste là pour faire joli...
		double racineCubiqueDuNombreDeBebetes = Math
		.pow((double) nb, 1.0 / 3.0);
		double etapeDeCouleur = (1.0 / racineCubiqueDuNombreDeBebetes);
		float r = 0.0f;
		float g = 0.0f;
		float b = 0.0f;
		for (int i = 0; i < nb; i++) {
			int x = (int) (generateur.nextFloat() * largeur);
			if (x > largeur - Bebete.TAILLEGRAPHIQUE)
				x -= Bebete.TAILLEGRAPHIQUE;
			int y = (int) (generateur.nextFloat() * hauteur);
			if (y > hauteur - Bebete.TAILLEGRAPHIQUE)
				y -= Bebete.TAILLEGRAPHIQUE;
			double direction =  (generateur.nextFloat() * 2 * Math.PI);
			double vitesse = Math.max(2, generateur.nextDouble() * vitesseMax);
			r += etapeDeCouleur;
			if (r > 1.0) {
				r -= 1.0f;
				g += etapeDeCouleur;
				if (g > 1.0) {
					g -= 1.0f;
					b += etapeDeCouleur;
					if (b > 1.0)
						b -= 1.0f;
				}
			}
			// Le fameux de la capture impossible du joker ?
			nouvBebetes.add(new BebeteEmergente(this, x, y, direction, vitesse,
					new Color(r, g, b)));
		}
		return nouvBebetes;
	}

	public int getNombreDeBebetes() {
		return getPositionnables().size();
	}

	public int getDelaiSimulation() { // Délégation
		return simu.getDelaiSimulation();
	}

	public void setDelaiSimulation(int delaiSimu) { // Délégation
		simu.setDelaiSimulation(delaiSimu);
	}

	public void nextStep()
	{
		simu.iteration();
	}
	
	@Override
	public void setDessinables(List<? extends Dessinable> dessinables) {
		super.setDessinables(dessinables);
		
		// a cause des deux mariages : une liste pour deux...
		// a condition qu'elles soient compatibles...
		ArrayList<Actionnable> output = new ArrayList<Actionnable>();

		if (dessinables != null) 
			{
			for (Positionnable p : dessinables) {
				if (p instanceof Actionnable)
					output.add((Actionnable)p);
			}
			simu.setActionnables(output);
			}
			
	
	}

}
