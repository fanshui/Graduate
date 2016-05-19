package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import similarityCom.Word;

//import com.sun.org.apache.bcel.internal.generic.RETURN;
//
//import similarityCom.Word;

public class SomeUtils {
	
	/**
	 * 加载 test.data 返回文件中词语组成的 ArrayList
	 * @return
	 */
	public static ArrayList<String> loadtest() {
        List<String> list = new ArrayList<String>();
		String line = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("test/test.data"));
            line = reader.readLine();
            while (line != null) {
                line = line.trim().replaceAll("\\s+", " ");
                String[] strs = line.split(" ");
                for (String string : strs) {
					list.add(string);
				}
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return (ArrayList<String>) list;
    }
	
	/**
	 * 输出LIST
	 * @param <E>
	 * @param arrayList
	 */
	public static <E> void printList(ArrayList<E> arrayList) {
		Iterator<E> iterator = arrayList.iterator();	
		FileWriter fw = null;
		BufferedWriter bw = null;;
		try {
			fw = new FileWriter("mid/eigenValuesDecom");
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		while (iterator.hasNext()) {
			E word = (E) iterator.next();
			try {
				bw.write(word.toString() + "\n");
				bw.flush();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
//			System.out.print(word);
//		    System.out.println();
		}
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 计算两个向量的欧氏距离
	 * @param arr1
	 * @param arr2
	 * @param len 向量维数
	 * @return
	 */
    public static double calEuraDist(double[] arr1,double[] arr2,int len){
        double result=0.0;
        for(int i=0;i<len;i++){
            result+=Math.pow(arr1[i]-arr2[i],2.0);
        }
        return Math.sqrt(result);
    }
	
    public static void convertToTest()  {
    	String line = null;
        BufferedReader reader = null;
        FileWriter fwt = null;
		BufferedWriter bwt = null;
		int index = 0;
		try {
			fwt = new FileWriter("test/test.data");
			bwt = new BufferedWriter(fwt);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        try {
			reader = new BufferedReader(new FileReader("test/resultRelWords.txt"));
			line = reader.readLine();
			while (line != null) {
				index = line.lastIndexOf('/');
//				System.out.println(index);
                bwt.write(line,0,index);
                bwt.write("   ");
                line = reader.readLine();
            }
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        
        try {
        	bwt.flush();
        	bwt.close();
			fwt.close();
			reader.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        
	}
    
	 
}
