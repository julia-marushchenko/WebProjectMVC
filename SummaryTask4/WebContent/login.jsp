<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/localization.jspf"%>

<html>


<c:set var="title" value="Login" />
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
		
		<%--=========================================================================== 
This is the CONTENT, containing the main part of the page.
===========================================================================--%>
		<tr>
			<td class="content center">
				<%--=========================================================================== 
Defines the web form.
===========================================================================--%>
				<form id="login_form" action="controller" method="post">
					<%--=========================================================================== 
Hidden field. In the query it will act as command=login.
The purpose of this to define the command name, which have to be executed 
after you submit current form. 
===========================================================================--%>
					<input type="hidden" name="command" value="login" /> 
					
					<label><fmt:message	key="login.label.login" /></label><br> 
					
					<input name="login"	type="text" /><br> 
					
					<label><fmt:message key="registration.label.password" /></label><br> 
					
					<input name="password" type="password" /><br>
					
					<p />
					
					<input type="submit" value=<fmt:message key="login.label.submit" />><br>
					
				</form>
				<%--=========================================================================== 
Type link to registration 
===========================================================================--%>
					<a href="controller?command=registration"> <fmt:message
							key="login.link.registration" />
					</a>
			</td>
		</tr>
	</table>


</body>
</html>