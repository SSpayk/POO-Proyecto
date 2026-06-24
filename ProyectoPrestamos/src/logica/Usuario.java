package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
	private String nombre;
	private String numero;
	private String correo;
	private List<Prestamo> prestamos;
	
	public Usuario(String nombre,String numero,String correo) {
		this.nombre = nombre;
		this.numero = numero;
		this.correo = correo;
		this.prestamos = new ArrayList<Prestamo>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public List<Prestamo> getPrestamos(){
		return prestamos;
	}
	
	public void agregarPrestamo(Prestamo prestamo) {
		prestamos.add(prestamo);
	
	}
	
	public void eliminarPrestamo(Prestamo prestamo) {
		prestamos.remove(prestamo);
		
	}
}
