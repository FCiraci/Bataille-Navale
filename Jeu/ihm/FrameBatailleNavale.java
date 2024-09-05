package Jeu.	ihm;
import javax.swing.*;

import Jeu.Controleur;

import java.awt.*;


public class FrameBatailleNavale extends JFrame{
	
	private PanelBateau panelBateau;
	private Controleur ctrl;
	private Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	private double largeur = tailleEcran.getWidth();
	private double longueur = tailleEcran.getHeight();

	public FrameBatailleNavale() {
		System.out.println("Initialisation de la Frame...");
		this.ctrl = new Controleur();
		this.setTitle("Bataille Navale");
		this.setSize(550,1000);
		this.setLocation((int)longueur/2+90, 0 );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.panelBateau = new PanelBateau(this.ctrl);
		this.add(this.panelBateau);
	
		this.setVisible(true);
		System.out.println("La Frame devrait être visible.");
	}

	public static void main(String[] args) {
		new FrameBatailleNavale();
	}
}
