/**
 * file: QuotesManagerLocal.java
 * authors:
 *  - Christopher MEIER
 *  - Daniel PALUMBO
 * date: 16.10.2017
 */
package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Local
public interface QuotesManagerLocal {
    /**
     * Get a page of quotes
     * @param page The page number
     * @param perPage The number of quotes per page
     * @param sortBy The field to sort the quotes by
     * @param asc If the order is ascending or descending.
     * @return The page of quotes
     */
    List<Quote> getPageOfQuotes(int page, int perPage, String sortBy, boolean asc);

    /**
     * Recuperate one quote from the DB
     * @param id The id of the quote to recuperate
     * @return The quote or null if there was a problem
     */
    Quote getQuote(int id);

    /**
     * Generate a number of random quotes
     * @param n the number of quotes to generate
     * @return true if there was no problem
     */
    boolean generateQuotes(int n);

    /**
     * Delete the quote specified by the id from the DB
     * @param id The id of the quote.
     * @return true if there was no problem
     */
    boolean deleteQuote(int id);

    /**
     * Delete all quotes from the DB.
     * @return true if there was no problem
     */
    boolean deleteAllQuotes();

    /**
     * Edit a quote in the DB
     * @param q The quote to edit
     * @return true if there was no problem
     */
    boolean editQuote(Quote q);

    /**
     * Save a quote to the DB
     * @param q The quote to save
     * @return true if there was no problem
     */
    boolean addQuote(Quote q);

    /**
     * Get the number of quotes saved.
     * @return
     */
    int getNbQuotes();

    /**
     * Extract a quote from the parameters of an HTTP request.
     * @param request The HTTP request
     * @return The quote or null if there is a problem.
     */
    Quote extractQuote(HttpServletRequest request);

    /**
     * List the possible categories
     * @return The list of categories
     */
    List<String> getCategories();
}
