package logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Prestamo {
	private LocalDateTime fecha;
	private Usuario usuario;
	private List<Item> items;
	
	
	public Prestamo(LocalDateTime fecha) {
		this.fecha = LocalDateTime.now();
		this.items = new ArrayList<Item>();
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
		i.clear();
	}
	

}