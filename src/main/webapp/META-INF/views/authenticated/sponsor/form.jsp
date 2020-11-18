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

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.sponsor.form.label.firmName" path="firmName"/>
	
	<jstl:if test="${command == 'create'}">
		<br/>
		<fieldset>
			<legend><acme:message code="authenticated.sponsor.creditCard.form.legend.creditCard"/></legend>
		</fieldset>
	</jstl:if>
	
	<jstl:if test="${command == 'update'}">
		<br/>
		<fieldset>
			<legend><acme:message code="authenticated.sponsor.creditCard.form.legend.creditCard.used"/></legend>
		</fieldset>
	</jstl:if>
	
	<acme:form-textbox code="authenticated.sponsor.creditCard.form.label.holderName" path="creditCard.holderName"/>
	<acme:form-textbox code="authenticated.sponsor.creditCard.form.label.number" path="creditCard.number" />
	<acme:form-textbox code="authenticated.sponsor.creditCard.form.label.brand" path="creditCard.brand"/>
	<acme:form-integer code="authenticated.sponsor.creditCard.form.label.month" path="creditCard.month" placeholder="mm" />
	<acme:form-integer code="authenticated.sponsor.creditCard.form.label.year" path="creditCard.year" placeholder="yyyy" />
	<acme:form-integer code="authenticated.sponsor.creditCard.form.label.cvv" path="creditCard.cvv" placeholder="XXXX"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-submit code="authenticated.sponsor.form.button.create" action="/authenticated/sponsor/create"/>
	</jstl:if>
	
	<jstl:if test="${command == 'update'}">
		<acme:form-submit code="authenticated.sponsor.form.button.update" action="/authenticated/sponsor/update"/>
		
		<jstl:if test="${hasCreditCard}">
			<acme:form-submit code="authenticated.sponsor.form.button.deleteCC" action="/authenticated/sponsor/delete"/>
		</jstl:if>
		
	</jstl:if>
	
	<acme:form-return code="authenticated.sponsor.form.button.return"/>
</acme:form>
