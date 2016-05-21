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
		// �����ļ�
		long startTime1 = System.currentTimeMillis();
		Loadglossary loadglossary = Loadglossary.getInstance();
		loadglossary.loadGlossary();
		loadglossary.loadprimitive();
		long endtime1 = System.currentTimeMillis();
		System.err.println("�����ļ�ʱ����" + (endtime1 - startTime1) + "ms");
		// ��ʽת�� resultRelWords->test.data
//		SomeUtils.convertToTest();

		long startTime = System.currentTimeMillis();
		int num = (int) Adjacentmatrix.getInstance().getMatrix().getColumnCount();
		Matrix AdjMatrix = Adjacentmatrix.getInstance().getMatrix();// �ڽӾ���
		long startTime2 = System.currentTimeMillis();
		System.err.println("�����ڽӾ���ʱ����" + (startTime2 - startTime) + "ms");

		// AdjMatrix.showGUI();//��ӡ�ڽӾ���
		startTime2 = System.currentTimeMillis();
		Matrix LapMatrix = MatrixOpera.laplacian(AdjMatrix);// ������˹
		// System.err.println("�´��ڴ򿪵���������˹����!");
		// LapMatrix.showGUI(); //��ӡ������˹����

		// �Գƾ��������ֵ�ֽ�
		Matrix[] eVDsymm = LapMatrix.eigSymm();
		long startTime3 = System.currentTimeMillis();
		System.err.println("�������ʱ����" + (startTime3 - startTime2) + "ms");
		// eVDsymm[0].showGUI(); //�����������
		// eVDsymm[1].showGUI();//�������ֵ

		// ������ֵ�ֽ��������
		ArrayList<EigenDim> arrayList = MatrixOpera.evdResultHandler(eVDsymm);
		// �������ֵ���Ӧ����������
//		SomeUtils.printList(arrayList);

		// ��L��ǰKС����ֵ��Ӧ��������������K��������������һ����һ��N��K�ľ���M
		// ��M��ÿһ�е���һ���µ������㣬����N���µ����������K-Means����

		// ������� MatrixOpera.array[][] ��һ��N��N�е����� ÿһ����һ���������� �����Ҷ�Ӧ����ֵ��С����
		// NXK �ľ���M:

		// ����K
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
		 * ������K-Means����
		 */

		startTime3 = System.currentTimeMillis();
		kmeans test = new kmeans(km, num, K, tuples);
		test.cluster();
		long startTime4 = System.currentTimeMillis();
		System.err.println("k-means����ʱ����" + (startTime4 - startTime3) + "ms");
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
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				test.evStrBuffer();
			}
		}.start();

		long endTime = System.currentTimeMillis();
		System.err.println("��������ʱ�䣺" + (endTime - startTime) + "ms");

	}

}
