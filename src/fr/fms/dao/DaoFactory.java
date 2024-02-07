package fr.fms.dao;
import fr.fms.entities.Courses;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.Cart;
import fr.fms.entities.User;

public class DaoFactory {

	
	public static Dao<Courses> getCoursesDao() {
		return new CoursesDao();		
	}
	
	public static Dao<User> getUserDao() {
		return new UserDao();
	}
	
 	
 	public static Dao<Order> getOrderDao() {
 		return new OrderDao();
 	}
 	
 	public static Dao <Cart> getCartDao() {
 		return new CartDao();
 	}
 	
 	public static Dao<Customer> getCustomerDao() {
 		return new CustomerDao();
 	}
 	
 	
}

