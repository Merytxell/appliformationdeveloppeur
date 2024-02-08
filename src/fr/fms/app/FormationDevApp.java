package fr.fms.app;

import java.util.Scanner;

import fr.fms.authentification.Authenticate;
import fr.fms.business.IBusinessimpl;
import fr.fms.entities.Courses;
import fr.fms.entities.Customer;

public class FormationDevApp {
	private static Scanner scan = new Scanner(System.in); 
	private static IBusinessimpl business = new IBusinessimpl();
	private static Authenticate authenticate = new Authenticate();

	public static final String TEXT_BLUE = "\u001B[36m";
	public static final String TEXT_RESET = "\u001B[0m";	
	private static final String COLUMN_ID = "ID de la Formation";
	private static final String COLUMN_NAMEOFCOURSES= "NAME";
	private static final String COLUMN_DESCRIPTION = "DESRIPTION";
	private static final String COLUMN_PRICE = "PRIX";
	private static final String COLUMN_NAME = "NAME";

	private static int idUser = 0;
	private static String login = null; 

	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenue sur Formation Dev, voici le menu des formations :\n");
		displayCourses();
		int choice = 0;
		while(choice != 6) {
			displayMenu();
			choice = scanInt();
			switch(choice) {
			case 1 : addCourses();				
			break;					
			case 2 : removeCourses();
			break;					
			case 3 : displayCart(true);
			break;					
			case 4 : displayCourses();
			break;						
			case 5 : connection();
			break;
			case 6 : System.out.println("à bientôt dans notre boutique :)");
			break;					
			default : System.out.println("veuillez saisir une valeur entre 1 et 6");
			}
		}
	}


	/**
	 * Méthode qui affiche le menu principale
	 */
	public static void displayMenu() {	
		if(login != null)	System.out.print(TEXT_BLUE + "Compte : " + login);
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("1 : Ajouter un article au panier");
		System.out.println("2 : Retirer un article du panier");
		System.out.println("3 : Afficher mon panier + total pour passer commande");
		System.out.println("4 : Afficher tous les articles en stock");
		System.out.println("5 : Connexion(Deconnexion) à votre compte");
		System.out.println("6 : sortir de l'application");
	}


	//Méthode qui affiche tous les articles en base en centrant le texte 

	public static void displayCourses() { 		
		System.out.println(Courses.centerString(COLUMN_ID) + Courses.centerString(COLUMN_NAMEOFCOURSES) + Courses.centerString(COLUMN_DESCRIPTION) + Courses.centerString(COLUMN_PRICE));
		business.readCourses().forEach(System.out::println);
	}

	/**
	 * Méthode qui supprime un article du panier
	 */
	public static void removeCourses() {
		System.out.println("Selectionner l'id de la formation à supprimer du panier");
		int idCourse = scanInt();
		business.rmFromCart(idCourse);
		displayCart(false);
	}

	/**
	 * Méthode qui ajoute un article au panier
	 */
	public static void addCourses() {
		System.out.println("Selectionner l'id de la formation à ajouter au panier");
		int idCourse = scanInt();
		Courses course = business.readOneCourse(idCourse);
		if(course != null) {
			business.addToCart(course);
			displayCart(false);
		}
		else System.out.println("l'article que vous souhaitez ajouter n'existe pas, pb de saisi id");
	}

	
	 //Méthode qui affiche le contenu du panier + total de la commande + propose de passer commande
	 
	private static void displayCart(boolean flag) {
		if(business.isCartEmpty()) 	System.out.println("PANIER VIDE");
		else {
			System.out.println("CONTENU DU PANIER :");
			String titles = Courses.centerString(COLUMN_ID) + Courses.centerString(COLUMN_NAMEOFCOURSES) + 
					Courses.centerString(COLUMN_DESCRIPTION) + Courses.centerString(COLUMN_PRICE) + Courses.centerString("QUANTITE");
			System.out.println(titles);
			business.getCart().forEach(a -> System.out.println(a.toString() + Courses.centerString(String.valueOf(a.getQuantity()))));
			if(flag) {
				System.out.println("MONTANT TOTAL : " + business.getTotal());
				System.out.println("Souhaitez vous passer commande ? Oui/Non :");
				if(scan.next().equalsIgnoreCase("Oui")) {
					nextStep();
				}
			}
		}
	}

		/**
		 *Méthode qui passe la commande, l'utilisateur doit être connecté
		 * si c'est le cas, l'utilisateur sera invité à associé un client à sa commande
		 * si le client n'existe pas, il devra le créer
		 * puis une commande associée au client sera ajoutée en base
		 * De même, des commandes minifiées seront associées à la commande
		 * une fois toutes les opérations terminées correctement, le panier sera vidé et un numéro de commande attribué
		 */
		private static void nextStep() {
			if(login == null)	{ 
				System.out.println("Vous devez être connecté pour continuer");
				connection();
			}
			if(login != null) {
				int idCustomer = newCustomer(idUser);	
				if(idCustomer != 0) {
					int idOrder = business.order(idCustomer);	
					if(idOrder == 0)	System.out.println("pb lors du passage de commande");
					else {
						System.out.println("Votre commande a bien été validé, voici son numéro : " + idOrder);
						business.clearCart();
					}
				}
			}
		}

		/**
		 * Méthode qui ajoute un client en base s'il n'existe pas déjà 
		 * @return id du client afin de l'associer à la commande en cours
		 */
		private static int newCustomer(int idUser) {
			System.out.println("Avez vous déjà un compte client ? Saisissez une adresse email valide :");
			String email = scan.next();		
			if(isValidEmail(email)) {	
				Customer customer = authenticate.existCustomerByEmail(email);
				if(customer == null) {
					System.out.println("Nous n'avons pas de compte client associé, nous allons le créer ");
					scan.nextLine();	
					System.out.println("saisissez votre nom :");
					String name = scan.nextLine();
					System.out.println("saisissez votre prénom :");
					String fName = scan.next();					
					System.out.println("saisissez votre tel :");
					String tel = scan.next();		
					scan.nextLine(); 
					System.out.println("saisissez votre adresse :");
					String address = scan.nextLine();
					Customer cust = new Customer(name, fName, email, address, address, idUser); 
					if(authenticate.addCustomer(cust))	
						return authenticate.existCustomerByEmail(email).getIdCustomer();
				}
				else {
					System.out.println("Nous allons associer la commande en cours avec le compte client : " + customer);
					return customer.getIdCustomer();
				}
			}
			else System.out.println("vous n'avez pas saisi un email valide");	
			return 0;
		}

		/**
		 * Méthode qui réalise la connexion/deconnexion d'un utilisateur
		 * si l'utilisateur n'existe pas, il lui est proposé d'en créer un
		 */
		private static void connection() {
			if(login != null) {
				System.out.println("Souhaitez vous vous déconnecter ? Oui/Non");
				String response = scan.next();
				if(response.equalsIgnoreCase("Oui")) {
					System.out.println("A bientôt " + login + TEXT_RESET);
					login = null;
					idUser = 0;
				}				
			}
			else {
				System.out.println("saisissez votre login : ");
				String log = scan.next();
				System.out.println("saisissez votre password : ");
				String pwd = scan.next();

				int id = authenticate.existUser(log,pwd);
				if(id > 0)	{
					login = log;
					idUser = id;
					System.out.print(TEXT_BLUE);
				}
				else {
					System.out.println("login ou password incorrect");
					System.out.println("Nouvel utilisateur, pour créer un compte, tapez ok");
					String ok = scan.next();
					if(ok.equalsIgnoreCase("ok")) {
						newUser();
					}
				}
			}
		}
		  //Méthode qui ajoute un nouvel utilisateur en base
		
		public static void newUser() {
			System.out.println("saisissez un login :");
			String login = scan.next();			
			int id = authenticate.existUser(login);	
			if(id == 0) { 
				System.out.println("saisissez votre password :");
				String password = scan.next();
				authenticate.addUser(login,password);		
				System.out.println("Ne perdez pas ces infos de connexion...");
				stop(2);
				System.out.println("création de l'utilisateur terminé, merci de vous connecter");
			}
			else	System.out.println("Login déjà existant en base, veuillez vous connecter");
		}

		public static void stop(int time) {
			try {
				Thread.sleep(time * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public static int scanInt() {
			while(!scan.hasNextInt()) {
				System.out.println("saisissez une valeur entière svp");
				scan.next();
			}
			return scan.nextInt();
		}

		public static boolean isValidEmail(String email) {
			String regExp = "^[A-Za-z0-9._-]+@[A-Za-z0-9._-]+\\.[a-z][a-z]+$";	
			return email.matches(regExp);
		}
	}


