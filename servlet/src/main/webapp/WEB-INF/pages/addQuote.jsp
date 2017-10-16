<%--
  File: addQuote.jsp
  Authors:
    - Christopher MEIER
    - Daniel PALUMBO
  Date: 16.10.17
  Page to add a quote.
--%>
<%@ page import="ch.heigvd.amt.bootcamp.web.AddQuoteServlet" %>
<%@ page import="ch.heigvd.amt.bootcamp.model.Quote" %>
<%@include file="includes/header.jsp"%>

<div class="container">
    <legend class="mt-4">Add a new quote</legend>
    <%@include file="includes/quoteForm.jsp"%>
</div>

<%@include file="includes/footer.jsp"%>