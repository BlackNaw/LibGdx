package comun;

public class Rectangulo {
	public Posicion posicion;
	public int ancho, alto;
	public int arriba, derecha, izquierda, abajo;

	public Rectangulo(Posicion posicion, int ancho, int alto) {
		super();
		this.posicion = posicion;
		this.ancho = ancho - 5;
		this.alto = alto - 5;
		calculaLados();
	}

	public boolean contiene(Rectangulo mayorRectangulo) {
		calculaLados();
		if (izquierda < mayorRectangulo.izquierda)
			return false;
		if (derecha > mayorRectangulo.derecha)
			return false;
		if (arriba > mayorRectangulo.arriba)
			return false;
		if (abajo < mayorRectangulo.abajo)
			return false;
		return true;
	}

	public boolean colision(Rectangulo rectangulo) {
		calculaLados();
		boolean porIzquierda = izquierda > rectangulo.izquierda && izquierda < rectangulo.derecha;
		boolean porDerecha = derecha < rectangulo.derecha && derecha > rectangulo.izquierda;
		boolean porArriba = arriba > rectangulo.abajo && arriba < rectangulo.arriba;
		boolean porAbajo = abajo < rectangulo.arriba && abajo > rectangulo.abajo;
		return (((porArriba || porAbajo) && (porIzquierda || porDerecha))||rectangulo.contiene(this));
	}

	private void calculaLados() {
		izquierda = posicion.x;
		abajo = posicion.y;
		arriba = posicion.y + alto;
		derecha = posicion.x + ancho;
	}
}
