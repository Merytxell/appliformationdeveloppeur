
package fr.fms.dao;
import fr.fms.entities.Courses;

// ici on effectuera des tests
public class TestCourse {

	public static void main (String [] args) {

		
		//test méthode create ok !
		CoursesDao courseDao = new CoursesDao();
		//Courses courses = new Courses(5, "git", "création de commit", "2 jours", "distanciel", 20);
		//courseDao.create(courses);
		
		//test méthode read all ok 
		for (Courses course : courseDao.readAll()) {
				System.out.println(course);
			}
		Courses course = courseDao.read(5);
		System.out.println(course);
		
	
		//test méthode update ok !
		//Courses courses = new Courses(5, "git", "création de commit", "2 jours", "distanciel", 20);
		//courses.setDescription("ajout des commits");
		//courseDao.update(courses);
		//System.out.println(courses);
		
		
		//test méthode delete ok 
		courseDao.delete(5);
		
	}
	
	private static void testCoursesDao() {
		CoursesDao courseDao = new CoursesDao();


		//Supprimer un article
		

		System.out.println();
		//Afficher tous les articles
		for(Courses cours : courseDao.readAll()) {
			System.out.println(cours);
					}	
	}

}








