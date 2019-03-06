package anno_test.config;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // 설정파일
@EnableWebMvc // 스프링 MVC 설정
@ComponentScan(basePackages = { "anno_test.controller", "anno_test.config"}) // 컨트롤러 패키지 스캐닝
@MapperScan(basePackages = { "anno_test.maps" })
public class MvcConfig {

	static HikariConfig config = new HikariConfig();
	static HikariDataSource ds;
	static SqlSessionFactoryBean sqlSessionFactory;
	static {
		config.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setUsername("scott");
		config.setPassword("Tiger07#");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);

	}

	@Bean(name = "con")
	public Connection connection() throws SQLException {
		return ds.getConnection();
	}

	@Bean(name = "sqlsession")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(ds);
		sqlSessionFactory.setTypeAliasesPackage("anno_test.maps");
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(ds);
	}

	@Bean(name = "hello_anno")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setViewClass(JstlView.class);
		irvr.setPrefix("/WEB-INF/view/");
		irvr.setSuffix(".jsp");

		return irvr;
	}
}
