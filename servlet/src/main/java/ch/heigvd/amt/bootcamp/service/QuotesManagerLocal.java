package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.Local;
import java.util.List;

@Local
public interface QuotesManagerLocal {
    List<Quote> getAllQuotes();
}
