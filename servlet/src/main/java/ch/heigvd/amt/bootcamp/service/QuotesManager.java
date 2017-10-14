package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Stateless
public class QuotesManager implements QuotesManagerLocal {
    @EJB
    QuotesDataStoreLocal quotesDataStore;

    @Override
    public List<Quote> getAllQuotes() {
        return quotesDataStore.getQuotes();
    }

    @Override
    public List<Quote> getPageOfQuotes(int page, int perPage, String sortBy, boolean asc) {
        return null;
    }

    @Override
    public Quote getQuote(int id) {
        return quotesDataStore.getQuote(id);
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
}
