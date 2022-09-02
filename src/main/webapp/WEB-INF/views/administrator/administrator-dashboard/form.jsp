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



<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-components" />
		</th>
		<td><acme:print value="${totalNumberOfComponents}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-retail-price-of-components" /></th>
		<jstl:forEach var="prices" items="${averageRetailPriceOfComponents}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-retail-price-of-components" /></th>
		<jstl:forEach var="prices" items="${deviationRetailPriceOfComponents}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-retail-price-of-components" /></th>
		<jstl:forEach var="prices" items="${minimumRetailPriceOfComponents}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-retail-price-of-components" /></th>
		<jstl:forEach var="prices" items="${maximumRetailPriceOfComponents}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-tools" />
		</th>
		<td><acme:print value="${totalNumberOfTools}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-retail-price-of-tools" /></th>
		<jstl:forEach var="prices" items="${averageRetailPriceOfTools}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-retail-price-of-tools" /></th>
		<jstl:forEach var="prices" items="${deviationRetailPriceOfTools}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-retail-price-of-tools" /></th>
		<jstl:forEach var="prices" items="${minimumRetailPriceOfTools}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-retail-price-of-tools" /></th>
		<jstl:forEach var="prices" items="${maximumRetailPriceOfTools}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-proposed-patronages" /></th>
		<td><acme:print value="${totalNumberOfProposedPatronages}" /></td>
	</tr>
		<tr>
	<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-accepted-patronages" /></th>
		<td><acme:print value="${totalNumberOfAcceptedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-denied-patronages" /></th>
		<td><acme:print value="${totalNumberOfDeniedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-budget-of-proposed-patronages" /></th>
		<jstl:forEach var="prices" items="${averageBudgetOfProposedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-proposed-patronages" /></th>
		<jstl:forEach var="prices" items="${deviationBudgetOfProposedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-proposed-patronages" /></th>
		<jstl:forEach var="prices" items="${minimumBudgetOfProposedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-proposed-patronages" /></th>
		<jstl:forEach var="prices" items="${maximumBudgetOfProposedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
		<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-budget-of-accepted-patronages" /></th>
		<jstl:forEach var="prices" items="${averageBudgetOfAcceptedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-accepted-patronages" /></th>
		<jstl:forEach var="prices" items="${deviationBudgetOfAcceptedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-accepted-patronages" /></th>
		<jstl:forEach var="prices" items="${minimumBudgetOfAcceptedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-accepted-patronages" /></th>
		<jstl:forEach var="prices" items="${maximumBudgetOfAcceptedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
			<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-budget-of-denied-patronages" /></th>
		<jstl:forEach var="prices" items="${averageBudgetOfDeniedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-denied-patronages" /></th>
		<jstl:forEach var="prices" items="${deviationBudgetOfDeniedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-denied-patronages" /></th>
		<jstl:forEach var="prices" items="${minimumBudgetOfDeniedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-denied-patronages" /></th>
		<jstl:forEach var="prices" items="${maximumBudgetOfDeniedPatronages}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
</table>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.total-number-of-items" />
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.total-number-of-patronages" />
</h2>
<div>
	<canvas id="canvas2"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"COMPONENTS","TOOLS"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${totalNumberOfComponents}"/>,
						<jstl:out value="${totalNumberOfTools}"/>
					]
				}
			]
		};
		var data1 = {
				labels : [
						"PROPOSED PATRONAGES","ACCEPTED PATRONAGES","DENIED PATRONAGES"
				],
				datasets : [
					{
						data : [
							<jstl:out value="${totalNumberOfProposedPatronages}"/>,
							<jstl:out value="${totalNumberOfAcceptedPatronages}"/>,
							<jstl:out value="${totalNumberOfDeniedPatronages}"/>
						]
					}
				]
			};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 10.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
		var options1 = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 10.0
							}
						}
					]
				},
				legend : {
					display : false
				}
			};
	
		var canvas,canvas2, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
		canvas2 = document.getElementById("canvas2");
		context = canvas2.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data1,
			options : options1
		});
	});
</script>



