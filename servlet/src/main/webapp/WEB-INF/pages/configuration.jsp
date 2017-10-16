<%--
  File: configuration.jsp
  Authors:
    - Christopher MEIER
    - Daniel PALUMBO
  Date: 16.10.17
  Page to configure the database.
--%>
<%@ page import="ch.heigvd.amt.bootcamp.web.ConfigurationServlet" %>

<%@include file="includes/header.jsp"%>

<div class="container">
    <legend class="mt-4">Configuration</legend>
    <div class="row my-4">
        <div class="col-sm-6">
            <form class="card" role="form" action="<c:url value="/configuration"/>" method="post">
                <div class="card-header">
                    Quotes generation
                </div>
                <div class="card-body">
                    <input name="conf" id="confGen" value="gen" hidden type="text">
                    <fieldset>
                        <div class="form-group">
                            <label for="nbThings">Number of quotes</label>
                            <input class="form-control" name="nbThings" id="nbThings" placeholder="Number of quotes to generate" type="text">
                        </div>
                    </fieldset>
                </div>
                <div class="card-footer">
                    <div class="col-ml-4">
                        <button type="submit" class="btn btn-primary float-right">Generate</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-sm-6">
            <form class="card" role="form" action="<c:url value="/configuration"/>" method="post">
                <div class="card-header">
                    Quotes deletion
                </div>
                <div class="card-body">
                    <input name="conf" id="confDel" value="del" hidden type="text">
                    <fieldset>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" name="confDelete" onload="function () {
                                  this.checked = false;
                                }">
                                Are you sure you want to delete all the quotes in the database ?
                            </label>
                        </div>
                    </fieldset>
                </div>
                <div class="card-footer">
                    <div class="col-ml-4">
                        <button type="submit" class="btn btn-danger float-right">Delete all</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="includes/footer.jsp"%>

