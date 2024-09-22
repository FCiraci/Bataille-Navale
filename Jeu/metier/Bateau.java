package Jeu.metier;

public class Bateau {
	
	private String nom;
	private int taille;
	private int nbBateau;
	private int id;
	private int pv;

	public Bateau(int id, int taille, int nbBateau, String nom, int pv)
	{
		this.id = id;
		this.taille = taille ;
		this.nbBateau = nbBateau;
		this.nom = nom;
		this.pv = pv;
	}

	public int getId() 			{return this.id;}
	public int getTaille() 		{return this.taille;}
	public int getNbBateau()	{return this.nbBateau;}
	public String getNom() 		{return this.nom;}
	public int getPV()			{return this.pv;}
	public void toucher()		{this.pv--;}
	public boolean estCouler()	{return this.pv == 0;}
	
}