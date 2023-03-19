package metier;

public interface ListePersonnelTeste {
    void add(PersonnelTeste val);
    PersonnelTeste get(int categorie, int indice);
    int getNbCategories();
    int nombre(int categorie);
    double pourcentageDePositifs(int categorie);
}