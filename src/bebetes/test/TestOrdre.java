package bebetes.test;

import static org.junit.Assert.*;
import static java.lang.Math.PI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bebetes.Bebete;
import bebetes.BebeteEmergente;
import bebetes.ChampDeBebetes;

public class TestOrdre {
	
	BebeteEmergente b1, b2;
	ChampDeBebetes c;
	ArrayList<Bebete> list;
	JFrame f ;
	
	// nombre d'iteration...
	int nbIter = 100;
	
	// parametres
	double angle =  (PI / 4f);
	double angle2 = (angle)*3;
	double vitesse1 = 5f;
	double vitesse2 = 5f;
	
	// oracles
	double newAngle = (angle+angle2)/2;
	double newVitesse = (vitesse1+vitesse2)/2;
	
	@BeforeClass
	public static void globalInit() {
		// pour que les bebetes se voient.
		Bebete.champDeVue = (float) PI   ;
	}

	@Before
	public void setUp() throws Exception {
		
		
		c = new ChampDeBebetes(600, 480, 0);
		b1 = new BebeteEmergente(c, 100, 100, angle, vitesse1, Color.green);
		b2 = new BebeteEmergente(c, 120, 100, angle2, vitesse2, Color.red);

		// a mettre si on veut voir...
//		f =  new JFrame("test");
//		f.add(c);
//		f.pack();
//		f.setVisible(true);
		
	}

	@After
	public void tearDown() throws Exception {
//		f.dispose();
//		f = null;
		list.remove(b1);
		list.remove(b2);
		c.setDessinables(null);
		list = null;
		c = null;
		b1=null;
		b2=null;
		System.gc();
	}

	@Test
	public void testNextStepSens12() {
		list = new ArrayList<Bebete>();
		list.add(b1);
		list.add(b2);
		c.setDessinables(list);
		
		int nb_pas = 0;
		while (nb_pas <= nbIter)
		{
			c.nextStep();
			// uniquement si on veut voir...
//			try {
//				TimeUnit.MILLISECONDS.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			assertEquals("direction", b1.getDirectionCourante(), b2.getDirectionCourante(), 0.01f);
			assertEquals("valeur direction", b1.getDirectionCourante(), newAngle, 0.01f);
			assertEquals("vitesse", b1.getVitesseCourante(), b2.getVitesseCourante(), 0.01f);
			assertEquals("valeur direction", b1.getVitesseCourante(), newVitesse, 0.01f);
			
			nb_pas++;
		}
		
		
	}
	
	@Test
	public void testNextStepSens21() {
		list = new ArrayList<Bebete>();
		list.add(b2);
		list.add(b1);
		c.setDessinables(list);
		
		int nb_pas = 0;
		while (nb_pas <= nbIter)
		{
			c.nextStep();
			// uniquement si on veut voir...
//			try {
//				TimeUnit.MILLISECONDS.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			assertEquals("direction", b1.getDirectionCourante(), b2.getDirectionCourante(), 0.01f);
			assertEquals("valeur direction", newAngle, b1.getDirectionCourante(),  0.01f);
			assertEquals("vitesse", b1.getVitesseCourante(), b2.getVitesseCourante(), 0.01f);
			assertEquals("valeur direction", newVitesse, b1.getVitesseCourante(),  0.01f);
			
			nb_pas++;
		}
		
		
	}
	


}
