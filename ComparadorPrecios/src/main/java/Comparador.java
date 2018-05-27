import java.util.ArrayList;


public class Comparador{
	
	private String nombre="";
	private int num_dimensiones=0;
	private ArrayList<String> resultado_final= new ArrayList();
	private ArrayList<String> capacidades = new ArrayList();
	
	public Comparador() {
		
	}
	
	public Comparador(String producto, Integer num_d, ArrayList<String> resultado, ArrayList<String> cap) {
		
		nombre = producto;
		num_dimensiones = num_d;
		resultado_final = resultado;
		capacidades = cap;
	}
	
	public String getProducto() {
		return nombre;
	}
	
	public int getCantidadDimensiones() {
		return num_dimensiones;
	}
	
	public ArrayList<String> getCapacidades(){
		return capacidades;
	}
	
	public ArrayList<String> getResultados(){
		return resultado_final;
	}
	
	public String getCapacidad(int i) {
		return capacidades.get(i);
	}
	
	public String getResultado(int i) {
		return resultado_final.get(i);
	}
}