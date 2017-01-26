package com.juangra.asteroids;

import static comun.Constantes.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import comun.GifDecoder;
import comun.Posicion;
import comun.Puntuacion;
import comun.Rectangulo;
import comun.Sondeo;
import elementos.Actor;
import elementos.Enemigos;
import elementos.AtSt;
import elementos.GestorEnemigos;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Actor nave;
	GestorEnemigos enemigos=new GestorEnemigos();
	AtSt at;
	Rectangulo pantalla;
	Animation<?> fondo, atAtD, atAtI;
	Texture perdido;
	float frameCounter;
	Music musicaFondo;
	boolean gameOver;
	Puntuacion puntuacion;

	@Override
	public void create() {
		batch = new SpriteBatch();
		nave = new Actor(new Posicion(Gdx.graphics.getWidth() / 3, 0), new Texture(Gdx.files.internal("halcon.png")));
		enemigos.generarEnemigos(NUMERO_ASTEROIDES, "tie.png");
		enemigos.anadirEnemigos(2);
		at = new AtSt();
		puntuacion=new Puntuacion("arcade.fnt");
		pantalla = new Rectangulo(new Posicion(0, 0), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fondo = GifDecoder.loadGIF("estrellas.gif");
		perdido = new Texture("gameover.png");
		iniciarMusica();
	}

	@Override
	public void render() {
		if (nave.alive) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			//LOGICA
			nave.direccionActual = (Sondeo.detectar(nave.direccionActual));
			if(Puntuacion.puntuaciones!=0&&(Puntuacion.puntuaciones%500==0)&&(Puntuacion.nivel)){
				enemigos.anadirEnemigos(2);
				Puntuacion.nivel=false;
			}
			if (!nave.enLimitesPantalla(pantalla))
				nave.direccionActual = nave.direccionActual.getContradireccion();
			nave.mover(1);
			at.mover(1);
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
				nave.disparar(true);
			}
			for (Enemigos asteroide : enemigos.obtenerNaves()) {
				if (nave.comprobarColision(asteroide.cuerpo)||nave.comprobarColision(asteroide.disparo.cuerpo)) {
					nave.explotar(null);
				}
				
				asteroide.actualizar(null);
				
			}
			if(at.comprobarColision(nave.cuerpo)){
				nave.explotar(null);
			}
			//PINTAR
			frameCounter += Gdx.graphics.getDeltaTime();
			batch.begin();
			batch.draw((TextureRegion) fondo.getKeyFrame(frameCounter, true), 0, 0,
					((TextureRegion) fondo.getKeyFrame(1f)).getRegionWidth() / 2,
					((TextureRegion) fondo.getKeyFrame(1f)).getRegionHeight() / 2, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight() + 200, 1f, 1f, -90f);
			if(nave.disparo.disparado){
				nave.disparo.mover(FACTOR_VELOCIDAD_DISPAROS_HALCON);
				for (Enemigos asteroide : enemigos.obtenerNaves()) {
					if(nave.disparo.comprobarColision(asteroide.cuerpo)){
						asteroide.explotar(batch);
					}
					
				}
			}
			
			puntuacion.pintar(batch, 0);
			nave.disparo.pintar(batch,0);
			nave.pintar(batch,0);
			at.pintar(batch,frameCounter);
			enemigos.pintarTodo(batch,frameCounter);
			batch.end();
		} else {
			//FIN JUEGO
			finJuego();
		}
		

	}

	@Override
	public void dispose() {
		batch.dispose();
		nave.dispose();
		nave.disparo.dispose();
		at.dispose();
		SONIDO_MOVIMIENTO.dispose();
		SONIDO_DISPARO.dispose();
		SONIDO_GAMEOVER.dispose();
		SONIDO_EXPLOSION.dispose();
		musicaFondo.dispose();
		enemigos.disposeTodo();
		perdido.dispose();
		puntuacion.dispose();

	}
	
	
	private void finJuego() {
		try {
			if(Puntuacion.puntuaciones>= Puntuacion.highScore)
			puntuacion.guardarArchivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		batch.begin();
		batch.draw(perdido, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (gameOver) {
			musicaFondo.stop();
			SONIDO_GAMEOVER.play();
			gameOver = false;
		}
		batch.end();
		if(Gdx.input.isKeyPressed(Keys.ENTER)){
			reiniciarJuego();
		}
		
	}

	private void reiniciarJuego() {
		
		nave.alive=true;
		enemigos.reiniciarTodo();
		iniciarMusica();
		at.reiniciar();
		nave.reiniciar();
		nave.disparo.reiniciar();
		Puntuacion.puntuaciones=0;
		enemigos.reiniciar();
		
	}
	
	
	private void iniciarMusica(){
		SONIDO_GAMEOVER.stop();
		SONIDO_EXPLOSION.stop();
		musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("musica/bso.ogg"));
		musicaFondo.setVolume(VOLUMEN*2);
		musicaFondo.setLooping(true);
		musicaFondo.play();
		gameOver = true;
	}
}
