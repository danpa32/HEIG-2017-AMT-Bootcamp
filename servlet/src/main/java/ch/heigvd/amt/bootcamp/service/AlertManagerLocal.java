/**
 * file: AlertManagerLocal.java
 * authors:
 *  - Christopher MEIER
 *  - Daniel PALUMBO
 * date: 16.10.2017
 */
package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Alert;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

@Local
public interface AlertManagerLocal {
    /**
     * Add an alert to the list of alert attached to a request
     * @param request The HTTP request
     * @param alert The alert to add to the list
     */
    void add(HttpServletRequest request, Alert alert);
}
