<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form>
	<acme:input-textbox code="administrator.configuration.configuration.form.label.currency" path="currency"/>
	<acme:input-textbox code="administrator.configuration.configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.configuration.configuration.form.label.strongSpam" path="strongSpam"/>
	<acme:input-textbox code="administrator.configuration.configuration.form.label.strongSpamThreshold" path="strongSpamThreshold"/>
		<acme:input-textbox code="administrator.configuration.configuration.form.label.weakSpam" path="weakSpam"/>
	<acme:input-textbox code="administrator.configuration.configuration.form.label.weakSpamThreshold" path="weakSpamThreshold"/>
	
	<acme:button  test="${command == 'show'}" code="administrator.configuration.configuration.form.button.update" action="/administrator/configuration/update"/>
	<acme:submit test="${command == 'update'}" code="administrator.configuration.configuration.form.button.submit" action="/administrator/configuration/update"/>
</acme:form>