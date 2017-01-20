package com.javi.asteroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import comun.GifDecoder;
import comun.Posicion;
import comun.Rectangulo;
import comun.Sondeo;
import elementos.Actor;
import elementos.Asteroide;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Actor nave;
	Asteroide asteroide;
	Rectangulo pantalla;
	Animation fondo;
	float frameCounter ;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		nave = new Actor(new Posicion(0, 0), new Texture(Gdx.files.internal("nave.png")));
		asteroide = new Asteroide(new Texture(Gdx.files.internal("asteroide.png")));
		pantalla = new Rectangulo(new Posicion(0, 0), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fondo = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("asteroide.gif").readBytes());
		}

	@Override
	public void render() {
//		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// inputs
		// Sondeo obtiene la direccion pulsada, establece direccion valida la
		// direccion obtenida
		nave.direccionActual = nave.direccionActual.validar(Sondeo.detectar(nave.direccionActual));
		// logica
		if (nave.isViva(pantalla))
			if (nave.actualizar(asteroide.cuerpo)) {
				asteroide.cambiar();
			}
		
		frameCounter = Gdx.graphics.getDeltaTime();
		batch.draw((Texture) fondo.getKeyFrame(frameCounter, true),200, 200);
		// dibujar
		batch.begin();
		nave.pintar(batch);
		asteroide.pintar(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		nave.dispose();
		asteroide.dispose();
	}
}
