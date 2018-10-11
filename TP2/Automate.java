
import java.util.*;
import java.io.*;
import java.util.Stack;

public class Automate {
	
	private ArrayList<String> etats_;
	private int index_;
	
	public Automate() {
		etats_= new ArrayList<String>();
	}
	
	public Automate(ArrayList<String> etat, int index) {
		etats_=etat;
		index_=index;
	}
	
	// permet de lire le fichier texte contenant des règles et qui crée l’automate responsable
	public void CreerAutomate(String nomDeFichier) throws NumberFormatException, IOException {
		etats_=new  ArrayList<String>();
		String ligne;
		String []parts;
		
		FileReader fileReader = new FileReader(nomDeFichier);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		int nbLigne=0;
		
		while((ligne = bufferedReader.readLine()) != null) {
			
			if(nbLigne == 0) {
				index_ = Integer.parseInt(ligne); 
				nbLigne++;
			}
			else {
				parts=ligne.split("=");
				etats_.add(parts[1]);
				//System.out.println(parts[1]);
				//System.out.println(etats_.get(nbLigne-1));
				nbLigne++;
			}
          }
		System.out.println("L'automate est cree!");
		bufferedReader.close();   
	};
	

	public ArrayList<String> getEtats_() {
		return etats_;
	}

	public void setEtats_(ArrayList<String> etats_) {
		this.etats_ = etats_;
	}

	public int getIndex_() {
		return index_;
	}

	public void setIndex_(int index_) {
		this.index_ = index_;
	}
}
