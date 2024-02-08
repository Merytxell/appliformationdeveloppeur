package fr.fms.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import fr.fms.dao.CoursesDao;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.entities.Courses;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.User;
import fr.fms.entities.Cart;

public class IBusinessimpl implements IBusiness {

	private HashMap<Integer,Courses> cart;
	private CoursesDao coursesDao = new CoursesDao();
	private Dao<User> userDao = DaoFactory.getUserDao();
	private Dao<Order> orderDao = DaoFactory.getOrderDao();
	private Dao<Cart> cartDao = DaoFactory.getCartDao();
	private Dao<Customer> customerDao = DaoFactory.getCustomerDao();

	public IBusinessimpl() {
		this.cart = new HashMap<Integer,Courses>();
	}

	@Override
	public void addToCart(Courses course) {
		Courses art = cart.get(course.getIdCourse());
		if(art!= null) {
			art.setQuantity(art.getQuantity() + 1);
		}
		else cart.put(course.getIdCourse(),course);

	}

	@Override
	public void rmFromCart(int id) {
		Courses course = cart.get(id);
		if(course != null) {
			if(course.getQuantity() == 1)	cart.remove(id);
			else course.setQuantity(course.getQuantity() - 1);
		}

	}

	@Override
	public ArrayList<Courses> getCart() {
		// 
		return new ArrayList<Courses> (cart.values());
	}

	@Override
	public int order(int idCustomer) {
		
			if(customerDao.read(idCustomer) != null) {
				double total = getTotal(); 
				Order order = new Order(total, new Date(), idCustomer);
				if(orderDao.create(order)) {	
					for(Courses course : cart.values()) {	
						cartDao.create(new Cart(0, course.getIdCourse(), course.getQuantity(), course.getUnitaryPrice(), order.getIdOrder()));
					}
					return order.getIdOrder();
				}
			}
			return 0;
		}

		@Override
		public ArrayList<Courses> readCourses() {

			return coursesDao.readAll();
		}

		@Override
		public Courses readOneCourse(int idCourse) {
			
			return coursesDao.read(idCourse);
		}
		public double getTotal() {
			double [] total = {0};
			cart.values().forEach((a) -> total[0] += a.getUnitaryPrice() * a.getQuantity()); 	
			return total[0];
		}
		public boolean isCartEmpty() {
			return cart.isEmpty();
		}

		public void clearCart() {
			cart.clear();		
		}
	}
	
