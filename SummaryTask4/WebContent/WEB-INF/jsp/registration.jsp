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

				<form id="registration_form" action="controller" method="post">
				
					<input type="hidden" name="command" value="registration" /> 
					
					<label><fmt:message	key="registration.label.login" /><br> 
					
					<input name="login" type="text" /><br> 
					
					<label><fmt:message key="registration.label.password" /> </label><br> 
					
					<input name="password" type="password" /><br> 
					
					<label><fmt:message key="registration.label.confirmPassword" /> </label><br> 
					
					<input name="confirmPassword" type="password" /><br> 
					
					<label><fmt:message key="registration.label.firstName" /></label><br> 
					
					<input name="firstName" type="text" /><br> 
					
					<label><fmt:message key="registration.label.lastName" /></label><br> 
					
					<input name="lastName" type="text" /><br> 
					
					<p></p>
					
					<input type="submit" value=<fmt:message key="registration.input.submit" />>
					
				</form>
			</td>
		</tr>
	</table>

</body>
</html>