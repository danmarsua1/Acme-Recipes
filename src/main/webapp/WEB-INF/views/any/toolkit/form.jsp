<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="any.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description"/>
	<acme:input-textbox code="any.toolkit.form.label.assembly-notes" path="assemblyNotes"/>
	<acme:input-url code="any.toolkit.form.label.link" path="link"/>
	<acme:input-textbox code="any.toolkit.form.label.publish" path="publish"/>  
	<acme:input-url code="any.toolkit.form.label.retail-price" path="totalPrice"/>
	<acme:button code="any.toolkit.form.label.listItems" action="/any/item/list?masterId=${id}"/>
	
</acme:form>