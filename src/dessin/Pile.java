package dessin;

import java.util.Vector;

public class Pile {

	private Vector<Float> vLifo = new Vector<Float>();

	// M�thode qui permet d'ajouter un �l�ment � la pile
	public void ajoutElement(float fElement) {
		vLifo.add(fElement);
	}

	//M�thode qui permet de r�cup�rer le dernier �l�ment ajout�
	public float recupElement() {
		float fElement = vLifo.lastElement();
		vLifo.remove(vLifo.size() - 1);
		return fElement;
	}

	//M�thode qui permet de supprimer tous les �l�ments de la pile
	public void supprimerPile() {
		vLifo.clear();
	}

}
