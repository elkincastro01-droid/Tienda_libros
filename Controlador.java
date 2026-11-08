package co.uptc.edu.controlador;

import java.util.ArrayList;
import java.util.List;

import co.uptc.edu.modelo.*;

public class Controlador {
	
	    private List<Libro> libros;
	    
	    // Constructor
	    public Controlador() {
	        libros = new ArrayList<>();
	    }

	    public boolean registrarLibro(Libro libro) {

	        if (libro == null) {
	            return false;
	        }

	        // Verificar que el ISBN no esté repetido
	        if (buscarPorISBN(libro.getIsbn()) != null) {
	            return false;
	        }

	        libros.add(libro);

	        return true;
	    }

	    // ==========================================
	    // READ - BUSCAR POR ISBN
	    // ==========================================

	    public Libro buscarPorISBN(String isbn) {

	        if (isbn == null || isbn.trim().isEmpty()) {
	            return null;
	        }

	        for (Libro libro : libros) {

	            if (libro.getIsbn().equalsIgnoreCase(isbn.trim())) {
	                return libro;
	            }
	        }

	        return null;
	    }

	    // ==========================================
	    // READ - BUSCAR POR TITULO
	    // ==========================================

	    public List<Libro> buscarPorTitulo(String titulo) {

	        List<Libro> resultados = new ArrayList<>();

	        if (titulo == null || titulo.trim().isEmpty()) {
	            return resultados;
	        }

	        for (Libro libro : libros) {

	            if (libro.getTitulo()
	                    .toLowerCase()
	                    .contains(titulo.toLowerCase().trim())) {

	                resultados.add(libro);
	            }
	        }

	        return resultados;
	    }

	    // ==========================================
	    // READ - LISTAR TODOS
	    // ==========================================

	    public List<Libro> listarLibros() {

	        return new ArrayList<>(libros);
	    }

	    // ==========================================
	    // UPDATE - ACTUALIZAR
	    // ==========================================

	    public boolean actualizarLibro(Libro libroActualizado) {

	        if (libroActualizado == null) {
	            return false;
	        }

	        Libro libro = buscarPorISBN(
	                libroActualizado.getIsbn()
	        );

	        if (libro == null) {
	            return false;
	        }

	        libro.setTitulo(libroActualizado.getTitulo());
	        libro.setAutor(libroActualizado.getAutor());
	        libro.setAnioPublicacion(
	                libroActualizado.getAnioPublicacion()
	        );
	        libro.setCategoria(
	                libroActualizado.getCategoria()
	        );
	        libro.setEditorial(
	                libroActualizado.getEditorial()
	        );
	        libro.setPaginas(
	                libroActualizado.getPaginas()
	        );
	        libro.setPrecio(
	                libroActualizado.getPrecio()
	        );
	        libro.setStock(
	                libroActualizado.getStock()
	        );
	        libro.setFormato(
	                libroActualizado.getFormato()
	        );

	        return true;
	    }

	    // ==========================================
	    // DELETE - ELIMINAR
	    // ==========================================

	    public boolean eliminarLibro(String isbn) {

	        Libro libro = buscarPorISBN(isbn);

	        if (libro == null) {
	            return false;
	        }

	        return libros.remove(libro);
	    }

}
