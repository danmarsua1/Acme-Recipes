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
		<td><acme:print value="${averageRetailPriceOfComponents}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-retail-price-of-components" /></th>
		<td><acme:print value="${deviationRetailPriceOfComponents}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-retail-price-of-components" /></th>
		<td><acme:print value="${minimumRetailPriceOfComponents}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-retail-price-of-components" /></th>
		<td><acme:print value="${maximumRetailPriceOfComponents}" /></td>
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
		<td><acme:print value="${averageRetailPriceOfTools}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-retail-price-of-tools" /></th>
		<td><acme:print value="${deviationRetailPriceOfTools}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-retail-price-of-tools" /></th>
		<td><acme:print value="${minimumRetailPriceOfTools}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-retail-price-of-tools" /></th>
		<td><acme:print value="${maximumRetailPriceOfTools}" /></td>
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
		<td><acme:print value="${averageBudgetOfProposedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-proposed-patronages" /></th>
		<td><acme:print value="${deviationBudgetOfProposedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-proposed-patronages" /></th>
		<td><acme:print value="${minimumBudgetOfProposedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-proposed-patronages" /></th>
		<td><acme:print value="${maximumBudgetOfProposedPatronages}" /></td>
	</tr>
		<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-budget-of-accepted-patronages" /></th>
		<td><acme:print value="${averageBudgetOfAcceptedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-accepted-patronages" /></th>
		<td><acme:print value="${deviationBudgetOfAcceptedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-accepted-patronages" /></th>
		<td><acme:print value="${minimumBudgetOfAcceptedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-accepted-patronages" /></th>
		<td><acme:print value="${maximumBudgetOfAcceptedPatronages}" /></td>
	</tr>
			<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-budget-of-denied-patronages" /></th>
		<td><acme:print value="${averageBudgetOfDeniedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-denied-patronages" /></th>
		<td><acme:print value="${deviationBudgetOfDeniedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-denied-patronages" /></th>
		<td><acme:print value="${minimumBudgetOfDeniedPatronages}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-denied-patronages" /></th>
		<td><acme:print value="${maximumBudgetOfDeniedPatronages}" /></td>
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



