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

    public void deleteClass(String className){
        for(int i = 0; i < classNames.size(); i++){
            if(classNames.get(i).equals(className)){
                classNames.remove(i);
            }
        }
    }

    public Completer getCompleter(){
        StringsCompleter completer1 = new StringsCompleter("help", "exit", "undo", "redo");

        ArgumentCompleter completer2 = new ArgumentCompleter(
            new StringsCompleter("create"),
            new StringsCompleter("class", "field", "method"),
            NullCompleter.INSTANCE
        );

        ArgumentCompleter completer3 = new ArgumentCompleter(
            new StringsCompleter("rename"),
            new StringsCompleter("class", "field", "method"),
            new StringsCompleter(classNames),
            NullCompleter.INSTANCE
        );

        ArgumentCompleter completer4 = new ArgumentCompleter(
            new StringsCompleter("delete"),
            new StringsCompleter("class"),
            new StringsCompleter(classNames),
            NullCompleter.INSTANCE
        );

        ArgumentCompleter completer5 = new ArgumentCompleter(
            new StringsCompleter("change"),
            new StringsCompleter("parameter", "parameters"),
            NullCompleter.INSTANCE
        );

        ArgumentCompleter completer6 = new ArgumentCompleter(
            new StringsCompleter("list"),
            new StringsCompleter("class", "classes", "relationships"),
            NullCompleter.INSTANCE
        );

        ArgumentCompleter completer7 = new ArgumentCompleter(
            new StringsCompleter("save", "load"),
            new FileNameCompleter(),
            NullCompleter.INSTANCE
        );

        ArgumentCompleter completer8 = new ArgumentCompleter(
            new StringsCompleter("create"),
            new StringsCompleter("relationship"),
            new StringsCompleter("aggregation", "composition", "inheritance", "realization"),
            new StringsCompleter(classNames),
            new StringsCompleter(classNames),
            NullCompleter.INSTANCE
        );

        ArgumentCompleter completer9 = new ArgumentCompleter(
            new StringsCompleter("delete"),
            new StringsCompleter("field", "method", "relationship", "parameter", "parameters"),
            NullCompleter.INSTANCE
        );
        
        Completer completer = new AggregateCompleter(completer1, completer2, completer3, completer4, completer5, completer6, completer7, completer8, completer9);

        return completer;
    }
}