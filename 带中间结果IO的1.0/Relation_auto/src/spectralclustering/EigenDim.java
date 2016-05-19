package spectralclustering;

import java.util.ArrayList;
import java.util.Iterator;

public class EigenDim {
	private double eigen; //����ֵ
	private ArrayList<Double> eigendim;//��������

	
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
		// TODO �Զ����ɵķ������
		StringBuilder sb = new StringBuilder( "����ֵ: " + eigen + "*******"+"��������  ��" );
		Iterator<Double>iterator = eigendim.iterator();
		while (iterator.hasNext()) {
			Double double1 = (Double) iterator.next();
			
			sb.append(double1.toString() + "   ");
		}
		return sb.toString();
	}
	
}
