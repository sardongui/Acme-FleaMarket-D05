<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="sponsor.creditCard.form.label.holderName" path="holderName" />
	<acme:list-column code="sponsor.creditCard.form.label.number" path="number" />
</acme:list>


