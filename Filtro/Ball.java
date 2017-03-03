package Filtro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ball extends Actor implements Cloneable {
	Sprite sprite;
	TextureAtlas textureMapa;
	TextureRegion textura;
	Body body;

	public Ball(World world, BallType type) {
		textureMapa = new TextureAtlas(Gdx.files.internal("bola.atlas"));
		textura = new TextureRegion(textureMapa.findRegion(type.getColor()));
		sprite = new Sprite(textura);
		sprite.setSize(type.getSize(), type.getSize());
		sprite.setPosition(300, 300);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() ) / Utiles.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() ) / Utiles.PIXELS_TO_METERS);
		body = world.createBody(bodyDef);
		CircleShape shape = new CircleShape();
		shape.setRadius(sprite.getWidth()/2 / Utiles.PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.1f;
		fixtureDef.filter.categoryBits = type.getCategoryBits();
		fixtureDef.filter.maskBits = -1;
		body.createFixture(fixtureDef);
		shape.dispose();
		body.setLinearVelocity(1, 1);
		sprite.setOrigin(0, 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			sprite.draw(batch);
	}

	public void dispose() {
		textureMapa.dispose();

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		sprite.setPosition(body.getPosition().x * Utiles.PIXELS_TO_METERS-sprite.getWidth()/2,
				body.getPosition().y* Utiles.PIXELS_TO_METERS-sprite.getHeight()/2);
	}
	
}
