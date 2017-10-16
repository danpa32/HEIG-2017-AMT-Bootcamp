package ch.heigvd.amt.bootcamp.web;

import ch.heigvd.amt.bootcamp.model.Alert;
import ch.heigvd.amt.bootcamp.model.Quote;
import ch.heigvd.amt.bootcamp.service.AlertManagerLocal;
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
    private
    QuotesManagerLocal quotesManager;

    @EJB
    private
    AlertManagerLocal alertManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int NB_QUOTES_PER_PAGE = 32;

        // QueryString for options links
        setSanitizedURL(request);

        // (Dis)activate delete confirmation
        String confirmParam = request.getParameter("confirm");
        if(confirmParam != null) {
            request.getSession().setAttribute("confirmDelete", !confirmParam.equals("0"));
        }

        // Deletion of a quote
        String delParam = request.getParameter("del");
        if(delParam != null) {
            try {
                int delId = Integer.parseInt(delParam);
                boolean success = quotesManager.deleteQuote(delId);

                if (success) {
                    alertManager.add(request, new Alert(Alert.Level.SUCCESS, "Success", "The quote has been successfully deleted."));
                } else {
                    alertManager.add(request, new Alert(Alert.Level.WARNING, "Failed", "The deletion of the quote has failed."));
                }

            } catch (NumberFormatException nfe) {
                alertManager.add(request, new Alert(Alert.Level.DANGER, "Unparseable parameter", "The delete parameter is not an integer."));
            }
        }

        // Asc/Desc
        String ascParam = request.getParameter("asc");
        boolean asc = true;
        if(ascParam != null) {
            asc = !ascParam.equals("0");
        }
        request.setAttribute("asc", asc);

        // Sort
        String sortParam = request.getParameter("sort");
        String sortBy = "id";
        if(sortParam != null) {
            try {
                Quote.FIELDS.valueOf(sortParam);
                sortBy = sortParam.toLowerCase();
            } catch (IllegalArgumentException iae) {
                alertManager.add(request, new Alert(Alert.Level.DANGER, "Unparseable parameter", "The sort parameter \"" + sortParam + "\" is not valid."));
            }
        }
        request.setAttribute("sortBy", sortBy.toUpperCase());

        // Pagination
        String pageParam = request.getParameter("page");
        int currentPage = 1;
        if(pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException nfe) {
                alertManager.add(request, new Alert(Alert.Level.DANGER, "Unparseable parameter", "The page parameter is not an integer."));
            }
        }
        setPaginationAttributes(request, currentPage, NB_QUOTES_PER_PAGE);

        // Recuperate the page of quote
        List quotes = quotesManager.getPageOfQuotes(currentPage, NB_QUOTES_PER_PAGE, sortBy, asc);

        request.setAttribute("quotes", quotes);

        request.getRequestDispatcher("/WEB-INF/pages/manage_quotes.jsp").forward(request, response);
    }

    private void setPaginationAttributes(HttpServletRequest request, int currentPage, int per_page) {
        int nbQuotes = quotesManager.getNbQuotes();
        int lastPage = ((nbQuotes - 1) / per_page) + 1;
        final int SURROUNDING_PAGES = 3;

        request.setAttribute("rangeStart", (currentPage - 1) * per_page + 1);
        request.setAttribute("rangeEnd", Integer.min(nbQuotes, currentPage * per_page));
        request.setAttribute("rangeLast", nbQuotes);

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lastPage", lastPage);

        request.setAttribute("prevPage", Integer.max(1, currentPage - 1));
        request.setAttribute("nextPage", Integer.min(lastPage, currentPage + 1));

        // Show first ?
        request.setAttribute("showPrevPagesElipse", currentPage - SURROUNDING_PAGES - 1 > 1);
        request.setAttribute("showFirstPageLink", currentPage - SURROUNDING_PAGES > 1);

        // Show surrounding ?
        request.setAttribute("firstSurroundPageLink", Integer.max(1,currentPage - SURROUNDING_PAGES));
        request.setAttribute("lastSurroundPageLink", Integer.min(lastPage, currentPage + SURROUNDING_PAGES));

        // Show last ?
        request.setAttribute("showNextPagesElipse", currentPage  + SURROUNDING_PAGES + 1 < lastPage );
        request.setAttribute("showLastPageLink", currentPage  + SURROUNDING_PAGES < lastPage );
    }

    /**
     * Generate URL used for sorting purpose
     * @param request
     */
    private void setSanitizedURL(HttpServletRequest request) {
        String query = request.getQueryString();

        String cleanQuery = removeQueryStringParams(query, "del", "confirm");
        request.setAttribute("cleanQuery", cleanQuery);

        String pageQuery = removeQueryStringParams(cleanQuery, "page");
        request.setAttribute("pageQuery", pageQuery);

        String ascQuery = removeQueryStringParams(cleanQuery, "asc");
        request.setAttribute("ascQuery", ascQuery);

        String sortQuery = removeQueryStringParams(cleanQuery, "sort");
        request.setAttribute("sortQuery", sortQuery);
    }

    private String removeQueryStringParams(String queryString, String... paramNames) {
        if(queryString == null) {
            return "";
        } else {
            for(String param : paramNames) {
                queryString = queryString.replaceAll(param + "[^&]*&*", "");
            }
            return queryString;
        }
    }
}
