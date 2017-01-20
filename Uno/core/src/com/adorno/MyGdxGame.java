package com.adorno;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	// Las imagenes son textures
	Texture img;
	private int altoPantalla, anchoPantalla;
	private int anchoImagen, altoImagen;

	private TextureRegion region;
	private int posX = 1, posY = 1;

	// Se declara la animacion
	Animation animation;
	private static final float FRAME_DURATION = 1 / 30f; // fps
	// Con esto se maneja el atlas
	TextureAtlas textureAtlas;

	// Tiempo que ha pasado
	float elapsedTime = 0;

	@Override
	public void create() {
		batch = new SpriteBatch();
		// Siempre deben usarse pngs
		// las imagenes deben estar en assets de android
		img = new Texture("badlogic.jpg");
		altoPantalla = Gdx.graphics.getHeight();
		anchoPantalla = Gdx.graphics.getWidth();
		anchoImagen = img.getWidth();
		altoImagen = img.getHeight();
		// Cuando dibujamos una region el punto 0,0 de una imagen ( o sea dentro
		// de ella) es la esquina superior izquierda
		region = new TextureRegion(img, 0, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2);
		textureAtlas = new TextureAtlas(Gdx.files.internal("william.atlas"));
		animation = new Animation(FRAME_DURATION, textureAtlas.findRegions("William Attack"));

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Incrementamos en tiempo delta el tiempo elapsed. GetDeltaTime nos
		// dice el tiempo que ha pasado desde la ultima vez que se ejecuto el
		// render
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw(img, anchoPantalla - anchoImagen, altoPantalla - altoImagen);
		// Definimos que parte de la imagen mostramos
		batch.draw(region, 0, 0);
		//Pintamos la animacion en el centro de la pantalla
		batch.draw((TextureRegion)animation.getKeyFrame(elapsedTime,true),anchoPantalla/2,altoPantalla/2);
		region.setRegion(region.getRegionX() + posX, region.getRegionY(), region.getRegionWidth(),
				region.getRegionHeight());
		if (region.getRegionX() > img.getWidth() / 2)
			posX = -1;
		if (region.getRegionX() == 0)
			posX = 1;
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
