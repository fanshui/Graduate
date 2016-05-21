package k_means; 

import java.util.ArrayList;

import spectralclustering.Adjacentmatrix;  
  
/*
 * 选择K个点作为初始质心    
  repeat    
    将每个点指派到最近的质心，形成K个簇    
    重新计算每个簇的质心    
  until 簇不发生变化或达到最大迭代次数    
   时间复杂度：O(tKmn)，其中，t为迭代次数，K为簇的数目，m为记录数，n为维数  
   空间复杂度：O((m+K)n)，其中，K为簇的数目，m为记录数，n为维数 
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
        stringBuffer = new StringBuffer("以下是结果：\n");
        cluster = new ArrayList<ArrayList<double[]>>();  
        for (int i = 0; i < K; ++i) {  
            cluster.add(new ArrayList<double[]>());  
        }  
        means = new ArrayList<double[]>();  
  
    }  
    
    public void evStrBuffer(){
    	this.stringBuffer.delete(0, stringBuffer.length());
    }
    
    // 获取 tupleA 和 tupleB的距离 , 下标0存放记录编号，下标1到dimNum存放实际元素  
    private double getDis(double tupleA[], double tupleB[]) {  
        double dis = 0;  
        for (int k = 1; k <= dimNum; ++k) {  
            dis += (tupleA[k] - tupleB[k]) * (tupleA[k] - tupleB[k]);  
        }  
        return Math.sqrt(dis);  
    }  
  
    // 获取t应该被分配到的簇的编号  
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
  
    // 重新求得每个簇的质心  
    private void updateMeans() {  
        means.clear();  
        for (int i = 0; i < K; ++i) {  
            double[] t = new double[dimNum + 1];  
            t[0] = -1; // means点的编号统一为-1  
            for (int j = 0; j < cluster.get(i).size(); ++j) {  
                for (int l = 1; l < dimNum + 1; ++l) {  
                    t[l] += cluster.get(i).get(j)[l]; // 每个维度累加求和  
                }  
            }  
            for (int l = 1; l < dimNum + 1; ++l) {  
                t[l] = t[l] / cluster.get(i).size(); // 除以每个簇的节点数，求得平均值  
            }  
            means.add(t);  
        }  
    }  
  
    // 根据质心重新计算簇  
    private void updateCluster() {  
        int index = 0;  
        for (int i = 0; i < K; ++i) // 清空簇  
        {  
            cluster.get(i).clear();  
        }  
        for (int i = 0; i < dataNum; ++i) {  
            index = clusterOfTuple(tuples.get(i));  
            cluster.get(index).add(tuples.get(i));  
        }  
    }  
  
    // 获取SSE值  
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
  
    // 进行聚类主的函数  
    public void cluster() {  
        int max = 1500, itNum = 0;                  // 迭代次数  
        double oldVar = -1, newVar = -1;  
  
        for (int i = 0; i < K; ++i) {                // 第一步：随机选取K个质心  
            int random = (int) (Math.random() * dataNum);  
            means.add(tuples.get(random));  
        }  
//        System.out.println("随机初始化质心为：");
        stringBuffer.append("随机初始化质心为：\n");
        printTuples(means);  
        updateCluster();                            // 根据到质心的距离初始化簇  
//        System.out.println("根据质心初始化簇"); 
        stringBuffer.append("根据质心初始化簇\n");
        printCluster(cluster);  
        newVar = getVar();  
  
        while (Math.abs(newVar - oldVar) > 0.0000000000000001 && itNum < max) {  
//            System.out.println("-------------------------第" + itNum  
//                    + "次迭代开始------------------------------");
            stringBuffer.append("-------------------------第" + itNum  
                    + "次迭代开始------------------------------\n");
            updateMeans();  
//            System.out.println("根据质心重新计算簇"); 
            stringBuffer.append("根据质心重新计算簇\n");
            updateCluster();  
            printCluster(cluster);  
            oldVar = newVar;  
            newVar = getVar();  
            stringBuffer.append("--------------------------第" + itNum  
                    + "次迭代完成，oldVar=" + oldVar + ",newVar=" + newVar  
                    + "-------------------------------\n");
            ++itNum;  
        }  
  
    }  
  
    private void printT(double[] t) {  
        stringBuffer.append((int) t[0] + 
        		"-> " + (String)Adjacentmatrix.getInstance().getNodeMap().get((int) t[0])
        		
        		+"\n");
    }  
  
    private void printTuples(ArrayList<double[]> tuples) {  
        for (int i = 0; i < tuples.size(); ++i) {  
            printT(tuples.get(i));  
        }  
    }  
  
    private void printCluster(ArrayList<ArrayList<double[]>> cluster) {  
        for (int i = 0; i < K; ++i) {  
            stringBuffer.append("第" + i + "个簇,size=" + cluster.get(i).size()+"\n"); 
            stringBuffer.append("簇中的点：\n");
            printTuples(cluster.get(i));  
            stringBuffer.append("\n");
        }  
    }  
  
    
  
}  