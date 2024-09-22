    package Jeu;

    import Jeu.metier.*;

    public class Controleur {
        
        private Plateau plateauJ, plateauBot;
        private Bateau torpilleur, croiseur, cuirasse, porteAvion;
        private Boolean tourJoueur = false;
        private Bateau[] tabBateauJoueur, tabBateauBot;

        public Controleur() {
            this.plateauJ = new Plateau();
            this.plateauBot = new Plateau();
            this.tabBateauJoueur = new Bateau[] {
                new Bateau(2, 2, 1, "Torpilleur", 2), 
                new Bateau(3, 2, 1, "Torpilleur", 2), 
                new Bateau(4, 3, 1, "Croiseur", 3), 
                new Bateau(5, 3, 1, "Croiseur", 3),  
                new Bateau(6, 4, 1, "Cuirassé", 4),
                new Bateau(7, 5, 1, "Porte-Avion", 5)};
            
            this.tabBateauBot = new Bateau[] {
                new Bateau(2, 2, 1, "Torpilleur", 2),
                new Bateau(3, 2, 1, "Torpilleur", 2),
                new Bateau(4, 3, 1, "Croiseur", 3),
                new Bateau(5, 3, 1, "Croiseur", 3),
                new Bateau(6, 4, 1, "Cuirassé", 4),
                new Bateau(7, 5, 1, "Porte-Avion", 5)};

        }
        
        public void initPlateauJ() {
            System.out.println("Initialisation du plateau du Joueur...");
            this.plateauJ.initPlateau();
            this.plateauJ.placerBateau(this.tabBateauJoueur);
            System.out.println("Plateau du joueur initialisé et les bateau sont placé.");
        }

        public void initPlateauBot() {
            System.out.println("Initialisation du plateau du BOT...");
            this.plateauBot.initPlateau();
            this.plateauBot.placerBateau(this.tabBateauBot);
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

        public boolean toucherBot(int i, int j) {
            boolean bOk = false;
            Case caseBot = getCaseBot(i, j);
        
            if (caseBot.getValeur() >= 2 && caseBot.getValeur() <= 7) {
                Bateau bateauToucher = tabBateauBot[chercherBateauParValeur(caseBot.getValeur(), tabBateauBot)];
                bateauToucher.toucher();
                bOk = true;
                plateauBot.setCase(i, j, new Case(10)); // Marquer la case comme touchée
                if (bateauToucher.estCouler()) {
                    System.out.println("VOUS AVEZ COULER LE " + bateauToucher.getNom().toUpperCase() + " DU BOT ! \n");
                }
            }
            return bOk;
        }
        
        public boolean toucherJoueur(int i, int j) {
            boolean bOk = false;
            Case caseJoueur = getCaseJoueur(i, j);
        
            if (caseJoueur.getValeur() >= 2 && caseJoueur.getValeur() <= 7) {
                Bateau bateauToucher = tabBateauJoueur[chercherBateauParValeur(caseJoueur.getValeur(), tabBateauJoueur)];
                bateauToucher.toucher();
                bOk = true;
                plateauJ.setCase(i, j, new Case(10)); // Marquer la case comme touchée
                if (bateauToucher.estCouler()) {
                    System.out.println("LE BOT A COULÉ VOTRE " + bateauToucher.getNom().toUpperCase() + " ! \n");
                }
            }
            return bOk;
        }

        private int chercherBateauParValeur(int valeurCase, Bateau[] tabBateau) {
            for (int i = 0; i < tabBateau.length; i++) {
                if (tabBateau[i].getId() == valeurCase) {
                    return i;
                }
            }
            return -1; // Si aucun bateau n'est trouvé (ce cas ne devrait normalement pas se produire)
        }
        

        public boolean estDejaToucher(int i, int j, boolean estBot)
        {
            boolean bToucher = false;
            if (estBot) {
                if (getCaseBot(i, j).getValeur() == 10) {
                    bToucher = true;
                }
            } else {
                if (getCaseJoueur(i, j).getValeur() == 10) {
                    bToucher = true;
                }
            }
            return bToucher;
        }
        

        public boolean estTourJoueur()
        {
            return this.tourJoueur;
        }

        public void setTourDuJoueur(boolean bool)
        {
            this.tourJoueur = bool;
        }

        public boolean aGagnerBot()
        {
            boolean gagner = false;
            int cptCouler = 0;
            for(int i = 0; i < tabBateauJoueur.length; i++)
            {
                if(tabBateauJoueur[i].estCouler())
                {   
                    cptCouler++;
                }
            }
            if(cptCouler == tabBateauJoueur.length)
            {
                System.out.println("Le BOT à gagner la partie !");
                return true;
            }
            return gagner;
        }

        public boolean aGagneJoueur()
        {
            boolean gagner = false;
            int cptCouler = 0;
            for(int i = 0; i < tabBateauBot.length; i++)
            {
                if(tabBateauBot[i].estCouler())
                {   
                    cptCouler++;
                }
            }
            if(cptCouler == tabBateauBot.length)
            {
                System.out.println("Vous avez gagner la partie !");
                return true;
            }
            return gagner;
        }

        public String toString() {
            return plateauJ.toString();
        }
    }
