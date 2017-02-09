package com.ivan;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends InputAdapter {
	private static final int MORTAL_BOUND_Y = 1500;
	Sprite sprite;
	TextureAtlas textureMapa;
	TextureRegion textura;
	Body body;
	boolean dead = false;
	float posicionXDown, posicionYDown;
	float posicionXUp, posicionYUp;
	float fuerzaX, fuerzaY;
	BodyDef bodyDef;
	float limiteVelocidad = 8;

	public Ball(World world) {
		textureMapa = new TextureAtlas(Gdx.files.internal("bola.atlas"));
		textura = new TextureRegion(textureMapa.findRegion("bola" + getRandomColor()));
		sprite = new Sprite(textura);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(-250 / Utiles.PIXELS_TO_METERS, -100 / Utiles.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		CircleShape shape = new CircleShape();
		shape.setRadius(sprite.getWidth() / 2 / Utiles.PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		shape.dispose();
		body.setFixedRotation(false);

	}

	public boolean isDead() {
		return dead;
	}

	public void update() {
		sprite.setPosition((body.getPosition().x * Utiles.PIXELS_TO_METERS) - sprite.getWidth() / 2,
				(body.getPosition().y * Utiles.PIXELS_TO_METERS) - sprite.getHeight() / 2);
		if (sprite.getY() < -MORTAL_BOUND_Y)
			dead = true;
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

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		posicionXDown = screenX;
		posicionYDown = screenY;
		// System.out.println("pulso");
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (body.getType() == BodyType.StaticBody)
			body.setType(BodyType.DynamicBody);

		posicionXUp = screenX;
		posicionYUp = screenY;
		fuerzaX = (float) ((posicionXDown - posicionXUp) /Utiles.PIXELS_TO_METERS*3);
		fuerzaY = -(float) ((posicionYDown - posicionYUp) /Utiles.PIXELS_TO_METERS*3);

		fuerzaX = (Math.abs(fuerzaX) < limiteVelocidad) ? fuerzaX : limiteVelocidad * (Math.abs(fuerzaX) / fuerzaX);
		fuerzaY = (Math.abs(fuerzaY) < limiteVelocidad) ? fuerzaY : limiteVelocidad * (Math.abs(fuerzaY) / fuerzaY);
		// System.out.println(fuerzaX+" "+fuerzaY);
		body.applyLinearImpulse(new Vector2(fuerzaX, fuerzaY), body.getWorldCenter(), true);

		return true;
	}

}
