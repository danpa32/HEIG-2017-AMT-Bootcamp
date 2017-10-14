<%@ page import="ch.heigvd.amt.bootcamp.web.AddQuoteServlet" %>
<%@ page import="ch.heigvd.amt.bootcamp.model.Quote" %>
<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 12.10.17
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>

<div class="container">
    <legend class="mt-4">Add a new quote</legend>
    <%@include file="includes/quoteForm.jsp"%>
</div>

<%@include file="includes/footer.jsp"%>