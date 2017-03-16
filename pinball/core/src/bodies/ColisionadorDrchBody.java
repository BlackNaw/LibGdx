package bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;

public class ColisionadorDrchBody extends MyBody {

	public ColisionadorDrchBody(World world, float posX, float posY) {
		super(world, posX, posY);
		sprite = new Sprite(new Texture(Gdx.files.internal("Imagenes/Colisionadores/colisionadorDrch.png")));
		sprite.setSize(53, 78);
		sprite.setPosition(posX, posY);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		body.setUserData(this);
	}

}
