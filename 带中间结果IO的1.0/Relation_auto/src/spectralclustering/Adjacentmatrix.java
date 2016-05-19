package spectralclustering;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import com.sun.swing.internal.plaf.basic.resources.basic_zh_TW;

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
			FileWriter fw = null;
			try {
				fw = new FileWriter("mid/wordmaps");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(fw);
			while (iterator.hasNext()) {
				
				String word = (String) iterator.next();
				NodeMap.put(wordID, word);
//				System.out.print(wordID +"  word:  "+word );
				
				try {
					bw.write(wordID +"  word:  "+word+"\n");
					bw.flush();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

//			    System.out.println();
				wordID ++;
			}
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		   
		//图的边插入   
		String str1 = null;
	    String str2 = null;
	    int current = 0;
	    double sim = 0;
	    Iterator<String> it = aList.iterator();
	    FileWriter fwt = null;
		BufferedWriter bwt = null;
		try {
			fwt = new FileWriter("mid/wordSim");
			bwt = new BufferedWriter(fwt);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		while (it.hasNext()) {
		   str1 = (String) it.next();
			for(int j = current + 1;j < temp; j++){
				str2 = aList.get(j);
				try {
					bwt.write(current + "——>" +str1 + " …… "+ j + "——>" + str2 + "  :   ");
					
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
//				System.out.print(current + "——>" +str1 + " …… "+ j + "——>" + str2 + "  :   "); //词语 str1 str2 是顶点
				sim = Word.calSimFromString(str1, str2);//sim是边
				if (sim < 0.3) {
					sim = 0;		//set the  threshold value 0.3
				}
				try {
					bwt.write(sim+"\n");
					bwt.flush();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
//				System.out.println(sim);
				matrix.setAsDouble(sim, current, j);
				matrix.setAsDouble(sim, j, current);
				
				}
			current ++;
			}
		
		try {
			fwt.close();
			bwt.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
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
