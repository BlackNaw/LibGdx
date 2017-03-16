package bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

import comunes.Constantes;
import fixturas.ISensor;

public class AgujeroNegroBody extends MyBody implements ISensor {

	public AgujeroNegroBody(World world, float posX, float posY) {
		super(world, posX, posY);
		textureAtlas = new TextureAtlas(Gdx.files.internal("Imagenes/Agujero/agujero.atlas"));
		animacion = new Animation<Object>(1 / 11f, textureAtlas.findRegions("agujero"));
		texturaRegion = new TextureRegion((TextureRegion) animacion.getKeyFrames()[0]);
		sprite = new Sprite((texturaRegion.getTexture()));
		sprite.setPosition(posX, posY);
		sprite.setSize(texturaRegion.getRegionWidth(), texturaRegion.getRegionHeight());
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		body.setUserData(this);
	}

	@Override
	public void sensorPulsado() {

	}

}
