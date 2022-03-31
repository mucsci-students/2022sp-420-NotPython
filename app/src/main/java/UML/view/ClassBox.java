package UML.view;

import UML.controller.*;
import javax.swing.*;
import java.awt.*;

public class ClassBox {
    GUIController guiCtr = new GUIController();
    String name;
    JPanel panel;
    JTextArea fieldArea;
    JTextArea methodArea;
    int x_pos;
    int y_pos;

    public ClassBox (String className, int x, int y){
        panel = new JPanel();
        fieldArea = new JTextArea("");
        methodArea = new JTextArea("");
        x_pos = x; 
        y_pos = y;
        init(className);
    }

    public JPanel init(String className){
        panel.setBackground(Color.WHITE);
        panel.setSize(100, 100);
        panel.setLayout(new GridLayout(3, 1));
        panel.setLocation(x_pos, y_pos);

        name = className;
        System.out.println(name);
        JLabel nameLbl = new JLabel(className);
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
    
        Font font = new Font("Courier New", Font.BOLD, 12);
        fieldArea.setEditable(false);
        fieldArea.setFont(font);
        methodArea.setEditable(false);
        methodArea.setFont(font);

        panel.add(nameLbl);
        panel.add(fieldArea);
        panel.add(methodArea);
        
        
        return panel;
    }

    public void updateFields(){
        fieldArea.setText(guiCtr.updateFieldsCtr(name));
    }

    public void updateMethods(){
        methodArea.setText(guiCtr.updateMethodsCtr(name));
    }
}

