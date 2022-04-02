package UML.view;

import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.awt.geom.Line2D;

public class ArrowDraw extends JPanel{

    int thickness = 2;
    BasicStroke solidLine = new BasicStroke(thickness);
    BasicStroke dashedLine = new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10, new float[]{9}, 0);
    JPanel srcPan;
    JPanel destPan;

    String type;
    String side;

    Point srcLoc;
    Point destLoc;
    int pointDist;


    public ArrowDraw(JPanel srcInput, JPanel destInput, String typeInput, Graphics g){
        srcPan = srcInput;
        destPan = destInput;
        type = typeInput;


        srcLoc = new Point();
        destLoc = new Point();
        paintComponent(g);
    }

    public void arrowRedraw(Graphics g){
        
        paintComponent(g);
    }

    public void drawType(){
        
        float slope = (destLoc.y - srcLoc.y) / (destLoc.x - srcLoc.x);
        Polygon shape = new Polygon();
        //make triangle
        if(type.equals("Inheritance") || type.equals("Realization")){
            if(side.equals("top")){
                
            }
            else if(side.equals("right")){

            }
            else if(side.equals("bottom")){

            }
            else if(side.equals("left")){

            }
        }
        //make diamond
        else {
            if(side.equals("top")){

            }
            else if(side.equals("right")){

            }
            else if(side.equals("bottom")){

            }
            else if(side.equals("left")){
                
            }
        }
    }

    // //String[] types = {"Aggregation", "Composition", "Inheritance", "Realization"};
    // public void makeLine(Graphics g){
    //     //super.paintComponent(g);
    //     Graphics2D g2 = (Graphics2D) g;
    //     //checkSides();

    //     if(type.equals("Realization")){
    //         g2.setStroke(dashedLine);
    //     }
    //     else{
    //         g2.setStroke(solidLine);
    //     }

    //     g2.draw(new Line2D.Float(srcLoc.x, srcLoc.y, destLoc.x, destLoc.y));
    //     System.out.println("THIS SHOULD PRINT A LINE");
    // }

        
    public void checkSides(Graphics g){
        //get point of where the arrow would point from 
        Point srcTop = new Point();
        srcTop.setLocation((srcPan.getX() + (srcPan.getWidth() / 2)), srcPan.getY());
        Point srcRight = new Point();
        srcRight.setLocation((srcPan.getX() + srcPan.getWidth()),((srcPan.getHeight() / 2) + srcPan.getY()));
        Point srcBottom = new Point();
        srcBottom.setLocation((srcPan.getX() + (srcPan.getWidth() / 2)), (srcPan.getY() + srcPan.getHeight()));
        Point srcLeft = new Point();
        srcLeft.setLocation((srcPan.getX()),((srcPan.getHeight() / 2) + srcPan.getY()));

        //get point of where the arrow would point to 
        Point destTop = new Point();
        destTop.setLocation((destPan.getX() + (destPan.getWidth() / 2)), destPan.getY());
        Point destRight = new Point();
        destRight.setLocation((destPan.getX() + destPan.getWidth()),((destPan.getHeight() / 2) + destPan.getY()));
        Point destBottom = new Point();
        destBottom.setLocation((destPan.getX() + (destPan.getWidth() / 2)), (destPan.getY() + destPan.getHeight()));
        Point destLeft = new Point();
        destLeft.setLocation((destPan.getX()),((destPan.getHeight() / 2) + destPan.getY()));

        //Calculate which side is shortest
        int curDist = 0;
        int pointDist = 9999999;
        //srcTop to destLeft
        curDist = checkOneSide(srcTop, destLeft);
        if(pointDist > curDist){
            srcLoc = srcTop;
            destLoc = destLeft;
            pointDist = curDist;
            side = "left";
        }
       
        //srcTop to destRight
        curDist = checkOneSide(srcTop, destRight);
        if(pointDist > curDist){
            srcLoc = srcTop;
            destLoc = destRight;
            pointDist = curDist;
            side = "right";
        }

        //srcTop to destBottom
        curDist = checkOneSide(srcTop, destBottom);
        if(pointDist > curDist){
            srcLoc = srcTop;
            destLoc = destBottom;
            pointDist = curDist;
            side = "bottom";
        }

        //srcRight to destTop
        curDist = checkOneSide(srcRight, destTop);
        if(pointDist > curDist){
            srcLoc = srcRight;
            destLoc = destTop;
            pointDist = curDist;
            side = "top";
        }

        //srcRight to destBottom
        curDist = checkOneSide(srcRight, destBottom);
        if(pointDist > curDist){
            srcLoc = srcRight;
            destLoc = destBottom;
            pointDist = curDist;
            side = "bottom";
        }

        //srcRight to destLeft
        curDist = checkOneSide(srcRight, destLeft);
        if(pointDist > curDist){
            srcLoc = srcRight;
            destLoc = destLeft;
            pointDist = curDist;
            side = "left";
        }

        //srcBottom to destTop
        curDist = checkOneSide(srcBottom, destTop);
        if(pointDist > curDist){
            srcLoc = srcBottom;
            destLoc = destTop;
            pointDist = curDist;
            side = "top";
        }

        //srcBottom to destLeft
        curDist = checkOneSide(srcBottom, destLeft);
        if(pointDist > curDist){
            srcLoc = srcBottom;
            destLoc = destLeft;
            pointDist = curDist;
            side = "left";
        }

        //SrcBottom to destRight
        curDist = checkOneSide(srcBottom, destRight);
        if(pointDist > curDist){
            srcLoc = srcBottom;
            destLoc = destRight;
            pointDist = curDist;
            side = "right";
        }

        //srcLeft to destRight
        curDist = checkOneSide(srcLeft, destRight);
        if(pointDist > curDist){
            srcLoc = srcLeft;
            destLoc = destRight;
            pointDist = curDist;
            side = "right";
        }

        //srcLeft to destTop
        curDist = checkOneSide(srcLeft, destTop);
        if(pointDist > curDist){
            srcLoc = srcLeft;
            destLoc = destTop;
            pointDist = curDist;
            side = "top";
        }

        //srcLeft to destBottom
        curDist = checkOneSide(srcLeft, destBottom);
        if(pointDist > curDist){
            srcLoc = srcLeft;
            destLoc = destBottom;
            pointDist = curDist;
            side = "bottom";
        }
        //paintComponent(g);
    }

    public int checkOneSide(Point src, Point dest){
        int xdist = Math.abs(src.x - dest.x);
        int ydist = Math.abs(src.y - dest.y);
        return xdist + ydist;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        checkSides(g);
        //makeLine(g);
        Graphics2D g2 = (Graphics2D) g;
        //checkSides();

        if(type.equals("Realization")){
            g2.setStroke(dashedLine);
        }
        else{
            g2.setStroke(solidLine);
        }

        g2.draw(new Line2D.Float(srcLoc.x, srcLoc.y, destLoc.x, destLoc.y));
    }

}
