package UML.model;

public class Relationship 
{
    //public String name;
    public String src;
    public String dest;
    
    //Relationship constructor
    //name to be converted to a type in the next sprint
    public Relationship(/*String name,*/ String src, String dest)
    {
        /*this.name = name;*/
        this.src = src;
        this.dest = dest;
    }
}