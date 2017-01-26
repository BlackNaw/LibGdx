package elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import comun.Posicion;
import comun.Rectangulo;
import interfaces.Actualizable;
import interfaces.Cambiable;
import interfaces.Colisionable;
import interfaces.Moveable;
import interfaces.Pintable;
import interfaces.Reiniciable;

public class AtSt extends Elemento implements Cambiable, Pintable, Moveable, Actualizable, Colisionable, Reiniciable {

	int velocidad = 1;
	float frameCounter;
	Animation<?> atAtAnimation[] = new Animation[2];
	TextureAtlas textureAtlas;
	int i = 0;
	boolean bandera = false;

	private static final float FRAME_DURARTION = 0.3f;

	public AtSt() {
		super(new Posicion(), new TextureRegion());
		// atAtAnimation = GifDecoder.loadGIF("at-at-izq.gif");
		textureAtlas = new TextureAtlas(Gdx.files.internal("atat.atlas"));
		atAtAnimation[0]= new Animation<Object>(FRAME_DURARTION, textureAtlas.findRegions("atat_derecha"));
		atAtAnimation[1]= new Animation<Object>(FRAME_DURARTION, textureAtlas.findRegions("atat_izquierda"));
		textureRegion.setRegion(((AtlasRegion) atAtAnimation[0].getKeyFrame(0.1f)).getTexture());
		// Si dejamos el codigo solo de arriba, la imagen nos coge el tamaño del
		// png del atlas, si queremos que nos coja el tamaño de una de las
		// imagenes individuales
		// del atlas.png tendremos que hacer lo siguiente
		// Converimos la imagen en una texture region y le cambiamos su alto y
		// su ancho por los de el fotograma que hay en el 0.1 segundo
		cuerpo.ancho = ((AtlasRegion) atAtAnimation[0].getKeyFrame(0.1f)).originalWidth;
		cuerpo.alto = ((AtlasRegion) atAtAnimation[0].getKeyFrame(0.1f)).originalHeight;
//		Gdx.app.log("Ancho", String.valueOf(imagen.getWidth()));
//		Gdx.app.log("Alto", String.valueOf(imagen.getHeight()));
		cambiar();
	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		mover(1);
		return comprobarColision(cuerpo);
	}

	@Override
	public void mover(float sentido) {
		if (bandera) {
			posicion.x -= sentido;
		}else {
			posicion.x += sentido;
		}
	}

	@Override
	public void pintar(SpriteBatch batch,float f) {
		if (bandera) {
			i = 1;
			if (posicion.x + cuerpo.ancho < 0) {
				posicion.x = -Gdx.graphics.getWidth();
				bandera = false;
			}
			
		}else {
			i = 0;
			if (posicion.x >= Gdx.graphics.getWidth()) {
				posicion.x = Gdx.graphics.getWidth() * 2;
				bandera = true;
			}
			
		}
		
		batch.draw((TextureRegion) atAtAnimation[i].getKeyFrame(f, true), posicion.x, posicion.y);
		//System.out.println("Izquierda: " + cuerpo.izquierda + " Derecha: " + cuerpo.derecha + " Arriba: " + cuerpo.arriba + " Abajo: " + cuerpo.abajo);
	}

	@Override
	public void cambiar() {
		posicion.x = 0;
		posicion.y = 1;
		cuerpo.calculaLados();
	}



	@Override
	public boolean comprobarColision(Rectangulo cuerpo) {
		return this.cuerpo.solapa(cuerpo);
	}

	@Override
	public void reiniciar() {
		cambiar();
		
	}

}
