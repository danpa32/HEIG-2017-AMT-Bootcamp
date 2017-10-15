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
                <c:forEach var="field" items="<%=Quote.FIELDS.values()%>">
                    <a class="dropdown-item <c:if test="${field eq requestScope.sortBy}">active</c:if>" href="<c:url value="?${requestScope.sortQuery}"><c:param name="sort" value="${field}" /></c:url>">${field}</a>
                </c:forEach>
            </div>
        </div>
        <c:if test="${requestScope.quotes.size() > 0}">
            <c:choose>
                <c:when test="${requestScope.asc == false}" >
                    <div class="btn-group" role="group">
                        <a href="<c:url value="?${requestScope.ascQuery}"><c:param name="asc" value="1" /></c:url>" class="btn btn-outline-primary">Asc</a>
                        <a href="<c:url value="?${requestScope.ascQuery}"><c:param name="asc" value="0" /></c:url>" class="btn btn-primary">Desc</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="btn-group" role="group">
                        <a href="<c:url value="?${requestScope.ascQuery}"><c:param name="asc" value="1" /></c:url>" class="btn btn-primary">Asc</a>
                        <a href="<c:url value="?${requestScope.ascQuery}"><c:param name="asc" value="0" /></c:url>" class="btn btn-outline-primary">Desc</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
    </header>

    <c:choose>
        <c:when test="${requestScope.quotes.size() > 0}">
            <div class="row">
                <c:forEach var="quote" items="${requestScope.quotes}">
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <blockquote class="blockquote mb-0">
                                    <p>${quote.text}</p>
                                    <footer class="blockquote-footer">${quote.author}<c:if test="${not empty quote.source}" > in <cite title="Source Title">${quote.source}</cite></c:if><c:if test="${not empty quote.date}" >, ${quote.date}</c:if></footer>
                                </blockquote>
                                <p class="card-text"><small class="text-muted">Category: ${quote.category}</small></p>
                            </div>
                            <div class="card-footer">
                                <div class="btn-group">
                                    <a href="<c:url value="/editQuote"><c:param name="id" value="${quote.id}" /></c:url>" class="btn btn-outline-primary">Edit</a>
                                    <a href="<c:url value="?${requestScope.cleanQuery}"><c:param name="del" value="${quote.id}" /></c:url>" class="btn btn-outline-danger"
                                            <c:if test="${sessionScope.confirmDelete != false}"> data-toggle="modal" data-target="#confirmDelete" </c:if>>Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <nav aria-label="Page navigation example">
                <c:set var="currentPage" value="${requestScope.currentPage}" />
                <c:set var="lastPage" value="${requestScope.lastPage}" />

                <ul class="pagination justify-content-center">
                    <li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>">
                        <a class="page-link" href="<c:url value="?${requestScope.cleanQuery}"><c:param name="page" value="${requestScope.prevPage}" /></c:url>" tabindex="-1">Previous</a>
                    </li>

                    <c:if test="${requestScope.showFirstPageLink}">
                        <!-- First page -->
                        <li class="page-item"><a class="page-link" href="<c:url value="?${requestScope.cleanQuery}"><c:param name="page" value="1" /></c:url>">1</a></li>
                    </c:if>
                    <c:if test="${requestScope.showPrevPagesElipse}">
                        <li class="page-item px-2">...</li>
                    </c:if>

                    <c:forEach begin="${requestScope.firstSurroundPageLink}" end="${requestScope.lastSurroundPageLink}" var="i">
                        <c:choose>
                            <c:when test="${currentPage == i}">
                                <li class="page-item active"><a class="page-link" href="<c:url value="?${requestScope.cleanQuery}"><c:param name="page" value="${i}" /></c:url>">${i}<span class="sr-only">(current)</span></a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="<c:url value="?${requestScope.cleanQuery}"><c:param name="page" value="${i}" /></c:url>">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <!-- Last page -->
                    <c:if test="${requestScope.showNextPagesElipse}">
                        <li class="page-item px-2">...</li>
                    </c:if>
                    <c:if test="${requestScope.showLastPageLink}">
                        <li class="page-item"><a class="page-link" href="<c:url value="?${requestScope.cleanQuery}"><c:param name="page" value="${lastPage}" /></c:url>">${lastPage}</a></li>
                    </c:if>

                    <li class="page-item <c:if test="${currentPage eq lastPage}">disabled</c:if>" >
                        <a class="page-link" href="<c:url value="?${requestScope.cleanQuery}"><c:param name="page" value="${requestScope.nextPage}" /></c:url>">Next</a>
                    </li>
                </ul>
            </nav>
        </c:when>
        <c:otherwise>
            <div class="row text-center">
                <div class="col-lg-8 mx-auto placeholder">
                    <h2>There is no quotes here!</h2>
                    <p>Please generate some in the <a href="<c:url value="/configuration"/>">Configuration</a> section or add new quotes manually.
                    </p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</div>
<!-- /.container -->

<%@include file="includes/footer.jsp"%>

