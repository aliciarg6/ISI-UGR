import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
      
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    response.getWriter().print("Hello App Engine!\r\n");

  }
  
  
	public static void main(String args[]){
		print("running...");
		Document document;
		try {
			//Obtener HTML
			document = Jsoup.connect("https://www.carrefour.es/supermercado/bebidas/cerveza/N-vyl4ay/c").get();

			String title = document.title(); //Get title
			print("  Title: " + title); //Print title.

			Elements price = document.select(".content-price mobile-only:contains($)"); //Obtener el precio
			Elements name = document.select("name-marca:contains(DenverCO)"); //Obtener nombre
			
			FileOutputStream fout=new FileOutputStream("cerveza.csv");  
			PrintStream csv=new PrintStream(fout);  
			csv.println("name	price	number sold");
			for (int i=0; i < price.size()-2; i++) {
				csv.println(address.get(i).text() + "	" + price.get(i).text());
			}
			fout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void print(String string) {
		System.out.println(string);
	}


  
  
  
  
 
  
  
  /*
   * Con este metodo compruebo el Status code de la respuesta que recibo al hacer la petición
   * */
  public static int getStatusConnectionCode(String url) {
		
	    Response response = null;
		
	    try {
		response = Jsoup.connect("https://www.carrefour.es/supermercado/?DSPLogout=true&_requestid=3734880").userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
	    } catch (IOException ex) {
		System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
	    }
	    return response.statusCode();
	}
  
  /*
   * El metodo devuelve un objeto de la clase Documente con el contenido del HTML  de la web
   */

  
  public static Document getHtmlDocument(String url) {

	    Document doc = null;
		try {
		    doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		    } catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		    }
	    return doc;
	}
  
  
  public class Scraping {
		
	    public static final String url = "https://jarroba.com/";
		
	    public static void main (String args[]) {
			
	        // Compruebo si me da un 200 al hacer la petición
	        if (getStatusConnectionCode(url) == 200) {
				
	            // Obtengo el HTML de la web en un objeto Document
	            Document document = getHtmlDocument(url);
				
	            // Busco todas las entradas que estan dentro de: 
	            Elements entradas = document.select("div.col-md-4.col-xs-12").not("div.col-md-offset-2.col-md-4.col-xs-12");
	            System.out.println("Número de entradas en la página inicial de Jarroba: "+entradas.size()+"\n");
				
	            // Paseo cada una de las entradas
	            for (Element elem : entradas) {
	                String titulo = elem.getElementsByClass("tituloPost").text();
	                String autor = elem.getElementsByClass("autor").toString();
	                String fecha = elem.getElementsByClass("fecha").text();
					
	                System.out.println(titulo+"\n"+autor+"\n"+fecha+"\n\n");
					
	                // Con el método "text()" obtengo el contenido que hay dentro de las etiquetas HTML
	                // Con el método "toString()" obtengo todo el HTML con etiquetas incluidas
	            }
					
	        }else
	            System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(url));
	    }
	}
  
}