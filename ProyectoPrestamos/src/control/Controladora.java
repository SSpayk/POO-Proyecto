package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import logica.Alerta;
import logica.Categoria;
import logica.Item;
import logica.Prestamo;
import logica.Tipo;
import logica.Usuario;

public class Controladora implements Serializable {
	private static Controladora instance = null;
	private List<Usuario> usuarios;
	private List<Prestamo> prestamos;
	private List<Item> items;
	private List<Tipo> tipos;
	private List<Categoria> categorias;
	
	public Controladora() {
		usuarios = new ArrayList<Usuario>();
		prestamos = new ArrayList<Prestamo>();
		items = new ArrayList<Item>();
		tipos = new ArrayList<Tipo>();
		categorias = new ArrayList<Categoria>();
		
		try {
			tipos.add(new Tipo("General"));
		} catch (Exception e) {
			
		}
	}
	
	public static Controladora getInstance() {
		if(instance == null)
			instance = new Controladora();
		return instance;
	}
	
	public void crearUsuario(String nombre, String numero, String correo) throws Exception {
		Usuario u = new Usuario(nombre,numero,correo);
		usuarios.add(u);
	}
	
	public void modificarUsuario(Integer indice,String nombre,String numero,String correo) throws Exception {
		Usuario u = usuarios.get(indice);
		u.setNombre(nombre);
		u.setNumero(numero);
		u.setCorreo(correo);
	}
	
	public Boolean borrarUsuario(Integer indice) {
		Usuario u = usuarios.get(indice);
		if(u.getPrestamos().isEmpty()) {
			usuarios.remove(u);
			return true;
		}
		return false;
	}
	
	public Usuario consultarUsuario(Integer indice) {
		return usuarios.get(indice);
	}
	
	public List<Usuario> listaUsuario(){
		return usuarios;
	}
	
	public void crearItem(String nombre,Integer codigo,String descripcion,Integer indiceTipo,List<Categoria> categorias) {
		Tipo t = tipos.get(indiceTipo);
		Item item = new Item(nombre,codigo,descripcion,t);
		t.agregarItemTipo(item);
		for(Categoria c: categorias) {
			c.agregarItemCategoria(item);
			item.agregarCategoria(c);
		}
		items.add(item);
	}
	
	public void modificarItem(Integer indice,String nombre,Integer codigo,String descripcion,Integer indiceTipo,List<Categoria> categorias) {
		Item item = items.get(indice);
	    Tipo tipoAnterior = item.getTipo();
	    tipoAnterior.borrarItemTipo(item);
	    Tipo nuevoTipo = tipos.get(indiceTipo);
	    nuevoTipo.agregarItemTipo(item);
	    item.setNombre(nombre);
	    item.setCodigo(codigo);
	    item.setDescripcion(descripcion);
	    item.setTipo(nuevoTipo);
	    for (Categoria c : item.getCategorias()) {
	        c.borrarItemCategoria(item);
	    }
	    item.getCategorias().clear();
	    for (Categoria c : categorias) {
	        c.agregarItemCategoria(item);
	        item.agregarCategoria(c);
	    }
	}
	
	public boolean borrarItem(Integer indice) {
	    Item item = items.get(indice);
	    if (item.itemDisponible()) {
	        item.getTipo().borrarItemTipo(item);
	        for (Categoria c : item.getCategorias()) {
	            c.borrarItemCategoria(item);
	        }
	        items.remove(item);
	        return true;
	    }
	    return false;
	}
	
	public Item consultarItem(Integer indice) {
	    return items.get(indice);
	}
	
	public List<Item> listaItems(){
		return items;
	}
	
	public void crearCategoria(String nombre) throws Exception {
	    Categoria c = new Categoria(nombre);
	    categorias.add(c);
	}
	
	public void modificarCategoria(Integer indice, String nombre) throws Exception {
	    Categoria c = categorias.get(indice);
	    c.setNombre(nombre);
	}
	
	public void borrarCategoria(Integer indice) {
	    Categoria c = categorias.get(indice);
	    for (Item item : c.getItems()) {
	        item.eliminarCategoria(c);
	    }
	    categorias.remove(c);
	}
	
	public Categoria consultarCategoria(Integer indice) {
	    return categorias.get(indice);
	}
	
	public List<Categoria> listaCategorias(){
		return categorias;
	}
	
	public void crearTipo(String nombre) throws Exception {
	    Tipo t = new Tipo(nombre);
	    tipos.add(t);
	}
	
	public void modificarTipo(Integer indice, String nombre) throws Exception {
	    Tipo t = tipos.get(indice);
	    t.setNombre(nombre);
	}
	
	public boolean borrarTipo(Integer indice) {
	    if (indice == 0) {
	        return false;
	    }
	    Tipo t = tipos.get(indice);
	    Tipo tipoGeneral = tipos.get(0);
	    for (Item item : t.getItems()) {
	        item.setTipo(tipoGeneral);
	        tipoGeneral.agregarItemTipo(item);
	    }
	    tipos.remove(t);
	    return true;
	}
	
	
	public Tipo consultarTipo(Integer indice) {
	    return tipos.get(indice);
	}
	
	public List<Tipo> listaTipos(){
		return tipos;
	}
	
	public List<Prestamo> listaPrestamos() {
	    return prestamos;
	}
	
	public Prestamo hacerPrestamo(Integer indiceUsuario, List<Item> items) {
	    Usuario u = usuarios.get(indiceUsuario);
	    Prestamo p = new Prestamo(u);
	    u.agregarPrestamo(p);
	    for (Item item : items) {
	        p.agregarItem(item);
	        item.agregarAPrestamo(p);
	    }
	    prestamos.add(p);
	    return p;
	}
	
	public void agregarItemPrestamo(Integer indicePrestamo, Item item) {
	    Prestamo p = prestamos.get(indicePrestamo);
	    p.agregarItem(item);
	    item.agregarAPrestamo(p);
	}
	
	public void eliminarItemPrestamo(Integer indicePrestamo, Item item) {
	    Prestamo p = prestamos.get(indicePrestamo);
	    p.eliminarItem(item);
	    item.eliminarDePrestamo();
	}
	
	public void retornarItemPrestamo(Integer indicePrestamo, Item item) {
	    Prestamo p = prestamos.get(indicePrestamo);
	    p.eliminarItem(item);
	    item.eliminarDePrestamo();
	}
	
	public void finalizarPrestamo(Integer indicePrestamo) {
	    Prestamo p = prestamos.get(indicePrestamo);
	    p.retornarItems();
	    p.getUsuario().eliminarPrestamo(p);
	    prestamos.remove(p);
	}
	
	public void crearAlerta(Integer indicePrestamo, LocalDateTime fecha, boolean alarmaRecurrente, Integer repeticiones, Integer intervalo) {
	    Prestamo p = prestamos.get(indicePrestamo);
	    Alerta a = new Alerta(fecha, alarmaRecurrente, repeticiones, intervalo);
	    p.setAlerta(a);
	}
	
	public List<Usuario> reportePorUsuario() {
	    return usuarios;
	}

	public List<Item> reportePorItem() {
	    return items;
	}

	public List<Categoria> reportePorCategoria() {
	    return categorias;
	}

	public List<Tipo> reportePorTipo() {
	    return tipos;
	}
	
	public static void guardarDatos() throws IOException {
		FileOutputStream file = new FileOutputStream("DatosPrestamos.dat");
		ObjectOutputStream stream = new ObjectOutputStream(file);
		stream.writeObject(instance);
		stream.close();
		file.close();
	}
	
	public static void cargarDatos() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("DatosPrestamos.dat");
		ObjectInputStream stream = new ObjectInputStream(file);
		instance = (Controladora) stream.readObject();
		stream.close();
		file.close();
	}

}
