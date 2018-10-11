import java.util.*;

public class MotDePasse {
	private String mot_;
	private int index_;
	
	public MotDePasse() {}

	public MotDePasse(String s , int i) {
		mot_=s;
		index_=i;
	}
	
	public String getMot_() {
		return mot_;
	}
	public void setMot_(String mot_) {
		this.mot_ = mot_;
	}
	public int getIndex_() {
		return index_;
	}
	public void setIndex_(int index_) {
		this.index_ = index_;
	}
}
