package co.uptc.edu.modelo;

import java.util.List;

public interface Repositorio_Libro {

	    boolean registrarLibro(Libro libro) throws Exception;
	    List<Libro> listarLibros() throws Exception;
	    Libro buscarPorISBN(String isbn) throws Exception;
	    List<Libro> buscarPorTitulo(String titulo) throws Exception;
	    boolean actualizarLibro(Libro libro) throws Exception;
	    boolean eliminarLibro(String isbn) throws Exception;
	}

