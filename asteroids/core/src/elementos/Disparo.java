package elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import static comun.Constantes.SONIDO_DISPARO;
import static comun.Constantes.SONIDO_EXPLOSION;
import static comun.Constantes.VELOCIDAD_DISPAROS_TIE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import comun.Posicion;
import comun.Rectangulo;
import interfaces.Actualizable;
import interfaces.Cambiable;
import interfaces.Colisionable;
import interfaces.Disparable;
import interfaces.Moveable;
import interfaces.Pintable;
import interfaces.Reiniciable;

public class Disparo extends Elemento implements Actualizable, Moveable, Pintable, Colisionable,Reiniciable {
	public boolean disparado = false;
	public Sound sonido;
	public boolean sonando=true;

	public Disparo(Posicion posicion, Texture imagen,Sound sonido) {
		super(posicion, imagen);
		super.posicion.x -= imagen.getWidth() / 2;
		this.sonido=sonido;
	}

	@Override
	public void pintar(SpriteBatch batch,float f) {
		if (disparado){
			if(sonando){
				sonido.play(0.2f);
				sonando=false;
			}
			batch.draw(imagen, posicion.x, posicion.y);
		}
	}

	@Override
	public void mover(float sentido) {
		posicion.y += VELOCIDAD_DISPAROS_TIE*sentido;
		if (posicion.y + imagen.getHeight() >= Gdx.graphics.getHeight()||posicion.y<= -Gdx.graphics.getHeight()) {
			disparado = false;
			sonando=true;
		}

	}
	


	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		mover(-1);
		return this.cuerpo.solapa(cuerpo);
	}

	public void actualizar(Posicion posicion) {
		this.posicion.x += (this.posicion.x - posicion.x);
		this.posicion.x = Math.abs(posicion.x+imagen.getWidth()+5);
		this.posicion.y += (this.posicion.y - posicion.y);
		this.posicion.y=Math.abs(posicion.y);
	}
	public void actualizar(Posicion posicion, int sentido) {
		this.posicion.x += (this.posicion.x - posicion.x);
		this.posicion.x = Math.abs(posicion.x+imagen.getWidth()+2);
		this.posicion.y += (this.posicion.y - posicion.y);
		this.posicion.y=Math.abs(posicion.y-imagen.getHeight()/2);
	}
	@Override
	public boolean comprobarColision(Rectangulo cuerpo) {
		if( this.cuerpo.solapa(cuerpo)){
			SONIDO_DISPARO.stop();
			SONIDO_EXPLOSION.play(0.7f);
			disparado=false;
			sonando=true;
			return true;
		}
		return false;
	}

	@Override
	public void reiniciar() {
		disparado=false;
		sonando=true;
		posicion.y=-1;
		
	}
	

}
