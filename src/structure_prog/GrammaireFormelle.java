package structure_prog;

import java.util.Vector;
import fenetres.*;

public class GrammaireFormelle {

	private String sAxiome; // Chaine de d�part pour les it�rations
	private Vector<Regle> vRegles = new Vector<Regle>(); //Vector des r�gles
	private int iIterationMax; // Nombre d'it�ration � calculer avant l'affichage
	private int iNbRegles; //Nombre de r�gles
	private FenetreNbRegles fenNR; // Fen�tre pour configurer les regles
	private FenetrePrincipale fenP;
	private boolean bValCorrectes; // Bool�en qui est vrai si les param�tres utilisateurs sont corrects
	
	//Constructeur
	public GrammaireFormelle(FenetrePrincipale fenPParam) {
		fenP=fenPParam;
		bValCorrectes=false;
		iIterationMax = -1;
	}
	
	public void creerFenNR(){
		fenNR = new FenetreNbRegles(iNbRegles,this);
	}
	
	public FenetreNbRegles getFenNR(){
		return fenNR;
	}
	
	public FenetrePrincipale getFenP(){
		return fenP;
	}

	public void setAxiome(String sAxiomeParam) {
		sAxiome = sAxiomeParam;
	}

	public String getAxiome() {
		return sAxiome;
	}

	public void setIterationMax(int iIterationParam) {
		iIterationMax = iIterationParam;
	}

	public int getIterationMax() {
		return iIterationMax;
	}

	public int getNbRegles() {
		return iNbRegles;
	}

	public void setNbRegles(int nbReglesParam) {
		iNbRegles = nbReglesParam;
	}

	public Vector<Regle> getVRegles() {
		return vRegles;
	}

	public void setVRegles(Vector<Regle> vReglesParam) {
		vRegles = vReglesParam;
	}
	
	public boolean getValCorrectes(){
		return bValCorrectes;
	}

	//M�thode qui ajoute l'ensemble des r�gles � d�finir
	public void ajouterRegles(char cCaractInitUtilP, String sTransfoUtilP) {
		Regle rRegle = new Regle(); // R�gle qui va �tre ajout�e
		char cCaractInitUtil = cCaractInitUtilP; // La variable de cette r�gle
		String sTransfoUtil = sTransfoUtilP; // La fonction de cette r�gle

		rRegle.setCCaractInit(cCaractInitUtil); // La variable entr�e par l'utilisateur est ajout�e � la r�gle
		rRegle.setSTransfo(sTransfoUtil); // La fonction entr�e par l'utilisateur est ajout�e � la r�gle
		vRegles.add(rRegle); // La r�gle est ajout�e � l'ensemble des r�gles
	}
	
	//Pour le petit tracer fait par seqTempo	
	public void recupValTempo(){
		sAxiome=fenP.recupValAxiome();
		iNbRegles=fenP.recupValNbRegle();
		iIterationMax = 1;
	}
	
	//M�thode qui r�cup�re si possible les valeurs utilisateurs sinon revoie -1 (ou w pour une chaine de caract�res)
	public void recupValUtil() {
		sAxiome=fenP.recupValAxiome();
		iNbRegles=fenP.recupValNbRegle();
		iIterationMax=fenP.recupValIterationMax();
		
		// on verifie si les valeurs sont correctes
		if (sAxiome=="w"||iNbRegles==-1||iIterationMax==-1||fenP.recupValAngle()==-1||fenP.recupValIterationDep()==-1){
			bValCorrectes=false;
		}else{
			bValCorrectes=true;
		}
	}
	
}
