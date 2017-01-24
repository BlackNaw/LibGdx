package elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import comun.Posicion;
import comun.Rectangulo;
import interfaces.Actualizable;
import interfaces.Cambiable;
import interfaces.Moveable;
import interfaces.Pintable;

public class Disparo extends Elemento implements Actualizable, Cambiable, Moveable, Pintable {

	public Disparo(Posicion posicion, Texture imagen) {
		super(posicion, imagen);
	}

	@Override
	public void pintar(SpriteBatch batch) {
		batch.draw(imagen, posicion.x, posicion.y);
	}

	@Override
	public void mover() {
		posicion.y += 1;

	}

	@Override
	public void cambiar() {
		
	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		mover();
		return false;
	}
	
	

}
