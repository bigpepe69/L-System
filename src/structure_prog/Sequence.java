package structure_prog;

import java.util.Vector;

public class Sequence {

	private GrammaireFormelle gfMaGF;
	private Vector<String> vSequenceC=new Vector<String>(); //Regroupe sous formes de String les itérations créées
	private int iIteration; //Numero de l'iteration à afficher
	private float fAngle;
	private float fTailleTrait;
	
	//Constructeur utilisé pour l'application finale
	public Sequence(GrammaireFormelle gfMaGFP) {
		iIteration = 0;
		gfMaGF=gfMaGFP;
		fAngle=(float)90;
		fTailleTrait=10;
	}
	
	//Constructeur utilisé pour les tests
	public Sequence() {
		iIteration = 0;
	}

	public void setMaGF(GrammaireFormelle gfMaGFParam) {
		gfMaGF = gfMaGFParam;
	}

	public GrammaireFormelle getMaGF() {
		return gfMaGF;
	}

	public Vector<String> getSequenceC() {
		return vSequenceC;
	}

	public void setSequenceC(Vector<String> vSequenceCParam) {
		vSequenceC = vSequenceCParam;
	}

	public int getIteration() {
		return iIteration;
	}

	public void setIteration(int iIterationP) {
		iIteration = iIterationP;
	}
	
	public void setTailleTrait(float fTailleTraitParam) {
		fTailleTrait = fTailleTraitParam;
	}

	public float getTailleTrait() {
		return fTailleTrait;
	}

	public void setAngle(float fAngleParam) {
		fAngle = fAngleParam;
	}

	public float getAngle() {
		return fAngle;
	}	

	// Méthode qui prépare un nombre d'itération maxi (iIterationMax) de séquences qui pourront être affichées
	public void iterer() {
		int i, j, k;
		boolean bEstDansUneRegle;
		String sSequenceFinale;
		char cCurrentCaract;
		vSequenceC.add(gfMaGF.getAxiome()); // La séquence 0 est l'axiome de départ
		
		for (i = 0; i < gfMaGF.getIterationMax(); i++) {
			String sCurrentSequence = vSequenceC.get(i); //Itération qui va être ajoutée
			sSequenceFinale = "";

			for (j = 0; j < sCurrentSequence.length(); j++) {
				cCurrentCaract = sCurrentSequence.charAt(j); //Teste les caractères un par un
				bEstDansUneRegle = false; 
				for (k = 0; k < gfMaGF.getNbRegles(); k++) { 
					if (cCurrentCaract == gfMaGF.getVRegles().get(k).getCaractInit()) { // Teste si le caractère est une variable pour chacune des règles
						sSequenceFinale=sSequenceFinale+gfMaGF.getVRegles().get(k).getTransfo(); //Le caractère modifié est ajouté à la séquence
						bEstDansUneRegle = true;
					}
				}
				if (bEstDansUneRegle == false) {
					sSequenceFinale=sSequenceFinale+cCurrentCaract; //Le caractère non-modifié est ajouté à la séquence
				}
			}
			vSequenceC.add(i + 1, sSequenceFinale); //L'itération est ajoutée au Vector des itérations
		}
	}
	
}
