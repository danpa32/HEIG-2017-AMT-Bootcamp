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

@WebServlet(name = "AddQuoteServlet", urlPatterns = {"/addQuote"})
public class AddQuoteServlet extends HttpServlet {
    @EJB
    QuotesManagerLocal quotesManager;

    @EJB
    AlertManagerLocal alertManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Quote q = quotesManager.extractQuote(request);

        boolean success = quotesManager.addQuote(q);

        if (success) {
            alertManager.add(request, new Alert(Alert.Level.SUCCESS, "Success", "The quote has been successfully added."));
        } else {
            alertManager.add(request, new Alert(Alert.Level.WARNING, "Failed", "The insertion of the quote has failed."));
        }

        request.getRequestDispatcher("/WEB-INF/pages/addQuote.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/addQuote.jsp").forward(request, response);
    }
}
