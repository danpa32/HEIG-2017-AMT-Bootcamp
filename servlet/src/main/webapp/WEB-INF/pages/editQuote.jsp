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
    <c:choose>
        <c:when test="${requestScope.quoteSpecified == false}">
            <div class="row text-center">
                <div class="col-lg-8 mx-auto placeholder">
                    <h2>The quote you want to edit has not been specified!</h2>
                    <p>Please use the edit button from the <a href="<c:url value="/manage_quotes"/>">Manage Quotes</a> section.
                    </p>
                </div>
            </div>
        </c:when>
        <c:when test="${requestScope.quote != null}">
            <legend class="mt-4">Edit a quote</legend>
            <%@include file="includes/quoteForm.jsp"%>
        </c:when>
        <c:otherwise>
            <div class="row text-center">
                <div class="col-lg-8 mx-auto placeholder">
                    <h2>The quote you want to edit does not exist!</h2>
                    <p>Please use the edit button from the <a href="<c:url value="/manage_quotes"/>">Manage Quotes</a> section.
                    </p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="includes/footer.jsp"%>