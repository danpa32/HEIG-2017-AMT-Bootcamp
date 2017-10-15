package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.Local;
import java.sql.SQLException;
import java.util.List;

@Local
interface QuotesDataStoreLocal {
    Quote getQuote(int id) throws SQLException;
    void generateQuotes(int n) throws SQLException;
    void deleteQuote(int id) throws SQLException;
    void deleteAllQuotes() throws SQLException;
    void editQuote(Quote q) throws SQLException;
    void addQuote(Quote q) throws SQLException;
    List<Quote> getPageOfQuotes(int page, int perPage, String sortBy, boolean asc) throws SQLException;
    int getNbQuotes() throws SQLException;
    List<String> getCategories();
}
