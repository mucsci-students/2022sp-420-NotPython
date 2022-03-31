package UML.view;

import UML.controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class ClassBox {
    GUIController guiCtr;
    String name;
    JPanel panel;
    JTextArea fieldArea;
    JTextArea methodArea;
    int x_pos;
    int y_pos;

    private Font font = new Font("Courier New", Font.BOLD, 12);

    public ClassBox (String className, int x, int y, GUIController guiCtr5){
        guiCtr = guiCtr5;
        panel = new JPanel();
        fieldArea = new JTextArea("");
        methodArea = new JTextArea("");
        x_pos = x; 
        y_pos = y;
        init(className);
    }

    public JPanel init(String className){
        panel.setBackground(Color.WHITE);
        panel.setSize(150, 150);
        panel.setLayout(new GridLayout(3, 1));
        panel.setLocation(x_pos, y_pos);

        name = className;
        JLabel nameLbl = new JLabel(className);
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
    
        fieldArea.setEditable(false);
        fieldArea.setFont(font);
        fieldArea.setWrapStyleWord(true);
        fieldArea.setRows(9);
        methodArea.setEditable(false);
        methodArea.setFont(font);
        methodArea.setWrapStyleWord(true);
        methodArea.setRows(19);

        panel.add(nameLbl);
        panel.add(fieldArea);
        panel.add(methodArea);
        
        
        return panel;
    }

    public void updateFields(){
        fieldArea.setText(guiCtr.updateFieldsCtr(name));
        int length = getDisplayLength();
        int lines = fieldArea.getLineCount();
        int height = getDisplayHeight() * lines;
        System.out.println(height);
        if (height < 150){
            height = 150;
        }
        panel.setSize(length, height);
    }

    public void updateMethods(){
        methodArea.setText(guiCtr.updateMethodsCtr(name));
        int length = getDisplayLength();
        int lines = methodArea.getLineCount();
        int height = getDisplayHeight() * lines;
        if (height < 150){
            height = 150;
        }
        panel.setSize(length, height);
    }

    private int getDisplayLength(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textwidth = (int)(font.getStringBounds(guiCtr.maximumWord(name), frc).getWidth());
        return textwidth;
    }

    private int getDisplayHeight(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textheight = (int)(font.getStringBounds("text", frc).getHeight());
        return textheight;
    }
}

