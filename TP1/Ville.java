import java.io.*;
import java.util.*;
import java.util.Stack;

public class Ville {
	
	private String villeNom_;
	private int villeId_;
	private boolean VilleStation_;
	
	Ville() {
		villeNom_="";
		villeId_=0;
		VilleStation_=true;
	}
	
	Ville(String nom, int id, boolean station){
		villeNom_= nom;
		villeId_ = id;
		VilleStation_ = station;
	}
	
	Ville(Ville v){
		villeNom_= v.villeNom_;
		villeId_ = v.villeId_;
		VilleStation_ = v.VilleStation_;
	}
	
		
	
	
	public String getVilleNom() {
		return villeNom_;
	}

	public int getVilleId() {
		return villeId_;
	}

	public boolean isVilleStation() {
		return VilleStation_;
	}

	public void setVilleNom(String villeNom_) {
		this.villeNom_ = villeNom_;
	}

	public void setVilleId(int villeId_) {
		this.villeId_ = villeId_;
	}

	public void setVilleStation(boolean villeStation_) {
		VilleStation_ = villeStation_;
	}

}
