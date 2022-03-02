package UML.model;

import java.util.ArrayList;

public class Method {
    public String type;
    public String name;
    public ArrayList <Parameter> parameters = new ArrayList <Parameter> ();

    public Method(String type, String name, ArrayList <String> param)
    {
        this.type = type;
        this.name = name;
        for(int i = 0; i < param.size() - 1; i += 2)
        {
            parameters.add(new Parameter(param.get(i), param.get(i + 1)));
        }
    }
}