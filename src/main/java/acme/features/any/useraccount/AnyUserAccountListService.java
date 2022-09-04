/*
 * AdministratorUserAccountListService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.useraccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyUserAccountRepository repository;

	// AbstractListService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;

		Collection<UserAccount> result;

		result = this.repository.findAllUserAccounts();
		System.out.println(result);
		final List<UserAccount> ls = new ArrayList<UserAccount>(result);
		final Set<UserAccount> s = new LinkedHashSet<UserAccount>(ls);
		for (final UserAccount userAccount : s) {
			userAccount.getRoles().forEach(r -> { ;
				
			});
		}

		return s;
	}
	
	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final StringBuilder buffer;
		Collection<UserRole> roles;

		request.unbind(entity, model, "identity.name", "identity.surname");

		roles = entity.getRoles();
		final String roles2 = entity.getAuthorityString();
//		buffer = new StringBuilder();
//		for (final UserRole role : roles) {
//			buffer.append(role.getAuthorityName());
//			buffer.append(" ");
//		}
//
//		model.setAttribute("roleList", buffer.toString());
		model.setAttribute("roles2", roles2);

	}

}
