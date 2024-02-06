package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Courses;

public class CoursesDao implements Dao  <Courses>{

	@Override
	public boolean create(Courses obj) {
		String str = "INSERT INTO T_Courses (Name,Description, Duration, Remote, UnitaryPrice) VALUES (?,?,?,?,?);";	
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getDescription());
			ps.setString(3, obj.getDuration());	
			ps.setString(4, obj.getRemote());
			ps.setInt(4, obj.getUnitaryPrice());
			if( ps.executeUpdate() == 1)	
				System.out.println("insertion du cours réussie");
		} catch (SQLException e) {

		} 	
		return false;
	}


	@Override
	public Courses read(int id) {

		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Articles where IdArticle=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) return new Courses(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4), rs.getInt(5));
		} catch (SQLException e) {

		} 	
		return null;
	}

	@Override
	public boolean update(Courses obj) {
		String str = "UPDATE T_Courses set Name=? , Description=? , Duration=?, Remote=?, UnitaryPrice=? ,  where idCourse=?;";
		try (PreparedStatement ps = connection.prepareStatement(str)){				
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getDescription());
			ps.setString(3, obj.getDuration());
			ps.setString(4, obj.getRemote());
			ps.setInt(5, obj.getUnitaryPrice());
			ps.setInt(6, obj.getIdCourse());
			if( ps.executeUpdate() == 1)	
				System.out.println("mise à jour réussie");
		} catch (SQLException e) {
			//logger.severe("pb sql sur la mise à jour d'un article " + e.getMessage());
		} 	
		return false;
	}


	@Override
	public boolean delete(Courses obj) {

		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Articles where IdArticle=" + obj.getIdCourse() + ";";									
			statement.executeUpdate(str);		
			System.out.println("supression réussie");
		} catch (SQLException e) {

		} 	
		return false;

	}

	public ArrayList<Courses> readAll(int idArticle) {
		ArrayList<Courses> courses = new ArrayList<Courses>();
		String strSql = "SELECT * FROM T_Courses" + idArticle;		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int rsIdArticle = resultSet.getInt(1);	
					String rsName = resultSet.getString(2);
					String rsDescription = resultSet.getString(3);
					String rsDuration = resultSet.getString(4);		
					String rsRemote = resultSet.getString(5);
					int rsUnitaryPrice = resultSet.getInt(6);
					courses.add((new Courses(rsIdArticle,rsName,rsDescription,rsDuration,rsRemote,rsUnitaryPrice)));						
				}	
			}
		} catch (SQLException e) {


		}
		return courses;
	}

}







