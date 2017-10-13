package ch.heigvd.amt.bootcamp.web;

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

        request.getRequestDispatcher("/WEB-INF/pages/manage_quotes.jsp").forward(request, response);
    }
}
