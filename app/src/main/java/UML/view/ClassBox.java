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
    int top_right_pos;
    int bottom_left_pos;

    //int[] corners;

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
        frameResize();
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
        nameLbl.setPreferredSize(new Dimension(getDisplayNameLength(), 30));
    
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

                top_right_pos = x_pos + panLength;
                bottom_left_pos = y_pos + panel.getHeight();   
    
                if(panel.getX() < -1){
                    x_pos = 5;
                }	
                if(panel.getY() < -1){
                    y_pos = 5;
                }	

                frameResize();

                panel.setLocation(x_pos, y_pos);
                panel.repaint();
                gui.panelDownSize();
                gui.mainFrame.repaint();
                gui.arrowUpdater();
			}

            public void mouseReleased(MouseEvent e) {
                frameResize();
                
            }
		};

        panel.addMouseListener(l);
		panel.addMouseMotionListener(l);

        return panel;
    }

    public void frameResize(){
        top_right_pos = x_pos + panLength;
        bottom_left_pos = y_pos + 20 + fieldHeight + methodHeight;

        if(top_right_pos + 15 > gui.PANEL_WIDTH){
            gui.updatePanelSize(top_right_pos + 15, gui.PANEL_HEIGHT);
        }	
        if(bottom_left_pos + 15 > gui.PANEL_HEIGHT){
            gui.updatePanelSize(gui.PANEL_WIDTH, bottom_left_pos + 15);
        }
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
        panel.setSize(panLength + 10, 20 + fieldHeight + methodHeight);
    }

    public void updateMethods(){
        methodArea.setText(guiCtr.updateMethodsCtr(name));
        getDisplayLength();
        int lines = methodArea.getLineCount();
        methodHeight = getDisplayHeight() * lines;
        panel.setSize(panLength + 10, 20 + fieldHeight + methodHeight);
    }
  
    public ClassBox clone()
    {
        return new ClassBox(this);
    }

    private int getDisplayNameLength(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textwidth = (int)(font.getStringBounds(name, frc).getWidth());
        return textwidth;
    }

    private void getDisplayLength(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textwidth = (int)(font.getStringBounds(guiCtr.maximumWord(name), frc).getWidth());
        panLength = textwidth /*+ 17*/;
    }

    private int getDisplayHeight(){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        int textheight = (int)(font.getStringBounds("text", frc).getHeight());
        return textheight + 1;
    }
}
