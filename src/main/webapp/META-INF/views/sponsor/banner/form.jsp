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
	
	<jstl:if test="${command == 'show' and sponsorHasCreditCard}" >
	
		<jstl:if test="${command == 'show' and bannerHasCreditCard}" >
			<fieldset>
				<legend><acme:message code="sponsor.banner.creditCard.form.legend.creditCard"/></legend>
				<acme:form-textbox readonly="true" code="sponsor.banner.creditCard.form.label.number" path="creditCard.number" />
			</fieldset>
			<acme:form-submit method="get" code="sponsor.banner.form.button.showCreditCard" 
			action="/sponsor/credit-card/show?id=${sponsorCreditCard}&banner=${banner}"/>
		</jstl:if>
		
		<jstl:if test="${command == 'show' and not bannerHasCreditCard and not creditCardLinked}" >
		
			<acme:form-submit method="get"
			code="sponsor.banner.form.button.addCreditCard" 
			action="/sponsor/credit-card/create?banner=${banner}"
			/>
		
		</jstl:if>
	
	</jstl:if>
	
	<jstl:if test="${command == 'show' and not sponsorHasCreditCard}" >
			
			<acme:form-submit method="get"
			code="sponsor.banner.form.button.addCreditCard" 
			action="/sponsor/credit-card/create?banner=${banner}"
			/>
			
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
	<acme:form-submit test="${command == 'delete' }"
		code="sponsor.banner.form.button.delete" 
		action="/sponsor/banner/delete"/>
			
	<acme:form-return code="sponsor.banner.form.button.return"/>
</acme:form>
