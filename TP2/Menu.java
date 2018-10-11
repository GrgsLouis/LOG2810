import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	// L'interface sur laquelle l'utilisateur travaillera.
	public static void main(String[]args) throws NumberFormatException, IOException {
		
		boolean isExit = false;
		String options = "abcd";
		Variante v1= new Variante();		
		Automate r1= new Automate();
		Traitement auto = new Traitement(r1,v1);


		while(!isExit){
			try {		
			Scanner in= new Scanner(System.in);
			System.out.println();
			System.out.println();
			
			System.out.println("Veuillez choisir un des options suivants: ");
			
			System.out.println( "(a) Creer l'automate  " );
			
			System.out.println( "(b) Traiter les requetes" );
				
			System.out.println( "(c) Afficher les mots de passe valides obtenus" );
			
			System.out.println( "(d) Quitter" );
			System.out.println();
			System.out.println();
			System.out.println( "Option souhaitee: " );
			
			options =in.nextLine();
			
			
				switch (options) {
				
				case "a":{
					String nomDeFichier="";
					System.out.println( "entrez le nom de fichier de regles: " );
					nomDeFichier=in.nextLine();
					r1.CreerAutomate(nomDeFichier);
					auto.setAutomate_(r1);
					
					
					break;
				}
				case "b":{
					String nomDeFichier="";
					System.out.println( "entrez le nom de fichier de variantes: " );
					nomDeFichier=in.nextLine();
					v1.traiterLesEntrees(nomDeFichier);
					auto.setVariante_(v1);
					
					
					break;
				}
				case "c":{
					auto.trouverMotDePasse(v1);
					auto.afficherLesMotsDePasse();
					
					break;
				}
			
			
				case "d": System.out.println("Vous avez quitte le programme!"); isExit = true; break;
				default:
					System.out.println("Erreur: Entree non valide. Veuillez reessayer!" );
					System.out.println("");
					System.out.println("");
					break;
				
				}
				
		}
			catch(FileNotFoundException e) {
				System.out.println("");
				System.out.println("Erreur: fichier non trouvee. Veuillez reessayer!" );
				System.out.println("");
			
			}
		
	}

		
		
	}	
}
