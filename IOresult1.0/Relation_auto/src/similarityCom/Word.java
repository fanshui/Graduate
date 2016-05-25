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
 *词语
 */

public class Word {
	private String word;
    private String type;
    private Set<String> primitives = new HashSet<String>();  //义原
    
    public static List<Word> all_words = new ArrayList<Word>();

    public String getWord() {
        return word;
    }
    
    /**
     *由字符串获取其Word实例
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
	 * @return 义原个数
	 */
	public int numOfPrimitives() {
		return primitives.size();
	}
	
	/**
	 * 打印义原
	 */
	public void printPrimitives() {
		Iterator<String> iterator = this.primitives.iterator();
		while (iterator.hasNext()) {  
		    String str = iterator.next();  
		    System.out.print(str + "   ");    
		}  
    }
	
	/**
	 * 一个Word实例  调用此方法 计算与另一个Word实例的相似度
	 * @param otherword
	 * @return
	 */
	public double calculate(Word otherword) {
		 //一个Set 是  private Set<String> primitives 另一个是otherword.primitives
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
		
		 //方法一  2*交集元素个数    /  （集合 1  +  集合2）
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
			 System.out.print("   两个概念中的义原完全不同！");
			 return 0;
		 }
		 
		 
		 
		 //方法二 交集元素个数/并集元素个数
		 //return 
	}
	
	
	/**
	 * 由传入的 两个字符串词语  计算之间相似度
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double calSimFromString(String str1, String str2){
		Word word1 = getWordfromword(str1);
		Word word2 = getWordfromword(str2);
		return word1.calculate(word2);
	}
	
	 
    
}
