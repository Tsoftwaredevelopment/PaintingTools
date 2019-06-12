package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;


public class DBUtils {

	private static BasicDataSource dataSource;

	static {
		dataSource = new BasicDataSource();
		
		Properties properties = new Properties();
		InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);							
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			String initsize = properties.getProperty("initsize");
			String maxsize = properties.getProperty("maxsize");
			//
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setInitialSize(Integer.parseInt(initsize));
			dataSource.setMaxActive(Integer.parseInt(maxsize));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (connection != null) {
				
				connection.setAutoCommit(true);
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
