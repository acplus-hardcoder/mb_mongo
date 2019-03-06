package hikaricp_test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class test_main {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	static {
		config.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
		config.setUsername("c##scott");
		config.setPassword("Tiger07#");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

	private test_main() {
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static List<HashMap<String, Object>> fetchData() throws SQLException {
		String SQL_QUERY = "select * from emp";
		List<HashMap<String, Object>> employees = null;
		try (Connection con = test_main.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_QUERY);
				ResultSet rs = pst.executeQuery();) {
			employees = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> employee;
			while (rs.next()) {
				employee = new HashMap<String, Object>();
				employee.put("empno", rs.getInt("empno"));
				employee.put("ename", rs.getString("ename"));
				employee.put("job", rs.getString("job"));
				employee.put("mgr", rs.getInt("mgr"));
				employee.put("hiredate", rs.getDate("hiredate"));
				employee.put("sal", rs.getInt("sal"));
				employee.put("comm", rs.getInt("comm"));
				employee.put("deptno", rs.getInt("deptno"));
				employees.add(employee);
			}
		}
		return employees;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(fetchData());

	}

}
