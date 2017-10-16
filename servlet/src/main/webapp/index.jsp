<%--
  File: index.jsp
  Authors:
    - Christopher MEIER
    - Daniel PALUMBO
  Date: 16.10.17
  Welcome page.
--%>
<%@include file="WEB-INF/pages/includes/header.jsp"%>

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

<%@include file="WEB-INF/pages/includes/footer.jsp"%>
