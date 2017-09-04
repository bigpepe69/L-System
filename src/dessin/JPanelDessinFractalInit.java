package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;


import javax.swing.JPanel;

import structure_prog.Sequence;
import fenetres.FenetrePrincipale;

public class JPanelDessinFractalInit extends JPanel{
	
	private static final long serialVersionUID = -949717389398397306L;
	
	private FenetrePrincipale fenP;
	private Sequence seqMaSequence;
	private String sCurrentSequence;
	private float fAngle;
	private float fTailleTrait;
	private Pile pValeurX=new Pile(); //Pile qui contient les coordonnées X sauvegardées
	private Pile pValeurY=new Pile(); //Pile qui contient les coordonnées Y sauvegardées
	private Pile pValeurAngle=new Pile(); //Pile qui contient les angles sauvegardés
	private float fPositionX; //Variable de la position actuelle du trait sur l'axe x
	private float fPositionY; //Variable de la position actuelle du trait sur l'axe Y
	private float fTailleX; //Variable qui définit la fin du trait suivant l'axe X
	private float fTailleY; //Variable qui définit la fin du trait suivant l'axe Y
	private float fAngleActuel; //Variable qui contient la valeur du dernier angle

	
	//Constructeur
	public JPanelDessinFractalInit(FenetrePrincipale fenPParam) {  
		super();
		fAngle = 90;
		fTailleTrait = 50;
		sCurrentSequence = "";
		fenP = fenPParam;
	}

	
	//Méthode qui ne trace pas le fractal mais fait le calcul pour mettre les coordonnées (en X et en Y) dans un Vector de Vector (le Vector.get(0) contient les coordonnées en X et le Vector.get(2) contient les coordonnées en Y).
	public Vector<Vector<Float>> tracerFictif(float fTailleTraitP){
		fAngleActuel=0; 
		fTailleX=fTailleTraitP; 
		fTailleY=0; 
		fPositionX=0; 
		fPositionY=0;
		//On vide la pile qui contient les valeurs sauvegardées précèdement au cas ou l'utilisateur a entré plus de [ que de ]
		pValeurX.supprimerPile();
		//On vide la pile qui contient les valeurs sauvegardées précèdement au cas ou l'utilisateur a entré plus de [ que de ]
		pValeurY.supprimerPile();
		//On vide la pile qui contient les valeurs sauvegardées précèdement au cas ou l'utilisateur a entré plus de [ que de ]
		pValeurAngle.supprimerPile();
		Vector<Vector<Float>> vPosition = new Vector<Vector<Float>>();
		//Vector qui contiendra toutes les positions des points sur X
		Vector<Float> vPositionX = new Vector<Float>();
		//Vector qui contiendra toutes les positions des points sur Y
		Vector<Float> vPositionY = new Vector<Float>();
		vPositionX.add(fPositionX);
		vPositionY.add(fPositionY);
		for (int i = 0; i < sCurrentSequence.length(); i++) {
			// On traite chaque caractere un par un
			char cCurrentCaract = sCurrentSequence.charAt(i);

			if (cCurrentCaract == 'F') {
				fPositionX=fPositionX+fTailleX;
				fPositionY=fPositionY+fTailleY;
				vPositionX.add(fPositionX);
				vPositionY.add(fPositionY);
			}
			if (cCurrentCaract == 'G') {
				fPositionX=fPositionX+fTailleX;
				fPositionY=fPositionY+fTailleY;
				vPositionX.add(fPositionX);
				vPositionY.add(fPositionY);
			}
			if (cCurrentCaract == '+') {
				fAngleActuel=fAngleActuel+fAngle;
				fTailleX=(float)(fTailleTraitP*Math.cos(fAngleActuel*Math.PI/180));
				fTailleY=(float)(fTailleTraitP*Math.sin(fAngleActuel*Math.PI/180));
			}
			if (cCurrentCaract == '-') {
				fAngleActuel=fAngleActuel-fAngle;
				fTailleX=(float)(fTailleTraitP*Math.cos(fAngleActuel*Math.PI/180));
				fTailleY=(float)(fTailleTraitP*Math.sin(fAngleActuel*Math.PI/180));
			}
			if (cCurrentCaract == '[') {
				pValeurX.ajoutElement(fPositionX);
				pValeurY.ajoutElement(fPositionY);
				pValeurAngle.ajoutElement(fAngleActuel);
			}
			if (cCurrentCaract == ']') {
				fPositionX=(float)pValeurX.recupElement();
				fPositionY=(float)pValeurY.recupElement();
				fAngleActuel=pValeurAngle.recupElement();
				fTailleX=(float)(fTailleTraitP*Math.cos(fAngleActuel*Math.PI/180));
				fTailleY=(float)(fTailleTraitP*Math.sin(fAngleActuel*Math.PI/180));
			}
		}
		vPosition.add(vPositionX);
		vPosition.add(vPositionY);
		return vPosition;
	}
	
	
	//Méthode qui recherche les extremums et on les retourne dans un vector dans cet ordre : Xmin, Xmax, Ymin, Ymax
	public Vector<Float> rechercheExtremum(Vector<Vector<Float>>vPositionP){
		float fMaxX, fMaxY, fMinX, fMinY;
		Vector<Float> vExtremum = new Vector<Float>();
		fMaxX=vPositionP.get(0).get(0);
		fMinX=vPositionP.get(0).get(0);
		fMaxY=vPositionP.get(1).get(0);
		fMinY=vPositionP.get(1).get(0);
		for (int i=1 ; i < vPositionP.get(0).size();i++){
			if (vPositionP.get(0).get(i)<fMinX){
				fMinX=vPositionP.get(0).get(i);
			}
			if (vPositionP.get(0).get(i)>fMaxX){
				fMaxX=vPositionP.get(0).get(i);
			}
			if (vPositionP.get(1).get(i)<fMinY){
				fMinY=vPositionP.get(1).get(i);
			}
			if (vPositionP.get(1).get(i)>fMaxY){
				fMaxY=vPositionP.get(1).get(i);
			}
		}
		vExtremum.add(fMinX);
		vExtremum.add(fMaxX);	
		vExtremum.add(fMinY);	
		vExtremum.add(fMaxY);
		return vExtremum;		
	}
	
	
	//Méthode qui calcule l'écart entre la valeur max et la valeur min d'une coordonnée (X ou Y)
	public float ecartMax(float fMinP, float fMaxP){
		return fMaxP-fMinP;
	}

	
	//Méthode qui calcule la coordonnée de départ pour que le fractal soit au centre du JPanelDessin
	public Vector<Float> coordonneDepart(float fEcartXP, float fEcartYP, float fMinXP, float fMinYP, Vector<Vector<Float>> vPositionP){
		Vector<Float> vPositionDepart = new Vector<Float>();
		
		//On met le cas ou fMinX est inférieur à notre point de départ à part
		if (fMinXP<vPositionP.get(0).get(0)){
			fPositionX=(float) ((this.getSize().getWidth() - (fEcartXP))/2 + vPositionP.get(0).get(0)-fMinXP);
		}else{
			fPositionX=(float) ((this.getSize().getWidth() - (fEcartXP))/2);
		}
		
		//On met le cas ou fMinY est inférieur à notre point de départ à part
		if (fMinYP<vPositionP.get(1).get(0)){
			fPositionY=(float) ((this.getSize().getHeight() - (fEcartYP))/2 + vPositionP.get(1).get(0)-fMinYP);
		}else{
			fPositionY=(float) ((this.getSize().getHeight() - (fEcartYP))/2);
		}
		vPositionDepart.add(fPositionX);
		vPositionDepart.add(fPositionY);
		return vPositionDepart;
	}
	
	
	//Méthode qui permet de calculer la meilleur taille de trait pour que notre fractal soit visible sur au maximum 80% du JPanelDessin
	public float tailleDuTrait(float fEcartXP, float fEcartYP){
		float fTailleTrait=50;
		if (((this.getSize().getWidth())-fEcartXP)/fEcartXP<((this.getSize().getHeight())-fEcartYP)/fEcartYP){
			fTailleTrait=(float)(0.8*fTailleTrait*(this.getSize().getWidth())/(fEcartXP));
		}else{
			fTailleTrait=(float)(0.8*fTailleTrait*(this.getSize().getHeight())/(fEcartYP));
		}
		return fTailleTrait;
	}
	
	
	//Méthode qui trace le Fractal initial dans le cadre de gauche
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Vector<Vector<Float>> vPositions = new Vector<Vector<Float>>();
		Vector<Float> vExtremum = new Vector<Float>();
		Vector<Float> vDepart = new Vector<Float>();
		float fEcartX, fEcartY;

		// Couleur du JPanelDessin = Blanc
		this.setBackground(Color.white);
		
		//On recherche les valeurs max et min de notre Fractal grace à la méthode tracerFictif qui renvoie les vectors des positions en X et Y
		vPositions=tracerFictif(50);
		vExtremum=rechercheExtremum(vPositions);
	
		//On cherche la valeur de la taille du trait pour que notre fractal soit visible au mieux
		fEcartX=ecartMax(vExtremum.get(0),vExtremum.get(1));
		fEcartY=ecartMax(vExtremum.get(2),vExtremum.get(3));
		fTailleTrait=tailleDuTrait(fEcartX, fEcartY);

		//On  cherche a centrer notre fractal dans notre cadre avec la nouvelle taille de trait.
		vPositions=tracerFictif(fTailleTrait);	
		vExtremum=rechercheExtremum(vPositions);

		//On recherche maintenant la valeur de début pour que notre fractal soit au centre
		fEcartX=ecartMax(vExtremum.get(0),vExtremum.get(1));
		fEcartY=ecartMax(vExtremum.get(2),vExtremum.get(3));
		vDepart=coordonneDepart(fEcartX, fEcartY, vExtremum.get(0), vExtremum.get(2), vPositions);
		fPositionX=vDepart.get(0);
		fPositionY=vDepart.get(1);
		fTailleX=fTailleTrait;
		fTailleY=0;
		fAngleActuel=0;
		pValeurX.supprimerPile();//On vide la pile qui contient les valeurs sauvegardées précèdement au cas ou l'utilisateur a entré plus de [ que de ]
		pValeurY.supprimerPile();//On vide la pile qui contient les valeurs sauvegardées précèdement au cas ou l'utilisateur a entré plus de [ que de ]
		pValeurAngle.supprimerPile();//On vide la pile qui contient les valeurs sauvegardées précèdement au cas ou l'utilisateur a entré plus de [ que de ]
		
		//On trace enfin notre fractal final au centre du JPanelDessin et avec un Zoom au mieux
		for (int i = 0; i < sCurrentSequence.length(); i++) { 
			char cCurrentCaract = sCurrentSequence.charAt(i); //Traite chaque caractere un par un

			if (cCurrentCaract == 'F') {
				g.setColor(Color.black);
				g.drawLine(Math.round(fPositionX),Math.round(fPositionY),Math.round(fPositionX+fTailleX), Math.round(fPositionY+fTailleY));
				fPositionX=fPositionX+fTailleX;
				fPositionY=fPositionY+fTailleY;
			}
			if (cCurrentCaract == 'G') {
				g.setColor(Color.black);
				g.drawLine(Math.round(fPositionX),Math.round(fPositionY),Math.round(fPositionX+fTailleX), Math.round(fPositionY+fTailleY));
				fPositionX=fPositionX+fTailleX;
				fPositionY=fPositionY+fTailleY;
			}
			if (cCurrentCaract == '+') {
				fAngleActuel=fAngleActuel+fAngle;
				fTailleX=(float)(fTailleTrait*Math.cos(fAngleActuel*Math.PI/180));
				fTailleY=(float)(fTailleTrait*Math.sin(fAngleActuel*Math.PI/180));
			}
			if (cCurrentCaract == '-') {
				fAngleActuel=fAngleActuel-fAngle;
				fTailleX=(float)(fTailleTrait*Math.cos(fAngleActuel*Math.PI/180));
				fTailleY=(float)(fTailleTrait*Math.sin(fAngleActuel*Math.PI/180));
			}
			if (cCurrentCaract == '[') {
				pValeurX.ajoutElement(fPositionX);
				pValeurY.ajoutElement(fPositionY);
				pValeurAngle.ajoutElement(fAngleActuel);
			}
			if (cCurrentCaract == ']') {
				fPositionX=(float)pValeurX.recupElement();
				fPositionY=(float)pValeurY.recupElement();
				fAngleActuel=pValeurAngle.recupElement();
				fTailleX=(float)(fTailleTrait*Math.cos(fAngleActuel*Math.PI/180));
				fTailleY=(float)(fTailleTrait*Math.sin(fAngleActuel*Math.PI/180));
			}
		}
	}
	
	
	//Méthode qui va chercher les paramètres utiles lors d'une selection dans la comboBox
	public void configValTempo(){
		seqMaSequence=fenP.getSequenceTempo();
		fAngle = seqMaSequence.getAngle();
		sCurrentSequence = seqMaSequence.getSequenceC().get(1);
	}
	
	
	//Méthode qui réinitialise les valeurs lors de la sélection de "Personaliser..." dans la comboBox
	public void configValZero(){
		fAngle = 0;
		sCurrentSequence = "";
	}
	
	
	//Méthode qui va chercher les paramètres utiles lors de l'appuie sur le bouton "Tracer"
	public void configValFinal(){
		seqMaSequence=fenP.getMaSequence();
		fAngle = seqMaSequence.getAngle();
		sCurrentSequence = seqMaSequence.getSequenceC().get(1);
	}
}
