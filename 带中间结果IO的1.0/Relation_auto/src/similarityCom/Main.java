package similarityCom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resultDB.MyDB;
import utils.SomeUtils;

public class Main {
	
	public static void fileHandle() {
		
		CharSequence fliter1 = "human|人";
		CharSequence fliter2 = "family|家";
		try {
			FileReader fr = new FileReader("dict/human_glossary.data");
			BufferedReader br =new BufferedReader(fr);
			
			FileWriter fw = new FileWriter("dict/human_family_glossary.data");
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line;
			while((line = br.readLine()) != null ){
				if (line.contains(fliter1) && line.contains(fliter2)) {
					bw.write(line+"\n");
				}
				
			}
			bw.flush();
			bw.close();
			fw.close();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		System.out.println("Done");
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		 
		//fileHandle();  //初次运行时加载
		//3767 师徒
//		String driver = "com.mysql.jdbc.Driver";
//	    String url = "jdbc:mysql://localhost:3307/words?useUnicode=true&characterEncoding=gbk";
//	    String username = "root";
//	    String password = "111";
//	    Connection conn = MyDB.getConnection(driver,url,username,password);
	    Word word1;
//	    int current = 1;
	    Word word2;
	    
        long startTime = System.currentTimeMillis();
	    
        
        /**
         * 测试用 根据传入的两个词语 计算相似度
         */
//        测试用
//        String str1 = "阿爸";
//        String str2 = "大婶";
//        System.out.print(str1 + "  与   " + str2 + "的相似度为：  ");
//        System.out.println(Word.calSimFromString(str1, str2));
        
        
        
        
        /**
         * 测试相似度用
         */
//		Iterator<Word> it = Word.all_words.iterator();
//		while (it.hasNext()) {
//			word1 = (Word) it.next();
//			for(int j = current;j < 382; j++){
//				word2 = Word.all_words.get(j);
//				double temp = word1.calculate(word2);
//				System.out.print(word1.getWord()+"("+word1.getPrimitives().size()+")  "+word2.getWord()+"("+word2.getPrimitives().size()+")  ");
//				System.out.print("total"+"("+(word1.getPrimitives().size()+word2.getPrimitives().size())+")");
//				System.out.println("    Sim： " + temp );	
//				String sql = " INSERT INTO `all_words_sim` (`word_1`, `word_1_num`, `word_2`,`word_2_type`, `word_2_num`,`total_num`,`sim`) VALUES "
//						+ "(" +"'"+ word1.getWord() +"'"+","+ word1.getPrimitives().size() + ","+ "'" +word2.getWord()+ "'"  + ","  
//						+ "'"+ word2.getType()+"'"+","+ word2.getPrimitives().size() + "," + (word1.getPrimitives().size()+word2.getPrimitives().size())
//						+  ","  + temp + ")";
//				System.out.println(sql);
//				MyDB.sql_DMLandDDL(sql,conn);
//			}
//			
//				current++;
//		}
		
		
//		System.out.println("可算是循环完了！！");
        
        
        //读取test 计算相似度
       
        ArrayList<String>aList = SomeUtils.loadtest();
        SomeUtils.printList(aList);
        String str1 = null;
        String str2 = null;
        int current = 1;
        double sim = 0;
        Iterator<String> it = aList.iterator();
		while (it.hasNext()) {
		   str1 = (String) it.next();
			for(int j = current;j < aList.size(); j++){
				str2 = aList.get(j);
				System.out.print(str1 + "  " + str2 + ": "); //词语 str1 str2 是顶点
				sim = Word.calSimFromString(str1, str2);//sim是边
				System.out.println(sim);
				}
			current ++;
			}
        
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
	}
	
}
