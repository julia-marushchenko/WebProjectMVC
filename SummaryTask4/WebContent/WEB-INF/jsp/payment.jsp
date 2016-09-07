<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/localization.jspf"%>

<html>

<c:set var="title" value="Registration" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>


	<table id="main-container">
		<%--=========================================================================== 
This is the HEADER, containing a top menu.
header.jspf contains all necessary functionality for it.
Just included it in this JSP document.
===========================================================================--%>

		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>
		<tr>
			<td class="content center">

				<form id="payment" action="controller" method="post">
				
					<input type="hidden" name="command" value="payment" /> 
					
					<label><fmt:message	key="payment.label.Amount" /><br> 
					
					<input name="amount" type="text" /><br> 
					
					<label><fmt:message key="payment.label.account" /> </label><br> 
					
					<input name="account" type="text" /><br> 
					
					<p></p>
					
					<input type="submit" value=<fmt:message key="registration.input.submit" />>
					
				</form>
			</td>
		</tr>
	</table>

</body>
</html>