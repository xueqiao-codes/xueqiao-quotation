package xueqiao.trade.hosting.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.soldier.base.logger.AppLog;

public class DBSingleConnection {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			AppLog.e(e.getMessage());
			Runtime.getRuntime().exit(1);
		}
	}
	
	public static Connection getNoneDbConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1/", "root", "root");
	}
	
	public static Connection getDbConnection(String dbName) throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + dbName, "root", "root");
	}
}
