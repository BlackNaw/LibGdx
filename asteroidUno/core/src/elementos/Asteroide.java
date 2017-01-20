package elementos;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import comun.Posicion;
import interfaces.Cambiable;
import interfaces.Pintable;

public class Asteroide extends Elemento implements Cambiable,Pintable{

	public Asteroide(Texture imagen) {
		super(new Posicion(),imagen);
//		//Peligroso
		cambiar();
	}

	@Override
	public void cambiar() {
		posicion.x=sorteo(Gdx.graphics.getWidth()-imagen.getWidth());
		posicion.y=sorteo(Gdx.graphics.getHeight()-imagen.getHeight());
		cuerpo.calculaLados();
	}
	


	public int sorteo(int max){
		return new Random().nextInt(max);
	}

	@Override
	public void pintar(SpriteBatch batch) {
		batch.draw(imagen,posicion.x,posicion.y);
		
	}
}
