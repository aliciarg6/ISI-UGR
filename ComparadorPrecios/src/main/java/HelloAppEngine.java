import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {
	
	//CARREFOUR
	ArrayList<String> precio_c = new ArrayList();
	ArrayList<String> marca_c = new ArrayList();
	ArrayList<String> capacidad_c = new ArrayList();
	ArrayList<String> url_c = new ArrayList();
	
	ArrayList<String> capacidades_c = new ArrayList();
	ArrayList<Double> precio_min_c= new ArrayList();
	ArrayList<String> marca_min_c = new ArrayList();
	ArrayList<String> url_min_c = new ArrayList();
	
	//EROSKI
	ArrayList<String> precio_e= new ArrayList();
	ArrayList<String> marca_e= new ArrayList();
	ArrayList<String> capacidad_e= new ArrayList();
	ArrayList<String> url_e = new ArrayList();
	
	ArrayList<String> capacidades_e= new ArrayList();
	ArrayList<Double> precio_min_e= new ArrayList();
	ArrayList<String> marca_min_e= new ArrayList();
	ArrayList<String> url_min_e = new ArrayList();
	
	//DIA
	ArrayList<String> precio_d= new ArrayList();
	ArrayList<String> marca_d = new ArrayList();
	ArrayList<String> capacidad_d = new ArrayList();
	ArrayList<String> url_d = new ArrayList();

	ArrayList<String> capacidades_d = new ArrayList();
	ArrayList<Double> precio_min_d= new ArrayList();
	ArrayList<String> marca_min_d = new ArrayList();
	ArrayList<String> url_min_d = new ArrayList();
	
	static ArrayList<String> capacidades = new ArrayList();
	
	static ArrayList<String> resultado_final= new ArrayList();
	
	String nombre;
	Integer num_dimensiones;
	
	
	

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
      
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    //response.getWriter().print("Hello App Engine!\r\n");
    
    nombre= request.getParameter("producto");
	ejecutar(nombre);
	num_dimensiones = getCantidadDimensiones();
	
	
	request.setAttribute("resultado_final", resultado_final);
	request.getRequestDispatcher("/comparar.jsp").forward(request, response);
	/*
	if(resultado_final.size()>0) {
		request.setAttribute("nombre", nombre);
		request.setAttribute("r1", r1);
		request.setAttribute("num_dimensiones", num_dimensiones);
		request.setAttribute("resultados", resultado_final);
		request.setAttribute("capacidades", capacidades);
		request.getRequestDispatcher("/comparar.jsp").forward(request, response);
		
	}else {
		
	}
    */
  }
  
public String getNombre() {
	String name = nombre;
	return name;
}

public String getResultado(Integer indice) {
	String r = resultado_final.get(indice);
	return r;
}

public String getDimension(Integer indice) {
	String dim=capacidades.get(indice);
	//Array de dimensiones cuando estén hechas las intersercción
	return dim;
}

public Integer getCantidadDimensiones() {
	Integer num=capacidades.size(); // Dimensión del array de dimensiones cuando estén hechas la intersección
	return num;
}

  
//Métodos para realizar el Scraping
	public static String eliminarUltimoCaracter(String precio) {
		
		if (precio != null && precio.length() > 0) {
	        precio = precio.substring(0, precio.length() - 1);
	    }
		
		return precio;
	}
	
public static String eliminarUltimoCaracterPunto(String c) {
		
		if (c != null && c.length() > 0 && c.endsWith(".")) {
	        c = c.substring(0, c.length() - 1);
	    }
		
		return c;
	}
	
	public static String cambiarPuntoComa(String precio) {
		precio = precio.replace(',', '.');
		return precio;
		
	}
	
	public static String[] obtenerCapacidad(String capacidad){
		return capacidad.split(" "); 
	}
	
	public static boolean CompararCapacidades(String d1, String d2) {
		boolean resultado=false;
		String[] aux1=d1.split(" ");
		String [] aux2=d2.split(" ");
	
		
		ArrayList<String> unidades=new ArrayList();
		unidades.add("u");
		unidades.add("ud");
		unidades.add("unid");
		unidades.add("uds");
		
		ArrayList<String> litros=new ArrayList();
		litros.add("l");
		litros.add("lt");
		litros.add("litros");
		litros.add("litro");

		ArrayList<String> gramos=new ArrayList();
		gramos.add("g");
		gramos.add("gr");
		gramos.add("gramos");
		gramos.add("grs");
		


		
		if(aux1[0].equals(aux2[0])) {
			if(unidades.contains(aux1[1]) && unidades.contains(aux2[1])) {
				resultado=true;
			}else if(litros.contains(aux1[1]) && litros.contains(aux2[1])) {
				resultado=true;
			}else if(gramos.contains(aux1[1]) && gramos.contains(aux2[1])) {
				resultado=true;
			}else if(aux1[1].equals(aux2[1])) {
				resultado=true;
			}else {
				resultado=false;
			}
		}else {
			resultado=false;
		}
		
		return resultado;
		
	}
	
	public static int contieneCapacidad(ArrayList<String> capacidad, String c) {
		boolean found=false;
		int index=-1;
		for(int i=0; i<capacidad.size() && !found; i++) {
			if(CompararCapacidades(capacidad.get(i),c)) {
				found=true;
				index=i;
			}
		}
		
		return index;
	}
	
	public static int minValor(double v1, double v2, double v3) {
		double min=v1;
		int i=1;
		if(v2<min) {
			min=v2;
			 i=2;
		}
		if(v3<min) {
			min=v3;
			i=3;
		}
		
		return i;
	}
	
	public static ArrayList<String> mejorPrecio(ArrayList<String> capacidades_c,ArrayList<String> capacidades_e,ArrayList<String> capacidades_d,
			ArrayList<Double> precio_min_c,ArrayList<Double> precio_min_e,ArrayList<Double> precio_min_d,
			ArrayList<String> marca_min_c,ArrayList<String> marca_min_e,ArrayList<String> marca_min_d,
			ArrayList<String> url_min_c,ArrayList<String> url_min_e,ArrayList<String>url_min_d) {
		
		int size1=capacidades_c.size();
		int size2=capacidades_e.size();
		int size3=capacidades_d.size();
		
		int min=size1;
		int aux=1;
		if(size2<min) {
			min=size2;
			aux=2;
		}
		if(size3<min) {
			min=size3;
			aux=3;
			
		}
		
		int index1;
		int index2;
		int index_min;
		String resul=" ";
		
	
		
		switch(aux) {
		case 1:
			for(int i=0; i<capacidades_c.size(); i++) {
				index1=contieneCapacidad( capacidades_e,capacidades_c.get(i));
				index2=contieneCapacidad( capacidades_d,capacidades_c.get(i));
				
				if(index1!=-1 && index2!=-1) {
					index_min=minValor(precio_min_c.get(i),precio_min_e.get(index1),precio_min_d.get(index2));
					switch (index_min) {
					case 1:
						resul="Carrefour: Marca " + marca_min_c.get(i)+ " Dimensión "+capacidades_c.get(i)+ " Precio "+ Double.toString(precio_min_c.get(i)) + " Url "+ url_min_c.get(i);
						capacidades.add(capacidades_c.get(i));
						break;
					case 2:
						resul="Eroski: Marca " + marca_min_e.get(index1)+ " Dimensión "+capacidades_e.get(index1)+ " Precio "+ Double.toString(precio_min_e.get(index1))+" Url "+ url_min_e.get(index1);
						capacidades.add(capacidades_e.get(index1));
						break; 
					case 3:
						resul="Dia: Marca " + marca_min_d.get(index2)+ " Dimensión "+capacidades_d.get(index2)+ " Precio "+ Double.toString(precio_min_d.get(index2))+" Url "+ url_min_d.get(index2);
						capacidades.add(capacidades_d.get(index2));
						break; 
					}
					 
					resultado_final.add(resul);
				}
				
			}

		break;
		case 2:
			
			
			for(int i=0; i<capacidades_e.size(); i++) {
				index1=contieneCapacidad( capacidades_c,capacidades_e.get(i));
				index2=contieneCapacidad( capacidades_d,capacidades_e.get(i));
				if(index1!=-1 && index2!=-1) {
					index_min=minValor(precio_min_c.get(index1),precio_min_e.get(i),precio_min_d.get(index2));
					switch (index_min) {
					case 1:
						resul="Carrefour: Marca " + marca_min_c.get(index1)+ " Dimensión "+capacidades_c.get(index1)+ " Precio "+ Double.toString(precio_min_c.get(index1)) +" Url "+ url_min_c.get(index1);
						capacidades.add(capacidades_c.get(index1));
						break;
					case 2:
						resul="Eroski: Marca " + marca_min_e.get(i)+ " Dimensión "+capacidades_e.get(i)+ " Precio "+ Double.toString(precio_min_e.get(i)) +" Url "+ url_min_e.get(i);
						capacidades.add(capacidades_e.get(i));
						break;
					case 3:
						resul="Dia: Marca " + marca_min_d.get(index2)+ " Dimensión "+capacidades_d.get(index2)+ " Precio "+ Double.toString(precio_min_d.get(index2)) +" Url "+ url_min_d.get(index2);
						capacidades.add(capacidades_d.get(index2));
						break;
					}
					
					resultado_final.add(resul);
					
				}
				
			}
		break;
		case 3:
			
			for(int i=0; i<capacidades_d.size(); i++) {
				index1=contieneCapacidad( capacidades_e,capacidades_d.get(i));
				index2=contieneCapacidad( capacidades_c,capacidades_d.get(i));
				if(index1!=-1 && index2!=-1) {
					index_min=minValor(precio_min_c.get(index2),precio_min_e.get(index1),precio_min_d.get(i));
					switch (index_min) {
					case 1:
						resul="Carrefour: Marca " + marca_min_c.get(index2)+ " Dimensión "+capacidades_c.get(index2)+ " Precio "+ Double.toString(precio_min_c.get(index2))  +" Url "+ url_min_c.get(index2);
						capacidades.add(capacidades_c.get(index2));
						break;
					case 2:
						resul="Eroski: Marca " + marca_min_e.get(index1)+ " Dimensión "+capacidades_e.get(index1)+ " Precio "+ Double.toString(precio_min_e.get(index1))+" Url "+ url_min_e.get(index1);
						capacidades.add(capacidades_e.get(index1));
						break;
					case 3:
						resul="Dia: Marca " + marca_min_d.get(i)+ " Dimensión "+capacidades_d.get(i)+ " Precio "+ Double.toString(precio_min_d.get(i)) +" Url "+ url_min_d.get(i);
						capacidades.add(capacidades_d.get(i));
						break;
					}
					resultado_final.add(resul);
					
			}
			}
		break;
			
		}
		
		return resultado_final;
	}
	
	
	
public static void Scrapeando(ArrayList<String> precio, ArrayList<String> capacidad, ArrayList<String> marca, ArrayList<String> url,ArrayList<String> capacidades, ArrayList<Double> precio_min,
		ArrayList<String> marca_min, ArrayList<String> url_min) {
		
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
			String u_min=" ";
			for(int j=0; j<capacidad.size(); j++) {
				if(capacidades.get(i).equals(capacidad.get(j))) {
					if(precios.get(j) < p_min ) {
						p_min=precios.get(j);
						m_min=marca.get(j);
						u_min=url.get(j);
					}
				}
			}
			precio_min.add(p_min);
			marca_min.add(m_min);
			url_min.add(u_min);
		}
		
		
		
	}

	public static void carrefour(String producto, ArrayList<String> precio, ArrayList<String> marca, ArrayList<String> capacidad, ArrayList<String> url){
		try {
	//CARREFOUR
			Document document = Jsoup.connect("https://www.carrefour.es/supermercado/c?Ntt="+producto+"&sb=true").get();

			//Array de elementos con todos los precios
			Elements capacidadC = document.select("p.name-formato");
			Elements marcaC = document.select("p.name-marca"); 
			Elements precioC = document.select("div.pre-bottom-inner"); 
			Elements urls=document.select("div.image > a[href]");

			 
			for(int i=0; i<capacidadC.size(); i++) {
				//Precio
				String[] aux_array=precioC.get(i).text().split(" ");
				precio.add(aux_array[0]);
				//Capacidad
				if(!capacidadC.get(i).text().isEmpty()) {
					String[] array=capacidadC.get(i).text().split(" ");
					String aux=array[array.length-2]+" "+array[array.length-1];
					aux=cambiarPuntoComa(aux);
					aux=eliminarUltimoCaracterPunto(aux);
					capacidad.add(aux);
				}else {
					capacidad.add(capacidadC.get(i).text());
				}
				marca.add(marcaC.get(i).text());
				String url_aux="https://www.carrefour.es"+urls.get(i).attr("href");
				url.add(url_aux);
			}
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	

	}
	
	public static void eroski(String producto , ArrayList<String> precio, ArrayList<String> marca, ArrayList<String> capacidad, ArrayList<String> url){
	
		try {
		//EORSKI
			Document eroski = Jsoup.connect("https://www.compraonline.grupoeroski.com/es/search/results/?q="+producto).get();
		     
			//Elements de elementos con todos los precios
			Elements marca_capacidad = eroski.select("h2[role]"); 
			Elements precioE = eroski.select("span.price-offer-now"); 
			Elements urls=eroski.select("div.product-description > a[href]");
			
			
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
			for(int i=0; i< capacidad.size();i++) {
				String aux=capacidad.get(i);
				aux=cambiarPuntoComa(aux);		
				capacidad.set(i,aux);
			}
			
			for(int i=0; i<precioE.size(); i++) {
				precio.add(precioE.get(i).text());
				String url_aux="https://www.compraonline.grupoeroski.com"+urls.get(i).attr("href");
				url.add(url_aux);
			}
			
		
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}
	
	public static void  dia(String producto , ArrayList<String> precio, ArrayList<String> marca, ArrayList<String> capacidad, ArrayList<String> url){
		//DIA
		try {
		Document dia = Jsoup.connect("https://www.dia.es/compra-online/search?text="+producto+"&x=0&y=0").get();
		
		//Array de elementos con todos los precios
		Elements marca_capacidad = dia.select("span.details"); 
		Elements precioD = dia.select("p.price"); 
		Elements urls=dia.select("div.prod_grid > a[href]");
		
		
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
		for(int i=0; i< capacidad.size();i++) {
			String aux=capacidad.get(i);
			aux=cambiarPuntoComa(aux);		
			capacidad.set(i,aux);
		}
		
		for(int i=0; i<precioD.size(); i++) {
			precio.add(precioD.get(i).text());
			
			String url_aux="https://www.dia.es/"+urls.get(i).attr("href");
			url.add(url_aux);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	public void ejecutar(String args ){
		
		String producto=args;
		
		 carrefour( producto,  precio_c,  marca_c, capacidad_c, url_c);
		 eroski(producto ,  precio_e, marca_e, capacidad_e, url_e);
		 dia(producto ,  precio_d, marca_d,  capacidad_d, url_d);
		
		
		Scrapeando(precio_c,  capacidad_c,  marca_c,  url_c,capacidades_c,  precio_min_c, marca_min_c,url_min_c); 
		Scrapeando(precio_e,  capacidad_e,  marca_e,  url_e,capacidades_e,  precio_min_e, marca_min_e,url_min_e); 
		Scrapeando(precio_d,  capacidad_d,  marca_d,  url_d,capacidades_d,  precio_min_d, marca_min_d,url_min_d); 
		
		resultado_final=mejorPrecio(capacidades_c, capacidades_e,capacidades_d, precio_min_c, precio_min_e, precio_min_d, marca_min_c, marca_min_e, marca_min_d, url_min_c,url_min_e, url_min_d);
		
		/*
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
		*/
		
	}


	public static void print(String string) {
		System.out.println(string);
	}
}
