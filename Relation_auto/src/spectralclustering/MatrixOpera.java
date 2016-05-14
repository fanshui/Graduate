package spectralclustering;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import utils.SomeUtils;

public class MatrixOpera {
	
	public static double[][] array ;
	/**
	 * 归一化拉普拉斯矩阵
	 * @param matrix
	 * @return
	 */
	public static Matrix laplacian(Matrix matrix) {
		double rowSum = 0;
		long rowCount = matrix.getRowCount();
		Matrix duplicateMatrix = matrix.clone();
		Matrix newMatrix = DenseMatrix.Factory.zeros(rowCount,rowCount);
		for (int i = 0; i < duplicateMatrix.getRowCount(); i++) {
			rowSum = 0;
			for (int j = 0; j < duplicateMatrix.getRowCount(); j++) {
				rowSum = rowSum + duplicateMatrix.getAsDouble(i,j);
			}
			newMatrix.setAsDouble(rowSum,i,i);	
		}
		
		return newMatrix.minus(duplicateMatrix);
	}

	/**
	 * 奇异值分解
	 * @param dense
	 * @return
	 */
	public static Matrix[] singularValueDecomposition(Matrix dense) {
		return dense.svd();
	}
	
	/**
	 * Calculates a low rank approximation of the singular 
	 * value decomposition of the matrix: A = U*S*V' but 
	 * considers only the k largest singular values.
	 * @param dense
	 * @param k
	 * @return
	 */
	public static Matrix[] sVDecompositionK(Matrix dense,int k) {
		return dense.svd(k);
	}
	
	/**
	 * 特征值分解
	 * @param dense
	 * @return
	 */
	public static Matrix[] eigenValueDecomposition(Matrix dense) {
		return dense.eig();
	}
	
	/**
	 * Calculates the Eigen decomposition of a symmetric matrix.
	 *	Returns:
     *  Eigen decomposition of the matrix.
	 * @param dense
	 * @return
	 */
	public static Matrix[] eVDecompositionSYM(Matrix dense) {
		return dense.eigSymm();
	}
	
	
	/**
	 * 对特征值分解的后处理 
	 * @param maArr
	 * @return ArrayList<特征值与特征向量>
	 */

	public static ArrayList<EigenDim> evdResultHandler(Matrix[] maArr) {
		ArrayList<EigenDim> result = new ArrayList();
		long n = maArr[0].getColumnCount();
		long m = n;long n1 =n;
		array = new double[(int) m][(int) n1];
		for(long i = 0; i < n; i ++ ){
			
			EigenDim eigenDim = new EigenDim();
			ArrayList<Double>list = new ArrayList<>();
			eigenDim.setEigen(maArr[1].getAsDouble(i,i));
			for(long j = 0;j < n;j ++){
				 m = i;n1= j;
				array[(int) m][(int) n1] = maArr[0].getAsDouble(i,j);
				list.add(maArr[0].getAsDouble(j,i));	//算出来有问题改这里
			}
			eigenDim.setEigendim(list);
//			CommomUtils.printList(list);
			result.add(eigenDim);
		}
		return result;
	}
	
	
//	public static void main(String[] args) {
//		Matrix matrix = DenseMatrix.Factory.zeros(3,3);
//		matrix.setAsDouble(-1, 0,0);
//		matrix.setAsDouble(1, 0,1);
//		matrix.setAsDouble(-4, 1,0);
//		matrix.setAsDouble(3, 1,1);
//		matrix.setAsDouble(1, 2,0);
//		matrix.setAsDouble(2, 2,2);
//		
////		matrix.showGUI();
//		
////		Matrix[] sVd = matrix.eig();
////		System.out.println("特征值分解: ");
////		for (Matrix matrix2 : sVd) {
////			System.out.println(matrix2);
////		}
//		
//		Matrix[] sVd1 = matrix.eigSymm();
//		System.out.println("对称特征值分解: ");
//		for (Matrix matrix2 : sVd1) {
//			System.out.println(matrix2);
//		}
//       SomeUtils.printList(evdResultHandler(sVd1));
//		
//       for(int i= 0;i<3;i++){
//			for(int j=0;j<3;j++){
//				System.out.print(i+" " +j +" : "+MatrixOpera.array[i][j]+"    ");
//			}
//			System.out.println();
//		}
//       
////		Matrix[] sVd2 = matrix.svd();
////		System.out.println("奇异值分解: ");
////		for (Matrix matrix2 : sVd2) {
////			System.out.println(matrix2);
////		}	
//		
////		Matrix[] sVd3 = matrix.svd(3);
////		System.out.println("k=2 奇异值分解: ");
////		for (Matrix matrix2 : sVd3) {
////			System.out.println(matrix2);
////		}		
		
//	}
	
}
