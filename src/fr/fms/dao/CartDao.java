package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.Cart;

public class CartDao implements Dao <Cart>{

	@Override
	public boolean create(Cart obj) {
		String str = "INSERT INTO T_Cart (IdCourse, Quantity, UnitaryPrice, IdOrder) VALUES (?,?,?,?);";	
		try (PreparedStatement ps = connection.prepareStatement(str)){	
			ps.setInt(1, obj.getIdCourse());
			ps.setInt(2, obj.getQuantity());
			ps.setDouble(3, obj.getUnitaryPrice());
			ps.setInt(4, obj.getIdOrder());

			ps.executeUpdate();			
			return true;
		} catch (SQLException e) {

		}
		return false;
	}


	@Override
	public Cart read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Cart obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Cart> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
