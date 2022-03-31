package UML.view;

import UML.controller.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClassBox {
    GUIController guiCtr;
    String name;
    JPanel panel;
    JLabel nameLbl;
    JTextArea fieldArea;
    JTextArea methodArea;
    int x_pos;
    int y_pos;
    int panLength;
    int panHeight;
    int methodHeight;
    int fieldHeight;

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
        nameLbl = new JLabel(className);
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
    
        fieldArea.setEditable(false);
        fieldArea.setFont(font);
        // fieldArea.setWrapStyleWord(true);
        // fieldArea.setRows(9);
        methodArea.setEditable(false);
        methodArea.setFont(font);

        panel.add(nameLbl);
        panel.add(fieldArea);
        panel.add(methodArea);
        
        MouseInputAdapter l = new MouseInputAdapter (){
			private int listen_x;
			private int listen_y;
			public void mousePressed(MouseEvent e) {
				this.listen_x = e.getX();
				this.listen_y = e.getY();
			}
			public void mouseDragged(MouseEvent e) {
				panel.setLocation(panel.getX() + (e.getX() - this.listen_x), panel.getY() + (e.getY() - this.listen_y));
			}
		};

        panel.addMouseListener(l);
		panel.addMouseMotionListener(l);

        return panel;
    }

    public void rename(String newName){
        this.name = newName;
        nameLbl.setText(newName);
        // panel.repaint();
        // panel.validate();
    }

    public void updateFields(){
        fieldArea.setText(guiCtr.updateFieldsCtr(name));
        int length = getDisplayLength();
        int lines = fieldArea.getLineCount();
        fieldHeight = getDisplayHeight() * lines;
        int height = fieldHeight + methodHeight;
        //System.out.println(height);
        if (height < 150){
            height = 150;
        }
        if (panLength < 150){
            panLength = 150;
        }
        panel.setSize(panLength, height);
    }

    public void updateMethods(){
        methodArea.setText(guiCtr.updateMethodsCtr(name));
        panLength = getDisplayLength();
        int lines = methodArea.getLineCount();
        methodHeight = getDisplayHeight() * lines;
        int height = fieldHeight + methodHeight;
        if (height < 150){
            height = 150;
        }
        if (panLength < 150){
            panLength = 150;
        }
        panel.setSize(panLength, height);
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

