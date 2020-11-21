<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="supplier.section.form.label.indexer" path="indexer"/>
	<acme:form-textbox code="supplier.section.form.label.title" path="title"/>
	<acme:form-textbox code="supplier.section.form.label.description" path="description"/>
	<acme:form-url code="supplier.section.form.label.photo" path="photo"/>
	
	<acme:form-return code="supplier.section.form.button.return"/>
</acme:form>
