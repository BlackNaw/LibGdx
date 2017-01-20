package com.adorno;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render() {
		// Vamos a usar un sistema para detectar eventos es un tanto especial
		// porque es un sondeo o polling te estan tocando
		Gdx.input.isTouched();

		// Para usar ma sde un dedo se usa el pointer Index
		Gdx.input.isTouched(0);

		// obtener las cordenadas de la pulsacion
		Gdx.input.getX();
		Gdx.input.getY();
		// Se puede ajustar para mas de un dedo
		Gdx.input.getX(0);
		// Podemos atrapar el cursor para que nse mueva pero podemos seguir
		// capturando los deltas
		// Gdx.input.setCursorCatched(true);
		// Existe un metodo para comprobarlo
		Gdx.input.isCursorCatched();

		// tambien podemos pedir las variaciones en cualquier coordena
		Gdx.input.getDeltaX();

		// Acaba de ser pulsada
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
			System.out.println("escape");

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)&&Gdx.graphics.getDeltaTime()>1/50f)
			System.out.println("Escape dos");
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
