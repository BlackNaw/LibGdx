package bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;

public class BarraColisionadorBody extends MyBody {

	public BarraColisionadorBody(World world, float posX, float posY, float giro) {
		super(world, posX, posY);
		texture = new Texture(Gdx.files.internal("Imagenes/Botones/botonRojo.png"));
		sprite = new Sprite(texture);
		sprite.setPosition(posX, posY);
		sprite.setSize(2, 85);
		sprite.setOrigin(0, 0);
		sprite.setRotation(giro);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		body.setUserData(this);
	}

}
