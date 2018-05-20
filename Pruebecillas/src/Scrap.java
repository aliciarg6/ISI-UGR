import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Scrap {
	
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
	
	
public static void Scrapeando(ArrayList<String> precio, ArrayList<String> capacidad, ArrayList<String> marca, ArrayList<String> capacidades, ArrayList<Double> precio_min,
		ArrayList<String> marca_min) {
		
		ArrayList<Double> precios = new ArrayList();
		for(int i=0; i<precio.size(); i++) {
			String aux = eliminarUltimoCaracter(precio.get(i));
			aux=cambiarPuntoComa(aux);
			precios.add(Double.parseDouble(aux));
		}
		
		for (int i=0; i <capacidad.size(); i++) {
			
			if(!capacidades.contains(capacidad.get(i))) {
				capacidades.add(capacidad.get(i));
			}	
		} 
		
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
		
		
		
	}

	public static void carrefour(String producto, ArrayList<String> precio, ArrayList<String> marca, ArrayList<String> capacidad){
		try {
	//CARREFOUR
		Document document = Jsoup.connect("https://www.carrefour.es/supermercado/c?Ntt="+producto+"&sb=true").get();

		//Array de elementos con todos los precios
		Elements capacidadC = document.select("p.name-formato");
		Elements marcaC = document.select("p.name-marca"); 
		Elements precioC = document.select("div.pre-bottom-inner"); 

		
		for(int i=0; i<capacidadC.size(); i++) {
			precio.add(precioC.get(i).text());
			capacidad.add(capacidadC.get(i).text());
			marca.add(marcaC.get(i).text());
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	

	}
	
	public static void eroski(String producto , ArrayList<String> precio, ArrayList<String> marca, ArrayList<String> capacidad){
	
		try {
		//EORSKI
		Document eroski = Jsoup.connect("https://www.compraonline.grupoeroski.com/es/search/results/?q="+producto).get();
	     
		//Elements de elementos con todos los precios
		Elements marca_capacidad = eroski.select("h2[role]"); 
		Elements precioE = eroski.select("span.price-offer-now"); 
		
		
		//Array de marca y de capacidad

		for(int i=0; i<marca_capacidad.size(); i++) {
			String[] aux = obtenerCapacidad(marca_capacidad.get(i).text());
			capacidad.add(aux[aux.length-2]+" "+aux[aux.length-1]);
			String palabra=aux[0];
			palabra+=" ";
			for(int j=1; j<aux.length-2; j++) {
				 palabra+=aux[j];
				 palabra+=" ";
			}
			marca.add(palabra);
		}
		
		for(int i=0; i<precioE.size(); i++) {
			precio.add(precioE.get(i).text());
		}
		
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}
	
	public static void  dia(String producto , ArrayList<String> precio, ArrayList<String> marca, ArrayList<String> capacidad){
		//DIA
		
		try {
		Document dia = Jsoup.connect("https://www.dia.es/compra-online/search?text="+producto+"&x=0&y=0").get();
	
		//Array de elementos con todos los precios
		Elements marca_capacidad = dia.select("span.details"); 
		Elements precioD = dia.select("p.price"); 
		
		
		//Array de marca y de capacidad

		for(int i=0; i<marca_capacidad.size(); i++) {
			String[] aux = obtenerCapacidad(marca_capacidad.get(i).text());
			capacidad.add(aux[aux.length-2]+" "+aux[aux.length-1]);
			String palabra=aux[0];
			palabra+=" ";
			for(int j=1; j<aux.length-2; j++) {
				 palabra+=aux[j];
				 palabra+=" ";
			}
			marca.add(palabra);
		}
		
		for(int i=0; i<precioD.size(); i++) {
			precio.add(precioD.get(i).text());
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	public static void main(String args[]){
		print("running...");
		System.out.println("Mete el nombre del producto:");
		Scanner entrada = new Scanner(System.in);
		String producto = entrada.nextLine();
		
		
		
		ArrayList<String> precio_c = new ArrayList();
		ArrayList<String> marca_c = new ArrayList();
		ArrayList<String> capacidad_c = new ArrayList();
		
		ArrayList<String> capacidades_c = new ArrayList();
		ArrayList<Double> precio_min_c= new ArrayList();
		ArrayList<String> marca_min_c = new ArrayList();
		
		ArrayList<String> precio_e= new ArrayList();
		ArrayList<String> marca_e= new ArrayList();
		ArrayList<String> capacidad_e= new ArrayList();
		
		ArrayList<String> capacidades_e= new ArrayList();
		ArrayList<Double> precio_min_e= new ArrayList();
		ArrayList<String> marca_min_e= new ArrayList();
		
		ArrayList<String> precio_d= new ArrayList();
		ArrayList<String> marca_d = new ArrayList();
		ArrayList<String> capacidad_d = new ArrayList();

		ArrayList<String> capacidades_d = new ArrayList();
		ArrayList<Double> precio_min_d= new ArrayList();
		ArrayList<String> marca_min_d = new ArrayList();
		
		
		
		 carrefour( producto,  precio_c,  marca_c, capacidad_c);
		 eroski(producto ,  precio_e, marca_e, capacidad_e);
		 dia(producto ,  precio_d, marca_d,  capacidad_d);
		
		
		Scrapeando(precio_c,  capacidad_c,  marca_c,  capacidades_c,  precio_min_c, marca_min_c); 
		Scrapeando(precio_e,  capacidad_e,  marca_e,  capacidades_e,  precio_min_e, marca_min_e); 
		Scrapeando(precio_d,  capacidad_d,  marca_d,  capacidades_d,  precio_min_d, marca_min_d); 
		
		System.out.println("Carrefour");
		for(int i=0; i<precio_min_c.size(); i++) {
			System.out.println("Marca " + marca_min_c.get(i)+ " Dimensión "+capacidades_c.get(i)+ " Precio "+ Double.toString(precio_min_c.get(i)) );
		}
		
		System.out.println("Eroski");
		for(int i=0; i<precio_min_e.size(); i++) {
			System.out.println("Marca " + marca_min_e.get(i)+ " Dimensión "+capacidades_e.get(i)+ " Precio "+ Double.toString(precio_min_e.get(i)) );
		}
		
		System.out.println("Dia");
		for(int i=0; i<precio_min_d.size(); i++) {
			System.out.println("Marca " + marca_min_d.get(i)+ " Dimensión "+capacidades_d.get(i)+ " Precio "+ Double.toString(precio_min_d.get(i)) );
		}
		
		
		
		
		//try {
			
			
			/*
			//CARREFOUR
			Document document = Jsoup.connect("https://www.carrefour.es/supermercado/c?Ntt="+nombre+"&sb=true").get();
			
			//Titulo de la página Web
			String title = document.title();
			System.out.println("Nombre Supermercado: " + title);
		
			//Array de elementos con todos los precios
			Elements capacidad = document.select("p.name-formato");
			Elements marca = document.select("p.name-marca"); 
			Elements precio = document.select("div.pre-bottom-inner"); 
			
			//Imprimir todos los elementos
			ArrayList<String> capacidades = new ArrayList();
			for (int i=0; i <capacidad.size(); i++) {
				
				if(!capacidades.contains(capacidad.get(i).text())) {
					capacidades.add(capacidad.get(i).text());
				}
				
				System.out.println(marca.get(i).text());
				System.out.println(capacidad.get(i).text());
				System.out.println(cambiarPuntoComa(eliminarUltimoCaracter(precio.get(i).text())));
				System.out.println(" ");
				
			}
			
			ArrayList<Double> precios = new ArrayList();
			for(int i=0; i<precio.size(); i++) {
				String aux = eliminarUltimoCaracter(precio.get(i).text());
				aux=cambiarPuntoComa(aux);
				precios.add(Double.parseDouble(aux));
			}
			
			ArrayList<Double> precio_min = new ArrayList();
			ArrayList<String> marca_min = new ArrayList();
			
			for(int i=0; i<capacidades.size(); i++) {
				Double p_min=100000000.0;
				String m_min="";
				for(int j=0; j<capacidad.size(); j++) {
					if(capacidades.get(i).equals(capacidad.get(j).text())) {
						//System.out.println("Aquiii");
						if(precios.get(j) < p_min ) {
							p_min=precios.get(j);
							m_min=marca.get(j).text();
						}
					}
				}
				precio_min.add(p_min);
				marca_min.add(m_min);
			}
			
			for(int i=0; i<precio_min.size(); i++) {
				System.out.println("Marca " + marca_min.get(i)+ " Dimensión "+capacidades.get(i)+ " Precio "+ Double.toString(precio_min.get(i)) );
			}
			
			*/
			
			/*
			//DIA
			
			Document dia = Jsoup.connect("https://www.dia.es/compra-online/search?text="+nombre+"&x=0&y=0").get();

			//Titulo de la página Web
			String title = dia.title();
			System.out.println("Nombre Supermercado: " + title);
		
			//Array de elementos con todos los precios
			Elements marca_capacidad = dia.select("span.details"); 
			Elements precio = dia.select("p.price"); 
			
			ArrayList<String> marca = new ArrayList();
			ArrayList<String> capacidad = new ArrayList();
			for(int i=0; i<marca_capacidad.size(); i++) {
				String[] aux = obtenerCapacidad(marca_capacidad.get(i).text());
				capacidad.add(aux[aux.length-2]+" "+aux[aux.length-1]);
				String palabra=aux[0];
				palabra+=" ";
				for(int j=1; j<aux.length-2; j++) {
					 palabra+=aux[j];
					 palabra+=" ";
				}
				marca.add(palabra);
			}
			
			for (int i=0; i <capacidad.size(); i++) {
			System.out.println(marca.get(i));
			System.out.println(capacidad.get(i));
			System.out.println(cambiarPuntoComa(eliminarUltimoCaracter(precio.get(i).text())));
			System.out.println(" ");
			}
		
			//Imprimir todos los elementos
			ArrayList<String> capacidades = new ArrayList();
			for (int i=0; i <capacidad.size(); i++) {
				
				if(!capacidades.contains(capacidad.get(i))) {
					capacidades.add(capacidad.get(i));
				}	
			} 
			
			ArrayList<Double> precios = new ArrayList();
			for(int i=0; i<precio.size(); i++) {
				String aux = eliminarUltimoCaracter(precio.get(i).text());
				aux=cambiarPuntoComa(aux);
				precios.add(Double.parseDouble(aux));
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
			
			
			
			
			
			//EORSKI
			Document eroski = Jsoup.connect("https://www.compraonline.grupoeroski.com/es/search/results/?q="+nombre).get();

			//Titulo de la página Web
			String title = eroski.title();
			System.out.println("Nombre Supermercado: " + title);
		     
			//Elements de elementos con todos los precios
			Elements marca_capacidad = eroski.select("h2[role]"); 
			Elements precio = eroski.select("span.price-offer-now"); 
			
			//Array de marca y de capacidad
			ArrayList<String> marca = new ArrayList();
			ArrayList<String> capacidad = new ArrayList();
			for(int i=0; i<marca_capacidad.size(); i++) {
				String[] aux = obtenerCapacidad(marca_capacidad.get(i).text());
				capacidad.add(aux[aux.length-2]+" "+aux[aux.length-1]);
				String palabra=aux[0];
				palabra+=" ";
				for(int j=1; j<aux.length-2; j++) {
					 palabra+=aux[j];
					 palabra+=" ";
				}
				marca.add(palabra);
			}
			
			//Array de precios
			ArrayList<Double> precios = new ArrayList();
			for(int i=0; i<precio.size(); i++) {
				String aux = eliminarUltimoCaracter(precio.get(i).text());
				aux=cambiarPuntoComa(aux);
				precios.add(Double.parseDouble(aux));
			}
		
			//Imprimir todos los elementos
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
			
			*/
			
			
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}


	public static void print(String string) {
		System.out.println(string);
	}
}
