package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Alert;
import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class QuotesManager implements QuotesManagerLocal {
    @EJB
    private
    QuotesDataStoreLocal quotesDataStore;

    @EJB
    private
    AlertManagerLocal alertManager;

    @Override
    public List<Quote> getPageOfQuotes(int page, int perPage, String sortBy, boolean asc) {
        try {
            return quotesDataStore.getPageOfQuotes(page, perPage, sortBy, asc);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Quote getQuote(int id) {
        try {
            return quotesDataStore.getQuote(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean generateQuotes(int n) {
        try {
            quotesDataStore.generateQuotes(n);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteQuote(int id) {
        try {
            quotesDataStore.deleteQuote(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteAllQuotes() {
        try {
            quotesDataStore.deleteAllQuotes();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editQuote(Quote q) {
        try {
            quotesDataStore.editQuote(q);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addQuote(Quote q) {
        try {
            quotesDataStore.addQuote(q);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getNbQuotes() {
        try {
            return quotesDataStore.getNbQuotes();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Quote extractQuote(HttpServletRequest request) {
        // Id
        String idParam = request.getParameter("id");
        int id = 0;
        if(idParam != null) {
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException nfe){
                alertManager.add(request, new Alert(Alert.Level.DANGER, "Unparseable parameter", "The id is not a number."));
            }
        }

        // Quote
        String quoteParam = request.getParameter("quote");
        if(quoteParam == null) {
            alertManager.add(request, new Alert(Alert.Level.DANGER, "Missing parameter", "The quote content parameter is required."));
            return null;
        } else if(quoteParam.isEmpty()) {
            alertManager.add(request, new Alert(Alert.Level.WARNING, "Empty parameter", "The quote content parameter can not be empty."));
            return null;
        }

        // Author
        String authorParam = request.getParameter("author");
        if (authorParam.isEmpty()) {
            authorParam = Quote.DEFAULT_AUTHOR;
        }

        // Date
        String dateParam = request.getParameter("date");
        Integer date = null;
        if(dateParam != null && !dateParam.isEmpty()) {
            try {
                date = Integer.parseInt(dateParam);
            } catch (NumberFormatException nfe){
                alertManager.add(request, new Alert(Alert.Level.DANGER, "Unparseable parameter", "The date is not a number."));
            }
        }

        // Source
        String sourceParam = request.getParameter("source");
        if(sourceParam == null) {
            sourceParam = "";
        }

        // Category
        String categoryParam = request.getParameter("category");
        if(categoryParam == null || categoryParam.isEmpty()) {
            categoryParam = Quote.DEFAULT_CATEGORY;
        }

        return new Quote(
                id,
                quoteParam,
                authorParam,
                date,
                sourceParam,
                categoryParam
        );
    }

    @Override
    public List<String> getCategories() {
        return quotesDataStore.getCategories();
    }


}
