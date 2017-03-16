package bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;

public class HUDBody extends MyBody {

	public HUDBody(World world, float posX, float posY) {
		super(world, posX, posY);
		// texture = new Texture("Imagenes/HUD/efectoMarco.png");
		texture = new Texture("Imagenes/HUD/marco_cadena.png");
		this.font = new BitmapFont(Gdx.files.internal("Fuente/pinballAzul.fnt"));

		sprite = new Sprite(texture);
		sprite.setPosition(posX + 5, posY - 138);
		sprite.setSize(texture.getWidth() / 5 + 56, texture.getHeight() / 5 - 15);

		// sprite.setPosition(posX, posY);
		// sprite.setSize(texture.getWidth()/2-50,texture.getHeight()/2);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
	}

}
