package spectralclustering;

import java.util.ArrayList;
import java.util.Iterator;

public class EigenDim {
	private double eigen; //特征值
	private ArrayList<Double> eigendim;//特征向量

	
	public double getEigen() {
		return eigen;
	}
	public void setEigen(double eigen) {
		this.eigen = eigen;
	}
	public ArrayList<Double> getEigendim() {
		return eigendim;
	}
	public void setEigendim(ArrayList<Double> eigendim) {
		this.eigendim = eigendim;
	}
	
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		StringBuilder sb = new StringBuilder( "特征值: " + eigen + "*******"+"特征向量  ：" );
		Iterator<Double>iterator = eigendim.iterator();
		while (iterator.hasNext()) {
			Double double1 = (Double) iterator.next();
			
			sb.append(double1.toString() + "   ");
		}
		return sb.toString();
	}
	
}
