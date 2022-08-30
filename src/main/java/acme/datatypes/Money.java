package acme.datatypes;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Money {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;
	
	// Atributes --------------------------------------------------------------
	
	@NotBlank
	protected String							technology;
	
	@NotBlank
	protected String							currency;
	
	@Positive
	protected Double							quantity;
	
	protected Map<Pair<String,String>,Double>	price;
	
	public Map<Pair<String,String>,Double> getPrice() {
	    final Pair<String, String> p = new Pair<>(this.technology , this.currency);  
	    final Map<Pair<String,String>,Double> res = new HashMap<Pair<String,String>,Double>();
	    res.put(p, this.quantity);
		return this.price;
	}

	@Override
	public String toString() {
		return "Money [technology=" + this.technology + ", quantity=" + this.quantity + " " + this.currency + "]";
	}
	
	

}
