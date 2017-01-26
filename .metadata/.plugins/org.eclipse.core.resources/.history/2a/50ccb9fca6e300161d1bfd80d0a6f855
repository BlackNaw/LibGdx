package comun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import static comun.Constantes.*;

import java.awt.RenderingHints.Key;


public class Sondeo {

	public static Direccion detectar(Direccion actual) {
		
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
			SONIDO_MOVIMIENTO.stop();
			SONIDO_MOVIMIENTO.play(VOLUMEN);
			return Direccion.oeste;	
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			SONIDO_MOVIMIENTO.stop();
			SONIDO_MOVIMIENTO.play(VOLUMEN);
			return Direccion.este;
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)){
			SONIDO_MOVIMIENTO.stop();
			SONIDO_MOVIMIENTO.play(VOLUMEN);
			return Direccion.norte;
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)){
			SONIDO_MOVIMIENTO.stop();
			SONIDO_MOVIMIENTO.play(VOLUMEN);
			return Direccion.sur;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			Gdx.app.exit();
	
		return actual;
	}
}
