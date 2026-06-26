package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
	private String nombre;
	private Integer codigo;
	private String descripcion;
	private Tipo tipo;
	private List<Categoria> categorias;
	private Prestamo prestamo;
	
	public Item(String nombre,Integer codigo,String descripcion,Tipo tipo) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.categorias = new ArrayList<Categoria>();
		this.prestamo = null;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void agregarCategoria(Categoria categoria) {
	    categorias.add(categoria);
	}

	public void eliminarCategoria(Categoria categoria) {
	    categorias.remove(categoria);
	}
	
	public Boolean itemDisponible() {
		return prestamo == null;
		
	}
	
	public Prestamo obtenerPrestamo() {
		return prestamo;
	}
	
	public void agregarAPrestamo(Prestamo prestamo) {
	    this.prestamo = prestamo;
	}

	public void eliminarDePrestamo() {
	    this.prestamo = null;
	}
}
