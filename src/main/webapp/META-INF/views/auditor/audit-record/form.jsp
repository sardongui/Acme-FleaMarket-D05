<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="auditor.audit-record.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="auditor.audit-record.form.label.title" path="title"/>
	<acme:form-textbox code="auditor.audit-record.form.label.body" path="body"/>
	<acme:form-url code="auditor.audit-record.form.label.status" path="status"/>
	
	<acme:form-return code="auditor.audit-record.form.button.return"/>
	  
</acme:form>
