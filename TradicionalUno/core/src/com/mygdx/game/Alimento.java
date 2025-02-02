package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Alimento extends Elemento implements Cambiable,Pintable {

	public Alimento(Texture imagen) {
		super(new Posicion(), imagen);
		// Peligroso
		cambiar();
	}

	@Override
	public void cambiar() {
		posicion.x=sorteo(Gdx.graphics.getWidth()-imagen.getWidth());
		posicion.y=sorteo(Gdx.graphics.getHeight()-imagen.getHeight());
		cuerpo.calculaLados();
	}

	public int sorteo(int max) {
		return new Random().nextInt(max);
	}

	@Override
	public void pinta(SpriteBatch batch) {
		batch.draw(imagen, posicion.x, posicion.y/*, 50, 50*/);
	}
}