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

<acme:form readonly="true">
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.initDate" path="initDate" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.finishDate" path="finishDate" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.budget" path="budget" readonly="true"/>
	<jstl:if test="${status!='PROPOSED'}">
	<acme:input-textbox code="inventor.patronage.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status=='PROPOSED'}">
		<acme:input-select code="inventor.patronage.form.label.status" path="status">
			<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:input-option code="DENIED" value="DENIED"/>
		</acme:input-select>
	</jstl:if>
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff" path="legalStuff" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.link" path="link" readonly="true"/>
	<acme:button code="inventor.patronage.form.label.showPatron" action="/any/user-account/show?id=${patronId}"/> 
	<jstl:if test="${command == 'show' && status == 'PROPOSED'}">
		<acme:submit code="inventor.patronage.form.button.update" action="/inventor/patronage/update"/>
	</jstl:if>
	<jstl:choose>
		<jstl:when test="${command != 'create' and !hasPatronageReport}">
			<acme:button code="inventor.patronage.form.label.createPatronageReport" action="/inventor/patronage-report/confirm?patronageId=${id}"/>
		</jstl:when>
		<jstl:when test="${command != 'create' and hasPatronageReport}">
			<acme:button code="inventor.patronage.form.label.showPatronageReport" action="/inventor/patronage-report/show?id=${patronageReportId}"/>
		</jstl:when>
	</jstl:choose>

</acme:form>
