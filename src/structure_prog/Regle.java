package structure_prog;

//Classe regroupant les propriétés des règles (varibable+transformation)
public class Regle {
	private char cCaractInit;
	private String sTransfo;

	//Constructeur
	public Regle() {
		cCaractInit = ' ';
		sTransfo = "";
	}

	public char getCaractInit() {
		return cCaractInit;
	}

	public void setCCaractInit(char cCaractInitParam) {
		cCaractInit = cCaractInitParam;
	}

	public String getTransfo() {
		return sTransfo;
	}

	public void setSTransfo(String sTransfoParam) {
		sTransfo = sTransfoParam;
	}
	
}
