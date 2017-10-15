package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Alert;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Stateless
public class AlertManager implements AlertManagerLocal {
    @Override
    public void add(HttpServletRequest request, Alert alert) {
        Collection<Alert> alerts = (Collection<Alert>) request.getAttribute("alerts");

        if(alerts == null) {
            alerts = new PriorityQueue<>(Comparator.comparing((a) -> a.getLevelEnum().ordinal()));
            request.setAttribute("alerts", alerts);
        }

        alerts.add(alert);
    }
}
