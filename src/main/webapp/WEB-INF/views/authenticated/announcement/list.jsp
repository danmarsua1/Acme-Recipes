<%--
- list.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<acme:list>
<acme:list-column code="authenticated.announcement.list.label.title" path="title"
		width="50%" />
	<acme:list-column code="authenticated.announcement.list.label.creation-moment"
		path="creationMoment" width="20%" />
	<acme:list-column code="authenticated.announcement.list.label.body" path="body"
		width="20%" />
</acme:list>

<sec:authorize access="hasRole('Administrator')">
	<acme:button code="administrator.announcement.list.button.create"
			action="/administrator/announcement/create"/>
</sec:authorize>
