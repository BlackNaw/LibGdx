package elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Interfaces.Actualizable;
import Interfaces.Colisionable;
import Interfaces.Movible;
import Interfaces.Pintable;
import comun.Posicion;
import comun.Rectangulo;

public class Actor extends Elemento implements Movible, Actualizable, Pintable, Colisionable {

	public Actor(Posicion posicion, Texture imagen) {
		super(posicion, imagen);
	}
	
	public boolean enLimitesPantalla(Rectangulo mayor){
		return cuerpo.contiene(mayor);
	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		mover(1);
		
		return comprobarColision(cuerpo);
	}

	@Override
	public void mover(float sentido) {

	}

	@Override
	public void pintar(SpriteBatch batch,float f) {
		batch.draw(imagen, posicion.x, posicion.y);

	}

	@Override
	public boolean comprobarColision(Rectangulo cuerpo) {
		return this.cuerpo.colision(cuerpo);
	}

}
