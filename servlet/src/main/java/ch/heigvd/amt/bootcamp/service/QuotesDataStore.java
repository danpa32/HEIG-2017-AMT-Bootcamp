package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;
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
        countries = new ArrayList<>();
        categories = new ArrayList<>();

        InputStream isQuotes = getClass().getClassLoader().getResourceAsStream("/quotes.json");
        BufferedReader brQuotes = new BufferedReader(new InputStreamReader(isQuotes));
        quoteAndAuthor = new JsonParser().parse(brQuotes).getAsJsonArray();

        InputStream isCountries = getClass().getClassLoader().getResourceAsStream("/countries.json");
        BufferedReader brCountries = new BufferedReader(new InputStreamReader(isCountries));
        JsonArray jCountries = new JsonParser().parse(brCountries).getAsJsonArray();
        for (JsonElement elem : jCountries) {
            countries.add(elem.getAsJsonObject().get("name").getAsString());
        }

        InputStream isCategories = getClass().getClassLoader().getResourceAsStream("/categories.json");
        BufferedReader brCategories = new BufferedReader(new InputStreamReader(isCategories));
        JsonArray jCategories = new JsonParser().parse(brCategories).getAsJsonArray();
        for (JsonElement elem : jCategories) {
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
            preparedStatement.setString(1, curr.getText());
            preparedStatement.setString(2, curr.getAuthor());
            preparedStatement.setInt(3, curr.getDate());
            preparedStatement.setString(4, curr.getSource());
            preparedStatement.setString(5, curr.getCategory());

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
    public void editQuote(Quote q) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE quote SET quote = ?, author = ?, date = ?, source = ?, category = ? WHERE id = ?;");
        preparedStatement.setString(1, q.getText());
        preparedStatement.setString(2, q.getAuthor());
        if (q.getDate() == null) {
            preparedStatement.setNull(3, Types.INTEGER);
        } else {
            preparedStatement.setInt(3, q.getDate());
        }
        preparedStatement.setString(4, q.getSource());
        preparedStatement.setString(5, q.getCategory());
        preparedStatement.setInt(6, q.getId());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public void addQuote(Quote q) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO quote (quote, author, date, source, category) VALUES (?, ?, ?, ?, ?);");
        preparedStatement.setString(1, q.getText());
        preparedStatement.setString(2, q.getAuthor());
        if (q.getDate() == null) {
            preparedStatement.setNull(3, Types.INTEGER);
        } else {
            preparedStatement.setInt(3, q.getDate());
        }
        preparedStatement.setString(4, q.getSource());
        preparedStatement.setString(5, q.getCategory());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public List<Quote> getPageOfQuotes(int page, int perPage, String sortBy, boolean asc) throws SQLException {
        List<Quote> quotesOfPageX = new ArrayList<>();
        int offset = (page - 1) * perPage;
        Connection connection = dataSource.getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM quote ");
        sb.append(" ORDER BY ");
        sb.append(sortBy);
        sb.append(" ");
        if (asc) {
            sb.append(" ASC ");
        } else {
            sb.append(" DESC ");
        }
        sb.append("LIMIT ");
        sb.append(perPage);
        sb.append(" OFFSET ");
        sb.append(offset);
        sb.append(";");

        PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String quote = resultSet.getString("quote");
            String author = resultSet.getString("author");
            Integer date = (Integer) resultSet.getObject("date");
            String source = resultSet.getString("source");
            String category = resultSet.getString("category");
            quotesOfPageX.add(new Quote(id, quote, author, date, source, category));
        }
        connection.close();

        return quotesOfPageX;
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
        List<Quote> allQuotes = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM quote;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String quote = resultSet.getString("quote");
            String author = resultSet.getString("author");
            Integer date = (Integer) resultSet.getObject("date");
            String source = resultSet.getString("source");
            String category = resultSet.getString("category");
            allQuotes.add(new Quote(id, quote, author, date, source, category));
        }
        connection.close();

        return allQuotes;
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
            String quote = resultSet.getString("quote");
            String author = resultSet.getString("author");
            Integer date = (Integer) resultSet.getObject("date");
            String source = resultSet.getString("source");
            String category = resultSet.getString("category");
            q = new Quote(id, quote, author, date, source, category);
        }

        connection.close();

        return q;
    }


}
