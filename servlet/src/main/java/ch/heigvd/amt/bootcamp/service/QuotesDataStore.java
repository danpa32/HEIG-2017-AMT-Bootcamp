package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Startup
@Singleton
public class QuotesDataStore implements QuotesDataStoreLocal {

    @Resource(lookup = "java:/quotes")
    private DataSource dataSource;

    private JsonArray quoteAndAuthor;

    private List<String> countries;
    private List<String> categories;

    @PostConstruct
    void init() {
        quoteAndAuthor = getJsonArrayResource("/quotes.json");
        JsonArray jCountries = getJsonArrayResource("/countries.json");
        JsonArray jCategories = getJsonArrayResource("/categories.json");

        countries = new ArrayList<>();
        for(JsonElement elem : jCountries) {
            countries.add(elem.getAsJsonObject().get("name").getAsString());
        }

        categories = new ArrayList<>();
        categories.add(Quote.DEFAULT_CATEGORY);
        for(JsonElement elem : jCategories) {
            categories.add(elem.getAsString());
        }
    }

    public List<String> getCategories() {
        return categories;
    }

    public void generateQuotes(int n) throws SQLException {
        List<Quote> quotes = new ArrayList<>();
        int idCount = 0;
        Random random = new Random();

        for (int j = 0; j < n; j++) {
            JsonElement elem = quoteAndAuthor.get(random.nextInt(quoteAndAuthor.size()));
            Quote q = new Quote(idCount,
                    elem.getAsJsonObject().get("quote").getAsString(),
                    elem.getAsJsonObject().get("author").getAsString(),
                    random.nextInt(500) + 1500,
                    countries.get(random.nextInt(countries.size())),
                    categories.get(random.nextInt(categories.size())));

            quotes.add(q);
            idCount++;
        }

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO quote (quote, author, date, source, category) VALUES (?, ?, ?, ?, ?)");

        int j = 0;
        for (Quote curr : quotes) {
            prepareStatementWithQuote(preparedStatement, curr);

            j++;

            preparedStatement.addBatch();
            if (j%1000 == 0 || j == n) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }

        connection.close();
    }

    @Override
    public void deleteQuote(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM quote WHERE id = ?;");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public void deleteAllQuotes() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM quote;");
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public void editQuote(Quote q) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE quote SET quote = ?, author = ?, date = ?, source = ?, category = ? WHERE id = ?;");
        prepareStatementWithQuote(preparedStatement, q);
        preparedStatement.setInt(6, q.getId());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public void addQuote(Quote q) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO quote (quote, author, date, source, category) VALUES (?, ?, ?, ?, ?);");
        prepareStatementWithQuote(preparedStatement, q);
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public List<Quote> getPageOfQuotes(int page, int perPage, String sortBy, boolean asc) throws SQLException {
        int offset = (page - 1) * perPage;

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM quote ");
        query.append(" ORDER BY ");
        query.append(sortBy);
        if (asc) {
            query.append(" ASC ");
        } else {
            query.append(" DESC ");
        }
        query.append("LIMIT ");
        query.append(perPage);
        query.append(" OFFSET ");
        query.append(offset);
        query.append(";");

        return extractQuotes(query.toString());
    }

    @Override
    public int getNbQuotes() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(id) AS total FROM quote;");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int size = resultSet.getInt("total");
        connection.close();
        return size;
    }

    @Override
    public List<Quote> getAllQuotes() throws SQLException {
        return extractQuotes("SELECT * FROM quote;");
    }

    @Override
    public Quote getQuote(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM quote WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean hasQuote = resultSet.next();

        Quote q = null;
        if(hasQuote) {
            q = extractQuote(resultSet);
        }

        connection.close();

        return q;
    }

    private List<Quote> extractQuotes(String query) throws SQLException {
        List<Quote> quotes = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            quotes.add(extractQuote(resultSet));
        }

        connection.close();
        return quotes;
    }

    private Quote extractQuote(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String quote = resultSet.getString("quote");
        String author = resultSet.getString("author");
        Integer date = (Integer) resultSet.getObject("date");
        String source = resultSet.getString("source");
        String category = resultSet.getString("category");
        return new Quote(id, quote, author, date, source, category);
    }

    private void prepareStatementWithQuote(PreparedStatement preparedStatement, Quote q) throws SQLException {
        preparedStatement.setString(1, q.getText());
        preparedStatement.setString(2, q.getAuthor());
        if (q.getDate() == null) {
            preparedStatement.setNull(3, Types.INTEGER);
        } else {
            preparedStatement.setInt(3, q.getDate());
        }
        preparedStatement.setString(4, q.getSource());
        preparedStatement.setString(5, q.getCategory());
    }

    private JsonArray getJsonArrayResource(String path) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return new JsonParser().parse(br).getAsJsonArray();
    }
}
