<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox readonly="true" code="inventor.item.form.label.code" path="code"/>
	<jstl:if test="${command != 'create'}">
		<acme:input-textbox code="inventor.item.form.label.type" path="type" readonly="true"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:input-select code="inventor.item.form.label.type" path="type">
			<acme:input-option code="COMPONENT" value="COMPONENT"/>
			<acme:input-option code="TOOL" value="TOOL"/>
		</acme:input-select>
	</jstl:if>
	<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.form.label.description" path="description"/>
	<acme:input-money code="inventor.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="inventor.item.form.label.link" path="link"/>
	
	<jstl:choose>
	    <jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && publish == false}">
	        <acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
	        <acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
	        <acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
	    </jstl:when>
	    <jstl:when test="${command == 'create'}">
	        <acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>
	    </jstl:when>
	</jstl:choose>
</acme:form>