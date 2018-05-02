import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin.Response;

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
				csv.println(name.get(i).text() + "	" + price.get(i).text());
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
		
	    org.jsoup.Connection.Response response = null;
		
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
  
  

  
}