package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.Local;
import java.util.List;

@Local
public interface QuotesManagerLocal {
    List<Quote> getAllQuotes();
    List<Quote> getPageOfQuotes(int page, int perPage, String sortBy, boolean asc);
    Quote getQuote(int id);
    boolean generateQuotes(int n);
    boolean deleteQuote(int id);
    boolean editQuote(Quote q);
    boolean addQuote(Quote q);
    int getNbQuotes();
}
