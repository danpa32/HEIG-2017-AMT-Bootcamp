package ch.heigvd.amt.bootcamp.web;

import ch.heigvd.amt.bootcamp.model.Alert;
import ch.heigvd.amt.bootcamp.service.QuotesManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfigurationServlet", urlPatterns = {"/configuration"})
public class ConfigurationServlet extends HttpServlet {

    @EJB
    QuotesManagerLocal quotesManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nbThingParam = request.getParameter("nbThings");
        if (nbThingParam != null) {
            try {
                int nbThings = Integer.parseInt(nbThingParam);

                boolean success = quotesManager.generateQuotes(nbThings);

                if (success) {
                    request.setAttribute("alert", new Alert(Alert.Level.SUCCESS, "Success", "The generation of " + nbThings + " quotes has succeeded"));
                } else {
                    request.setAttribute("alert", new Alert(Alert.Level.WARNING, "Failed", "The generation of " + nbThings + " quotes has failed"));
                }

                request.setAttribute("nbGeneratedQuotes", request.getParameter("nbGeneratedQuotes"));
            } catch (NumberFormatException nfe) {
                request.setAttribute("alert", new Alert(Alert.Level.DANGER, "Incorrect parameter", "Couldn't parse parameter"));
            }
        } else {
            request.setAttribute("alert", new Alert(Alert.Level.DANGER, "Missing parameter", "Couldn't find the number of quotes to generate"));
        }

        request.getRequestDispatcher("/WEB-INF/pages/configuration.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/configuration.jsp").forward(request, response);
    }
}
