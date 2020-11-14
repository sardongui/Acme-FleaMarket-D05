<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="buyer.request.list.label.creation" path="creation" />
	<acme:list-column code="buyer.request.list.label.quantity" path="quantity" />
</acme:list>