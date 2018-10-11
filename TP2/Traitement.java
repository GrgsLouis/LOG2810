import java.io.IOException;
import java.util.ArrayList;


import java.util.*;
import java.io.*;
import java.util.Stack;

public class Traitement{
	private Automate automate_;
	private Variante variante_;
	private ArrayList<MotDePasse> arrayResults_;
	
	
	public Traitement() {}
	public Traitement(Automate automate, Variante v){
		automate_ = automate;
		variante_= v;
		arrayResults_= new ArrayList<MotDePasse> ();
	}
	public Automate getAutomate_() {
		return automate_;
	}

	public void setAutomate_(Automate automate) {
		this.automate_ = automate;
	}

	public Variante getVariante_() {
		return variante_;
	}
	
	

	public void setVariante_(Variante variante_) {
		this.variante_ = variante_;
	}
	
	// cette fonction trouve le bon mot de passe 
	public void trouverMotDePasse(Variante var) {
		
		int temp1= arrayResults_.size();
		//var.setVariantes_(variantes_);
		if(automate_.getIndex_()==var.getIndex_()) {
			for(int i=0; i<automate_.getEtats_().size();i++) {
				for(int j=0;j<var.getVariantes_().size();j++) {
					//System.out.println(automate_.getEtats_().get(i)+"   S"+var.getVariantes_().get(j));
					
					if(automate_.getEtats_().get(i).equals("S"+var.getVariantes_().get(j))) {
						MotDePasse temp = new MotDePasse(var.getVariantes_().get(j),var.getIndex_());
						
						arrayResults_.add(temp);
					}
				}
			}
		}else {
			
			System.out.println("Variante and Regle not matching, they don't have the same index");
			System.out.println();
		}
			
		if(arrayResults_.size()>temp1) {
			System.out.println("Les variantes les regle matches");
			System.out.println();
			System.out.println("Mot de passe trouve en dernier: "+arrayResults_.get(arrayResults_.size()-1).getMot_());
			System.out.println();
			
			
		}
	}
	
	// Elle permet d'afficher tous les mots de passe trouves 
	public void afficherLesMotsDePasse() {
		
		if(arrayResults_.size() != 0) {
			System.out.println("Affichage de tout les mots de passe deja trouvee");
			System.out.println();
			for(int i=0; i<arrayResults_.size();i++) {
				
				System.out.println("Mot de passe "+(i+1)+ ": "+arrayResults_.get(i).getMot_()+"  Regle Variante est : "+arrayResults_.get(i).getIndex_());
			
			}
		}
		else {
			System.out.println("Aucun mot de passe enregistre!");
		}
	}
	

	
}
