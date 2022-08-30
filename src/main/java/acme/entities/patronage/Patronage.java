package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Patronage extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected StatusPatronage status;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@NotBlank
	@Length(max = 255)
	protected String legalStuff;

	@NotNull
	@Valid
	protected Money budget; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date creationMoment;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date initDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishDate;
	
	@URL
	protected String link;
	
	protected boolean published;
	
	// Relationships ----------------------------------------------------------
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	protected Patron patron;
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	protected Inventor inventor;
}