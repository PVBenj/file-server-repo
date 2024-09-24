package View.Resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CustomFont {
    
    public static Font panelNumberLabel = createFont("/home/benjamin/file-server-repo/Client/src/main/java/View/Resources/Fonts/SFPRODISPLAYBOLD.OTF", 55);
    public static Font panelHeadingFont = createFont("/home/benjamin/file-server-repo/Client/src/main/java/View/Resources/Fonts/SFPRODISPLAYBOLD.OTF", 35);
    public static Font sectionHeadingFont = createFont("/home/benjamin/file-server-repo/Client/src/main/java/View/Resources/Fonts/SFPRODISPLAYBOLD.OTF", 18);
    public static Font formLabelFont = createFont("/home/benjamin/file-server-repo/Client/src/main/java/View/Resources/Fonts/SFPRODISPLAYBOLD.OTF", 18);
    public static Font formTextFieldFont = createFont("/home/benjamin/file-server-repo/Client/src/main/java/View/Resources/Fonts/SFPRODISPLAYREGULAR.OTF", 18);
    public static Font tableHeaderFont = createFont("/home/benjamin/file-server-repo/Client/src/main/java/View/Resources/Fonts/SFPRODISPLAYBOLD.OTF", 18);
    public static Font tableRowFont = createFont("/home/benjamin/file-server-repo/Client/src/main/java/View/Resources/Fonts/SFPRODISPLAYREGULAR.OTF", 18);
    public static Font subPanelHeadingFont = createFont("/home/benjamin/file-server-repo/Client/src/main/java/View/Resources/Fonts/SFPRODISPLAYBOLD.OTF", 30);
    
    public static Font createFont(String fontFilePath, float size) {
        try{
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            return customFont;
        } catch(IOException | FontFormatException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}