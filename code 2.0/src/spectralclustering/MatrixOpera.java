package spectralclustering;

import java.util.ArrayList;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

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
	
}
