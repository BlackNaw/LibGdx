package elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import comun.Avance;
import comun.Direccion;
import comun.Posicion;
import comun.Rectangulo;
import interfaces.Actualizable;
import interfaces.Colisionable;
import interfaces.Moveable;
import interfaces.Pintable;

public class Actor extends Elemento implements Moveable, Actualizable, Pintable, Colisionable {

	public Direccion direccionActual = Direccion.norte;
	Avance avance;

	public Actor(Posicion posicion, Texture imagen) {
		super(posicion, imagen);
		avance = new Avance(direccionActual);
	}
	
	public boolean isViva(Rectangulo mayor){
		return cuerpo.contiene(mayor);
	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		mover();
		return comprobarColision(cuerpo);
	}

	@Override
	public void mover() {
		avance.direccionar(direccionActual);
		posicion.x += avance.x;
		posicion.y += avance.y;
	}

	@Override
	public void pintar(SpriteBatch batch) {
		batch.draw(imagen, posicion.x, posicion.y);

	}

	@Override
	public boolean comprobarColision(Rectangulo cuerpo) {
		return this.cuerpo.solapa(cuerpo);
	}

}
