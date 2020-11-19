<%--
	- form.jsp
	-
	- Copyright (c) 2019 Rafael Corchuelo.
	-
	- In keeping with the traditional purpose of furthering education and research, it is
	- the policy of the copyright owner to permit non-commercial use and redistribution of
	- this software. It has been tested carefully, but it is not guaranteed for any particular
	- purposes.  The copyright owner does not offer any warranties or representations, nor do
	- they accept any liabilities with respect to them.
	--%>
	

	<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	

	<div class="w-100 text-center">
		<b><acme:message code="administrator.chart.form.label.numberOfNewsGroupedByCategory"/></b>
		<canvas id="numberOfNewsGroupedByCategory"></canvas>
	</div>
	<br></br>
	<div class="w-100 text-center">
		<b><acme:message code="administrator.chart.form.label.ratioOfWarningNewsVersusRestOfit"/></b>
	    <canvas id="ratioOfWarningNewsVersusRestOfit"></canvas>
	</div>
	<br></br>
	<div class="w-100 text-center">
		<b><acme:message code="administrator.chart.form.label.numberOfAdvertisementsGroupedByDiscount"/></b>
		<canvas id="numberOfAdvertisementsGroupedByDiscount"></canvas>
	</div>
	<br></br>
		<div class="w-100 text-center">
		<b><acme:message code="administrator.chart.form.label.ratioOfItemsGroupedByItemCategory"/></b>
		<canvas id="ratioOfItemsGroupedByItemCategory"></canvas>
	</div>
	<br></br>
	<br></br>
		<div class="w-100 text-center">
		<b><acme:message code="administrator.chart.form.label.ratioOfSponsorsGroupedByCreditCard"/></b>
		<canvas id="ratioOfSponsorsGroupedByCreditCard"></canvas>
	</div>
	<br></br>
	<div>
	<acme:message code="administrator.chart.form.label.RequestsByStatus"/>
    	<canvas id="ratioOfRequestsGroupedByStatus"></canvas>
	</div>
	
	<script type ="text/javascript">
	$(document).ready(function(){
		 var CanvasCompany = document.getElementById("numberOfNewsGroupedByCategory");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataCompany = {
				 labels : [
					 <jstl:forEach items = "${numberOfNewsGroupedByCategory}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${numberOfNewsGroupedByCategory}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "yellow", "red", "green", "purple"]
					 }
				 ]
		 };
		 var pieChartCompany = new Chart(CanvasCompany, {
			 type: 'pie',
			 data: DataCompany
		 });
	 });
	
	$(document).ready(function(){
		 var CanvasCompany = document.getElementById("ratioOfWarningNewsVersusRestOfit");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataCompany = {
				 labels : ["Warning", "Not warning"],
				 datasets:[
					 {
						 data: ["<jstl:out value = '${ratioOfWarningNewsVersusRestOfit[0][0]}' />","<jstl:out value = '${ratioOfWarningNewsVersusRestOfit[0][1]}' />"],
						 backgroundColor :["yellow", "green", "red", "blue", "purple"]
					 }
				 ]
		 };
		 var pieChartCompany = new Chart(CanvasCompany, {
			 type: 'pie',
			 data: DataCompany
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("numberOfAdvertisementsGroupedByDiscount");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${numberOfAdvertisementsGroupedByDiscount}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${numberOfAdvertisementsGroupedByDiscount}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "yellow", "green", "purple"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("ratioOfItemsGroupedByItemCategory");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${ratioOfItemsGroupedByItemCategory}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${ratioOfItemsGroupedByItemCategory}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "yellow", "green", "purple"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasCompany = document.getElementById("ratioOfSponsorsGroupedByCreditCard");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
			 
			 var DataCompany = {
					 labels : ["Have not Credit Card", "Credit Card has not expired", "Credit Card has expired"],
					 datasets:[
						 {
							 data: ["<jstl:out value = '${ratioOfSponsorsGroupedByCreditCard[0][0]}' />",
								 "<jstl:out value = '${ratioOfSponsorsGroupedByCreditCard[0][1]}' />", 
								 "<jstl:out value = '${ratioOfSponsorsGroupedByCreditCard[0][2]}' />"],
							 backgroundColor :["yellow", "green", "red", "blue", "purple"]
						 }
					 ]
			 };
		 var pieChartCompany = new Chart(CanvasCompany, {
			 type: 'pie',
			 data: DataCompany
		 });
	 });
	 
	 $(document).ready(function(){
		 var CanvasInvestor = document.getElementById("ratioOfRequestsGroupedByStatus");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataInvestor = {
				 labels : [
					 <jstl:forEach items = "${ratioOfRequestsGroupedByStatus}" var="item">
					 "<jstl:out value= "${item[0]}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items= "${ratioOfRequestsGroupedByStatus}" var="item">
							 "<jstl:out value = "${item[1]}" />" ,
							 </jstl:forEach>
						 ],
						 backgroundColor :["blue", "red", "green"]
					 }
				 ]
		 };
		 var pieChartInvestor = new Chart(CanvasInvestor, {
			 type: 'pie',
			 data: DataInvestor
		 });
	 });
	 
	 
	 $(document).ready(function(){
		 var CanvasApplicationsByDays = document.getElementById("TimeSeriesRequests");
		 Chart.defaults.global.defaultFontFamily = "Modeka";
		 Chart.defaults.global.defaultFontSize = 15;
		 
		 var DataApplicationsByDays = {
				 labels : [
					 <jstl:forEach items = "${allDatesBeforeThreeWeeks}" var="item">
					 "<jstl:out value= "${item}" />" ,
					 </jstl:forEach>
				 ],
				 datasets:[
					 {
						 data: [
							 <jstl:forEach items="${allDatesBeforeThreeWeeks}" var="item">
							 <jstl:set var="value" value="0"/>
							 <jstl:forEach items="${numberOfRejectedRequestsLastThreeWeeks}" var="item2">
							   <jstl:if test="${item == item2[0]}">
							      <jstl:set var="value" value="${item2[1]}"/>
							   </jstl:if>
							 </jstl:forEach>
							 <jstl:out value="${value}"/>,
							 </jstl:forEach>
						 ],
						 borderColor:["red"],
		                 label:"<acme:message code='administrator.chart.form.label.RejectedRequests'/>"
					 },
					 {
					     data: [
					    	 <jstl:forEach items="${allDatesBeforeThreeWeeks}" var="item">
							 <jstl:set var="value" value="0"/>
							 <jstl:forEach items="${numberOfPendingRequestsLastThreeWeeks}" var="item2">
							   <jstl:if test="${item == item2[0]}">
							      <jstl:set var="value" value="${item2[1]}"/>
							   </jstl:if>
							 </jstl:forEach>
							 <jstl:out value="${value}"/>,
							 </jstl:forEach>
					     ],
						 borderColor:["blue"],
	                     label:"<acme:message code='administrator.chart.form.label.PendingRequests'/>"
					 },
					 {
						 data: [
							 <jstl:forEach items="${allDatesBeforeThreeWeeks}" var="item">
							 <jstl:set var="value" value="0"/>
							 <jstl:forEach items="${numberOfAcceptedRequestsLastThreeWeeks}" var="item2">
							   <jstl:if test="${item == item2[0]}">
							      <jstl:set var="value" value="${item2[1]}"/>
							   </jstl:if>
							 </jstl:forEach>
							 <jstl:out value="${value}"/>,
							 </jstl:forEach>
						 ],
						 borderColor:["green"],
						 label:"<acme:message code='administrator.chart.form.label.AcceptedRequests'/>",
					 }
				 ]
		 
		 };
					
		 var pieChartApplicationsByDays = new Chart(CanvasApplicationsByDays, {
			 type: 'line',
			 data: DataApplicationsByDays,
		 });
	 }); 
	 
	</script>

