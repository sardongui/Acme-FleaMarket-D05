<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.new.list.label.moment" path="moment" />
	<acme:list-column code="authenticated.new.list.label.category" path="category" />
	<acme:list-column code="authenticated.new.list.label.title" path="title" />
	<acme:list-column code="authenticated.new.list.label.body" path="body" />
	<acme:list-column code="authenticated.new.list.label.relatedNews" path="relatedNews" />
</acme:list>
