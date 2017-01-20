package com.adorno;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

	// Las fuentes de tipo de letra no se crean aquí. Los recursos solo estna
	// disponibles en el create
	private BitmapFont font;

	// Para cualquier aplicacion gráfica es mucho más costosa pintar 20 imagenes
	// que 1 imagen con las 20 cosas que representan las anteriores 20 imagenes.
	// Por lo tanto la idea es meter todas las imagenes que queremos mostrar en
	// un lote y entonces dibujar el lote
	private SpriteBatch batch;
	private int posicionX=100;

	// Donde se especifica los recursos a utilizar y sus propiedades se ejecuta
	// una vez al inicio
	// No existe el constructor porque este está destinado a cargar todos los
	// elementos necesarios básicos de libgdx estos elementos existiran a
	// disposicion de los elementos del desarrollador en create()
	@Override
	public void create() {
		font = new BitmapFont();
		font.setColor(Color.RED);
		batch = new SpriteBatch();
	}

	// Es un método que va a ser ejecutado tantas veces como sea posible (fps)
	// aquí se calculan y dibujan todas las interacciones del escenario, el
	// munod o el mundo ashleu u la ai
	@Override
	public void render() {
		//Hay que limpiar la pantalla. La biblioteca de libgdx
		//Con esto se limpia con un color
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		//Con esto se limpie con ese color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Dibujemos pues. Begin es el comienzo para describir aquello que se
		// quiere dibujar
		batch.begin();
		font.draw(batch, "Hola mundo", posicionX, 200);
		// Si ya no hay más cosas que dibujar
		batch.end();
		posicionX++;
	}

	// Liberador de recursos, aunque no lo sepamos estamos programando en c++
	// eso quiere decir que el recolector de basura falla aqui y esto nos obliga
	// a liberar lo recursos cada bjeto disposeable tiene un metodo dispose que
	// utilizamos aqui se ejecuta una vez al finall
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
