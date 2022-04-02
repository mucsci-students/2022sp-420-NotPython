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
    GUI gui;
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

    public ClassBox (String className, int x, int y, GUIController guiCtr5, GUI gui5){
        guiCtr = guiCtr5;
        gui = gui5;
        panel = new JPanel();
        fieldArea = new JTextArea("");
        methodArea = new JTextArea("");
        x_pos = x; 
        y_pos = y;
        init(className);
    }
  
    public ClassBox(ClassBox other)
    {
        this.name = other.name;
        this.x_pos = other.x_pos;
        this.y_pos = other.y_pos;
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
        gui.snapshot();
        guiCtr.snapshotDiagram();
			}
			public void mouseDragged(MouseEvent e) {
                panel.setLocation(panel.getX() + (e.getX() - this.listen_x), panel.getY() + (e.getY() - this.listen_y));	
                x_pos = panel.getX();
                y_pos = panel.getY();
                if(panel.getX() < -1){
                    x_pos = 5;
                }	
                if(panel.getY() < -1){
                    y_pos = 5;
                }		
                panel.setLocation(x_pos, y_pos);
                panel.repaint();
                gui.mainFrame.repaint();
                gui.arrowUpdater();
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
        getDisplayLength();
        int lines = fieldArea.getLineCount();
        fieldHeight = getDisplayHeight() * lines;
        //int height = fieldHeight + methodHeight;
        //System.out.println(height);
        if (panHeight < 150){
            panHeight = 150;
        }
        if (panLength < 150){
            panLength = 150;
        }
        panel.setSize(panLength, panHeight);
    }

    public void updateMethods(){
        methodArea.setText(guiCtr.updateMethodsCtr(name));
        getDisplayLength();
        int lines = methodArea.getLineCount();
        methodHeight = getDisplayHeight() * lines;
        //int height = fieldHeight + methodHeight;
        if (panHeight < 150){
            panHeight = 150;
        }
        if (panLength < 150){
            panLength = 150;
        }
        panel.setSize(panLength, panHeight);
    }
  
    public ClassBox clone()
    {
        return new ClassBox(this);
    }

    private void getDisplayLength(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textwidth = (int)(font.getStringBounds(guiCtr.maximumWord(name), frc).getWidth());
        panLength = textwidth;
    }

    private int getDisplayHeight(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textheight = (int)(font.getStringBounds("text", frc).getHeight());
        return textheight;
    }

}
