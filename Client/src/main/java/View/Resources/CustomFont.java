package View.Resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomFont {
    
    public static Font panelNumberLabel = createFont("/Fonts/SFPRODISPLAYBOLD.OTF", 55);
    public static Font panelHeadingFont = createFont("/Fonts/SFPRODISPLAYBOLD.OTF", 35);
    public static Font sectionHeadingFont = createFont("/Fonts/SFPRODISPLAYBOLD.OTF", 18);
    public static Font formLabelFont = createFont("/Fonts/SFPRODISPLAYBOLD.OTF", 18);
    public static Font formTextFieldFont = createFont("/Fonts/SFPRODISPLAYREGULAR.OTF", 18);
    public static Font tableHeaderFont = createFont("/Fonts/SFPRODISPLAYBOLD.OTF", 16);
    public static Font tableRowFont = createFont("/Fonts/SFPRODISPLAYREGULAR.OTF", 16);
    public static Font subPanelHeadingFont = createFont("/Fonts/SFPRODISPLAYBOLD.OTF", 30);
    public static Font joptionPaneFont = createFont("/Fonts/SFPRODISPLAYBOLD.OTF", 16);
    
    /* public static Font createFont(String fontFilePath, float size) {
        try{
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            return customFont;
        } catch(IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }
        return null;
    } */
    
    public static Font createFont(String fontPath, float fontSize) {
        try {
            InputStream is = CustomFont.class.getResourceAsStream(fontPath); // Ensure Home is the correct class name
            if (is == null) {
                throw new IOException("Font not found: " + fontPath);
            }
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(fontSize);
        } catch (FontFormatException | IOException e) {
            System.err.println(e.getMessage());
            return new Font("Serif", Font.PLAIN, (int) fontSize); // Fallback font
        }
    }



}