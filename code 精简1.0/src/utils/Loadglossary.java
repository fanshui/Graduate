package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import similarityCom.Primitive;
import similarityCom.Word;

public class Loadglossary {
	
	private static Loadglossary instance = new Loadglossary();  
    private Loadglossary() {} 
   
    public static Loadglossary getInstance() {  
    	return instance;  
    }  
	
    
    /**
     * ������ԭ�ļ���
     */
    public void loadprimitive(){
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "dict/primitive.data"));
            line = reader.readLine();

            while (line != null) {
                line = line.trim().replaceAll("\\s+", " ");

                String[] strs = line.split(" ");
                int id = Integer.parseInt(strs[0]);
                //String[] words = strs[1].split("\\|");
                //String english = words[0];
                String chinese = strs[1].split("\\|")[1];
              
                Primitive.all_primitives.put(id,chinese);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //System.out.println(line);
            e.printStackTrace();
        }
    }

    /**
     * ���شʵ��ļ�
     */
    public void loadGlossary() {
	        String line = null;
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader("dict/glossary.data"));
	            line = reader.readLine();
	            while (line != null) {
	                line = line.trim().replaceAll("\\s+", " ");
	                String[] strs = line.split(" ");
	                String word = strs[0];
	                String type = strs[1];
	                String related = strs[2];
	                for (int i = 3; i < strs.length; i++) {
	                    related += (" " + strs[i]);
	                }
	                
	                Word w = new Word();
	                w.setWord(word);
	                w.setType(type);
	                
	                //������ԭ���� ������ӵ�Set<> primitives��
	                String[] parts = related.split(",");
	    	        for (int i = 0; i < parts.length; i++) {
	    	        	w.getPrimitives().add(parts[i]);
	    	        	}
	    	        
	    	        //�� w �ӵ�List<>all_words��
	                Word.all_words.add(w);
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
	    }
}
