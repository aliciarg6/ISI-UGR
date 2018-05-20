package webscrapping;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Comparador {
	
	public static String eliminarUltimoCaracter(String precio) {
		
		if (precio != null && precio.length() > 0) {
	        precio = precio.substring(0, precio.length() - 1);
	    }
		
		return precio;
	}
	
	public static String cambiarPuntoComa(String precio) {
		precio = precio.replace(',', '.');
		return precio;
		
	}
	
	public static String[] obtenerCapacidad(String capacidad){
		return capacidad.split(" "); 
	}
	
	public void Scrapeando(ArrayList<String> precio, ArrayList<String> capacidad, ArrayList<String> marca) {
		
		ArrayList<Double> precios = new ArrayList();
		for(int i=0; i<precio.size(); i++) {
			String aux = eliminarUltimoCaracter(precio.get(i));
			aux=cambiarPuntoComa(aux);
			precios.add(Double.parseDouble(aux));
		}
		
		ArrayList<String> capacidades = new ArrayList();
		for (int i=0; i <capacidad.size(); i++) {
			
			if(!capacidades.contains(capacidad.get(i))) {
				capacidades.add(capacidad.get(i));
			}	
		} 
		
		ArrayList<Double> precio_min = new ArrayList();
		ArrayList<String> marca_min = new ArrayList();
		
		for(int i=0; i<capacidades.size(); i++) {
			Double p_min=100000000.0;
			String m_min="";
			for(int j=0; j<capacidad.size(); j++) {
				if(capacidades.get(i).equals(capacidad.get(j))) {
					//System.out.println("Aquiii");
					if(precios.get(j) < p_min ) {
						p_min=precios.get(j);
						m_min=marca.get(j);
					}
				}
			}
			precio_min.add(p_min);
			marca_min.add(m_min);
		}
		
		for(int i=0; i<precio_min.size(); i++) {
			System.out.println("Marca " + marca_min.get(i)+ " Dimensión "+capacidades.get(i)+ " Precio "+ Double.toString(precio_min.get(i)) );
		}
		
	}
	
	public void carrefour(String producto){
		//CARREFOUR
		Document document = Jsoup.connect("https://www.carrefour.es/supermercado/c?Ntt="+nombre+"&sb=true").get();
		
		//Titulo de la página Web
		String title = document.title();
		System.out.println("Nombre Supermercado: " + title);
	
		//Array de elementos con todos los precios
		Elements capacidad = document.select("p.name-formato");
		Elements marca = document.select("p.name-marca"); 
		Elements precio = document.select("div.pre-bottom-inner"); 
		
		
	
	}
	

	
	public ArrayList<String> eroski(String producto){
		
		
	}
	
	public ArrayList<String> dia(String producto){
		
	}


}
