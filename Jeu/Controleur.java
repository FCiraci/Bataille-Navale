package Jeu;

import Jeu.metier.*;

public class Controleur {
    
    private Plateau plateauJ, plateauBot;
    private Bateau torpilleur, croiseur, cuirasse, porteAvion;
    private Bateau[] tabBateau;

    public Controleur() {
        this.plateauJ = new Plateau();
        this.plateauBot = new Plateau();
        this.torpilleur = new Bateau(2, 2, 2, "Torpilleur");
        this.croiseur = new Bateau(3, 3, 2, "Croiseur");
        this.cuirasse = new Bateau(4, 4, 1, "Cuirassé");
        this.porteAvion = new Bateau(5, 5, 1, "Porte-Avion");
        this.tabBateau = new Bateau[] {this.torpilleur, this.croiseur, this.cuirasse, this.porteAvion};
    }

    public void initPlateauJ() {
        System.out.println("Initialisation du plateau du Joueur...");
        this.plateauJ.initPlateau();
        this.plateauJ.placerBateau(this.tabBateau);
        System.out.println("Plateau du joueur initialisé et les bateau sont placé.");
    }

    public void initPlateauBot() {
        System.out.println("Initialisation du plateau du BOT...");
        this.plateauBot.initPlateau();
        this.plateauBot.placerBateau(this.tabBateau);
        System.out.println("Plateau du BOT initialisé et les bateau sont placé.");
    }

    public int getNbLigne() {
        return this.plateauJ.getNbLigne();
    }

    public int getNbColonne() {
        return this.plateauJ.getNbColonne();
    }

    public Case getCaseJoueur(int i, int j) {
        return this.plateauJ.getCase(i, j);
    }
    
    public Case getCaseBot(int i, int j)
    {
        return this.plateauBot.getCase(i, j);
    }

    public void setCaseJoueur(int i, int j, Case nvlCase) {
        this.plateauJ.setCase(i, j, nvlCase); 
    }
    public void setCaseBot(int i, int j, Case nvlCase) {
        this.plateauBot.setCase(i, j, nvlCase); 
    }

    public boolean toucher(int i, int j) {
        boolean bOk = false;
        if (getCaseBot(i, j).getValeur() >= 2 && getCaseBot(i, j).getValeur() <= 5) {
            bOk = true;
            plateauJ.setCase(i, j, new Case(0));
            System.out.println("Bateau touché en position " + i + " " + j);
        }
        return bOk;
    }

    public String toString() {
        return plateauJ.toString();
    }
}
