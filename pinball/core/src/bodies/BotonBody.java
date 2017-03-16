package bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;

public class BotonBody extends MyBody {
	Texture desactivado;
	Texture activado;

	public BotonBody(World world, float posX, float posY, float giro) {
		super(world, posX, posY);
		activado = new Texture(Gdx.files.internal("Imagenes/Botones/botonVerde.png"));
		desactivado = new Texture(Gdx.files.internal("Imagenes/Botones/botonRojo.png"));
		texture = activado;
		sprite = new Sprite(texture);
		sprite.setPosition(posX, posY);
		sprite.setSize(3, 15);
		sprite.setOrigin(((texture.getWidth() / 2) / Constantes.PIXELS_TO_METERS) + 1,
				((texture.getHeight() / 2) / Constantes.PIXELS_TO_METERS) + 8);
		sprite.setRotation(giro);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		body.setUserData(this);
	}

	public void desactivar() {
		sprite.setTexture(desactivado);
	}

	public void activar() {
		sprite.setTexture(activado);

	}

}
