package resultDB;
//�������ݿ�����
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//�������ݿ����
import java.sql.Statement;
import java.sql.ResultSet;

public class MyDB {
	/**
	 * ���ݿ�����
	 * @driver
	 * @url
	 * @usename
	 * @password
	 */
	public static Connection getConnection(String driver,String url,String username,String password){
		Connection conn = null;
			try {
				Class.forName(driver);   //classLoader,���ض�Ӧ����
				System.out.println("�ɹ�����MySQL��������");
				conn = DriverManager.getConnection(url, username, password);//�������ݿ�
				System.out.println("���ݿ����ӳɹ���");
				
			} catch (ClassNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return conn;
		}
	
	/**
	 * author fanghui
	 * ��ѯ
	 * @param sql sql���
	 * @param conn ���ӵ��Ǹ����ݿ�� ����
	 * @category�����������ʵ��
	 * 
	 */
	public static void sql_Query(String sql,Connection conn) {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			
			System.out.println("word_1"+"\t"+"word_1_num" + "\t" + "word_2" +"\t" + "word_2_num"+"\t" + "total_num" +"\t"+"sim");
			while(rs.next()){
				System.out.println(rs.getString("word_1")+"\t" + rs.getInt("word_1_num") + "\t" + rs.getString("word_2") + "\t"+ rs.getInt("word_2_num") + rs.getInt("total_num")+rs.getDouble("sim") );	
			
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
}
	
	
	
	/**
	 * ���� insert into
	 * @sql sql���
	 * @conn ���ӵ��Ǹ����ݿ�� ����
	 */
	public static void sql_DMLandDDL(String sql,Connection conn) {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
			/*if(sql.startsWith("insert")|sql.startsWith("INSERT")){
				System.out.println("���û����в�����" + count +"����¼");
			}else if(sql.startsWith("update")|sql.startsWith("UPDATE")){
				System.out.println("������"+count +"��");
			}else if(sql.startsWith("delete")|sql.startsWith("DELETE")){
				System.out.println("ɾ����"+ count +"��");
			}else {
				System.out.println("���ݿⶨ�����ԣ�");
			}*/
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	}



