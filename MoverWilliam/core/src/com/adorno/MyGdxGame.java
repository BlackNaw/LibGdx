package com.adorno;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	float elapsedTime=0;
	Animation animation;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		animation=AnimationE.getAnimation(AnimationE.move);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		elapsedTime+=Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw((TextureRegion)animation.getKeyFrame(elapsedTime,true),0,0);
		batch.end();
//		elapsedTime++;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
