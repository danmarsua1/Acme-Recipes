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
	<jstl:if test="${!acme:anyOf(command, 'create, confirm')}">
		<acme:input-textbox readonly="true" code="inventor.patronage-report.form.label.sequenceNumber" path="sequenceNumber"/>
		<acme:input-textbox readonly="true" code="inventor.patronage-report.form.label.creationMoment" path="creationMoment"/>
		<acme:input-textbox readonly="true" code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
		<acme:input-textbox readonly="true" code="inventor.patronage-report.form.label.link" path="link"/>
	</jstl:if>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:hidden-data path="patronageId"/>
			<acme:input-textbox readonly="true" code="inventor.patronage-report.form.label.sequenceNumber" path="sequenceNumber"/>
			<acme:input-textbox code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
			<acme:input-textbox code="inventor.patronage-report.form.label.link" path="link"/>
			<acme:submit code="inventor.patronage-report.button.create" action="/inventor/patronage-report/create?patronageId=${patronageId}"/>
		</jstl:when>
		<jstl:when test="${command == 'confirm'}">
			<acme:hidden-data path="patronageId"/>
			<acme:hidden-data path="sequenceNumber"/>
			<acme:hidden-data path="memorandum"/>
			<acme:hidden-data path="link"/>
			<acme:message code="inventor.patronage-report.form.text"/>
			<br/><br/>
			<acme:submit code="inventor.patronage-report.button.confirm" action="/inventor/patronage-report/create?patronageId=${patronageId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>