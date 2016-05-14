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
		
		CharSequence fliter1 = "human|��";
		CharSequence fliter2 = "family|��";
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		System.out.println("Done");
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		 
		//fileHandle();  //��������ʱ����
		//3767 ʦͽ
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
         * ������ ���ݴ������������ �������ƶ�
         */
//        ������
//        String str1 = "����";
//        String str2 = "����";
//        System.out.print(str1 + "  ��   " + str2 + "�����ƶ�Ϊ��  ");
//        System.out.println(Word.calSimFromString(str1, str2));
        
        
        
        
        /**
         * �������ƶ���
         */
//		Iterator<Word> it = Word.all_words.iterator();
//		while (it.hasNext()) {
//			word1 = (Word) it.next();
//			for(int j = current;j < 382; j++){
//				word2 = Word.all_words.get(j);
//				double temp = word1.calculate(word2);
//				System.out.print(word1.getWord()+"("+word1.getPrimitives().size()+")  "+word2.getWord()+"("+word2.getPrimitives().size()+")  ");
//				System.out.print("total"+"("+(word1.getPrimitives().size()+word2.getPrimitives().size())+")");
//				System.out.println("    Sim�� " + temp );	
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
		
		
//		System.out.println("������ѭ�����ˣ���");
        
        
        //��ȡtest �������ƶ�
       
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
				System.out.print(str1 + "  " + str2 + ": "); //���� str1 str2 �Ƕ���
				sim = Word.calSimFromString(str1, str2);//sim�Ǳ�
				System.out.println(sim);
				}
			current ++;
			}
        
		long endTime = System.currentTimeMillis();
		System.out.println("��������ʱ�䣺"+(endTime-startTime)+"ms");
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
	}
	
}
