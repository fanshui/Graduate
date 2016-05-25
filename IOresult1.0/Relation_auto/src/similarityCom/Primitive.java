package similarityCom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * “Â‘≠
 *
 */
public  class Primitive {

	private int id;
    private String primitive;
	public static Map<Integer, String> all_primitives = new HashMap<Integer, String>();

    

    public Primitive(int id, String primitive) {
        this.id = id;
        this.primitive = primitive;
    }

    public String getPrimitive() {
        return primitive;
    }

    public int getId() {
        return id;
    }

    public static void main(String[] args) {
    	Iterator<Map.Entry<Integer, String>> entries = all_primitives.entrySet().iterator();
    	while (entries.hasNext()) {  
    		  
    	    Map.Entry<Integer, String> entry = entries.next();  
    	  
    	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
    	  
    	}  
    }
}
