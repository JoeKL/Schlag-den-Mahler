package game;
import javax.swing.*;

class GameCard {
    int Value; // 2-14
    String Color;
    JLabel Card;

    GameCard(int Wert, String Farbe, String IconPath, String IconDesc){
        Value = Wert;
        Color = Farbe;
        Card = new JLabel(createImageIcon(IconPath, IconDesc));
    }

    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
