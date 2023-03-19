package metier;

import domaine.Personnel;

public class PersonnelTeste {
    private Personnel personnel;
    private boolean positif;

    public PersonnelTeste(Personnel personnel, boolean positif) {
        this.personnel = personnel;
        this.positif = positif;
    }

    public String getContact() { return personnel.getContact(); }
    public boolean isPositif() { return positif; }
    public int getCategorie() {
        return personnel.getAge() < 25 ? 0 : personnel.getAge() >= 50 ? 2 : 1;
    }

    @Override
    public String toString() { return personnel.toString(); }
}