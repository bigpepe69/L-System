package structure_prog;

import dessin.Pile;
import fenetres.FenetrePrincipale;

public class Test {
	
	private FenetrePrincipale fenP = new FenetrePrincipale();
	Sequence seq1 = new Sequence();
	GrammaireFormelle gf1 = new GrammaireFormelle(fenP);
	
	public Test () {
		fenP.setLocationRelativeTo(null);
		fenP.setVisible(true);
		
		//TEST METHODE ITERER
		gf1.setNbRegles(1);
		gf1.ajouterRegles('F',"F+F-F-F+F");
		gf1.setIterationMax(3);
		gf1.setAxiome("F");
		seq1.setMaGF(gf1);
		seq1.iterer();
		System.out.println("\n"+seq1.getSequenceC().get(0));
		System.out.println(seq1.getSequenceC().get(1));
		System.out.println(seq1.getSequenceC().get(2));
		System.out.println(seq1.getSequenceC().get(3));
		
		//TEST PILE
		Pile pTest = new Pile();
		pTest.ajoutElement(10);
		pTest.ajoutElement(5);
		pTest.ajoutElement(3);
		pTest.ajoutElement(1);
		System.out.println("\n"+pTest.recupElement());
		System.out.println(pTest.recupElement());
		System.out.println(pTest.recupElement());
		System.out.println(pTest.recupElement());
		
		//TEST METHODE RECUPE DES DONNEES UTILISATEUR
		gf1.recupValUtil();
		System.out.println("\n");
		System.out.println(gf1.getAxiome());
		System.out.println(gf1.getIterationMax());
		System.out.println(gf1.getNbRegles());
		
	}

}
