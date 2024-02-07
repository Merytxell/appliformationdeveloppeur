package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Courses;


	public interface IBusiness {	
		
		//ajoute cours au panier
		public void addToCart(Courses course);		
		
		//retire du panier
		public void rmFromCart(int id);		
		
		//Liste du panier
		public ArrayList<Courses> getCart();	
		
		
		  //méthode qui réalise la commande en base avec l'idUser + total de la commande en cours + date du jour + contenu du panier :
		 // la méthode va céer une commande en base -> idOrder + montant + date + idUser
		  //puis va ajouter autant de commandes minifiées associées : orderItem -> idOrderItem + idArticle + Quantity + Price + idOrder
		
		public int order(int idUser);		
		
		
		// Liste des cours en  base
		 
		public ArrayList<Courses> readCourses();	
		
		//méthode qui renvoie le cours correspondant à l'id
		public Courses readOneCourse(int id);	
		
}
