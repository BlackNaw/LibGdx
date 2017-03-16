package bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;

public class MuroBody extends MyBody {

	public MuroBody(World world, float posX, float posY, int alto, int ancho, String tipoMuro) {
		super(world, posX, posY);
		texture = new Texture(Gdx.files.internal("Imagenes/Muros/" + tipoMuro));
		sprite = new Sprite(texture);
		sprite.setPosition(posX, posY);
		sprite.setSize(ancho, alto);
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		body.setUserData(this);
	}

}
