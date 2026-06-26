package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tipo implements Serializable {
	private String nombre;
	private List<Item> items;
	
	public Tipo(String nombre) throws Exception {
		if (!esTextoValido(nombre))
            throw new Exception("El nombre del tipo no es valido.");
		this.nombre = nombre;
		this.items = new ArrayList<Item>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws Exception {
		if (!esTextoValido(nombre))
            throw new Exception("El nombre del tipo no es valido.");
		this.nombre = nombre;
	}
	
	public List<Item> getItems() {
	    return items;
	}
	
	public void agregarItemTipo(Item item) {
		items.add(item);
	}
	
	public void borrarItemTipo(Item item) {
	    items.remove(item);
	}
	
	public static boolean esTextoValido(String texto) {
        Pattern p = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
        Matcher m = p.matcher(texto);
        return m.matches();
    }
}
