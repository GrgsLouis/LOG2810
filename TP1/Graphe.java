import java.io.*;
import java.util.*;
import java.util.Stack;

import javafx.util.Pair;

public class Graphe {
	
	private ArrayList<Ville> arrayVille_; // list des villes contenues dans le graphe
	private ArrayList<Arc> arrayArc_;	// list des arcs contenus dans le graphe
	
	
	Graphe(){
		arrayVille_ = new ArrayList<Ville>();
		arrayArc_ = new ArrayList<Arc>();
	}
	

	public ArrayList<Ville> getArrayVille_() {
		return arrayVille_;
	}

	public ArrayList<Arc> getArrayArc_() {
		return arrayArc_;
	}

	public void setArrayVille_(ArrayList<Ville> arrayVille_) {
		this.arrayVille_ = arrayVille_;
	}

	public void setArrayArc_(ArrayList<Arc> arrayArc_) {
		this.arrayArc_ = arrayArc_;
	}
	
	// Affichage du graphe sur le console
	public void lireGraphe() {
		int i=0;
		while(i<arrayArc_.size()) {
			System.out.print("("+arrayArc_.get(i).getVille1_().getVilleNom()+","+arrayArc_.get(i).getVille1_().getVilleId()+",(");
			System.out.print("("+arrayArc_.get(i).getVille2_().getVilleNom()+","+arrayArc_.get(i).getDuree_()+")");
			while(i+1< arrayArc_.size() && arrayArc_.get(i).getVille1_().getVilleId() ==  arrayArc_.get(i+1).getVille1_().getVilleId()) {
				System.out.print("("+arrayArc_.get(i+1).getVille2_().getVilleNom()+","+arrayArc_.get(i+1).getDuree_()+")");
				i++;	
			}
			System.out.print("))");
			System.out.print("\n");
			i++;
		}
	}
	
	// Creation du graphe a partir d'un fichier
	public void CreerGraphe(String nomDeFichier) throws NumberFormatException, IOException {
		 
		String [] nomsVilles = {"Montreal","Quebec","Ottawa", "Toronto", "Halifax", "Sept-Iles","Thunder Bay", 
				  "Sandy Lake","Winnipeg", "Regina", "Saskatoon","Calgary", "Vancouver", "Edmenton", "Fort McMurray", "Churchill",
				  "Prince George", "Fort Nelson","Whitehorse", "Yellowknife"};
		String ligne;
		int nbLigne=0;
		String []parts;
	
		String nom=null;
		int id1=0;
		int id2=0;
		boolean station=false;
		int duree=0;
		
		
		FileReader fileReader = new FileReader(nomDeFichier);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while((ligne = bufferedReader.readLine()) != null) {
			parts=ligne.split(",");
			
			if(ligne.equals("")) {
          		nbLigne++;
          	}
          	
          	if(nbLigne==0) {
          		id1 = Integer.parseInt(parts[0]);
          		int temp=Integer.parseInt(parts[1]);
    			station =(temp==1);
    			
    			
    			nom = nomsVilles[id1-1];
    			arrayVille_.add(new Ville(nom,id1,station));
          	}
          	
          	else if (nbLigne ==1) {
          		if(!ligne.equals("")) {
          			id1=Integer.parseInt(parts[0]);
          			id2=Integer.parseInt(parts[1]);
          			duree=Integer.parseInt(parts[2]);
          			arrayArc_.add(new Arc(arrayVille_.get(id1-1),arrayVille_.get(id2-1),duree));
          		}
          	}
          }
		bufferedReader.close();   
	}
	
		// fonction qui prend en parametre une ville et retourne la liste de ses voisins avec la distance qui les séparent
		public ArrayList<Pair<Ville,Integer>> obtenirVoisin( Ville v ) {
			ArrayList<Pair<Ville,Integer>> temp= new ArrayList<Pair<Ville,Integer>>();
			for(int i=0; i< arrayArc_.size();i++) {
				if(arrayArc_.get(i).getVille1_().getVilleId()==v.getVilleId()) {
					temp.add(new Pair<>(arrayArc_.get(i).getVille2_(), arrayArc_.get(i).getDuree_()));
				}
				else if(arrayArc_.get(i).getVille2_().getVilleId()==v.getVilleId()) {
					temp.add(new Pair<>(arrayArc_.get(i).getVille1_(), arrayArc_.get(i).getDuree_()));
				}	
			}
			
				
			return temp;
		
		
		}

	
}
