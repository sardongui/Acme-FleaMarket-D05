<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="supplier.request.form.label.creationMoment" path="creation"/>
	<acme:form-textbox code="supplier.request.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="supplier.request.form.label.quantity" path="quantity"/>
	<acme:form-textbox code="supplier.request.form.label.notes" path="notes"/>
	<acme:form-textbox code="supplier.request.form.label.item.title" path="item.title"/>
	<acme:form-textbox code="supplier.request.form.label.item.ticker" path="item.ticker"/> 
	<acme:form-textbox code="supplier.request.form.label.item.itemCategory" path="item.itemCategory"/> 
	<acme:form-textbox code="supplier.request.form.label.item.description" path="item.description"/> 
	<acme:form-textbox code="supplier.request.form.label.item.price" path="item.price"/> 
	
	<acme:form-return code="supplier.request.form.button.return"/>
</acme:form>
