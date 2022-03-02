package UML.controller;

import javax.swing.*;
import java.awt.*;

import UML.model.Diagram;
import UML.view.GUIPopup;

public class GUIController {

    Diagram dg = new Diagram();
    GUIPopup guiPop = new GUIPopup();

    public String guiSaveCtr(){
        String message;
        String fileName = guiPop.guiSavePop();
        message = dg.saveDiagram(fileName);
        return message;
    }
    
    public String guiLoadCtr(){
        String message;
        String fileName = guiPop.guiLoadPop();
        message = dg.loadDiagram(fileName);
        return message;
    }

    public String guiCreateClassCtr(){
        String message;
        String className = guiPop.guiCreateClassPop();
        message = dg.createClass(className);
        return message;
    }

    public void createMethodCtr(){

    }

    public void createFieldCtr(){

    }

    public void createRelationshipCtr(){

    }

    public void deleteClassCtr(){

    }

    public void deleteMethodCtr(){

    }

    public void deleteFieldCtr(){

    }

    public void deleteRelationshipCtr(){

    }

    public void renameClassCtr(){

    }

    public void renameMethodCtr(){

    }

    public void editMethodReturnCtr(){

    }

    public void editMethodParamsCtr(){

    }

    public void renameFieldCtr(){

    }

    public void editFieldTypeCtr(){

    }

    public void listClassCtr(){

    }

    public void listClassesCtr(){

    }

    public void listRelationshipsCtr(){

    }
}
