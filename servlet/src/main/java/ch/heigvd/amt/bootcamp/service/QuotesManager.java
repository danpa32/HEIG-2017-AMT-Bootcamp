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
        try {
            return quotesDataStore.getAllQuotes();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Quote> getPageOfQuotes(int page, int perPage, String sortBy, boolean asc) {

        return null;
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
}
