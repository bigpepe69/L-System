package dessin;

import java.util.Vector;

public class Pile {

	private Vector<Float> vLifo = new Vector<Float>();

	// Méthode qui permet d'ajouter un élèment à la pile
	public void ajoutElement(float fElement) {
		vLifo.add(fElement);
	}

	//Méthode qui permet de récupérer le dernier élèment ajouté
	public float recupElement() {
		float fElement = vLifo.lastElement();
		vLifo.remove(vLifo.size() - 1);
		return fElement;
	}

	//Méthode qui permet de supprimer tous les élèments de la pile
	public void supprimerPile() {
		vLifo.clear();
	}

}
