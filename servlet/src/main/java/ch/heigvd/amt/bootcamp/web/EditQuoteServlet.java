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

@WebServlet(name = "EditQuoteServlet", urlPatterns = {"/editQuote"})
public class EditQuoteServlet extends HttpServlet {

    @EJB
    QuotesManagerLocal quotesManager;

    @EJB
    AlertManagerLocal alertManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer date = null;
        String dateParam = request.getParameter("date");
        if(dateParam != null && !dateParam.isEmpty()) {
            try {
                date = Integer.parseInt(dateParam);
            } catch (NumberFormatException nfe){
                alertManager.add(request, new Alert(Alert.Level.DANGER, "Unparseable parameter", "The date is not a number."));
            }
        }

        String authorParam = request.getParameter("author");
        if (authorParam.isEmpty()) {
            authorParam = "Unknown";
        }

        boolean success = quotesManager.editQuote(new Quote(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("quote"),
                authorParam,
                date,
                request.getParameter("source"),
                request.getParameter("category")
        ));

        if (success) {
            alertManager.add(request, new Alert(Alert.Level.SUCCESS, "Success", "The quote has been successfully updated."));
        } else {
            alertManager.add(request, new Alert(Alert.Level.WARNING, "Failed", "The update of the quote has failed."));
        }

        request.getRequestDispatcher("/WEB-INF/pages/editQuote.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean quoteSpecified = false;
        String quoteId = request.getParameter("id");
        if(quoteId != null) {
            int id = Integer.parseInt(quoteId);
            Quote quote = quotesManager.getQuote(id);
            quoteSpecified = true;
            request.setAttribute("quote", quote);
        }

        request.setAttribute("quoteSpecified", quoteSpecified);

        request.getRequestDispatcher("/WEB-INF/pages/editQuote.jsp").forward(request, response);
    }
}
