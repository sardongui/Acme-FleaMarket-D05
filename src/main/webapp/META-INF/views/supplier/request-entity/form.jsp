<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="supplier.request.form.label.creationMoment" path="creation" readonly="true"/>
	<acme:form-textbox code="supplier.request.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-textbox code="supplier.request.form.label.quantity" path="quantity"/>
	<acme:form-textbox code="supplier.request.form.label.notes" path="notes"/> 
	<acme:form-textbox code="supplier.request.form.label.status" path="status"/>
	
	<jstl:if test="${command == 'update' || status != 'ACCEPTED'}">`
		<acme:form-textarea code="supplier.request.form.label.rejectionJustification" path="rejectionJustification" />
	</jstl:if>
	<acme:menu-separator />
	<acme:form-textbox code="supplier.item.form.label.referenceItem" path="referenceItem" readonly="true"/>
	<acme:form-textbox code="supplier.item.form.label.itemSupplier" path="itemSupplier" readonly="true"/>
	<acme:menu-separator />
	<acme:form-submit test="${(command == 'show' && status == 'PENDING') || command == 'update'}"
		code="supplier.request.form.button.update" action="update" />
	
	<acme:form-return code="supplier.request.form.button.return"/>
</acme:form>
