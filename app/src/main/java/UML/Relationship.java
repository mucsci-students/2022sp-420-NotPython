package UML;

public class Relationship 
{
    public String name;
    public String src;
    public String dest;
    
    //Relationship constructor
    public Relationship(String name, String src, String dest)
    {
        this.name = name;
        this.src = src;
        this.dest = dest;
    }
}