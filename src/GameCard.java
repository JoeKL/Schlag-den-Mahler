import javax.swing.*;

public class GameCard {
    int Value; // 2-14
    String Symbol; // Karo, Herz, Pik, Kreuz, Sonder
    String Color;
    JLabel Card;
    ImageIcon SourcePic;

    GameCard(int Wert, String Symbolt, String Farbe, String IconPath, String IconDesc){
        Value = Wert;
        Symbol = Symbolt;
        Color = Farbe;
        SourcePic = createImageIcon(IconPath, IconDesc);
        //String temp = this.Color + " " + this.Value;
        //Card = new JLabel(temp, SourcePic, JLabel.CENTER);
        Card = new JLabel(SourcePic);
    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
