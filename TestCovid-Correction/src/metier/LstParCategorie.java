package metier;

import outils.TestStack;

import java.util.HashMap;


public class LstParCategorie implements ListePersonnelTeste {
    private final int nbCategories = 3;
    private HashMap<Integer, TestStack<PersonnelTeste>> hm;
    private int[] nbPositifs = new int[nbCategories];

    // TODO: créer 3 (selon nbCategories) listes pour stocker les résultats par catégorie (donc pour les 3 catégories)
    public LstParCategorie() {
        hm = new HashMap<>();
        for (int i=0; i<nbCategories; i++) {
            hm.put(i, new TestStack<>(100));
        }
    }

    public int getNbCategories() {
        return nbCategories;
    }

    // TODO: définir dans quelle catégorie devra être répertoriée cette personne selon son âge ==> categorie = age<25 ? 0 : age>=50 ? 2 : 1;
    // TODO: ajouter ces infos dans la bonne liste selon l’âge/la catégorie de la personne
    @Override
    public void add(PersonnelTeste p) {
        int categorie = p.getCategorie();
        hm.get(categorie).push(p);
        if (p.isPositif()) {
            nbPositifs[categorie]++;
        }
    }

    @Override
    public PersonnelTeste get(int categorie, int indice) throws IllegalArgumentException {
        if(indice > hm.get(categorie).getElementCount()){
            PersonnelTeste pt = hm.get(categorie).peek();
            hm.get(categorie).pop();
            return pt;
        }
        else
            throw new IllegalArgumentException();

    }

    @Override
    public int nombre(int categorie) {
        return hm.get(categorie).getElementCount();
    }

    @Override
    public double pourcentageDePositifs(int categorie) {
        return (double)nbPositifs[categorie] * 100 / nombre(categorie);
    }
}