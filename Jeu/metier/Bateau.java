package Jeu.metier;

public class Bateau {
	
	private String nom;
	private int taille;
	private int nbBateau;
	private int id;

	public Bateau(int id, int taille, int nbBateau, String nom)
	{
		this.id = id;
		this.taille = taille ;
		this.nbBateau = nbBateau;
		this.nom = nom;
	}

	public int getId() 		{return this.id;}
	public int getTaille() 	{return this.taille;}
	public int getNbBateau() {return this.nbBateau;}
	public String getNom() 	{return this.nom;}

}