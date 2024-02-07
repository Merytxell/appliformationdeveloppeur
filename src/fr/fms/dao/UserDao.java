package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.User;

public class UserDao implements Dao <User>{

	@Override
	public boolean create(User obj) {
		String str = "INSERT INTO T_Users (Login,Password) VALUES (?,?);";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPwd());			
			if( ps.executeUpdate() == 1)	return true;				
		} catch (SQLException e) {
			//logger.severe("pb sql sur la création d'un utilisateur ");
		} 				
		return false;
	}


	@Override
	public User read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Users where IdUser=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) 
				return new User(rs.getInt(1) , rs.getString(2) , rs.getString(3));
		} catch (SQLException e) {
			//logger.severe("pb sql sur la lecture d'un user " + e.getMessage());
		} 	
		return null;
	}

	@Override
	public boolean update(User obj) {
		try (Statement statement = connection.createStatement()){
			String str = "UPDATE T_Users set Login='" + obj.getLogin() +"' , " +
					"Password='" 		+ obj.getPwd() +"'" + " where idUser=" + obj.getId() + ";";			
			if(statement.executeUpdate(str) == 1) return true;
		} catch (SQLException e) {
			//	logger.severe("pb sql sur la mise à jour d'un utilisateur " + e.getMessage());
		} 	
		return false;
	}

	@Override
	public boolean delete(int i) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Users where IdUser=" + i+ ";";									
			if(statement.executeUpdate(str) == 1) return true;		
		} catch (SQLException e) {
			//logger.severe("pb sql sur la suppression d'un utilisateur ");
		}
		return false;
	}

	@Override
	public ArrayList<User> readAll() {
		ArrayList<User> users = new ArrayList<User>();
		String strSql = "SELECT * FROM T_Users";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int rsId = resultSet.getInt(1);	
					String rsLogin = resultSet.getString(2);
					String rsPassword = resultSet.getString(3);							
					users.add((new User(rsId,rsLogin,rsPassword)));						
				}	
			}
		} catch (SQLException e) {
		}

		return users;
	}
	public User findUserByCredentials(String login, String password) {
		String str = "SELECT * FROM T_Users where Login=? and Password=?;";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, login);									
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) 
				return new User(rs.getInt(1) , rs.getString(2) , rs.getString(3));
		} catch (SQLException e) {
			//logger.severe("pb sql sur renvoi d'un utilisateur à partir des credentials ");
		} 	
		return null;

	}
	public User findUserByLogin(String login) {
		String str = "SELECT * FROM T_Users where Login=?;";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, login);									
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) 
					return new User(rs.getInt(1) , rs.getString(2) , rs.getString(3));
			}
		} catch (SQLException e) {
			//logger.severe("pb sql sur renvoi d'un utilisateur à partir de son login ");
		} 	
		return null;
	}
}
