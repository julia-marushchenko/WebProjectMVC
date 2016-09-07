<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/localization.jspf"%>

<html>


<c:set var="title" value="Client" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>

<%--=========================================================================== 
This is the CONTENT, containing the main part of the page.
===========================================================================--%>
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
				<%--=========================================================================== 
Defines the web form.
===========================================================================--%>
				
			<form action="controller" >
				<input type="hidden" name="command" value="client" /> 
				<input value=<fmt:message	key="client.button.showAccounts"/> type="submit"/>
				
				
				<table id="accounts_table" align="center">
				<caption><label><fmt:message	key="client.label.accounts"/></label></caption>
			<tr>
				<th><label><fmt:message	key="client.label.cardNumber"/></label></th>
				<th><label><fmt:message	key="client.label.amount"/></label></th>
				<th><label><fmt:message	key="client.label.status"/></label></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>

			<c:forEach items="${accounts}" var="account">
				<tr>
					<td><c:out value="${account.cardNumber}" /></td>
					<td><c:out value="${account.ammount}" /></td>
					<td><c:out value="${account.status}" /></td>
					
					  <td>
						<a href="controller?command=block&card=${account.cardNumber}"> <fmt:message
								key="client.label.block" />
					</a>

					</td>
					<td>
						<a href="controller?command=unblock&card=${account.cardNumber}"> <fmt:message
								key="client.label.unblock" />
					</a>
					</td>  
					 <td>
						<a href="controller?command=payment&card=${account.cardNumber}"> <fmt:message
								key="client.label.payment" />
					</a>

					</td>
				</tr>
			</c:forEach>

		</table>
		</form>
		
		<form action="controller" >
				<input type="hidden" name="command" value="client" /> 
				
				
				<table align="center">
				<caption><label><fmt:message key="client.label.payments"/></label></caption>
				<tr>
				<th><label><fmt:message	key="client.label.cardNumber"/></label></th>
				<th><label><fmt:message	key="client.label.ammount"/></label></th>
				<th><label><fmt:message	key="client.label.date"/></label></th>
				<th><label><fmt:message	key="client.label.status"/></label></th>
				
			</tr>

			<c:forEach items="${payments}" var="payment">
				<tr>
					<td><c:out value="${payment.creditCard}" /></td>
					<td><c:out value="${payment.ammount}" /></td>
					<td><c:out value="${payment.date}" /></td>
					<td><c:out value="${payment.statusPayment}" /></td>
				</tr>
			</c:forEach>
		</form>
		</td>
		</tr>
			
	</table>

	

</body>
</html>