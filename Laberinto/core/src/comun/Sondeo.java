package comun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Sondeo {

	public static void detectar(Actor actor) {
		
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
			//TODO: Disminuir la x 
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			//TODO: Aumentar la y
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)){
			//TODO: Aumentar la y
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)){
			//TDOO: Disminuir la y
		}
	}
}
