<%@ page import="ch.heigvd.amt.bootcamp.web.ManageQuotesServlet" %>
<%@ page import="ch.heigvd.amt.bootcamp.model.Quote" %>
<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 12.10.17
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>

<!-- Page Content -->
<div class="container">

    <!-- Page Features -->

    <header class="my-4 btn-toolbar justify-content-end">
        <a href="./addQuote" class="btn btn-primary mr-2">Add New</a>
        <div class="btn-group mr-2" role="group">
            <a id="btnGroupDrop1" href="#" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Sort by
            </a>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <c:forEach var="category" items="${Quote.CATEGORIES}">
                    <a class="dropdown-item" href="#">${category}</a>
                </c:forEach>
            </div>
        </div>
        <a href="#" class="btn btn-outline-primary">Asc/Desc</a>
    </header>

    <c:choose>
        <c:when test="${requestScope.quotes.size() > 0}">
            <div class="row text-center">
                <c:forEach var="quote" items="${requestScope.quotes}">
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">${quote.title}</h4>
                                <p class="card-text">${quote.description}</p>
                            </div>
                            <div class="card-footer">
                                <div class="btn-group">
                                    <a href="#" class="btn btn-outline-primary">Edit</a>
                                    <a href="#" class="btn btn-outline-danger">Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </c:when>
        <c:otherwise>
            <div class="row text-center">
                <div class="col-lg-8 mx-auto placeholder">
                    <h2>There is no quotes here!</h2>
                    <p>Please generate some in the <a href="<c:url value="/configuration"/>">Configuration</a> section or add new "things" manually.
                    </p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</div>
<!-- /.container -->

<%@include file="includes/footer.jsp"%>

