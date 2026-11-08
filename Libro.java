package co.uptc.edu.modelo;

public class Libro {
	
	private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String categoria;
    private String editorial;
    private int paginas;
    private double precio;
    private int stock;
    private String formato;
    
    	public Libro(String isbn, String titulo, String autor, int anioPublicacion, String categoria, String editorial,
			int paginas, double precio, int stock, String formato) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.anioPublicacion = anioPublicacion;
		this.categoria = categoria;
		this.editorial = editorial;
		this.paginas = paginas;
		this.precio = precio;
		this.stock = stock;
		this.formato = formato;
	}

	public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getAutor() {
			return autor;
		}

		public void setAutor(String autor) {
			this.autor = autor;
		}

		public int getAnioPublicacion() {
			return anioPublicacion;
		}

		public void setAnioPublicacion(int anioPublicacion) {
			this.anioPublicacion = anioPublicacion;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public String getEditorial() {
			return editorial;
		}

		public void setEditorial(String editorial) {
			this.editorial = editorial;
		}

		public int getPaginas() {
			return paginas;
		}

		public void setPaginas(int paginas) {
			this.paginas = paginas;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public String getFormato() {
			return formato;
		}

		public void setFormato(String formato) {
			this.formato = formato;
		}

	public Libro() {
		
	}

}
