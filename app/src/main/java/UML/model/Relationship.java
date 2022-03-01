package UML.model;

public class Relationship 
{
    public String type;
    public String src;
    public String dest;
    
    //Relationship constructor
    //name to be converted to a type in the next sprint
    public Relationship(String type, String src, String dest)
    {
        this.type = type;
        this.src = src;
        this.dest = dest;
    }
}