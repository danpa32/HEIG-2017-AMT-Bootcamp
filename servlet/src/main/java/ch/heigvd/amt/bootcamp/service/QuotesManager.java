package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
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
}
