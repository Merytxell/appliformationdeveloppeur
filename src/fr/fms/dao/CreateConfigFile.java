package fr.fms.dao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class CreateConfigFile {



	public static void main(String args[]) throws IOException {
		
		Properties prop = readPropertiesFile("src/config.properties");
		System.out.println(prop.getProperty("db.driver\", \"org.mariadb.jdbc.Driver"));
		System.out.println(prop.getProperty("db.url\", \"jdbc:mariadb://localhost:3306/formationdev"));
		System.out.println( prop.getProperty("db.login","root"));
		System.out.println(prop.getProperty("db.password","fms2024"));

	}
	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
}

