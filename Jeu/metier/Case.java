package Jeu.metier;

public class Case {
    private int valeur;

    public Case() {
        this.valeur = 10;
    }

    public Case(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return this.valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String toString() {
        return String.valueOf(this.valeur);
    }
}
