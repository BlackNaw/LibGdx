package bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class TableroBody extends MyBody {

	public TableroBody(World world, float posX, float posY) {
		super(world, posX, posY);
		sprite = new Sprite(new Texture(Gdx.files.internal("tablero.png")));
		sprite.setSize(390, Gdx.graphics.getHeight());
		bodyDef.position.set(0, 0);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		this.body = world.createBody(bodyDef);
		body.setUserData(this);
	}

}
