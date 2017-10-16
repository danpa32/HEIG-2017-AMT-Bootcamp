<%--
  Created by IntelliJ IDEA.
  User: christopher
  Date: 14.10.17
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>

<form class="my-4" method="post" role="form">
    <fieldset>
        <c:set var="quote" value="${requestScope.quote}" />
        <c:set var="hasQuote" value="${quote != null}" />
        <c:if test="${hasQuote}">
            <div class="form-group">
                <label for="id">Id</label>
                <input class="form-control" id="id" name="id" placeholder="Id" type="text" value="${quote.id}" disabled>
            </div>
        </c:if>
        <div class="form-group">
            <label for="quote">Quote</label>
            <textarea id="quote" name="quote" class="form-control" placeholder="Quote" rows="5"><c:if test="${hasQuote}">${quote.text}</c:if></textarea>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="author">Author</label>
                <input class="form-control" id="author" name="author" placeholder="Author" type="text" value="<c:if test="${hasQuote}">${quote.author}</c:if>">
            </div>
            <div class="form-group col-md-6">
                <label for="category">Category</label>
                <select id="category" name="category" class="form-control">
                    <c:forEach var="category" items="${requestScope.categories}">
                        <option value="${category}" <c:if test="${hasQuote && quote.category == category}">selected</c:if>>${category}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="source">Source</label>
                <input class="form-control" id="source" name="source" placeholder="Source" type="text"  value="<c:if test="${hasQuote}">${quote.source}</c:if>">
            </div>
            <div class="form-group col-md-6">
                <label for="date">Date</label>
                <input class="form-control" id="date" name="date" placeholder="Date" type="date" value="<c:if test="${hasQuote}">${quote.date}</c:if>">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="<c:url value="/manage_quotes" />" class="btn btn-outline">Return</a>
    </fieldset>
</form>
