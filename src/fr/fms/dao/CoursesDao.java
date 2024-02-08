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
		String str = "INSERT INTO T_Courses (IdCourse,Name,Description, Duration, Remote, UnitaryPrice) VALUES (?,?,?,?,?,?);";	
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setInt(1, obj.getIdCourse());
			ps.setString(2, obj.getName());
			ps.setString(3, obj.getDescription());
			ps.setString(4, obj.getDuration());	
			ps.setString(5, obj.getRemote());
			ps.setDouble(6, obj.getUnitaryPrice());
			return ps.executeUpdate() == 1;
			//System.out.println("insertion du cours réussie");
		} catch (SQLException e) {

		} 	
		return false;
	}


	@Override
	public Courses read(int id) {
		String str = "SELECT * FROM T_Courses WHERE IdCourse = ?";
		try (PreparedStatement ps= connection.prepareStatement(str)){
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					int rsIdCourse = rs.getInt("IdCourse");
					String rsName = rs.getString("Name");
					String rsDescription = rs.getString("Description");
					String rsDuration = rs.getString("Duration");
					String rsRemote = rs.getString("Remote");
					double rsPrice = rs.getDouble("UnitaryPrice");



					return new Courses (rsIdCourse,rsName, rsDescription,rsDuration,rsRemote, rsPrice);
				}

			}
		}catch (SQLException e) {

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
			ps.setDouble(5, obj.getUnitaryPrice());
			ps.setInt(6, obj.getIdCourse());
			if( ps.executeUpdate() == 1)	
				System.out.println("mise à jour réussie");
		} catch (SQLException e) {
			//logger.severe("pb sql sur la mise à jour d'un article " + e.getMessage());
		} 	
		return false;
	}


	@Override
	public boolean delete(int i) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Courses where IdCourse=" + i + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			//logger.severe("pb sql sur la suppression d'un article " + e.getMessage());
		} 	
		return false;
	}



	public ArrayList<Courses> readAll() {
		ArrayList<Courses> courses = new ArrayList<Courses>();
		String strSql = "SELECT * FROM T_Courses";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int rsIdArticle = resultSet.getInt(1);	
					String rsName = resultSet.getString(2);
					String rsDescription = resultSet.getString(3);
					String rsDuration = resultSet.getString(4);		
					String rsRemote = resultSet.getString(5);
					double rsUnitaryPrice = resultSet.getDouble(6);
					courses.add((new Courses(rsIdArticle,rsName,rsDescription,rsDuration,rsRemote,rsUnitaryPrice)));						
				}	
			}
		} catch (SQLException e) {


		}
		return courses;
	}

}










