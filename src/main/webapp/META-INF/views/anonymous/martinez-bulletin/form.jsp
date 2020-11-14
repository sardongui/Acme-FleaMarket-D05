<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.martinez-bulletin.form.label.web" path="web"/>
	<acme:form-textarea code="anonymous.martinez-bulletin.form.label.description" path="description"/>
	
	<acme:form-submit code="anonymous.martinez-bulletin.form.button.create" action="/anonymous/martinez-bulletin/create"/>
	<acme:form-return code="anonymous.martinez-bulletin.form.button.return"/>
</acme:form>
