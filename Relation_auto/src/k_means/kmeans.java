package k_means; 

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.ArrayList;

import main.FinalMain;
import spectralclustering.Adjacentmatrix;  
  
/*
 * ѡ��K������Ϊ��ʼ����    
  repeat    
    ��ÿ����ָ�ɵ���������ģ��γ�K����    
    ���¼���ÿ���ص�����    
  until �ز������仯��ﵽ����������    
   ʱ�临�Ӷȣ�O(tKmn)�����У�tΪ����������KΪ�ص���Ŀ��mΪ��¼����nΪά��  
   �ռ临�Ӷȣ�O((m+K)n)�����У�KΪ�ص���Ŀ��mΪ��¼����nΪά�� 
 * */
public class kmeans {  
    private int K;  
    private int dataNum;  
    private int dimNum;  
    private ArrayList<double[]> tuples;  
    private ArrayList<ArrayList<double[]>> cluster;  
    private ArrayList<double[]> means;  
    public StringBuffer stringBuffer;
  
    public kmeans(int K, int dataNum, int dimNum, ArrayList<double[]> tuples) {  
        this.K = K;  
        this.dataNum = dataNum;  
        this.dimNum = dimNum;  
        this.tuples = tuples;  
        stringBuffer = new StringBuffer("�����ǽ����\n");
        cluster = new ArrayList<ArrayList<double[]>>();  
        for (int i = 0; i < K; ++i) {  
            cluster.add(new ArrayList<double[]>());  
        }  
        means = new ArrayList<double[]>();  
  
    }  
    
    public void evStrBuffer(){
    	this.stringBuffer.delete(0, stringBuffer.length());
    }
    
    // ��ȡ tupleA �� tupleB�ľ��� , �±�0��ż�¼��ţ��±�1��dimNum���ʵ��Ԫ��  
    private double getDis(double tupleA[], double tupleB[]) {  
        double dis = 0;  
        for (int k = 1; k <= dimNum; ++k) {  
            dis += (tupleA[k] - tupleB[k]) * (tupleA[k] - tupleB[k]);  
        }  
        return Math.sqrt(dis);  
    }  
  
    // ��ȡtӦ�ñ����䵽�Ĵصı��  
    private int clusterOfTuple(double t[]) {  
        int index = 0;  
        double meanDis = getDis(means.get(0), t);  
        double dis = 0;  
        for (int i = 1; i < K; ++i) {  
            dis = getDis(means.get(i), t);  
            if (dis < meanDis) {  
                meanDis = dis;  
                index = i;  
            }  
        }  
        return index;  
    }  
  
    // �������ÿ���ص�����  
    private void updateMeans() {  
        means.clear();  
        for (int i = 0; i < K; ++i) {  
            double[] t = new double[dimNum + 1];  
            t[0] = -1; // means��ı��ͳһΪ-1  
            for (int j = 0; j < cluster.get(i).size(); ++j) {  
                for (int l = 1; l < dimNum + 1; ++l) {  
                    t[l] += cluster.get(i).get(j)[l]; // ÿ��ά���ۼ����  
                }  
            }  
            for (int l = 1; l < dimNum + 1; ++l) {  
                t[l] = t[l] / cluster.get(i).size(); // ����ÿ���صĽڵ��������ƽ��ֵ  
            }  
            means.add(t);  
        }  
    }  
  
    // �����������¼����  
    private void updateCluster() {  
        int index = 0;  
        for (int i = 0; i < K; ++i) // ��մ�  
        {  
            cluster.get(i).clear();  
        }  
        for (int i = 0; i < dataNum; ++i) {  
            index = clusterOfTuple(tuples.get(i));  
            cluster.get(index).add(tuples.get(i));  
        }  
    }  
  
    // ��ȡSSEֵ  
    private double getVar() {  
        double var = 0;  
        for (int i = 0; i < K; ++i) {  
            for (int j = 0; j < cluster.get(i).size(); ++j) {  
                double d = getDis(cluster.get(i).get(j), means.get(i));  
                var += d * d;  
            }  
        }  
        return var;  
    }  
  
    // ���о������ĺ���  
    public void cluster() {  
        int max = 1500, itNum = 0;                  // ��������  
        double oldVar = -1, newVar = -1;  
  
        for (int i = 0; i < K; ++i) {                // ��һ�������ѡȡK������  
            int random = (int) (Math.random() * dataNum);  
            means.add(tuples.get(random));  
        }  
//        System.out.println("�����ʼ������Ϊ��");
        stringBuffer.append("�����ʼ������Ϊ��\n");
        printTuples(means);  
        updateCluster();                            // ���ݵ����ĵľ����ʼ����  
//        System.out.println("�������ĳ�ʼ����"); 
        stringBuffer.append("�������ĳ�ʼ����\n");
        printCluster(cluster);  
        newVar = getVar();  
  
        while (Math.abs(newVar - oldVar) > 0.0000000000000001 && itNum < max) {  
//            System.out.println("-------------------------��" + itNum  
//                    + "�ε�����ʼ------------------------------");
            stringBuffer.append("-------------------------��" + itNum  
                    + "�ε�����ʼ------------------------------\n");
            updateMeans();  
//            System.out.println("�����������¼����"); 
            stringBuffer.append("�����������¼����\n");
            updateCluster();  
            printCluster(cluster);  
            oldVar = newVar;  
            newVar = getVar();  
//            System.out.println("--------------------------��" + itNum  
//                    + "�ε�����ɣ�oldVar=" + oldVar + ",newVar=" + newVar  
//                    + "-------------------------------"); 
            stringBuffer.append("--------------------------��" + itNum  
                    + "�ε�����ɣ�oldVar=" + oldVar + ",newVar=" + newVar  
                    + "-------------------------------\n");
            ++itNum;  
        }  
  
    }  
  
    private void printT(double[] t) {  
//        System.out.print((int) t[0] + 
//        		"-> " + (String)Adjacentmatrix.getInstance().getNodeMap().get((int) t[0])
//        		
//        		+".  (");
        stringBuffer.append((int) t[0] + 
        		"-> " + (String)Adjacentmatrix.getInstance().getNodeMap().get((int) t[0])
        		
        		+"\n");
//        for (int i = 1; i < dimNum + 1; ++i) {  
//            System.out.print(t[i] + ","); 
//            stringBuffer.append(t[i] + ",");
//        }  
//        System.out.println(")");
//        stringBuffer.append(")\n");
    }  
  
    private void printTuples(ArrayList<double[]> tuples) {  
        for (int i = 0; i < tuples.size(); ++i) {  
            printT(tuples.get(i));  
        }  
    }  
  
    private void printCluster(ArrayList<ArrayList<double[]>> cluster) {  
        for (int i = 0; i < K; ++i) {  
//            System.out.println("��" + i + "����,size=" + cluster.get(i).size());
            stringBuffer.append("��" + i + "����,size=" + cluster.get(i).size()+"\n");
//            System.out.print("���ģ�");  
//            printT(means.get(i));  
//            System.out.println("���еĵ㣺"); 
            stringBuffer.append("���еĵ㣺\n");
            printTuples(cluster.get(i));  
//            System.out.println();
            stringBuffer.append("\n");
        }  
    }  
  
    
  
}  