<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="buyer.request.form.label.creation" path="creation"/>
	<acme:form-textbox code="buyer.request.form.label.ticker" path="ticker"/>
	<acme:form-double code="buyer.request.form.label.quantity" path="quantity"/>
	<acme:form-textbox code="buyer.request.form.label.notes" path="notes"/>
	<acme:form-textbox code="buyer.request.form.label.item.title" path="item.title"/>
	
	<acme:form-return code="buyer.request.form.button.return"/>
</acme:form>
