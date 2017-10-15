package ch.heigvd.amt.bootcamp.web;

import ch.heigvd.amt.bootcamp.model.Alert;
import ch.heigvd.amt.bootcamp.model.Quote;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean success = quotesManager.editQuote(new Quote(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("quote"),
                request.getParameter("author"),
                Integer.parseInt(request.getParameter("date")),
                request.getParameter("source"),
                request.getParameter("category")
        ));

        if (success) {
            request.setAttribute("alert", new Alert(Alert.Level.SUCCESS, "Success", "The quote has been successfully updated."));
        } else {
            request.setAttribute("alert", new Alert(Alert.Level.WARNING, "Failed", "The update of the quote has failed."));
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
