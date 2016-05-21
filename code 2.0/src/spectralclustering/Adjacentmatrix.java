package spectralclustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import RaController.FinalMain;
import similarityCom.Word;
import utils.SomeUtils;

/**
 * ����ͼ
 * ͼ���ڽӾ�����
 * @author fanghui
 *
 */
public class Adjacentmatrix {
	
	private Matrix matrix = null;//����
	private Map<Integer,String> NodeMap = new HashMap<Integer,String>();
	
	private static Adjacentmatrix instance = new Adjacentmatrix();  
    private Adjacentmatrix() {
    	ArrayList<String>aList = SomeUtils.loadtest();
		long temp = (long)aList.size();
		matrix = DenseMatrix.Factory.zeros(temp,temp);
//	      System.out.println(adjacentmatrix.getMatrix());//��ӡ��ʼ����
//	     matrix.showGUI();
	        
		//���test�еĴ��ﲢ�ӵ�map ��  
	       System.out.println("����"+temp+"����");
			Iterator<String> iterator = aList.iterator();
			int wordID = 0;	
			while (iterator.hasNext()) {
				String word = (String) iterator.next();
				NodeMap.put(wordID, word);
				wordID ++;
			}
		   
		//ͼ�ı߲���   
		String str1 = null;
	    String str2 = null;
	    int current = 0;
	    double sim = 0;
	    Iterator<String> it = aList.iterator();
		while (it.hasNext()) {
		   str1 = (String) it.next();
			for(int j = current + 1;j < temp && FinalMain.stopFlag == 0; j++){
				str2 = aList.get(j);
				sim = Word.calSimFromString(str1, str2);//sim�Ǳ�
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
