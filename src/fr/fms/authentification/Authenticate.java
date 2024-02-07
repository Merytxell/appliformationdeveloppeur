package fr.fms.authentification;

import fr.fms.dao.CustomerDao;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.UserDao;
import fr.fms.entities.Customer;
import fr.fms.entities.User;

public class Authenticate {

	private Dao<Customer> customerDao = DaoFactory.getCustomerDao();
	private Dao<User> userDao = DaoFactory.getUserDao();

	
	public int existUser(String log, String pwd) {
		User user = ((UserDao)userDao).findUserByCredentials(log,pwd);
		if(user != null )	return user.getId();
		return 0;
	}
	
	
	public int existUser(String log) {
		User user = ((UserDao)userDao).findUserByLogin(log);
		if(user != null )	return user.getId();
		return 0;
	}

	
	public Customer existCustomerByEmail(String email) {
		return ((CustomerDao)customerDao).findCustomerByEmail(email);
	}

	public void addUser(String email, String password) {
		userDao.create(new User(email, password));		
	}

	public boolean addCustomer(Customer customer) {
		return customerDao.create(customer);		
	}
}
