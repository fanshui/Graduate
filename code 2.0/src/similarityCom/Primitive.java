package similarityCom;

import java.util.HashMap;
import java.util.Map;

/**
 * “Â‘≠¿‡
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

}
