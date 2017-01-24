package elementos;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import comun.Constantes;
import comun.Posicion;
import comun.Rectangulo;
import interfaces.Actualizable;
import interfaces.Cambiable;
import interfaces.Moveable;
import interfaces.Pintable;

public class Asteroide extends Elemento implements Cambiable, Pintable, Moveable, Actualizable {
	int velocidad = 1;

	public Asteroide(Texture imagen) {
		super(new Posicion(), imagen);
		// //Peligroso
		cambiar();
	}

	@Override
	public void cambiar() {
		posicion.x = sorteo(Gdx.graphics.getWidth() - imagen.getWidth());
		posicion.y = Gdx.graphics.getBackBufferHeight() + sorteo(Gdx.graphics.getBackBufferHeight());
		velocidad = 1 + sorteo(Constantes.VELOCIDAD_MAX_ASTEROID);
		cuerpo.calculaLados();
	}

	public boolean enLimite() {
		return posicion.y > 0;
	}

	public int sorteo(int max) {
		return new Random().nextInt(max);
	}

	@Override
	public void pintar(SpriteBatch batch) {
		batch.draw(imagen, posicion.x, posicion.y);

	}

	@Override
	public void mover() {
		posicion.y -= velocidad;
		cuerpo.calculaLados();

	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		if (enLimite())
			mover();
		else {
			cambiar();
		}
		return false;
	}
}
