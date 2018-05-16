import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Bebidas{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int EAN;
	private String nombre;
	private String marca;
	private String url;
	private int tamanio;
	private String unidad;
	private String supermercado;
	
	public Long getId() {
		return id;
	}
	
	public int getEAN() {
		return EAN;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public String getURL() {
		return url;
	}
	
	public int getTamanio() {
		return tamanio;
	}
	
	public String getUnidad() {
		return unidad;
	}
	
	public String getSupermercado() {
		return supermercado;
	}
	
	public int setEAN(int ean) {
		this.EAN=ean;
	}
	
	public String setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public String setMarca(String marca) {
		this.marca=marca;
	}
	
	public String setURL(String url) {
		this.url=url;
	}
	
	public int setTamanio(int tam) {
		this.tamanio=tam;
	}
	
	public String setUnidad(String unidad) {
		this.unidad=unidad;
	}
	
	public String setSupermercado(String supermercado) {
		this.supermercado=supermercado;
	}
	
	
	
	
	
}	