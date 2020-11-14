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
	
	<jstl:if test="${command == 'show'}" >
			
			 <acme:form-submit method="get"
			code="authenticated.item.form.button.viewMessages" 
			action="/authenticated/message/list?item=${item}"
			/>
			
			<acme:form-submit method="get"
			code="authenticated.item.form.button.newMessage" 
			action="/authenticated/message/create?item=${item}&forum=${forum}"/>
			
	</jstl:if>
	
	<acme:form-return code="authenticated.item.form.button.return"/>
</acme:form>
