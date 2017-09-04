package structure_prog;

import java.util.Vector;

public class Sequence {

	private GrammaireFormelle gfMaGF;
	private Vector<String> vSequenceC=new Vector<String>(); //Regroupe sous formes de String les it�rations cr��es
	private int iIteration; //Numero de l'iteration � afficher
	private float fAngle;
	private float fTailleTrait;
	
	//Constructeur utilis� pour l'application finale
	public Sequence(GrammaireFormelle gfMaGFP) {
		iIteration = 0;
		gfMaGF=gfMaGFP;
		fAngle=(float)90;
		fTailleTrait=10;
	}
	
	//Constructeur utilis� pour les tests
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

	// M�thode qui pr�pare un nombre d'it�ration maxi (iIterationMax) de s�quences qui pourront �tre affich�es
	public void iterer() {
		int i, j, k;
		boolean bEstDansUneRegle;
		String sSequenceFinale;
		char cCurrentCaract;
		vSequenceC.add(gfMaGF.getAxiome()); // La s�quence 0 est l'axiome de d�part
		
		for (i = 0; i < gfMaGF.getIterationMax(); i++) {
			String sCurrentSequence = vSequenceC.get(i); //It�ration qui va �tre ajout�e
			sSequenceFinale = "";

			for (j = 0; j < sCurrentSequence.length(); j++) {
				cCurrentCaract = sCurrentSequence.charAt(j); //Teste les caract�res un par un
				bEstDansUneRegle = false; 
				for (k = 0; k < gfMaGF.getNbRegles(); k++) { 
					if (cCurrentCaract == gfMaGF.getVRegles().get(k).getCaractInit()) { // Teste si le caract�re est une variable pour chacune des r�gles
						sSequenceFinale=sSequenceFinale+gfMaGF.getVRegles().get(k).getTransfo(); //Le caract�re modifi� est ajout� � la s�quence
						bEstDansUneRegle = true;
					}
				}
				if (bEstDansUneRegle == false) {
					sSequenceFinale=sSequenceFinale+cCurrentCaract; //Le caract�re non-modifi� est ajout� � la s�quence
				}
			}
			vSequenceC.add(i + 1, sSequenceFinale); //L'it�ration est ajout�e au Vector des it�rations
		}
	}
	
}
