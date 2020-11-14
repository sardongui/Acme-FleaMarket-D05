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
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="administrator.advertisement.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="administrator.advertisement.form.label.title" path="title"/>
	<acme:form-url code="administrator.advertisement.form.label.picture" path="picture"/>
	<acme:form-moment code="administrator.advertisement.form.label.displayPeriod" path="displayPeriod"/>
	<acme:form-textbox code="administrator.advertisement.form.label.text" path="text"/>
	<acme:form-textbox code="administrator.advertisement.form.label.discounts" path="discounts" placeholder="SMALL, AVERAGE, LARGE" />
	<jstl:if test="${displayPeriodFuture==true}">
		<acme:form-submit test="${command == 'show' }"
		code="administrator.advertisement.form.button.update" 
		action="/administrator/advertisement/update"/>
		<acme:form-submit test="${command == 'show' }"
		code="administrator.advertisement.form.button.delete" 
		action="/administrator/advertisement/delete"/>
	</jstl:if>
		<acme:form-submit test="${command == 'update' }"
		code="administrator.advertisement.form.button.update" 
		action="/administrator/advertisement/update"/>
		<acme:form-submit test="${command == 'delete' }"
		code="administrator.advertisement.form.button.delete" 
		action="/administrator/advertisement/delete"/>
	
	<acme:form-submit test="${command == 'create' }"
		code="administrator.advertisement.form.button.create" 
		action="/administrator/advertisement/create"/>
		
	<acme:form-return code="administrator.advertisement.form.button.return"/>
</acme:form>
