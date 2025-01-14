package view;

import model.GameObjects.Ranch;
import model.SoundPlayer;
import view.Threads.RedessineUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panneau d'interface utilisateur du jeu. (Magasin)
 */
public class GameUIPanel extends JPanel {
    private Ranch ranch;
    private int money = 0;
    private int nbsheep = 0;
    private int nbgrass = 0;

    private JLabel label_date;
    private JLabel label_coin;
    private JLabel label_sheep;
    private JLabel label_grass;

    public static int currentTypeFence = -1; // 0: horizontale, 1: verticale

    //threads
    private GameFrame frame;
    private final RedessineUI redessineUi = new RedessineUI(this);

    public void startRedessine(){
        redessineUi.start();
    }

    public void stopRedessine(){
        redessineUi.Pause();
    }

    public GameUIPanel(GameFrame frame, Ranch r){
        this.ranch = r;
        this.frame = frame;
        this.setPreferredSize(new Dimension(GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT));
        this.setLayout(null);
        this.setVisible(true);

        JPanel sous_panel = new JPanel();
        sous_panel.setBounds(0, 50, GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT/2);
//        sous_panel.setBackground(Color.LIGHT_GRAY);
        sous_panel.setBackground(new Color(150,150,100));
        this.add(sous_panel);

        JButton button_sheep = new JButton("BUY SHEEP");
        button_sheep.setBounds(0, 50, 150, 15);
        sous_panel.add(button_sheep);


        JButton button_grass = new JButton("BUY GRASS");
        button_grass.setBounds(0, 50, 150, 15);
        sous_panel.add(button_grass);

        label_date = new JLabel("date : "+ ranch.getDate());
        label_date.setBounds(0, 0, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_date.setForeground(Color.WHITE);
        this.add(label_date);

        label_coin = new JLabel("number of coins : "+ money);
        label_coin.setBounds(0, 10, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_coin.setForeground(Color.WHITE);
        this.add(label_coin);

        label_sheep = new JLabel("number of sheeps : "+ nbsheep);
        label_sheep.setBounds(0, 20, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_sheep.setForeground(Color.WHITE);
        this.add(label_sheep);

        label_grass = new JLabel("number of grasses : "+ nbgrass);
        label_grass.setBounds(0, 30, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_grass.setForeground(Color.WHITE);
        this.add(label_grass);


        //buy fence horizontale
        JButton btnFenceH = new JButton("BUY FENCE HORIZONTALE");
        btnFenceH.setBounds(0, 50, 150, 15);
        sous_panel.add(btnFenceH);

        //buy fence verticale
        JButton btnFenceV = new JButton("BUY FENCE VERTICALE");
        btnFenceV.setBounds(0, 50, 150, 15);
        sous_panel.add(btnFenceV);


        //Si cliqué bouton buy fence Horizontale, on fix TypeFence qui est 0
        btnFenceH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTypeFence = 0;
                frame.requestFocus();

            }
        });

        //Si buy fence Verticale, il est 1
        btnFenceV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTypeFence = 1;
                frame.requestFocus();

            }
        });

        // Ajout d'un écouteur pour le bouton d'achat de moutons
        button_sheep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ranch.BuySheep();
//                gamePanel.requestFocusInWindow();
                ranch.getRancher().StopAllMoveDirections();
                frame.requestFocus();
            }
        });

        // // Ajout d'un écouteur pour le bouton d'achat d'herbe
        button_grass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ranch.BuyGrass();
//                gamePanel.requestFocusInWindow();
                ranch.getRancher().StopAllMoveDirections();
                frame.requestFocus();
            }
        });



        // Définition de la couleur de fond du panneau
        setBackground(new Color(120,150,100));
    }

    // Retourne l'objet Ranch associé à cette instance de GameUIPanel
    public Ranch getRanch() {
        return ranch;
    }

    // Mise à jour des variables de l'interface utilisateur en fonction des données actuelles du ranch
    public void updateVariables(){
//        if (money < ranch.getMoney()){
//            money =  money+ (ranch.getMoney() - money)/10;
//        }  else if (money > ranch.getMoney()){
//            money = money - (money - ranch.getMoney())/10;
//        }
//        if (nbsheep < ranch.getSheepFlock().size()){
//            nbsheep++;
//        }  else if (nbsheep > ranch.getSheepFlock().size()){
//            nbsheep--;
//        }
//        if (nbgrass < ranch.getGrasses().size()){
//            nbgrass++;
//        }  else if (nbgrass > ranch.getGrasses().size()){
//            nbgrass--;
//        }

        label_date.setText("date : "+ ranch.getDate());
        money = ranch.getMoney();
        nbsheep = ranch.getSheepFlock().size();
        nbgrass = ranch.getGrasses().size();
    }

    // Efface le panneau en le remplissant d'une couleur de fond
    // Affiche les variables mises à jour dans l'interface utilisateur
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //clear the panel

        label_coin.setText("number of coins : "+ money);
        label_sheep.setText("number of sheeps : "+ nbsheep);
        label_grass.setText("number of grasses : "+ nbgrass);

    }



}