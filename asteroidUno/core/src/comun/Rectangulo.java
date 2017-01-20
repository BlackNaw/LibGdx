package comun;

public class Rectangulo {
	public Posicion posicion;
	public int ancho, alto;
	public int arriba, derecha, izquierda, abajo;

	public Rectangulo(Posicion posicion, int ancho, int alto) {
		super();
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		calculaLados();
	}
	
	public boolean contiene(Rectangulo mayorRectangulo){
		calculaLados();
		if(izquierda<mayorRectangulo.izquierda)
			return false;
		if(derecha>mayorRectangulo.derecha)
			return false;
		if(arriba>mayorRectangulo.arriba)
			return false;
		if(abajo<mayorRectangulo.abajo)
			return false;
		return true;
	}
	
	public boolean solapa(Rectangulo rectangulo){
		calculaLados();
		boolean porIzquierda=izquierda>rectangulo.izquierda&&izquierda<rectangulo.derecha;
		boolean porDerecha=derecha<rectangulo.derecha&&derecha>rectangulo.izquierda;
		boolean porArriba=arriba>rectangulo.abajo&&arriba<rectangulo.arriba;
		boolean porAbajo=abajo<rectangulo.arriba&&abajo>rectangulo.abajo;
		return ((porArriba||porAbajo)&&(porIzquierda||porDerecha));
	}

	public void calculaLados() {
		izquierda=posicion.x;
		abajo=posicion.y;
		arriba=posicion.y+alto;
		derecha=posicion.x+ancho;
	}

//	if(rectanguloA.izquierdaAbajo.x <= rectanguloB.derechaArriba.x &&
//			rectanguloA.derechaArriba.x>= rectanguloB.izquierdaAbajo.x &&
//			rectanguloA.izquierdaAbajo.y<= rectanguloB.derechaArriba.y &&
//			rectanguloA.derechaArriba.y>= rectanguloB.izquierdaAbajo.y)
//		       return true;
//	return false;
	
	@Override
	public String toString() {
		return "izq "+izquierda+" der "+derecha;
	}

}
