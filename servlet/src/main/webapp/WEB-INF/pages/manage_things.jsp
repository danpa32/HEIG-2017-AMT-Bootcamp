<%@ page import="ch.heigvd.amt.bootcamp.web.ManageThingsServlet" %>
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

    <header class="my-4 text-right">
        <p class="lead">
            <a href="./manage_things" class="btn btn-primary btn-lg">Add New</a>
            <a href="./configuration" class="btn btn-primary btn-lg">Sort by</a>
        </p>
    </header>

    <c:choose>
        <c:when test="${requestScope.things.size() > 0}">
            <div class="row text-center">
                <c:forEach var="thing" items="${requestScope.things}">
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card">
                            <img class="card-img-top" src="http://placehold.it/500x325" alt="">
                            <div class="card-body">
                                <h4 class="card-title">${thing.getTitle()}</h4>
                                <p class="card-text">${thing.getDescription()}</p>
                            </div>
                            <div class="card-footer">
                                <a href="#" class="btn btn-primary">Edit</a>
                                <a href="#" class="btn btn-primary">Delete</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="row text-center my-4">
                <div class="mx-auto">
                    <a class="btn btn-secondary">1</a>
                    <a class="btn btn-secondary">2</a>
                    <a class="btn btn-secondary">3</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row text-center">
                <div class="col-lg-8 mx-auto placeholder">
                    <h2>There is no "things" here!</h2>
                    <p>Please generate some in the <a href="#">Configuration</a> section or add new "things" manually.
                    </p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</div>
<!-- /.container -->

<%@include file="includes/footer.jsp"%>

