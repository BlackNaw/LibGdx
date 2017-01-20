package com.adorno;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cuatro extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int posX = 0;
	MyInputProcessor miInput;
	InputMultiplexer multi;
	MyAnotherInputProcessor miOtro;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		// Si ponemos un controlador de eventos debemos quitar el sondeo
		// Asocio el procesador de eventos al mio
		miInput = new MyInputProcessor();
		multi = new InputMultiplexer();
		multi.addProcessor(0,miInput);
		multi.addProcessor(1,new MyAnotherInputProcessor());
		Gdx.input.setInputProcessor(multi);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (!miInput.levantada)
			posX++;
		batch.draw(img, posX, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}