package bodies;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;

public class BallBody extends MyBody {

	public BallBody(World world, float posX, float posY) {
		super(world, posX, posY);
		textureMapa = new TextureAtlas(Gdx.files.internal("bola.atlas"));
		texturaRegion = new TextureRegion(textureMapa.findRegion("bola" + getRandomColor()));
		sprite = new Sprite(texturaRegion);
		sprite.setPosition(posX, posY);
		sprite.setSize(20, 20);
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		bodyDef.bullet = true;
		this.body = world.createBody(bodyDef);
		body.setUserData(this);

	}

	public static String getRandomColor() {
		switch (new Random().nextInt(6)) {
		default:
		case 0:
			return "amarilla";
		case 1:
			return "azul";
		case 2:
			return "morada";
		case 3:
			return "naranja";
		case 4:
			return "roja";
		case 5:
			return "verde";
		}
	}
}
