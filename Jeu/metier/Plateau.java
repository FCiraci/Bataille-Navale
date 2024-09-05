package Jeu.metier;

public class Plateau {
	
	private Case[][] plateauInitiale;
    private char[] direction;

	public Plateau() {
		this.plateauInitiale = new Case[11][11];
		this.initPlateau();
        this.direction = new char[] {'H', 'B', 'G', 'D'}; // Déplacement ici car utilisé par placerBateau
	}

	public void initPlateau() {
		for(int i = 0; i < getNbLigne(); i++) {
			for(int j = 0; j < getNbColonne(); j++) {
				this.setCase(i, j, new Case(0));
			}
		}
	}

	public int getNbLigne() {
        return this.plateauInitiale.length;
    }

	public int getNbColonne() {
        return this.plateauInitiale[0].length;
    }

	public Case getCase(int i, int j) {
        return this.plateauInitiale[i][j];
    }

	public void setCase(int i, int j, Case nvlCase) {
        this.plateauInitiale[i][j] = nvlCase;
    }

    // Méthode pour placer les bateaux sur le plateau
    public void placerBateau(Bateau[] tabBateau) {
        for (Bateau bateau : tabBateau) {
            for (int n = 0; n < bateau.getNbBateau(); n++) {
                boolean placer = false;
                while (!placer) {
                    int i = (int) (Math.random() * 11);
                    int j = (int) (Math.random() * 11);
                    char dir = direction[(int) (Math.random() * direction.length)];
                    if (peutPlacerBateau(bateau, i, j, dir)) {
                        placementBateau(bateau, i, j, dir);
                        placer = true;
                    }
                }
            }
        }
    }

    // Méthode pour vérifier si un bateau peut être placé à une position donnée
    public boolean peutPlacerBateau(Bateau bateau, int i, int j, char dir) {
        int taille = bateau.getTaille();
        switch (dir) {
            case 'H':
                if (i - taille < -1) return false;
                for (int k = 0; k < taille; k++) {
                    if (this.getCase(i - k, j).getValeur() != 0) return false;
                }
                break;
            case 'B':
                if (i + taille >= getNbLigne()) return false;
                for (int k = 0; k < taille; k++) {
                    if (this.getCase(i + k, j).getValeur() != 0) return false;
                }
                break;
            case 'G':
                if (j - taille < -1) return false;
                for (int k = 0; k < taille; k++) {
                    if (this.getCase(i, j - k).getValeur() != 0) return false;
                }
                break;
            case 'D':
                if (j + taille >= getNbColonne()) return false;
                for (int k = 0; k < taille; k++) {
                    if (this.getCase(i, j + k).getValeur() != 0) return false;
                }
                break;
        }
        return true;
    }

    // Méthode pour placer effectivement le bateau sur le plateau
    public void placementBateau(Bateau bateau, int i, int j, char dir) {
        int taille = bateau.getTaille();
        for (int k = 0; k < taille; k++) {
            switch (dir) {
                case 'H':
                    this.setCase(i - k, j, new Case(bateau.getId()));
                    break;
                case 'B':
                    this.setCase(i + k, j, new Case(bateau.getId()));
                    break;
                case 'G':
                    this.setCase(i, j - k, new Case(bateau.getId()));
                    break;
                case 'D':
                    this.setCase(i, j + k, new Case(bateau.getId()));
                    break;
            }
        }
    }
}
