<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="authenticated.item.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="authenticated.item.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.item.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.item.form.label.description" path="description"/>
	<acme:form-url code="authenticated.item.form.label.photo" path="photo"/>
	<acme:form-textbox code="authenticated.item.form.label.itemCategory" path="itemCategory"/>
	<acme:form-money code="authenticated.item.form.label.price" path="price"/>
	<acme:form-url code="authenticated.item.form.label.link" path="link"/>
	<acme:form-textbox code="authenticated.item.form.label.newItem" path="newItem"/>
	
	<jstl:if test="${command == 'show'}" >
			
			 <acme:form-submit method="get"
			code="authenticated.item.form.button.viewMessages" 
			action="/authenticated/message/list?item=${item}"
			/>
			
			<acme:form-submit method="get"
			code="authenticated.item.form.button.newMessage" 
			action="/authenticated/message/create?item=${item}&forum=${forum}"/>
			
			<jstl:if test="${isFinalMode}" >
				<acme:check-access test="hasRole('Buyer')">
					<acme:form-submit method="get"
					code="authenticated.item.form.button.orderItem" 
					action="/buyer/request-entity/create?item=${item}"/>
				</acme:check-access>
			</jstl:if>
			<acme:form-submit method="get" code="authenticated.audit-record.form.button.list"
	 	action="/authenticated/audit-record/list?item=${item}"/>
			
	</jstl:if>
	
	<input id="item" name="item" value="${item}" type="hidden" />
	
	<acme:form-return code="authenticated.item.form.button.return"/>
</acme:form>
