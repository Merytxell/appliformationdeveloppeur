package fr.fms.dao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;


public class BddConnection {

	private static Connection connection = null;
	private static String driver;
	private static String url;
	private static String login;
	private static String password;

	private static final Logger logger = Logger.getLogger(BddConnection.class.getName());


	//on sécurise la connection et on la met en place
	private BddConnection() {
		try  {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,login,password);
		}			
		catch (ClassNotFoundException | SQLException e) {
			logger.severe("connection pb : " + e.getMessage());

		}
		catch (Exception e) {
			logger.severe("pb : " + e.getMessage());
		}
	}
	//singleton pour véifier que la connection existe
	public static synchronized Connection getConnection() {	
		if(connection == null) 	new BddConnection();
		return connection;
	}


	private static void getConfigFile() throws FileNotFoundException, IOException {
		Properties props = new Properties();		
		try (FileInputStream fis = new FileInputStream("files/config.properties")){
			props.load(fis);
		} catch (FileNotFoundException e1) {
			logger.severe("Fichier de config non trouvé " + e1.getMessage());
		} catch (IOException e1) {
			logger.severe("Erreur lecture fichier config " + e1.getMessage());
		}		
		driver = props.getProperty("db.driver");
		url = props.getProperty("db.url");
		login = props.getProperty("db.login");
		password = props.getProperty("db.password");
	}
}
