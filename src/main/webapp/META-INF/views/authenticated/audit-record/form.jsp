<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-moment code="authenticated.audit-record.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="authenticated.audit-record.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.audit-record.form.label.body" path="body"/>
	<acme:form-textbox code="authenticated.audit-record.form.label.status" path="status" readonly="true"/>
	<acme:form-select code="authenticated.audit-record.form.label.finalMode" path="finalMode" >
		<acme:form-option code="authenticated.audit-record.form.label.finalMode.final" value="true" />
		<acme:form-option code="authenticated.audit-record.form.label.finalMode.draft" value="false" />
	</acme:form-select>
	  
	<acme:form-return code="authenticated.audit-record.form.button.return"/>
</acme:form> 
