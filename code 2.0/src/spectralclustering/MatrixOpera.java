package spectralclustering;

import java.util.ArrayList;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

public class MatrixOpera {
	
	public static double[][] array ;
	/**
	 * ��һ��������˹����
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
	 * ����ֵ�ֽ�
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
	 * ����ֵ�ֽ�
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
	 * ������ֵ�ֽ�ĺ��� 
	 * @param maArr
	 * @return ArrayList<����ֵ����������>
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
				list.add(maArr[0].getAsDouble(j,i));	//����������������
			}
			eigenDim.setEigendim(list);
//			CommomUtils.printList(list);
			result.add(eigenDim);
		}
		return result;
	}
	
}
