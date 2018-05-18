package com.comparadorsamialicia;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

Entity bebidas = new Entity("Bebidas");

bebidas.setProperty("EAN", );
book.setProperty("nombre", " ");
book.setProperty("marca", "");
book.setProperty("url", "");
book.setProperty("tamanio", );
book.setProperty("unidad", "");
book.setProperty("supermercado", "");

ds.put(bebidas);


private int EAN;
private String nombre;
private String marca;
private String url;
private int tamanio;
private String unidad;
private String supermercado;