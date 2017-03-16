package bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;

public class SuperBumperBody extends MyBody {
	Texture iluminado;
	Texture off;

	public SuperBumperBody(World world, float posX, float posY) {
		super(world, posX, posY);
		off = new Texture(Gdx.files.internal("Imagenes/Puertas/Blanco/blancoOff.png"));
		texture = new Texture(Gdx.files.internal("Imagenes/Bumpers/Nivel5/escudo.png"));
		iluminado = new Texture(Gdx.files.internal("Imagenes/Bumpers/Nivel5/escudoIluminado.png"));
		sprite = new Sprite(off);
		sprite.setPosition(posX, posY);
		sprite.setSize(115, 115);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		body.setUserData(this);
	}

	public void cambiarTexture() {
		sprite.setTexture(texture);
	}

	public void iluminar() {
		sprite.setTexture(iluminado);
	}

	public void texturaOn() {
		sprite.setTexture(texture);
	}

	public void texturaOff() {
		sprite.setTexture(off);
	}

}
