package ch.heigvd.amt.bootcamp.web;

import ch.heigvd.amt.bootcamp.service.QuotesManagerLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CategoriesFilter", urlPatterns = {"/editQuote", "/addQuote"})
public class CategoriesFilter implements Filter {
    @EJB
    private
    QuotesManagerLocal quotesManager;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setAttribute("categories", quotesManager.getCategories());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
