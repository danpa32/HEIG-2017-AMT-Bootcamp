package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.Local;
import java.util.List;

@Local
public interface QuotesDataStoreLocal {
    List<Quote> getQuotes();
}
