package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import control.Game;

public class MyGdxGame extends ApplicationAdapter {
	World world;
	Game game;
	SpriteBatch batch;

	@Override
	public void create() {
		batch=new SpriteBatch();
		world = new World(new Vector2(0, 0), true);
		game=new Game(world);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(1f / 60f, 6, 2);

		game.act();
		game.render();


	}

	@Override
	public void dispose() {
		game.dispose();
		world.dispose();
	}
}
