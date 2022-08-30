package acme.features.inventor.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Configuration;
import acme.entities.Quantity;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;
import acme.roles.Inventor;

@Controller
public class InventorQuantityController extends AbstractController<Inventor,Quantity>{
	
	@PostConstruct
	protected void initialise(){
	}
}
