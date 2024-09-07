package Jeu.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Jeu.Controleur;

public class PanelBateau extends JPanel implements ActionListener{

    private JPanel pnlJoueur, pnlBot;
    private final int DIMENSION = 30;
    private Controleur ctrl;
    private JButton[][] tabBtnJoueur, tabBtnBot;


    public PanelBateau(Controleur ctrl) {
        this.setLayout(new GridLayout(2, 1, 20, 20));
        this.ctrl = ctrl;
        ctrl.initPlateauBot();
        ctrl.initPlateauJ();
    
        this.pnlJoueur = new JPanel();
        this.pnlJoueur.setLayout(new GridLayout(11, 11, 5, 5));
        this.pnlBot = new JPanel();
        this.pnlBot.setLayout(new GridLayout(11, 11, 5, 5));
        this.tabBtnJoueur = new JButton[11][11];
        this.tabBtnBot = new JButton[11][11];
    
        for (int i = 0; i < ctrl.getNbLigne(); i++) {
            for (int j = 0; j < ctrl.getNbColonne(); j++) {
                JButton btnCaseJoueur = new JButton();
                JButton btnCaseBot = new JButton();
    
                if (ctrl.getCaseJoueur(i, j).getValeur() == 0) {
                    btnCaseJoueur.setBackground(Color.BLUE);
                } else if (ctrl.getCaseJoueur(i, j).getValeur() == 2 || ctrl.getCaseJoueur(i, j).getValeur() == 3 || ctrl.getCaseJoueur(i, j).getValeur() == 4 || ctrl.getCaseJoueur(i, j).getValeur() == 5) {
                    btnCaseJoueur.setBackground(Color.DARK_GRAY);
                } else if (ctrl.getCaseJoueur(i, j).getValeur() == 10) {
                    btnCaseJoueur.setBackground(Color.RED);
                }
    
                if (ctrl.getCaseBot(i, j).getValeur() == 0) {
                    btnCaseBot.setBackground(Color.BLUE);
                } else if (ctrl.getCaseBot(i, j).getValeur() == 2 || ctrl.getCaseBot(i, j).getValeur() == 3 || ctrl.getCaseBot(i, j).getValeur() == 4 || ctrl.getCaseBot(i, j).getValeur() == 5) {
                    btnCaseBot.setBackground(Color.DARK_GRAY);
                } else if (ctrl.getCaseBot(i, j).getValeur() == 10) {
                    btnCaseBot.setBackground(Color.RED);
                }

                this.tabBtnJoueur[i][j] = btnCaseJoueur;
                this.tabBtnBot[i][j] = btnCaseBot;

                Dimension tailleBouton = new Dimension(DIMENSION, DIMENSION);

                btnCaseJoueur.setPreferredSize(tailleBouton);
                btnCaseBot.setPreferredSize(tailleBouton);

                btnCaseBot.addActionListener(this);

                this.pnlJoueur.add(btnCaseJoueur);
                this.pnlBot.add(btnCaseBot);
            }
        }
    
        this.add(pnlJoueur);
        this.add(pnlBot);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {

        JButton source = (JButton)e.getSource();

        for(int i = 0; i < tabBtnBot.length; i++)
        {
            for(int j = 0; j < tabBtnBot[0].length; j++)
            {
                if(source == tabBtnBot[i][j])
                {
                    if(ctrl.toucher(i, j))
                    {
                        tabBtnBot[i][j].setBackground(Color.RED);
                        System.out.println("Vous avez toucher le bateau adverse en i = " + i + " | j = " + j);
                    }
                }
            }
        }
    }
    
}
