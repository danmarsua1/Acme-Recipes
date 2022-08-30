<%--
- form.jsp
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

<acme:form>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="patron.patronage.form.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="patron.patronage.form.label.initDate" path="initDate"/>
	<acme:input-textbox code="patron.patronage.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-textbox code="patron.patronage.form.label.status" path="status"/>
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-textbox code="patron.patronage.form.label.link" path="link"/>
	<acme:button code="patron.patronage.form.label.showInventor" action="/any/user-account/show?id=${inventorId}"/> 
	<jstl:choose>
		<jstl:when test="${command != 'create'}">
			<acme:button code="patron.patronage.form.label.createPatronageReport" action="/patron/patronage-report/create?masterId=${id}"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>

