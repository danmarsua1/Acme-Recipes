<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="authenticated.configuration.show.label.currency" />
		</th>
		<td><acme:print value="${currency}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="authenticated.configuration.show.label.acceptedCurrencies" />
		</th>
		<td><acme:print value="${acceptedCurrencies}" /></td>
	</tr>
</table>