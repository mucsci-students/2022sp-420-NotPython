package UML.view;

import UML.controller.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
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
        panel.setSize(100, 100);
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setLocation(x_pos, y_pos);
        name = className;
        nameLbl = new JLabel(className);
        nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        Dimension minSize = new Dimension(10, 20);
        Dimension prefSize = new Dimension(20, 20);
        Dimension maxSize = new Dimension(60, 100);
        fieldArea.setEditable(false);
        fieldArea.setFont(font);
        methodArea.setEditable(false);
        methodArea.setFont(font);
        fieldArea.add(new Box.Filler(minSize, prefSize, maxSize));
        methodArea.add(new Box.Filler(minSize, prefSize, maxSize));

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
    }

    public void updateFields(){
        fieldArea.setText(guiCtr.updateFieldsCtr(name));
        getDisplayLength();
        int lines = fieldArea.getLineCount();
        fieldHeight = getDisplayHeight() * lines;
        panel.setSize(panLength, 15 + fieldHeight + methodHeight);
    }

    public void updateMethods(){
        methodArea.setText(guiCtr.updateMethodsCtr(name));
        getDisplayLength();
        int lines = methodArea.getLineCount();
        methodHeight = getDisplayHeight() * lines;
        panel.setSize(panLength, 15 + fieldHeight + methodHeight);
    }
  
    public ClassBox clone()
    {
        return new ClassBox(this);
    }

    private void getDisplayLength(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textwidth = (int)(font.getStringBounds(guiCtr.maximumWord(name), frc).getWidth());
        panLength = textwidth + 17;
    }

    private int getDisplayHeight(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textheight = (int)(font.getStringBounds("text", frc).getHeight());
        return textheight;
    }

}
