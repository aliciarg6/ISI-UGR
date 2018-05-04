package webscrapping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ScrapeWeb {
	

		public static void main(String args[]){
			print("running...");
			Document document;
			try {
				//Obtener HTML
				document = Jsoup.connect("https://www.carrefour.es/supermercado/c?Ntt=agua&sb=true").get();
				Document doc = Jsoup.connect("https://www.elpais.com").get();
				
				
				//Titulo de la página Web
				String title = document.title();
				System.out.println("Titulo: " + title);
			
				//Array de elementos con todos los precios
				Elements prices = document.select("p.price"); 
				Elements names = document.select("div.text-inner"); //Obtener nombre
				
				//Imprimir todos los elementos
				
				for (int i=0; i < names.size(); i++) {
					System.out.print(names.get(i).text() );
					System.out.print(" ");
					//System.out.print(prices.get(i));
					System.out.println(" ");
				}
				

			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		public static void print(String string) {
			System.out.println(string);
		}

	  
	

}


