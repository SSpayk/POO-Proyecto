package logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Prestamo implements Serializable {
	private LocalDateTime fecha;
	private Usuario usuario;
	private List<Item> items;
	private Alerta alerta;
	
	
	public Prestamo(Usuario usuario) {
		this.usuario = usuario;
		this.fecha = LocalDateTime.now();
		this.items = new ArrayList<Item>();
		this.alerta = null;
		
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public List<Item> obtenerItems(){
		return items;
	}
	
	public void agregarItem(Item item) {
		items.add(item);
	}
	
	public void eliminarItem(Item item) {
		items.remove(item);
	}
	
	public void retornarItems() {
		for(Item i: items) {
			i.eliminarDePrestamo();
		}
		items.clear(); 
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Alerta getAlerta() {
		return alerta;
	}
	
	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
		
	}
}