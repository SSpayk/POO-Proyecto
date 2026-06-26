package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Serializable {
	private String nombre;
	private String numero;
	private String correo;
	private List<Prestamo> prestamos;
	
	public Usuario(String nombre,String numero,String correo) throws Exception {
		if (!esTextoValido(nombre))
			throw new Exception("El nombre no es valido.");
		if (!esNumeroValido(numero))
			throw new Exception("El numero de telefono no es valido.");
		if (!esEmailValido(correo))
			throw new Exception("El correo no es valido.");
		this.nombre = nombre;
		this.numero = numero;
		this.correo = correo;
		this.prestamos = new ArrayList<Prestamo>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws Exception {
		if (!esTextoValido(nombre))
	        throw new Exception("El nombre no es valido.");
		this.nombre = nombre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) throws Exception {
		if (!esNumeroValido(numero))
			throw new Exception("El numero de telefono no es valido.");
		this.numero = numero;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) throws Exception {
		if (!esEmailValido(correo))
			throw new Exception("El email no es valido.");
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
	
	public static boolean esEmailValido(String email) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean esNumeroValido(String numero) {
        Pattern p = Pattern.compile("[0-9-]+");
        Matcher m = p.matcher(numero);
        return m.matches();
    }
    
    public static boolean esTextoValido(String texto) {
        Pattern p = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
        Matcher m = p.matcher(texto);
        return m.matches();
    }
}
