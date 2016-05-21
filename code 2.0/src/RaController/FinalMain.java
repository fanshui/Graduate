package RaController;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.ujmp.core.Matrix;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import k_means.kmeans;
import spectralclustering.Adjacentmatrix;
import spectralclustering.EigenDim;
import spectralclustering.MatrixOpera;
import utils.Loadglossary;

public class FinalMain {
	public static ArrayList<double[]> arDouble;
	public static int stopFlag = 0;

	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		// 加载文件
		long startTime1 = System.currentTimeMillis();
		Loadglossary loadglossary = Loadglossary.getInstance();
		loadglossary.loadGlossary();
		loadglossary.loadprimitive();
		long endtime1 = System.currentTimeMillis();
		System.err.println("加载文件时长：" + (endtime1 - startTime1) + "ms");
		// 格式转换 resultRelWords->test.data
//		SomeUtils.convertToTest();

		long startTime = System.currentTimeMillis();
		int num = (int) Adjacentmatrix.getInstance().getMatrix().getColumnCount();
		Matrix AdjMatrix = Adjacentmatrix.getInstance().getMatrix();// 邻接矩阵
		long startTime2 = System.currentTimeMillis();
		System.err.println("构建邻接矩阵时长：" + (startTime2 - startTime) + "ms");

		// AdjMatrix.showGUI();//打印邻接矩阵
		startTime2 = System.currentTimeMillis();
		Matrix LapMatrix = MatrixOpera.laplacian(AdjMatrix);// 拉普拉斯
		// System.err.println("新窗口打开的是拉普拉斯矩阵!");
		// LapMatrix.showGUI(); //打印拉普拉斯矩阵

		// 对称矩阵的特征值分解
		Matrix[] eVDsymm = LapMatrix.eigSymm();
		long startTime3 = System.currentTimeMillis();
		System.err.println("矩阵操作时长：" + (startTime3 - startTime2) + "ms");
		// eVDsymm[0].showGUI(); //输出特征向量
		// eVDsymm[1].showGUI();//输出特征值

		// 对特征值分解后结果处理
		ArrayList<EigenDim> arrayList = MatrixOpera.evdResultHandler(eVDsymm);
		// 输出特征值与对应的特征向量
//		SomeUtils.printList(arrayList);

		// 求L的前K小特征值对应的特征向量。把K个特征向量放在一起构造一个N×K的矩阵M
		// 把M的每一行当成一个新的样本点，对这N个新的样本点进行K-Means聚类

		// 求出来的 MatrixOpera.array[][] 是一个N行N列的数组 每一列是一个特征向量 从左到右对应特征值由小到大
		// NXK 的矩阵M:

		// 设置K
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader("proper.json"));
		int K = object.get("Dimnum").getAsInt();
		int km = object.get("kmeans_K").getAsInt();
//		System.out.println("Dimnum = " + K);
		ArrayList<double[]> tuples = new ArrayList<double[]>();

		double[][] t = new double[num][K + 1];
		for (int i = 0; i < num; i++) {
			t[i][0] = i;
			for (int j = 0; j < K; j++) {
//				System.out.print(MatrixOpera.array[i][j] + "    ");
				t[i][j + 1] = MatrixOpera.array[i][j];
			}
			tuples.add(t[i]);
//			System.out.println();
		}


		/**
		 * 接下来K-Means聚类
		 */

		startTime3 = System.currentTimeMillis();
		kmeans test = new kmeans(km, num, K, tuples);
		test.cluster();
		long startTime4 = System.currentTimeMillis();
		System.err.println("k-means聚类时长：" + (startTime4 - startTime3) + "ms");
		new Thread() {
			public void run() {
				try {
					FileWriter fw = new FileWriter("test/result.data");
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(test.stringBuffer.toString());
					bw.flush();
					bw.close();
					fw.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				test.evStrBuffer();
			}
		}.start();

		long endTime = System.currentTimeMillis();
		System.err.println("程序运行时间：" + (endTime - startTime) + "ms");

	}

}
