package xmlmapper_test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void main(String[] args) throws SQLException, IOException {
		try (InputStream in = test_main.class.getResourceAsStream("/mybatis-config.xml")) {
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
			try (SqlSession session = factory.openSession()) {
				Map<String, Object> result = session.selectOne("sample.mybatis.selectTest");
				System.out.println(result);
			}
		}
	}

}
