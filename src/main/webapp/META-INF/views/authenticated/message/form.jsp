<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.message.form.label.creation" path="creation" readonly="true" />
	</jstl:if>
	<acme:form-textbox code="authenticated.message.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	<acme:form-textbox code="authenticated.message.form.label.tags" path="tags" placeholder="tag1,tag2,..." />
	<jstl:if test="${command == 'create' }">
		<acme:form-checkbox code="authenticated.message.form.label.isConfirmed" path="confirmed" />
	</jstl:if>
	
	<acme:form-submit test="${command == 'create' }" method="post"
		code="authenticated.message.form.button.create" 
		action="/authenticated/message/create"/>
		
	<input id="item" name="item" value="${item}" type="hidden" />
    <input id="forum" name="forum" value="${forum}" type="hidden" />
	
	<acme:form-return code="authenticated.message.form.button.return"/>
</acme:form>


