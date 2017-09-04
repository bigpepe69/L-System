package structure_prog;

import java.util.Vector;
import fenetres.*;

public class GrammaireFormelle {

	private String sAxiome; // Chaine de départ pour les itérations
	private Vector<Regle> vRegles = new Vector<Regle>(); //Vector des règles
	private int iIterationMax; // Nombre d'itération à calculer avant l'affichage
	private int iNbRegles; //Nombre de règles
	private FenetreNbRegles fenNR; // Fenêtre pour configurer les regles
	private FenetrePrincipale fenP;
	private boolean bValCorrectes; // Booléen qui est vrai si les paramètres utilisateurs sont corrects
	
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

	//Méthode qui ajoute l'ensemble des règles à définir
	public void ajouterRegles(char cCaractInitUtilP, String sTransfoUtilP) {
		Regle rRegle = new Regle(); // Règle qui va être ajoutée
		char cCaractInitUtil = cCaractInitUtilP; // La variable de cette règle
		String sTransfoUtil = sTransfoUtilP; // La fonction de cette règle

		rRegle.setCCaractInit(cCaractInitUtil); // La variable entrée par l'utilisateur est ajoutée à la règle
		rRegle.setSTransfo(sTransfoUtil); // La fonction entrée par l'utilisateur est ajoutée à la règle
		vRegles.add(rRegle); // La règle est ajoutée à l'ensemble des règles
	}
	
	//Pour le petit tracer fait par seqTempo	
	public void recupValTempo(){
		sAxiome=fenP.recupValAxiome();
		iNbRegles=fenP.recupValNbRegle();
		iIterationMax = 1;
	}
	
	//Méthode qui récupère si possible les valeurs utilisateurs sinon revoie -1 (ou w pour une chaine de caractères)
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
