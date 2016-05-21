package spectralclustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import similarityCom.Word;
import utils.SomeUtils;

/**
 * 建立图
 * 图的邻接矩阵类
 * @author fanghui
 *
 */
public class Adjacentmatrix {
	
	private Matrix matrix = null;//矩阵
	private Map<Integer,String> NodeMap = new HashMap<Integer,String>();
	
	private static Adjacentmatrix instance = new Adjacentmatrix();  
    private Adjacentmatrix() {
    	ArrayList<String>aList = SomeUtils.loadtest();
		long temp = (long)aList.size();
		matrix = DenseMatrix.Factory.zeros(temp,temp);
//	      System.out.println(adjacentmatrix.getMatrix());//打印初始矩阵
//	     matrix.showGUI();
	        
		//输出test中的词语并加到map 中  
	       System.out.println("共有"+temp+"个词");
			Iterator<String> iterator = aList.iterator();
			int wordID = 0;	
			while (iterator.hasNext()) {
				String word = (String) iterator.next();
				NodeMap.put(wordID, word);
				wordID ++;
			}
		   
		//图的边插入   
		String str1 = null;
	    String str2 = null;
	    int current = 0;
	    double sim = 0;
	    Iterator<String> it = aList.iterator();
		while (it.hasNext()) {
		   str1 = (String) it.next();
			for(int j = current + 1;j < temp; j++){
				str2 = aList.get(j);
				sim = Word.calSimFromString(str1, str2);//sim是边
				if (sim < 0.3) {
					sim = 0;		//set the  threshold value 0.3
				}
				matrix.setAsDouble(sim, current, j);
				matrix.setAsDouble(sim, j, current);
				
				}
			current ++;
			}

    } 
   
    public static Adjacentmatrix getInstance() {  
    	return instance;  
    }  
	 
	public Matrix getMatrix() {
		return matrix;
	}

	public Map<Integer, String> getNodeMap() {
		return NodeMap;
	}
    
	
}
