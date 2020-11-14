<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form  readonly="true">
	<acme:form-textbox code="authenticated.new.form.label.category" path="category"/>
	<acme:form-url code="authenticated.new.form.label.picture" path="picture"/>
	<acme:form-textbox code="authenticated.new.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.new.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.new.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.new.form.label.body" path="body"/>
	<acme:form-textbox code="authenticated.new.form.label.relatedNews" path="relatedNews"/>
			
	<acme:form-return code="authenticated.new.form.button.return"/>
</acme:form>
