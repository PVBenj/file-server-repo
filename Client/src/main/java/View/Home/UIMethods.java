package View.Home;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;


public interface UIMethods {
    void changeColor(JPanel hover, Color myColor);
    void changeFontColor(JLabel text, Color myColor);
    void loadFonts();
}
