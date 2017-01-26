package elementos;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import comun.Constantes;
import comun.Posicion;
import comun.Rectangulo;
import interfaces.Actualizable;
import interfaces.Cambiable;
import interfaces.Disparable;
import interfaces.Explotable;
import interfaces.Moveable;
import interfaces.Pintable;
import interfaces.Reiniciable;

public  abstract class Enemigos extends Elemento
		implements Cambiable, Pintable, Moveable, Actualizable, Reiniciable, Disparable, Explotable {
	int velocidad = 1;
	public Disparo disparo;
	int indice = 0;
	protected boolean viva = true;
	Animation<?> explosion;
	TextureAtlas textureAtlas;
	int posicionExploX, posicionExploY;
	float aux;

	public Enemigos(Texture imagen,String explosion) {
		super(new Posicion(), imagen);
		this.disparo = new Disparo(new Posicion(posicion.x + imagen.getWidth() / 2, posicion.y - imagen.getHeight()),
				new Texture("disparoTie1.png"), Constantes.SONIDO_DISPARO);
		this.textureAtlas = new TextureAtlas(Gdx.files.internal(explosion+".atlas"));
		this.explosion = new Animation<>(0.1f, textureAtlas.findRegions("explosion"));
		cambiar();
	}

	@Override
	public void cambiar() {
		this.posicion.x = sorteo(Gdx.graphics.getWidth() - imagen.getWidth());
		this.posicion.y = Gdx.graphics.getHeight()+ sorteo(Gdx.graphics.getHeight())+imagen.getHeight();
		this.velocidad = 1 + sorteo(Constantes.VELOCIDAD_MAX_ASTEROID);
		this.cuerpo.calculaLados();
		this.disparo.actualizar(posicion);
	}

	public boolean enLimite() {
		return posicion.y+imagen.getHeight() > 0;
	}

	public int sorteo(int max) {
		return new Random().nextInt(max);
	}

	@Override
	public void pintar(SpriteBatch batch, float f) {
		if (viva) {
			batch.draw(imagen, posicion.x, posicion.y);
		} else {
			if (aux < 4.5f) {
				aux += 0.1f;
				batch.draw((TextureRegion) explosion.getKeyFrame(aux, true),
						posicionExploX - ((TextureRegion) explosion.getKeyFrame(0.1f)).getRegionWidth() / 2+imagen.getWidth()/2,
						posicionExploY - ((TextureRegion) explosion.getKeyFrame(0.1f)).getRegionHeight() / 2);
			}
		}

	}

	@Override
	public void mover(float sentido) {
		this.posicion.y += velocidad * sentido;
		this.cuerpo.calculaLados();
		if (!this.disparo.disparado)
			this.disparo.actualizar(posicion, 1);

	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		if (enLimite()) {
			mover(-1);
			if (this.posicion.y <= Gdx.graphics.getHeight()
					&& this.posicion.y >= (Gdx.graphics.getHeight() * (50 - this.velocidad ) / 100)) {
				this.viva = true;
				disparar(true);
				disparo.mover(-1);
			} else {
				disparar(false);
			}

		} else {
			cambiar();
		}
		return false;
	}

	@Override
	public void reiniciar() {
		aux=5;//Esto lo que hace es que el gif no se reproduzca
		cambiar();
	}

	@Override
	public void disparar(boolean shoot) {
		this.disparo.disparado = shoot;

	}

	@Override
	public void explotar(SpriteBatch batch){};
}
