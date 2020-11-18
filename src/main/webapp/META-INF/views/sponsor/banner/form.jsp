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
	<acme:form-url code="sponsor.banner.form.label.picture" path="picture"/>
	<acme:form-textbox code="sponsor.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.banner.form.label.target" path="target"/>
	<br/>
	
	<jstl:if test="${command != 'create' and bannerHasCreditCard}" >
	
		<fieldset>
			<legend><acme:message code="sponsor.banner.creditCard.form.legend.creditCard.used"/></legend>
			<acme:form-textbox readonly="true" code="sponsor.banner.creditCard.form.label.number" path="creditCard.number" />
		</fieldset>
		<acme:form-submit method="get" code="sponsor.banner.form.button.showCreditCard" 
		action="/sponsor/credit-card/show?id=${sponsorCreditCard}&banner=${banner}"/>
			
	</jstl:if>
	
	<jstl:if test="${command != 'create' and not bannerHasCreditCard}" >
	
		<jstl:if test="${command != 'create' and sponsorHasCreditCard and not creditCardLinked and isExpired == false}" >
		
			<acme:form-submit code="sponsor.banner.form.button.linkCreditCard" action="/sponsor/banner/update?linkTo=true"	/>
		
		</jstl:if>
	
	</jstl:if>
	
	<jstl:if test="${command == 'create'}" >
	
		<fieldset>
			<legend><acme:message code="sponsor.banner.creditCard.form.legend.creditCard.used"/></legend>
		</fieldset>
		
		<jstl:if test="${creditCardLinked}">
			<span style="color:red"><acme:message code="sponsor.banner.error.creditCardLinked"/></span>
			<br/><br/>
		</jstl:if>
		
		<jstl:if test="${!creditCardLinked}">
			
			<jstl:if test="${hasCreditCard}">
			
				<jstl:if test="${!isExpired}">
					<span style="color:green"><acme:message code="sponsor.banner.error.foundCreditCard"/></span>
					<br/><br/>
					<acme:form-textbox readonly="true" code="sponsor.banner.creditCard.form.label.holderName" path="creditCard.holderName"/>
					<acme:form-textbox readonly="true" code="sponsor.banner.creditCard.form.label.number" path="creditCard.number" />
					<acme:form-textbox readonly="true" code="sponsor.banner.creditCard.form.label.brand" path="creditCard.brand"/>
					<acme:form-integer readonly="true" code="sponsor.banner.creditCard.form.label.month" path="creditCard.month" />
					<acme:form-integer readonly="true" code="sponsor.banner.creditCard.form.label.year" path="creditCard.year" />
					<acme:form-integer readonly="true" code="sponsor.banner.creditCard.form.label.cvv" path="creditCard.cvv" />
				</jstl:if>
				
				<jstl:if test="${isExpired}">
					<span style="color:red"><acme:message code="sponsor.banner.error.expiredCreditCard"/></span>
					<br/><br/>
				</jstl:if>
				
			</jstl:if>
			
			<jstl:if test="${!hasCreditCard}">
				<span><acme:message code="sponsor.banner.error.noFoundCreditCard"/></span>
				<br/><br/>
			</jstl:if>
			
		</jstl:if>
			
	</jstl:if>
	
	<input id="banner" name="banner" value="${banner}" type="hidden" />

	<acme:form-submit test="${command == 'show' }"
		code="sponsor.banner.form.button.update" 
		action="/sponsor/banner/update"/>
	<acme:form-submit test="${command == 'show' }"
		code="sponsor.banner.form.button.delete" 
		action="/sponsor/banner/delete"/>
		
	<acme:form-submit test="${command == 'create' }" method="post"
		code="sponsor.banner.form.button.create" 
		action="/sponsor/banner/create"/>
	<acme:form-submit test="${command == 'update' }" method="post"
		code="sponsor.banner.form.button.update" 
		action="/sponsor/banner/update"/>
	<acme:form-submit test="${command == 'update' }"
		code="sponsor.banner.form.button.delete" 
		action="/sponsor/banner/delete"/>
	<acme:form-submit test="${command == 'delete' }"
		code="sponsor.banner.form.button.delete" 
		action="/sponsor/banner/delete"/>
			
	<acme:form-return code="sponsor.banner.form.button.return"/>
</acme:form>
