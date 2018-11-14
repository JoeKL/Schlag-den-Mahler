package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Random;

public class Main {

    private static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    private static GameCard[] CardDeck;
    private static GameCard[] Extra;
    private static int wins;
    private static int losses;
    private static int buttonCounter;
    private static int cardCounter;

    static {
        Extra = new GameCard[4];
        CardDeck = new GameCard[52];
        wins = 0;
        losses = 0;
        buttonCounter = 0;
        cardCounter = 0;
    }

    private  static void IniCards(){//Initialisieren der Karten
        for(int i = 0; i < 13; i++) {
            CardDeck[i] = new GameCard((i+2), "Rot", "res/card" + i + ".png", "Card" + i);
        }
        for(int i = 0; i < 13; i++) {
            CardDeck[(i+13)] = new GameCard((i+2), "Rot", "res/card" + (i+13) + ".png", "Card" + (i+13));
            //System.out.println("Karte " + (i+13));
        }
        for(int i = 0; i < 13; i++) {
            CardDeck[(i+26)] = new GameCard((i+2), "Schwarz","res/card" + (i+26) + ".png", "Card" + (i+26));
            //System.out.println("Karte " + (i+26));
        }
        for(int i = 0; i < 13; i++) {
            CardDeck[(i + 39)] = new GameCard((i + 2), "Schwarz", "res/card" + (i + 39) + ".png", "Card" + (i + 39));
            //System.out.println("Karte " + (i+39));
        }
        shuffleDeck();
    }

    private static void IniExtra(){
        for(int i = 0; i <= 3; i++) {
            //System.out.println("Karte " + 52);
            if(i == 0)
                Extra[0] = new GameCard(0, "Sonder", "res/deck.png", "Deck");
            else
                Extra[i] = new GameCard(i, "Sonder","res/pos" + i + ".png", "Pos" + i);
            //System.out.println("Karte " + (i+52));
        }
    }

    private static void shuffleDeck(){
        Random rng = new Random();
        for (int i = 0; i< 1000000; i++){
            swap(rng.nextInt(51),rng.nextInt(51));
        }
    }

    private static void swap(int a, int b){
        GameCard holder = CardDeck[a];
        CardDeck[a] = CardDeck[b];
        CardDeck[b] = holder;
    }

    private static void setCard(int a, int b){
        Extra[a] = CardDeck[b];
    }

    private static void Game(){
        IniCards();
        IniExtra();


        JFrame MainFrame = new JFrame();
        MainFrame.setTitle("Schlag den Mahler");
        MainFrame.setResizable(false);
        MainFrame.setSize(630,345);
        JRadioButton redButton = new JRadioButton("Rot",true);
        JRadioButton blackButton = new JRadioButton("Schwarz");
        JRadioButton higherButton = new JRadioButton("Kleiner", true);
        JRadioButton lowerButton = new JRadioButton("Größer");
        JRadioButton inButton = new JRadioButton("Innen", true);
        JRadioButton outButton = new JRadioButton("Außen");


        ButtonGroup Question1 = new ButtonGroup();
        Question1.add(redButton);
        Question1.add(blackButton);

        JPanel radioPanel1 = new JPanel(new GridLayout(0, 1));
        radioPanel1.add(redButton);
        radioPanel1.add(blackButton);
        radioPanel1.setBorder(BorderFactory.createLineBorder(Color.black,1));


        ButtonGroup Question2 = new ButtonGroup();
        Question2.add(higherButton);
        Question2.add(lowerButton);

        JPanel radioPanel2 = new JPanel(new GridLayout(0, 1));
        radioPanel2.add(higherButton);
        radioPanel2.add(lowerButton);
        radioPanel2.setBorder(BorderFactory.createLineBorder(Color.black,1));


        ButtonGroup Question3 = new ButtonGroup();
        Question3.add(inButton);
        Question3.add(outButton);

        JPanel radioPanel3 = new JPanel(new GridLayout(0, 1));
        radioPanel3.add(inButton);
        radioPanel3.add(outButton);
        radioPanel3.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JPanel Background = new JPanel();
        Background.setBackground(Color.decode("#4E9465"));

        GridLayout experimentLayout = new GridLayout(2,4,2,2);
        Background.setLayout(experimentLayout);



        JPanel Pane1 = new JPanel();
        Pane1.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        Pane1.setBackground(Color.decode("#4E9465"));
        Pane1.add(Extra[0].Card);
        Background.add(Pane1);

        JPanel Pane2 = new JPanel();
        Pane2.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        Pane2.setBackground(Color.decode("#4E9465"));
        Pane2.add(Extra[1].Card);
        Background.add(Pane2);

        JPanel Pane3 = new JPanel();
        Pane3.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        Pane3.setBackground(Color.decode("#4E9465"));
        Pane3.add(Extra[2].Card);
        Background.add(Pane3);

        JPanel Pane4 = new JPanel();
        Pane4.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        Pane4.setBackground(Color.decode("#4E9465"));
        Pane4.add(Extra[3].Card);
        Background.add(Pane4);


        JLabel score = new JLabel();
        //score.setBackground(Color.decode("#4E9465"));
        score.setText("Gewonnen: " + wins + " Verloren: "  + losses);

        JLabel restart = new JLabel();
        restart.setText("<html>Du hast alle Karten ausgespielt.<br/>Das Deck wird erneut gemischt.<br/>Gewonnen: " + wins + " Verloren: "  + losses + "</html>");

        JPanel dialog = new JPanel();
        //dialog.setBackground(Color.decode("#FFFFFF"));
        dialog.setMinimumSize(new Dimension(400,50));
        dialog.add(restart);
        JFrame frame = new JFrame("JOptionPane showMessageDialog component example");



        ActionListener test = e -> {
            cardCounter++;
            if(cardCounter < CardDeck.length){
                buttonCounter++;
                if(buttonCounter == 1){
                    setCard(buttonCounter, cardCounter);
                    Pane2.removeAll();
                    Pane2.add(Extra[1].Card);
                    Pane2.validate();
                    Pane2.repaint();

                    if(!Objects.equals(getSelectedButtonText(Question1), Extra[1].Color)){
                        buttonCounter = 0;
                        losses++;
                    }
                    score.setText("Gewonnen: " + wins + " Verloren: "  + losses);
                }
                if(buttonCounter == 2){
                    setCard(buttonCounter, cardCounter);

                    Pane3.removeAll();
                    Pane3.add(Extra[2].Card);
                    Pane3.validate();
                    Pane3.repaint();

                    if(Objects.equals(getSelectedButtonText(Question2), "Kleiner")){
                        if(!(Extra[1].Value >= Extra[2].Value)){
                            buttonCounter = 0;
                            losses++;

                        }
                    }else{
                        if (!(Extra[1].Value <= Extra[2].Value)) {
                            buttonCounter = 0;
                            losses++;
                        }
                    }

                    score.setText("Gewonnen: " + wins + " Verloren: "  + losses);
                }
                if(buttonCounter == 3){
                    setCard(buttonCounter, cardCounter);
                    Pane4.removeAll();
                    Pane4.add(Extra[3].Card);
                    Pane4.validate();
                    Pane4.repaint();
                    buttonCounter = 0;
                    if(((Extra[1].Value < Extra[3].Value && Extra[3].Value < Extra[2].Value&& Objects.equals(getSelectedButtonText(Question3), "Innen"))||
                            ((Extra[1].Value>Extra[3].Value&&Extra[3].Value>Extra[2].Value&& Objects.equals(getSelectedButtonText(Question3), "Innen"))))||
                            ((Extra[1].Value>Extra[3].Value&&Extra[3].Value<Extra[2].Value&& !Objects.equals(getSelectedButtonText(Question3), "Innen"))||
                                    ((Extra[1].Value<Extra[3].Value&Extra[3].Value>Extra[2].Value&& !Objects.equals(getSelectedButtonText(Question3), "Innen")))))
                    {
                        wins++;
                    }
                    else{
                        losses++;
                    }

                    score.setText("Gewonnen: " + wins + " Verloren: "  + losses);
                }
            } else {
                System.out.println("Keine Karten mehr vorhanden!");
                shuffleDeck();
                cardCounter = 0;
                System.out.println("Neu Gemischt!");
                restart.setText("<html>Du hast alle Karten ausgespielt.<br/>Das Deck wird erneut gemischt.<p/>Gewonnen: " + wins + " Verloren: "  + losses + "</html>");
                JOptionPane.showMessageDialog(frame, dialog);

            }
        };

        JPanel Pane5 = new JPanel();
        Pane5.setBorder(BorderFactory.createEmptyBorder(50,0,50,0));
        Pane5.setBackground(Color.decode("#4E9465"));
        JButton NextCard = new JButton();
        NextCard.setPreferredSize(new Dimension(145,30));
        NextCard.setText("Neue Karte");
        NextCard.addActionListener(test);
        Pane5.add(NextCard);
        Pane5.add(score);
        Background.add(Pane5);

        JPanel Pane6 = new JPanel();
        Pane6.setBorder(BorderFactory.createEmptyBorder(50,0,50,0));
        Pane6.setBackground(Color.decode("#4E9465"));
        Pane6.add(radioPanel1);
        Background.add(Pane6);

        JPanel Pane7 = new JPanel();
        Pane7.setBorder(BorderFactory.createEmptyBorder(50,0,50,0));
        Pane7.setBackground(Color.decode("#4E9465"));
        Pane7.add(radioPanel2);
        Background.add(Pane7);

        JPanel Pane8 = new JPanel();
        Pane8.setBorder(BorderFactory.createEmptyBorder(50,0,50,0));
        Pane8.setBackground(Color.decode("#4E9465"));
        Pane8.add(radioPanel3);
        Background.add(Pane8);

        MainFrame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        MainFrame.add(Background);
        MainFrame.setVisible(true);

    }

    public static void main(String[] args) {
        Game();
    }
}
