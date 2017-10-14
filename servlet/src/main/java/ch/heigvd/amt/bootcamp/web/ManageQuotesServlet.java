package ch.heigvd.amt.bootcamp.web;

import ch.heigvd.amt.bootcamp.model.Alert;
import ch.heigvd.amt.bootcamp.service.QuotesManager;
import ch.heigvd.amt.bootcamp.service.QuotesManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageQuotesServlet", urlPatterns = {"/manage_quotes"})
public class ManageQuotesServlet extends HttpServlet {

    @EJB
    QuotesManagerLocal quotesManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List quotes = quotesManager.getAllQuotes();

        request.setAttribute("quotes", quotes);

        setSanitizedURL(request);

        // (Dis)activate delete confirmation
        String confirmParam = request.getParameter("confirm");
        if(confirmParam != null) {
            request.getSession().setAttribute("confirmDelete", !confirmParam.equals("0"));
        }

        // Alert
        request.setAttribute("alert", new Alert(Alert.Level.DANGER, "Some alert title", "An alert message."));

        // Asc/Desc
        String ascParam = request.getParameter("asc");
        boolean asc = !ascParam.equals("0");
        request.setAttribute("asc", asc);

        // Pagination
        int page = 1;
        int lastPage = 1;
        request.setAttribute("page", page);
        request.setAttribute("lastPage", lastPage);

        request.getRequestDispatcher("/WEB-INF/pages/manage_quotes.jsp").forward(request, response);
    }

    /**
     * Generate URL used for sorting purpose
     * @param request
     */
    private void setSanitizedURL(HttpServletRequest request) {
        String query = request.getQueryString();

        String cleanQuery = removeQueryStringParam(query, "del");
        cleanQuery = removeQueryStringParam(cleanQuery, "confirm");
        request.setAttribute("cleanQuery", cleanQuery);

        String ascQuery = removeQueryStringParam(query, "asc");
        request.setAttribute("ascQuery", ascQuery);

        String sortQuery = removeQueryStringParam(query, "sort");
        request.setAttribute("sortQuery", sortQuery);
    }

    private String removeQueryStringParam(String queryString, String paramName) {
        if(queryString == null) {
            return "";
        } else {
            return queryString.replaceAll(paramName + "[^&]*&?", "").replaceFirst("&$", "");
        }
    }
}
