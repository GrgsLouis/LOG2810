import java.util.*;


public class CheapVehicule {
	
	private String model_;
	private int consommation_;
	private double niveauEssence_;
	
	
	public CheapVehicule(String model) {
		// TODO Auto-generated constructor stub
		model_=model;
		niveauEssence_=100;
		if(model== "Fourgon") {
			
			consommation_=8;
			
			
		}else if(model== "Voiture") {
			
			
			consommation_=5;
			
			
		}else if(model== "Pick up") {
			
			
			consommation_=7;
			
			
		}
	}
	public String getModel_() {
		return model_;
	}
	public void setModel_(String model_) {
		this.model_ = model_;
	}
	public int getConsommation_() {
		return consommation_;
	}
	public void setConsommation_(int consommation_) {
		this.consommation_ = consommation_;
	}
	public double getNiveauEssence_() {
		return niveauEssence_;
	}
	public void setNiveauEssence_(double niveauEssence_) {
		this.niveauEssence_ = niveauEssence_;
	}
	
	
}
