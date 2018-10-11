
import java.util.*;
import java.io.*;
import java.util.Stack;

public class Variante {
	
	private ArrayList<String> variantes_;
	private int index_;
	
	public Variante() {
		variantes_=new  ArrayList<String>();
		
	}
	
	public Variante(ArrayList<String> variantes, int index) {
		index_=index;
		variantes_=variantes;
	}
	
	public ArrayList<String> getVariantes_() {
		return variantes_;
	}

	public void setVariantes_(ArrayList<String> variantes_) {
		this.variantes_ = variantes_;
	}

	public int getIndex_() {
		return index_;
	}

	public void setIndex_(int index_) {
		this.index_ = index_;
	}

	//permet de lire le fichier texte contenant un numéro d’automat et des variantes de mots de passe à tester.
	public void traiterLesEntrees(String nomDeFichier) throws NumberFormatException, IOException {
		 variantes_= new ArrayList<String>();
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
				
				variantes_.add(ligne);
				nbLigne++;
			}
          }
		System.out.println("La requete est envoyee. Veuillez choisir l'option (c).");
		bufferedReader.close();   
	};
}
