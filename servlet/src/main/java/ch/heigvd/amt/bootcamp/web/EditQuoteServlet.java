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
    private
    QuotesManagerLocal quotesManager;

    @EJB
    private
    AlertManagerLocal alertManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Quote q = quotesManager.extractQuote(request);

        if(q != null) {
            boolean success = quotesManager.editQuote(q);

            if (success) {
                alertManager.add(request, new Alert(Alert.Level.SUCCESS, "Success", "The quote has been successfully updated."));
            } else {
                alertManager.add(request, new Alert(Alert.Level.WARNING, "Failed", "The update of the quote has failed."));
            }
        }

        specifyQuote(request);
        request.getRequestDispatcher("/WEB-INF/pages/editQuote.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        specifyQuote(request);
        request.getRequestDispatcher("/WEB-INF/pages/editQuote.jsp").forward(request, response);
    }

    private void specifyQuote(HttpServletRequest request) {
        boolean quoteSpecified = false;
        String quoteId = request.getParameter("id");
        if(quoteId != null) {
            try {
                int id = Integer.parseInt(quoteId);
                Quote quote = quotesManager.getQuote(id);
                quoteSpecified = true;
                request.setAttribute("quote", quote);
            } catch (NumberFormatException nfe) {
                alertManager.add(request, new Alert(Alert.Level.DANGER, "Not valid id", "The id is not a valid number."));
            }
        }

        request.setAttribute("quoteSpecified", quoteSpecified);
    }
}
