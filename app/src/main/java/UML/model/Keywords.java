package UML.model;

import java.util.ArrayList;

import jline.TerminalFactory;
import jline.console.ConsoleReader;
import jline.console.completer.*;

public class Keywords {
    ArrayList<String> classNames = null;
    ArrayList<String> methodNames = null;
    ArrayList<String> parameterNames = null;
    ArrayList<String> types = null;
  

    public Keywords ()
    {
        classNames = new ArrayList<>();
        methodNames = new ArrayList<>();
        parameterNames = new ArrayList<>();
        types = new ArrayList<>();
    }

    /*
    * adds a new class name
    */
    public void addClass(String className){
        classNames.add(className);
    }

    public void addMethod(String methodName){
        methodNames.add(methodName);
    }

    public void addParameter(String parameterName){
        parameterNames.add(parameterName);
    }

    public void addType(String type){
        types.add(type);
    }

    public Completer getNewCompleters(Completer[] completers){
        StringsCompleter classNamesCompleter = new StringsCompleter(classNames);

        Completer finalCompleter = new AggregateCompleter(completers[0], classNamesCompleter);
        
        return finalCompleter;
    }
}