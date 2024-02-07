
package fr.fms.dao;
import fr.fms.entities.Courses;

// ici on effectuera des tests
public class TestCourse {

	public static void main (String [] args) {

		
		//test méthode create ok !
		CoursesDao courseDao = new CoursesDao();
		//Courses courses = new Courses(5, "git", "création de commit", "2 jours", "distanciel", 20);
		//courseDao.create(courses);
		
		//test méthode read all ok !
		for (Courses course : courseDao.readAll()) {
				System.out.println(course);
			}
		Courses course = courseDao.read(3);
		System.out.println(course);
		
	
		
		
	}
	//test des méthodes crud
	private static void testCoursesDao() {
		CoursesDao courseDao = new CoursesDao();


		//afficher les articles

	for (Courses course : courseDao.readAll()) {
			System.out.println(course);
		}
		System.out.println();
		//Afficher un article à partir de son id
		Courses course = courseDao.read(4);
		System.out.println(course);


		//Mise à jour de l'article
		course.setDescription("création d'un commit");
		courseDao.update(course);
		

		//Supprimer un article
		if(course != null)		
		courseDao.delete(course);

		//ajouter un article	
		Courses courses = new Courses(5, "git", "création de commit", "2 jours", "distanciel", 20);
		courseDao.create(courses);
		 
		 
		

		System.out.println();
		//Afficher tous les articles
		for(Courses cours : courseDao.readAll()) {
			System.out.println(cours);
					}	
	}

}








