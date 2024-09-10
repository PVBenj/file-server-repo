/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CustomFont {
    String fontFilePath;
    float size;
    
    CustomFont(String fontFilePath, float size) {
        this.fontFilePath = fontFilePath;
        this.size = size;
    }
    
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