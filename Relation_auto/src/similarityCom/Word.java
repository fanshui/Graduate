package similarityCom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 *����
 */

public class Word {
	private String word;
    private String type;
    private Set<String> primitives = new HashSet<String>();  //��ԭ
    
    public static List<Word> all_words = new ArrayList<Word>();

    public String getWord() {
        return word;
    }
    
    /**
     *���ַ�����ȡ��Wordʵ��
     * @param word
     * @return
     */
    public static Word getWordfromword(String word) {
    	Iterator<Word> iterator = all_words.iterator();
		Word word1 = null;
    	int end = 0;
    	while (iterator.hasNext() && end == 0) {
			word1 = (Word) iterator.next();
			if (word.equals(word1.word)) {
				end = 1;
			}
    	}
    	 return word1;
	}
    
    
   public void setWord(String word) {
        this.word = word;
    }
   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    public void setPrimitive(List<String> someprimitive) {
        this.primitives.addAll(someprimitive);
    }

	public Set<String> getPrimitives() {
		return primitives;
	}
	
	/**
	 * 
	 * @return ��ԭ����
	 */
	public int numOfPrimitives() {
		return primitives.size();
	}
	
	/**
	 * ��ӡ��ԭ
	 */
	public void printPrimitives() {
		Iterator<String> iterator = this.primitives.iterator();
		while (iterator.hasNext()) {  
		    String str = iterator.next();  
		    System.out.print(str + "   ");    
		}  
    }
	
	/**
	 * һ��Wordʵ��  ���ô˷��� ��������һ��Wordʵ�������ƶ�
	 * @param otherword
	 * @return
	 */
	public double calculate(Word otherword) {
		 //һ��Set ��  private Set<String> primitives ��һ����otherword.primitives
		 //this.primitives    otherword.primitives
		Word copyword = new Word();
		Word copyotherword = new Word();
		copyword.word = this.getWord();
		copyotherword.word = otherword.getWord();
		copyword.type = this.getType();
		copyotherword.type = otherword.getType();
		
		 copyword.primitives = new HashSet<String>(); 
		 copyotherword.primitives = new HashSet<String>();  
		
		Iterator<String> iterator1 = this.primitives.iterator();
		Iterator<String> iterator2 = otherword.primitives.iterator(); 
		
		while(iterator1.hasNext()){ 
			copyword.primitives.add(iterator1.next()); 
		}
		
		while(iterator2.hasNext()){ 
			copyotherword.primitives.add(iterator2.next()); 
		}
		
		 //����һ  2*����Ԫ�ظ���    /  ������ 1  +  ����2��
		int count1 = copyword.primitives.size();
		int count2 = copyotherword.primitives.size();
		int countsum = count1 + count2;
		
		if (copyword.primitives.containsAll(copyotherword.primitives)) {
			return 2*(double)count2/(double)countsum;
		}else if (copyotherword.primitives.containsAll(copyword.primitives)) {
			return 2*(double)count1/(double)countsum;
		}
		else if(copyword.primitives.retainAll(copyotherword.primitives)){
			return (2 * (double)copyword.primitives.size())/(double)countsum;
		 }else{
			 System.out.print("   ���������е���ԭ��ȫ��ͬ��");
			 return 0;
		 }
		 
		 
		 
		 //������ ����Ԫ�ظ���/����Ԫ�ظ���
		 //return 
	}
	
	
	/**
	 * �ɴ���� �����ַ�������  ����֮�����ƶ�
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double calSimFromString(String str1, String str2){
		Word word1 = getWordfromword(str1);
		Word word2 = getWordfromword(str2);
		return word1.calculate(word2);
	}
	
	 
	 
	 
//	 public static void main(String[] args) {
//		
//		 //������еĴ���
////		 Iterator<Word> iterator = all_words.iterator();
////		int wordID = 1;	
////		while (iterator.hasNext()) {
////			Word word = (Word) iterator.next();
////			System.out.print(wordID +"  word:  "+word.getWord() +   "       type: " + word.getType() + " primitives: ");
////		    word.printPrimitives();
////		    System.out.println();
////			wordID ++;
////		}
//		 
//
//		 
////		 String string = "ѧУ";
////		 Word word = Word.getWordfromword(string);
////		 System.out.print("  word:  "+word.getWord() +   "       type: " + word.getType() + " primitives: ");
////		 word.printPrimitives();
//		 
//
//		 
//	}
	    
    
}
