package com.game.snake;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture cabeza, alimento, perdido;
	DireccionesE direccionActual = DireccionesE.norte;
	float posCabezaX, posCabezaY, posAlimentoX, posAlimentoY;
	float cabezaWhith, cabezaHeigth, alimentoWidth, alimentoHeigth;
	boolean derrota = false;
	TextureRegion regionCabeza;
	float rotacion;

	@Override
	public void create() {
		batch = new SpriteBatch();
		cabeza = new Texture("cabeza.png");
		alimento = new Texture("alimento.png");
		perdido = new Texture("gameover.png");
		cabezaWhith = 50;
		cabezaHeigth = 50;
		alimentoWidth = 50;
		alimentoHeigth = 50;
		posCabezaX = Gdx.graphics.getWidth() / 2 - cabezaWhith / 2;
		posCabezaY = Gdx.graphics.getHeight() / 2 - cabezaHeigth / 2;
		regionCabeza = new TextureRegion(cabeza);
		sortearPosicionAlimento();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (!derrota) {
			batch.draw(regionCabeza, posCabezaX, posCabezaY, cabezaWhith / 2, cabezaHeigth / 2, cabezaWhith, cabezaHeigth,
					1f, 1f, rotacion);
			batch.draw(alimento, posAlimentoX, posAlimentoY, alimentoWidth, alimentoHeigth);
			comprobarPulsado();
			moverSnake();
			if (comprobarColision()) {
				sortearPosicionAlimento();
			}
		}else {
			batch.draw(perdido,0 ,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		}
		
		batch.end();
	}

	private void sortearPosicionAlimento() {
		Random random = new Random();
		posAlimentoX = random.nextInt((int) (Gdx.graphics.getWidth() - alimentoWidth));
		posAlimentoY = random.nextInt((int) (Gdx.graphics.getHeight() - alimentoHeigth));		
	}

	private boolean comprobarColision() {
		if(posCabezaX - 6 <= posAlimentoX + alimentoWidth / 2 &&
				posCabezaX + cabezaWhith / 2 + 6>= posAlimentoX &&
				posCabezaY - 6 <= posAlimentoY + alimentoHeigth / 2 &&
				posCabezaY + cabezaHeigth / 2  + 6>= posAlimentoY)
			       return true;
		return false;
	}

	private boolean comprobarLimite(float posX, float posY) {

		if (posX + cabezaWhith - 6 >= Gdx.graphics.getWidth() || posX + 6 == 0) {
			
			return false;
		}
		if (posY + cabezaHeigth - 6 >= Gdx.graphics.getHeight() || posY + 6 == 0) {
			return false;
		}
		return true;
	}

	private void comprobarPulsado() {
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)
				&& direccionActual != DireccionesE.getContraDireccion(DireccionesE.este)) {
			direccionActual = DireccionesE.oeste;
		} else if (Gdx.input.isKeyJustPressed(Keys.RIGHT)
				&& direccionActual != DireccionesE.getContraDireccion(DireccionesE.oeste)) {
			direccionActual = DireccionesE.este;
		} else if (Gdx.input.isKeyJustPressed(Keys.UP)
				&& direccionActual != DireccionesE.getContraDireccion(DireccionesE.sur)) {
			direccionActual = DireccionesE.norte;
		} else if (Gdx.input.isKeyJustPressed(Keys.DOWN)
				&& direccionActual != DireccionesE.getContraDireccion(DireccionesE.norte)) {
			direccionActual = DireccionesE.sur;
		}
	}

	private void moverSnake() {
		if (comprobarLimite(posCabezaX, posCabezaY)) {
			switch (direccionActual) {
			case norte:
				posCabezaY++;
				rotacion = 0f;
				break;
			case sur:
				posCabezaY--;
				rotacion = 180f;
				break;
			case este:
				posCabezaX++;
				rotacion = -90f;
				break;
			case oeste:
				posCabezaX--;
				rotacion = 90f;
				break;
			}
		}else {
			derrota = true;
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
		cabeza.dispose();
		alimento.dispose();
	}
}
