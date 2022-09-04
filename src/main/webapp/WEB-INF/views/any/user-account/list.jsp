<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.user-account.list.label.name" path="identity.name" width="40%"/>
	<acme:list-column code="any.user-account.list.label.surname" path="identity.surname" width="40%"/>	
	<acme:list-column code="any.user-account.list.label.role-list" path="roleList" width="40%"/>	
</acme:list>