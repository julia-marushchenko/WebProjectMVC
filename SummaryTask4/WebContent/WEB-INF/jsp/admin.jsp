<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/localization.jspf"%>
<html lang="${language}">


<c:set var="title" value="Admin" />
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
				
				<form action="controller" >
				<input type="hidden" name="command" value="administrator" /> 
				<input value=<fmt:message	key="admin.button.showUsers"/> type="submit"/>
				
				
				<table id="accounts_table" align="center">
				<caption><label><fmt:message	key="client.label.users"/></label></caption>
			<tr>
				<th><label><fmt:message	key="admin.label.login"/></label></th>
				<th><label><fmt:message	key="admin.label.password"/></label></th>
				<th><label><fmt:message	key="admin.label.firstName"/></label></th>
				<th><label><fmt:message	key="admin.label.lastName"/></label></th>				
				<th><label><fmt:message	key="admin.label.status"/></label></th>
				<th>Block User</th>
				<th>Unblock User</th>
				<th>Credit Card</th>
				<th>Block Card</th>
			</tr>

			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.login}" /></td>
					<td><c:out value="${user.password}" /></td>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.lastName}" /></td>
					<td><c:out value="${user.status}" /></td>
					
					  <td>
						<a href="controller?command=blockUser&login=${user.login}"> <fmt:message
								key="admin.label.block" />
					</a>

					</td>
					<td>
						<a href="controller?command=unblockUser&user=${user.login}"> <fmt:message
								key="admin.label.unblock" />
					</a>
					</td>  
					<td>
					<c:out value="${user.cardNumber}" />
					</td>
					 <td>
					 <a href="controller?command=block&card=${user.cardNumber}"> <fmt:message
								key="client.label.block" />
					 </td>
				</tr>
			</c:forEach>

		</table>
		</form>
		
		
				
				</td>
				</tr>
	</table>


</body>
</html>