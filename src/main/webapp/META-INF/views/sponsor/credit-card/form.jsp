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
	<acme:form-textbox code="sponsor.creditCard.form.label.holderName" path="holderName"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.number" path="number" />
	<acme:form-textbox code="sponsor.creditCard.form.label.brand" path="brand"/>
	<acme:form-integer code="sponsor.creditCard.form.label.month" path="month" placeholder="mm" />
	<acme:form-integer code="sponsor.creditCard.form.label.year" path="year" placeholder="yyyy" />
	<acme:form-integer code="sponsor.creditCard.form.label.cvv" path="cvv" placeholder="XXXX"/>
	
	<acme:form-submit method="post" test="${command == 'create'}"
	 	code="sponsor.creditCard.form.button.create"
	 	action="/sponsor/credit-card/create" />

	<jstl:if test="${command !='create' }">
		<acme:form-submit test="${command == 'show' }"
			code="sponsor.creditCard.form.button.update" 
			action="/sponsor/credit-card/update"/>
			
		<acme:form-submit test="${command == 'update' }"
			code="sponsor.creditCard.form.button.update" 
			action="/sponsor/credit-card/update"/>
			
		<acme:form-submit test="${command == 'show' }"
			code="sponsor.creditCard.form.button.delete" 
			action="/sponsor/credit-card/delete"/>
			
		<acme:form-submit test="${command == 'delete' }"
			code="sponsor.creditCard.form.button.delete" 
			action="/sponsor/credit-card/delete"/>
	</jstl:if>	
	
	<input id="banner" name="banner" value="${banner}" type="hidden" />
    <input id="creditCard" name="creditCard" value="${creditCard}" type="hidden" />
			
	<acme:form-return code="sponsor.creditCard.form.button.return"/>
</acme:form>
