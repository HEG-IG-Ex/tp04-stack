package metier;

import dao.Bdd;
import domaine.Personnel;

import java.util.List;

public class Main {

    /* Méthode principale : teste tout le personnel, affiche les taux, puis contacte le personnel positif d'une catégorie choisie aléatoirement */
    public static void main(String[] args) {
        ListePersonnelTeste lst = testerToutLePersonnel(Bdd.getLstPersonnel());
        afficherTauxParCategorie(lst);
        int categorie = (int)(Math.random() * 3);   // choisi aléatoirement une catégorie entre 0 et 2 (pour contacter ces personnes)
        contacterPersonnelPositif(lst, 2);
    }

    /* Teste tout le personnel, conserve le résultat, puis retourne une collection contenant tous les résultats.
    *  Pour des questions de performance, il est indispensable que la collection retournée comporte 3 listes distinctes,
    *  soit une liste par catégorie (selon l'âge de la personne) ==> categorie = age<25 ? 0 : age>=50 ? 2 : 1; */
    private static ListePersonnelTeste testerToutLePersonnel(List<Personnel> lstPers) {
        ListePersonnelTeste lst = new LstParCategorie();     // obtenir un tableau contenant 3 liste (1 liste par catégorie)
        for (Personnel pers : lstPers) {
            // TODO: tester la personne ==> appeler outils.Test.resultat(noPersonne)
            // TODO: conserver le résultat du test de la personne avec (et non pas dans !) la classe Personnel !
            // TODO: ajouter ces infos dans lst, par catégorie (selon l'âge de la personne) ==> categorie = age<25 ? 0 : age>=50 ? 2 : 1;
            try {
                PersonnelTeste p = new PersonnelTeste(pers, outils.Test.resultat(pers.getNo()));
                lst.add(p);
            } catch (outils.Test.PersonneInconnueException e) { }
        }
        return lst;
    }

    /* Affiche le pourcentage de personnel testé positif de chaque catégorie.
    *  Pour des questions de performance, le pourcentage doit pouvoir être retrouvé(calculé) SANS PARCOURIR la liste des personnes testées !
    *  Comme il doit y avoir une liste de personnes par catégorie (voir la méthode testerToutLePersonnel), le taux est calculé simplement
    *  en divisant le nombre de positifs par le nombre de personnes dans la liste */
    private static void afficherTauxParCategorie(ListePersonnelTeste lst) {
        for (int cat=0; cat<lst.getNbCategories(); cat++) {
            System.out.println("Pourcentage de personnel testé positif dans la catégorie " + cat + " : " + lst.pourcentageDePositifs(cat) + "%");
        }
    }

    /* Récupère et affiche le contact des personnes positives d'une certaine catégorie (reçue en paramètre) */
    private static void contacterPersonnelPositif(ListePersonnelTeste lst, int categorie) {
        System.out.println("Personnes positives de la catégorie " + categorie + (categorie==0 ? " (-25 ans)" : categorie==1 ? " (25-49 ans)" : " (50+ ans)"));
        // TODO: boucle de récupération des personnes testées d'une certaine catégorie ==> appeler lst.get(categorie)
        // TODO: vérifier si cette personne est positive (cette information a du être conservée avec (et non pas dans !) Personnel)
        // TODO: si oui, afficher la personne ainsi que ses données de contact ==> utiliser getContact()
        int initialElementCount = lst.nombre(categorie);

        // Not sur of the behavior wished for the iteration on a stack with the index i
        // As element can only be access from emptying it
        // Not really using it in the lst.get(cat, i)
        for (int i=0; i<initialElementCount; i++) {
            try{
                PersonnelTeste p = lst.get(categorie, i);
                if (p.isPositif()) { System.out.println(p + " ==> " + p.getContact()); }
            } catch (IllegalArgumentException iae){
                System.err.println("The element wished doesn't exist");
            }

        }
    }
}