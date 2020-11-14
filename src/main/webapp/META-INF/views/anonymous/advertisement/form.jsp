<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form  readonly="true">
	<acme:form-moment code="anonymous.advertisement.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="anonymous.advertisement.form.label.title" path="title"/>
	<acme:form-url code="anonymous.advertisement.form.label.picture" path="picture"/>
	<acme:form-moment code="anonymous.advertisement.form.label.displayPeriod" path="displayPeriod"/>
	<acme:form-textbox code="anonymous.advertisement.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.advertisement.form.label.discounts" path="discounts"/>
	
	<acme:form-return code="anonymous.advertisement.form.button.return"/>
</acme:form>
