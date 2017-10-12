package ch.heigvd.amt.bootcamp.web;

import ch.heigvd.amt.bootcamp.web.model.Thing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class FrontControllerServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
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
    Random rand = new Random();

    List things = new ArrayList<String>();
    for (int i = 1; i <= 50; i++) {
      things.add(new Thing(i + "th", loremIpsum[rand.nextInt(loremIpsum.length)]));
    }

    request.setAttribute("things", things);
    request.getRequestDispatcher("/WEB-INF/pages").forward(request, response);
  }



}
