package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Quote;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Singleton
public class QuotesDataStore implements QuotesDataStoreLocal {
    List<Quote> quotes;

    public QuotesDataStore() {
        String[] loremIpsum = {
            "Parem quaedam esse Saepe nequaquam Mummio numquam excellentiae suosque Q.",
            "Vicissimque simulatione coluntur solet causa verum etiam quidem minus solet videri fictum est temporis enim hoc id dandis et princeps mihi ab redderet et alio Amor coniungendam videri ab simulatum.",
            "Conpage ad eosque truncata praecipitem Domitianum mox velut abiecerunt discursu eodem mox raptavere impetu superscandentes. ",
            "Epigonus et gentilitatem Epigonus omnium oppressi praediximus appellatos molitioni Epigonus enim fabricarum Montium his vocabulis statuuntur nominum termino culpasse futurae.",
            "Nullam et et abundans superiore.",
            "Admodum corporis vel virtute ut virtute qui ut iucundius aedificio.",
            "Libere etiam studium ab exspectemus in non in ne ad absit sanciatur consilium studium semper.",
            "Ex offert sit atque statum sexus conductae nomine in in elegerit offert hastam diem conductae hastam est solvitur uterque ut."
        };

        String[] sources = {
                "CH", "USA", "BE", "UK"
        };
        Random rand = new Random();

        this.quotes = new ArrayList<Quote>();

        for (int i = 1; i <= 200; i++) {
            quotes.add(new Quote(
                    i,
                    loremIpsum[rand.nextInt(loremIpsum.length)],
                    "author " + i,
                    rand.nextInt(500) + 1500,
                    sources[rand.nextInt(sources.length)],
                    Quote.CATEGORIES[rand.nextInt(Quote.CATEGORIES.length)]));
        }
    }

    @Override
    public List<Quote> getQuotes() {
        return quotes;
    }

    @Override
    public Quote getQuote(int id) {
        for(Quote q : getQuotes()) {
            if(q.getId() == id) {
                return q;
            }
        }
        return null;
    }
}