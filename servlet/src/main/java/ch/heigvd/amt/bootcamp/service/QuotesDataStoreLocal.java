package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.Local;
import java.sql.SQLException;
import java.util.List;

@Local
public interface QuotesDataStoreLocal {
    List<Quote> getQuotes();
    Quote getQuote(int id);
    void generateQuotes(int n) throws SQLException;
}
