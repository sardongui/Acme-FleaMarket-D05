<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="supplier.item.form.label.newItem" path="newItem" readonly="true"/>
	<acme:form-moment code="supplier.item.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="supplier.item.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="supplier.item.form.label.title" path="title"/>
	<acme:form-textbox code="supplier.item.form.label.description" path="description"/>
	<acme:form-url code="supplier.item.form.label.photo" path="photo"/>
	<acme:form-textbox code="supplier.item.form.label.itemCategory" path="itemCategory"/>
	<acme:form-money code="supplier.item.form.label.price" path="price"/>
	<acme:form-url code="supplier.item.form.label.link" path="link"/>

	<acme:form-return code="supplier.item.form.button.return"/>
</acme:form>
