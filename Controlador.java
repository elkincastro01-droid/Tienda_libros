package co.uptc.edu.Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import co.uptc.edu.Conexion.ConexionBD;
import co.uptc.edu.modelo.Repositorio_Libro;
import co.uptc.edu.modelo.Libro;

public class Controlador implements Repositorio_Libro {

	    // Validaciones
	    public boolean esNumeroEntero(String texto) {
	        return texto != null && texto.matches("\\d+");
	    }

	    public boolean esNumeroDecimal(String texto) {
	        return texto != null && texto.matches("\\d+(\\.\\d+)?");
	    }

	    public boolean esTextoValido(String texto) {
	        return texto != null && texto.matches("[a-zA-Za-áéíóúÁÉÍÓÚñÑ\\s0-9.,:-]+");
	    }

	    @Override
	    public boolean registrarLibro(Libro libro) throws Exception {
	        validarCampos(libro);
	        String sql = "INSERT INTO libro (isbn, titulo, autor, anio_publicacion, categoria, editorial, paginas, precio, porcentaje_iva, stock, formato, imagen) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, libro.getIsbn());
	            ps.setString(2, libro.getTitulo());
	            ps.setString(3, libro.getAutor());
	            ps.setInt(4, libro.getAnioPublicacion());
	            ps.setString(5, libro.getCategoria());
	            ps.setString(6, libro.getEditorial());
	            ps.setInt(7, libro.getPaginas());
	            ps.setDouble(8, libro.getPrecio());
	            ps.setDouble(9, libro.getPorcentajeIva());
	            ps.setInt(10, libro.getStock());
	            ps.setString(11, libro.getFormato());
	            ps.setString(12, libro.getImagen());
	            return ps.executeUpdate() > 0;
	        }
	    }

	    @Override
	    public List<Libro> listarLibros() throws Exception {
	        List<Libro> lista = new ArrayList<>();
	        String sql = "SELECT * FROM libro";
	        try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                lista.add(mapearLibro(rs));
	            }
	        }
	        return lista;
	    }

	    @Override
	    public Libro buscarPorISBN(String isbn) throws Exception {
	        if (isbn == null || isbn.trim().isEmpty()) {
	            return null;
	        }
	        String sql = "SELECT * FROM libro WHERE LOWER(isbn) = LOWER(?)";
	        try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, isbn.trim());
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return mapearLibro(rs);
	                }
	            }
	        }
	        return null;
	    }

	    @Override
	    public List<Libro> buscarPorTitulo(String titulo) throws Exception {
	        List<Libro> resultados = new ArrayList<>();
	        if (titulo == null || titulo.trim().isEmpty()) {
	            return resultados;
	        }
	        String sql = "SELECT * FROM libro WHERE LOWER(titulo) LIKE LOWER(?)";
	        try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, "%" + titulo.trim() + "%");
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    resultados.add(mapearLibro(rs));
	                }
	            }
	        }
	        return resultados;
	    }

	    @Override
	    public boolean actualizarLibro(Libro libro) throws Exception {
	        validarCampos(libro);
	        String sql = "UPDATE libro SET titulo = ?, autor = ?, anio_publicacion = ?, categoria = ?, editorial = ?, "
	                   + "paginas = ?, precio = ?, porcentaje_iva = ?, stock = ?, formato = ?, imagen = ? WHERE isbn = ?";
	        try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, libro.getTitulo());
	            ps.setString(2, libro.getAutor());
	            ps.setInt(3, libro.getAnioPublicacion());
	            ps.setString(4, libro.getCategoria());
	            ps.setString(5, libro.getEditorial());
	            ps.setInt(6, libro.getPaginas());
	            ps.setDouble(7, libro.getPrecio());
	            ps.setDouble(8, libro.getPorcentajeIva());
	            ps.setInt(9, libro.getStock());
	            ps.setString(10, libro.getFormato());
	            ps.setString(11, libro.getImagen());
	            ps.setString(12, libro.getIsbn());
	            return ps.executeUpdate() > 0;
	        }
	    }

	    @Override
	    public boolean eliminarLibro(String isbn) throws Exception {
	        if (tieneVentasAsociadas(isbn)) {
	            throw new IllegalStateException("No se puede eliminar el libro porque tiene ventas asociadas.");
	        }
	        String sql = "DELETE FROM libro WHERE isbn = ?";
	        try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, isbn);
	            return ps.executeUpdate() > 0;
	        }
	    }

	    private boolean tieneVentasAsociadas(String isbn) {
	        String sql = "SELECT COUNT(*) FROM detalle_compra WHERE isbn_libro = ?";
	        try (Connection con = ConexionBD.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, isbn);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        } catch (Exception e) {
	            return false;
	        }
	        return false;
	    }

	    private Libro mapearLibro(ResultSet rs) throws Exception {
	        return new Libro(
	                rs.getInt("id"),
	                rs.getString("isbn"),
	                rs.getString("titulo"),
	                rs.getString("autor"),
	                rs.getInt("anio_publicacion"),
	                rs.getString("categoria"),
	                rs.getString("editorial"),
	                rs.getInt("paginas"),
	                rs.getDouble("precio"),
	                rs.getDouble("porcentaje_iva"),
	                rs.getInt("stock"),
	                rs.getString("formato"),
	                rs.getString("imagen")
	        );
	    }

	    private void validarCampos(Libro libro) {
	        if (libro.getIsbn() == null || libro.getIsbn().trim().isEmpty()) {
	            throw new IllegalArgumentException("El ISBN del libro es obligatorio.");
	        }
	        if (!esTextoValido(libro.getTitulo())) {
	            throw new IllegalArgumentException("El título del libro no es válido.");
	        }
	        if (!esTextoValido(libro.getAutor())) {
	            throw new IllegalArgumentException("El nombre del autor no es válido.");
	        }
	        if (libro.getAnioPublicacion() <= 0) {
	            throw new IllegalArgumentException("El año de publicación debe ser un valor positivo.");
	        }
	        if (libro.getPrecio() <= 0) {
	            throw new IllegalArgumentException("El precio del libro debe ser mayor a 0.");
	        }
	        if (libro.getStock() < 0) {
	            throw new IllegalArgumentException("El stock no puede ser negativo.");
	        }
	    }
	
}
