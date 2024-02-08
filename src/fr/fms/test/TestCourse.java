package fr.fms.test;
import java.util.Scanner;
import java.util.function.Predicate;

import fr.fms.dao.CoursesDao;
import fr.fms.dao.CustomerDao;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.UserDao;

import fr.fms.entities.Courses;
import fr.fms.entities.Customer;
import fr.fms.entities.User;

// ici on effectuera des tests
public class TestCourse {

	public static void main (String [] args) {
		
		
		
	
	
		//recherche des users - ok
		//System.out.println(new UserDao().findUserByLogin("Neo"));
		
		//créer un user - ok
		//UserDao userDao= new UserDao();
		//User user = new User (2,"Trinity","010101");
		//userDao.create(user);
		
		//vérfication des users ok
	//	Scanner scan = new Scanner (System.in);
		//UserDao userDao = new UserDao();
		//System.out.println("saisissez votre identifiant");
	//	String login = scan.nextLine();
	//	System.out.println("saisissez votre password");
	//	String pwd = scan.nextLine();
	//	User user = userDao.findUserByCredentials(login,pwd);
	//	if (user != null) {
		//	for (Courses courses : new CoursesDao().readAll()) {
		//		System.out.println(courses);
	//	}
		//else System.out.println("accès refusé");
		//scan.close();
		
		//afficher tous les utilisateurs en base : ok !
		//for(User user : userDao.readAll())
			//System.out.println(user);
		
		//affichage des cours avec leur id - ok !
		//CoursesDao courseDao = new CoursesDao();
		//Courses courses = courseDao.read(4);
		//System.out.println(courses);
		
		//créer un customer
		/*CustomerDao customerDao = new CustomerDao();
		Customer customer = new Customer (3,"Avila","Claire","avila.claire@mail.com","060000","route des vins",5);
		customerDao.create(customer);
		System.out.println(customer);*/
		
		//associer customer à user : à faire
		//Customer customer = new Customer();
		
	//}
	
	
//}
		
		
		//test méthode create ok !
		//CoursesDao courseDao = new CoursesDao();
		//Courses courses = new Courses(5, "git", "création de commit", "2 jours", "distanciel", 20);
		//courseDao.create(courses);
		
		//test méthode read all ok 
		//for (Courses course : courseDao.readAll()) {
				//System.out.println(course);
			//}
		//Courses course = courseDao.read(5);
		//System.out.println(course);
		
	
		//test méthode update ok !
		//Courses courses = new Courses(5, "git", "création de commit", "2 jours", "distanciel", 20);
		//courses.setDescription("ajout des commits");
		//courseDao.update(courses);
		//System.out.println(courses);
		
		
		//test méthode delete ok 
		//courseDao.delete(5);
		
}
	
	










