package acme.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quantity extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Positive
	@Min(1)
	private Integer quantity;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Toolkit toolkit;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Item item;
	
//	@NotNull
//	@Valid
//	@ManyToOne(optional = false)
//	protected Recipe recipe;
}
