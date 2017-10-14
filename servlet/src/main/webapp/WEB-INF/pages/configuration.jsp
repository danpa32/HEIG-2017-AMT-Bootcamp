<%@ page import="ch.heigvd.amt.bootcamp.web.ConfigurationServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 12.10.17
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>

<div class="container">
    <form class="my-4" role="form" action="./configuration" method="post">
        <fieldset>
            <div class="form-group col-md-4">
                <label for="nbThings">Number of things</label>
                <input class="form-control" name="nbThings" id="nbThings" placeholder="Number of things to generate" type="text">
            </div>

            <div class="col-md-4">
                <button type="submit" class="btn btn-primary float-right">Generate</button>
            </div>
        </fieldset>
    </form>
</div>

<%@include file="includes/footer.jsp"%>

