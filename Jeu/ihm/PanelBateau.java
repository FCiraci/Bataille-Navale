package Jeu.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Jeu.Controleur;

public class PanelBateau extends JPanel implements ActionListener{

    private JPanel pnlJoueur, pnlBot;
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
                } 
                else if (ctrl.getCaseJoueur(i, j).getValeur() >= 2 && ctrl.getCaseJoueur(i, j).getValeur() <= 7 ) {
                    btnCaseJoueur.setBackground(Color.DARK_GRAY);
                } 
                else if (ctrl.getCaseJoueur(i, j).getValeur() == 10) {
                    btnCaseJoueur.setBackground(Color.RED);
                }
    
                if (ctrl.getCaseBot(i, j).getValeur() == 0) {
                    btnCaseBot.setBackground(Color.BLUE);
                } 
                else if (ctrl.getCaseBot(i, j).getValeur() >= 2 && ctrl.getCaseBot(i, j).getValeur() <= 7 ) {
                    btnCaseBot.setBackground(Color.BLUE);
                } 
                else if (ctrl.getCaseBot(i, j).getValeur() == 10) {
                    btnCaseBot.setBackground(Color.RED);
                }

                this.tabBtnJoueur[i][j] = btnCaseJoueur;
                this.tabBtnBot[i][j] = btnCaseBot;

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
                    if(ctrl.toucherBot(i, j))
                    {
                        ctrl.setTourDuJoueur(true);
                        tabBtnBot[i][j].setBackground(Color.RED);
                        tabBtnBot[i][j].setEnabled(false);
                        System.out.println("Vous avez toucher le bateau adverse en i = " + i + " | j = " + j +".\n");
                    }
                    else
                    {
                        if(!ctrl.estDejaToucher(i, j, true))
                        {
                            ctrl.setTourDuJoueur(false);
                            tabBtnBot[i][j].setBackground(Color.GRAY);
                            tabBtnBot[i][j].setEnabled(false);
                            System.out.println("Vous avez tirer en i = " + i + " | j = " + j + ".\n Mais vous avez raté.\n");
                        }
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        jouerBot();
                    }
                }
            }
        }
        if(ctrl.aGagneJoueur())
        {
            JOptionPane.showMessageDialog(this, "Félicitations, vous avez gagné la partie !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
        if(ctrl.aGagnerBot())
        {
            JOptionPane.showMessageDialog(this, "Le BOT a gagné la partie !", "Défaite", JOptionPane.INFORMATION_MESSAGE);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    public void jouerBot()
    {
        int i, j;

        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        i = (int) (Math.random() * ctrl.getNbLigne());
        j = (int) (Math.random() * ctrl.getNbColonne());

        if(!ctrl.estTourJoueur())
        {
            if(ctrl.toucherJoueur(i, j))
            {
                ctrl.setTourDuJoueur(false);
                tabBtnJoueur[i][j].setBackground(Color.RED);
                tabBtnJoueur[i][j].setEnabled(false);
                System.out.println("Le BOT à toucher votre beateau en i = " + i + " | j = " + j +".\n");
                jouerBot();
            }
            else
            {
                if(!ctrl.estDejaToucher(i, j, false))
                {
                    ctrl.setTourDuJoueur(true);
                    tabBtnJoueur[i][j].setBackground(Color.GRAY);
                    System.out.println("Le BOT à tirer en i = " + i + " | j = " + j + ".\n Mais il a raté. \n"); 
                }
                tabBtnJoueur[i][j].setEnabled(false);
            }
        }
        if(ctrl.aGagneJoueur())
        {
            JOptionPane.showMessageDialog(this, "Félicitations, vous avez gagné la partie !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
        if(ctrl.aGagnerBot())
        {
            JOptionPane.showMessageDialog(this, "Le BOT a gagné la partie !", "Défaite", JOptionPane.INFORMATION_MESSAGE);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }
    
}
