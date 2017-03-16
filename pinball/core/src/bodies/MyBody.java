package bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import behaviors.MyBehavior;

public abstract class MyBody {
	public Sprite sprite;
	TextureAtlas textureMapa;
	TextureRegion texturaRegion;
	TextureAtlas textureAtlas;
	public Animation<?> animacion;
	BodyDef bodyDef = new BodyDef();
	public Body body;
	public MyBehavior myBehavior;
	public BitmapFont font;
	public Texture texture;

	public MyBody(World world, float posX, float posY) {
	}

}