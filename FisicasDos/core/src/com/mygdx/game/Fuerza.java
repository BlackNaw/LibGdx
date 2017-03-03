package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Fuerza extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Sprite sprite;
	Texture img;
	World world;
	Body body;
	final float PIXELS_TO_METERS = 100f;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	public static final int FactorZoomCamara = 3;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		world = new World(new Vector2(0, 0f), true);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight() / 2 / PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.1f;
		body.createFixture(fixtureDef);
		shape.dispose();
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth()*FactorZoomCamara, Gdx.graphics.getHeight()*FactorZoomCamara);
		// Habilitar el input processor
		Gdx.input.setInputProcessor(this);
		//EStablece una ralentizacion del movimiento rectilineo
//		body.setLinearDamping(4);
		//para probar applyforcetocenter
//		body.setLinearVelocity(0, -2);
		//Para probarRotacion (true deshabilita la rotacion) solo para fuerzas
		body.setFixedRotation(false);
		//Contrarrestar la rotacion
		body.setAngularDamping(4);

	}

	@Override
	public void render() {
		world.step(1f / 60f, 6, 2);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);
		sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth() / 2,
				(body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sprite.setRotation((float)Math.toDegrees(body.getAngle()));
		batch.begin();
		sprite.draw(batch);
		batch.end();
		debugRenderer.render(world, debugMatrix);
	}

	@Override
	public boolean keyUp(int keycode) {
		// un movimiento lineal sin aceleracion
		//Establece la velocidad pero no modifica la que hubiese
		if (keycode == Input.Keys.RIGHT)
			body.setLinearVelocity(2, 0);
		if (keycode==Input.Keys.LEFT)
			body.setLinearVelocity(-1, 0);
		if (keycode==Input.Keys.UP)
			body.applyForceToCenter(0f, 10f,true);
		if(keycode==Input.Keys.SPACE)
		{}
		if (keycode==Input.Keys.NUMPAD_3)
			body.setAngularVelocity(1);
		if(keycode==Input.Keys.NUMPAD_6)
			body.applyTorque(10, true);
		if(keycode==Input.Keys.NUMPAD_4)
			body.applyTorque(-5, true);
		
		//ahora los impulsos
		
		if(keycode==Input.Keys.NUMPAD_7)
			body.applyLinearImpulse(new Vector2(-10, 3), body.getWorldCenter(), true);
		if(keycode==Input.Keys.NUMPAD_8)
			body.applyAngularImpulse(10, true);
		
		//una ultima fuerza para touch
		
		return true;
	}

	@Override
	public void dispose() {
		img.dispose();
		batch.dispose();
		world.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
