import java.util.Arraylist;

public class Class 
{
    public static String name;
    public static ArrayList <Attribute> attributes;

    public Class (String initName)
    {
        name = initName;
        attributes = new ArrayList<Attribute>();
    }

    public static void rename(String newName)
    {
        name = newName;
    }

    public static void add_attribute (String attrib, String class_name)
    {
        if (attributes.contains(attrib))
        {
            System.out.println("ERROR: " + name + " is already an attribute associated with " + class_name);
        }
        else 
        {
            attributes.add(attrib);
        }
    }

    public static void delete_attribute (String name, String class_name)
    {
        if(attributes.contains(name))
        {
            for (int loc = 0; loc < attributes.length(); loc++ )
            {
                if (attributes(loc).name .equals(name))
                {
                    attributes.remove(loc);
                }
            }
        }
        else 
        {
            System.out.println("ERROR: " + name + " is not an attribute associated with " + class_name);
        }
    }

}