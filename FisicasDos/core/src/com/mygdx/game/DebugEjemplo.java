package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

public class DebugEjemplo extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Sprite sprite;
	Texture img;
	World world;
	Body body;
	// 100 pixeles son un metro
	final float PIXELS_TO_METERS = 100f;

	// El motor que renderiza la fisica dibujandolo con lineas para que veamos
	// si coincide el mundo fisico con las imagenes
	Box2DDebugRenderer debugRenderer;
	// una matriz de 4x4
	Matrix4 debugMatrix;
	// La camara
	OrthographicCamera camera;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		// Un mundo sin gravedad
		world = new World(new Vector2(0, 0f), true);
		BodyDef bodyDef = new BodyDef();
		// Si queremos que tenga las propiedades reales
		// hay que pasarlo a metros El valor es más pequeño en el mundo que en
		// el sprite
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight() / 2 / PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		// 1 m2 tiene 100 gramos de peso
		fixtureDef.density = 0.1f;
		body.createFixture(fixtureDef);
		shape.dispose();
		
		//Creando el debug
		debugRenderer=new Box2DDebugRenderer();
		camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

	}

	@Override
	public void render() {
		world.step(1f / 60f, 6, 2);
		// sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) -
		// sprite.getWidth() / 2,
		// (body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2);
		camera.update();
		//prepara el batch para recibir el debug
		batch.setProjectionMatrix(camera.combined);
		// Estamos escalando el lote de sprites que se usan en la proyeccion al
		// tamaño de box2d (que funciona en metros)
		debugMatrix=batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);
		
		sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth() / 2,
				(body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		//mostrar el debug
		debugRenderer.render(world, debugMatrix);
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
	public boolean keyUp(int keycode) {
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
