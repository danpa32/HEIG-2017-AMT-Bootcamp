package ch.heigvd.amt.bootcamp.service;

import ch.heigvd.amt.bootcamp.model.Alert;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

@Local
public interface AlertManagerLocal {
    void add(HttpServletRequest request, Alert alert);
}
