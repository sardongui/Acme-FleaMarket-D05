<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.supplier.form.label.companyName" path="companyName"/>
	<acme:form-url code="authenticated.supplier.form.label.homePage" path="homePage"/>
	<acme:form-textbox code="authenticated.supplier.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.supplier.form.label.itemCategory" path="itemCategory"/>

	<acme:form-submit test="${command == 'create'}" code="authenticated.supplier.form.button.create" action="/authenticated/supplier/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.supplier.form.button.update" action="/authenticated/supplier/update"/>
	
	<acme:form-return code="authenticated.supplier.form.button.return"/>
</acme:form>
