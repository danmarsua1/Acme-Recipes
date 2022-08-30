<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.configuration.show.label.currency" />
		</th>
		<td><acme:print value="${currency}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.configuration.show.label.acceptedCurrencies" />
		</th>
		<td><acme:print value="${acceptedCurrencies}" /></td>
	</tr>
		<tr>
		<th scope="row"><acme:message
				code="administrator.configuration.show.label.strongSpam" />
		</th>
		<td><acme:print value="${strongSpam}" /></td>
	</tr>
		<tr>
		<th scope="row"><acme:message
				code="administrator.configuration.show.label.strongSpamThreshold" />
		</th>
		<td><acme:print value="${strongSpamThreshold}" /></td>
	</tr>
			<tr>
		<th scope="row"><acme:message
				code="administrator.configuration.show.label.weakSpam" />
		</th>
		<td><acme:print value="${weakSpam}" /></td>
	</tr>
		<tr>
		<th scope="row"><acme:message
				code="administrator.configuration.show.label.weakSpamThreshold" />
		</th>
		<td><acme:print value="${weakSpamThreshold}" /></td>
	</tr>
</table>