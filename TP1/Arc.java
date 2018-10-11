import java.io.*;
import java.util.*;
import java.util.Stack;


public class Arc {
	private Ville ville1_;
	private Ville ville2_;
	private int duree_;
	
	Arc(){
		duree_=0;
	}
	
	Arc(Ville ville1, Ville ville2, int duree){
		ville1_= ville1;
		ville2_ = ville2;
		duree_ = duree;
	}

	public Ville getVille1_() {
		return ville1_;
	}

	public Ville getVille2_() {
		return ville2_;
	}

	public int getDuree_() {
		return duree_;
	}

	public void setVille1_(Ville ville1_) {
		this.ville1_ = ville1_;
	}

	public void setVille2_(Ville ville2_) {
		this.ville2_ = ville2_;
	}

	public void setDuree_(int duree_) {
		this.duree_ = duree_;
	}

	
}
