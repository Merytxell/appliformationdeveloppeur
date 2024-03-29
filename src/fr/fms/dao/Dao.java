package fr.fms.dao;
import java.sql.Connection;
import java.util.ArrayList;

import fr.fms.entities.Courses;



public interface Dao <T> {

	public static Connection connection = BddConnection.getConnection();
	public boolean create (T obj);
	public T read (int id);
	public boolean update(T obj);
	boolean delete(int i);
	public ArrayList<T> readAll();


}


