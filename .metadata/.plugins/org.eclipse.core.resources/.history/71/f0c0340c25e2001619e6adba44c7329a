package elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

import comun.Posicion;
import comun.Rectangulo;

public class Elemento implements Disposable {
	public Posicion posicion;
	public Texture imagen;
	public Rectangulo cuerpo;

	public Elemento(Posicion posicion, Texture imagen) {
		super();
		this.posicion = posicion;
		this.imagen = imagen;
		cuerpo = new Rectangulo(this.posicion, imagen.getWidth(), imagen.getHeight());
	}

	@Override
	public void dispose() {
		imagen.dispose();
	}

}
