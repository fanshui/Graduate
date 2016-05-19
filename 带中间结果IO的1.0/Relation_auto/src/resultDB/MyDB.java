package resultDB;
//用于数据库连接
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//用于数据库操作
import java.sql.Statement;
import java.sql.ResultSet;

public class MyDB {
	/**
	 * 数据库连接
	 * @driver
	 * @url
	 * @usename
	 * @password
	 */
	public static Connection getConnection(String driver,String url,String username,String password){
		Connection conn = null;
			try {
				Class.forName(driver);   //classLoader,加载对应驱动
				System.out.println("成功加载MySQL驱动程序");
				conn = DriverManager.getConnection(url, username, password);//连接数据库
				System.out.println("数据库连接成功！");
				
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return conn;
		}
	
	/**
	 * author fanghui
	 * 查询
	 * @param sql sql语句
	 * @param conn 连接的那个数据库的 连接
	 * @category里面具体表具体实现
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
}
	
	
	
	/**
	 * 插入 insert into
	 * @sql sql语句
	 * @conn 连接的那个数据库的 连接
	 */
	public static void sql_DMLandDDL(String sql,Connection conn) {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
			/*if(sql.startsWith("insert")|sql.startsWith("INSERT")){
				System.out.println("向用户表中插入了" + count +"条记录");
			}else if(sql.startsWith("update")|sql.startsWith("UPDATE")){
				System.out.println("更新了"+count +"行");
			}else if(sql.startsWith("delete")|sql.startsWith("DELETE")){
				System.out.println("删除了"+ count +"行");
			}else {
				System.out.println("数据库定义语言！");
			}*/
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	}



