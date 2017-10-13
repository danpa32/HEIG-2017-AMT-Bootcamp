<%@ page import="ch.heigvd.amt.bootcamp.web.HomeServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 12.10.17
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>

<div class="container">

    <!-- Jumbotron Header -->
    <header class="jumbotron my-4 text-center">
        <h1 class="display-3">Welcome</h1>
        <p class="lead">
            <a href="<c:url value="/manage_quotes"/>" class="btn btn-primary btn-lg">Manage Quotes</a>
            <a href="<c:url value="/configuration"/>" class="btn btn-primary btn-lg">Configuration</a>
        </p>
    </header>

</div>

<%@include file="includes/footer.jsp"%>
