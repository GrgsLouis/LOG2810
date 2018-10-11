import java.io.IOException;
import java.util.*;

import javafx.util.Pair;


public class Dijkstra {
	private int MAXVALUE=100000;	// un valeur infini
	private Graphe graphe_;			
	private ArrayList<Ville> chemin_= new ArrayList<Ville>();	// nous permet de retracer le plus court chemin
	private Arc[] tableau_= new Arc[20];	// trier les villes selon leur distance à une ville donnée par l'utilisateur 
	
	
	
	public Arc[] getTableau_() {
		return tableau_;
	}
	
	public ArrayList<Ville> getChemin_(){
		return chemin_;
	}
	
	
	Dijkstra(Graphe graphe){
		graphe_ = graphe;
		
	}
	
	// retourner l'index d'une ville dont le tableau trier 
	public int getIndexVille(Ville v) {
		int a=0;
		for(int i=0;i<tableau_.length;i++) {
			if(v.getVilleId()==tableau_[i].getVille1_().getVilleId()) {
				a=i;
			}
		}
		return a;
	}
	
	// permet de mettre la ville de depart entrée par l'utilisateur à la case 0 de tableau trier 
	public void premiereIteration(Ville v1) {
		tableau_[0]=new Arc(v1,v1,0);
		int a=1000;int b=0;
		for(int i =1;i<tableau_.length;i++) {
			if(  v1.getVilleId() != graphe_.getArrayVille_().get(b).getVilleId()) {
			tableau_[i]=new Arc(graphe_.getArrayVille_().get(b),new Ville(),MAXVALUE);
			}else {
				a=i;
			}
				b++;
			
		}
		if(a< tableau_.length) {
			tableau_[a]= new Arc(graphe_.getArrayVille_().get(tableau_.length-1),new Ville(),MAXVALUE);
		
		}
		
		
	}
	
	// Permet de trouver l'indice d'un arc contenu dans la liste de tableau ayant la plus petite duree plus grande que celle de l'indice 
	public int trouverPlusPetitIndice(Arc[] tableau, int indice, int value) {
		int min=1000000;
		int temp = indice; 
		for(int i=indice;i<tableau.length;i++) {
			if(!(tableau[i].getVille1_().getVilleId()==tableau[indice].getVille1_().getVilleId())) {
			if(tableau[i].getDuree_()<=min && tableau[i].getDuree_()>= value ) {
				temp=i;
				min=tableau[i].getDuree_();
				
			}
			}
		}
		
		return temp;
	}
	
	
	
	public void plusCourtChemin( Ville v1, Ville v2, CheapVehicule vehicule) {
		
		this.premiereIteration(v1);
		int a= 0;
		boolean villeTrouve= (v1==v2);
		int nbIteration =0;
		while(!villeTrouve && nbIteration<tableau_.length) {
			// trie des villes selon l'éloignement de la ville de départ dans le tableau 
			Arc syphax=tableau_[nbIteration];
			tableau_[nbIteration]=tableau_[a];
			tableau_[a]=syphax;
			
			// obtenir les voisins de la ville ayant la plus petite eloignement de la ville de depart 
			ArrayList<Pair<Ville,Integer>> temp=new ArrayList<Pair<Ville,Integer>>(graphe_.obtenirVoisin(tableau_[nbIteration].getVille1_()));
			// mise a jour des durees dans le tableau selon les voisins obtenus ci-haut 
			for(int k=0;k<temp.size();k++) {
				for (int j=nbIteration+1;j<tableau_.length;j++) {
					if(tableau_[j].getVille1_().getVilleId()==temp.get(k).getKey().getVilleId() && (tableau_[j].getDuree_()>tableau_[nbIteration].getDuree_()+temp.get(k).getValue())) {
						tableau_[j].setDuree_(tableau_[nbIteration].getDuree_()+temp.get(k).getValue());
						tableau_[j].setVille2_(tableau_[nbIteration].getVille1_());
					}
			}
			}
		
			// on verifie si la ville est atteinte, sinon on continue 
			if(tableau_[nbIteration].getVille1_().getVilleId()==v2.getVilleId()) {
				villeTrouve=true;
			}
			else {
			a=trouverPlusPetitIndice(tableau_,nbIteration,tableau_[nbIteration].getDuree_());
				nbIteration++;
				
			}
			
		}
		
		// retracement du chemin de la ville de depart à l'arrivée
		int indexVille=0;
		Ville chemin = new Ville(v2.getVilleNom(),v2.getVilleId(),v2.isVilleStation());
		if(!(chemin==v1))
				{
			indexVille=getIndexVille(chemin);
			chemin=tableau_[indexVille].getVille1_();
			chemin_.add(chemin);
		while(!(chemin==v1)) {
			
			indexVille=getIndexVille(chemin);
			chemin=tableau_[indexVille].getVille2_();
			chemin_.add(chemin);
			
		}
		}else {
			chemin_.add(chemin);
		}
		boolean arrive= false;
		if(v1==v2) {
			arrive= true;
		}
		
		//verification avec CheapCar et SueprCar de la faisabilité du trajet 
		int temps=tableau_[getIndexVille(v2)].getDuree_()*60;
		String superCar= "non";
		for(int k=0 ;k<2 && !arrive;k++) {
			if(k==1) {
				superCar="oui";
				vehicule.setNiveauEssence_(100);
				
				if(vehicule.getModel_()=="Pick up")
					vehicule.setConsommation_(vehicule.getConsommation_()-3);
				
				else
					vehicule.setConsommation_(vehicule.getConsommation_()-2);
				
					
			}
		for(int i=chemin_.size()-1;i>=0 && !(vehicule.getNiveauEssence_()<12) && !(arrive) ;i--) {
			double conso=vehicule.getConsommation_();
			double  niveau=  vehicule.getNiveauEssence_();
			
			
			double durree=(double) ((tableau_[getIndexVille(chemin_.get(i))].getDuree_()) - (tableau_[(getIndexVille(tableau_[getIndexVille(chemin_.get(i))].getVille2_()))].getDuree_()));
			niveau = niveau - (durree*conso);

			
			vehicule.setNiveauEssence_(niveau);
			
			
			if(chemin_.get(i).isVilleStation()){
				if( !(vehicule.getNiveauEssence_()<12) && (i!= chemin_.size()-1) && (i!=0)) {
					temps+=15;
					vehicule.setNiveauEssence_(100);
				}
			}
			
			
			if(tableau_[getIndexVille(chemin_.get(i))].getVille1_().getVilleId()==v2.getVilleId() &&  !(vehicule.getNiveauEssence_()<12)) {
				
			
				arrive = true;
			}
				
		}
		}
		if(arrive) {
			 if(superCar=="non") {
				 	System.out.println("\nBraquage reussit avec " +vehicule.getModel_()+" CheapCar"+ " en " + temps + " minutes avec un niveau d'essence: "+ vehicule.getNiveauEssence_() +"%");
			 }else{
		
				 System.out.println("\nBraquage reussit avec " +vehicule.getModel_()+" SuperCar"+ " en " + temps + " minutes avec un niveau d'essence: "+ vehicule.getNiveauEssence_() +"%");
			 }
		 }else
			System.out.println("Niveau d'essence trop bas : "+ vehicule.getNiveauEssence_() + "%");
		
		
		
		
		
		
		
		}
	
	public static void main(String args []) throws  IOException {
		int cont = 1;
		while(cont==1) {
			
		// Creation de graphe
		Graphe graphe = new Graphe();
		
		// le cheminement du fichier.txt
		graphe.CreerGraphe("C:\\Users\\Utilisateur\\Desktop\\Grgs\\PolyTechnique\\Hiver2018\\LOG2810\\TP1\\villes.txt");
		
		Scanner in= new Scanner(System.in);
		
		System.out.println("Veuillez saisir le numero de la ville de depart: ");
		int x=in.nextInt();
		System.out.println("Veuillez saisir le numero de la ville d'arrivee:  ");
		int y=in.nextInt();
		System.out.println("Veuillez saisir le model du vehicule (1 pour voiture 2 pour fourgon ou 3 pour pick up):");
		int z=in.nextInt();

			Ville depart = graphe.getArrayVille_().get(x-1);
			Ville arrivee = graphe.getArrayVille_().get(y-1);
			
			Dijkstra djikstra = new Dijkstra(graphe);
			System.out.println("On travail actuellemnt sur le graphe suivant: \n");
			
			//Lecture du graphe 
			djikstra.graphe_.lireGraphe();
			
			CheapVehicule a = new CheapVehicule("Voiture");
			CheapVehicule b = new CheapVehicule("Fourgon");
			CheapVehicule c = new CheapVehicule("Pick up");
			
			if(z==1) {
				djikstra.plusCourtChemin(depart, arrivee, a);
			}else if(z==2){
				djikstra.plusCourtChemin(depart, arrivee, b);
				
			}if(z==3){
				djikstra.plusCourtChemin(depart, arrivee, c);
				
			}
			
			//Affichage de resultats
			for(int i =djikstra.getChemin_().size()-1;i>=0;i--) {
				if(i!=0) {
				System.out.print(djikstra.getChemin_().get(i).getVilleId() + "  ---> ");
				}else
					System.out.print(djikstra.getChemin_().get(i).getVilleId() );
			
			}
			System.out.println("\npour continuer entrez 1  et pour quitter 0 ");
			cont=in.nextInt();
		}
	}
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
}
