package com.ivan.fisicas;

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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Fuerzas extends ApplicationAdapter implements InputProcessor {

	SpriteBatch batch;
	Texture img;
	Sprite sprite;

	// Box2d
	World world;
	Body body;

	// 100 pixelos son un metro
	final float PIXELS_TO_METERS = 100f;

	/*
	 * El motor que renderiza la fisica dibujandoo con lineas para que veamos si
	 * coincide el mundo fisixo con las imagenes
	 */

	Box2DDebugRenderer debugRender;
	// Una matriz de 4x4
	Matrix4 debugMatrix;

	// La camara
	OrthographicCamera camera;

	@Override
	public void create() {
		super.create();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		// Se le puede dar posicion
		sprite = new Sprite(img);
		// Ponemos la imagen dentro de la pantalla
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		// definimos el mundo
		world = new World(new Vector2(0, 0), true);

		// vamos a adecuar el body al mundo
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;

		/*
		 * Si queremos que tenga las propiedades reales hay que pasarlo a
		 * metros. El valor es mas pequeño en el mundo que en el sprite
		 */
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((sprite.getWidth() / 2) / PIXELS_TO_METERS, (sprite.getHeight() / 2) / PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);

		// 1m2 tiene 100 gramos de peso
		fixtureDef.density = 0.1f;
		shape.dispose();

		// creando el debug
		debugRender = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		world.step(1f / 60f, 6, 2);
		camera.update();
		/*
		 * Perpara el batch para recibir el debug
		 */
		batch.setProjectionMatrix(camera.combined);
		/*
		 * Estamos escalando el lote de sprites que se usan en la proyeccion al
		 * tamaño de box2d (que funciona en metros)
		 */
		debugMatrix=batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS,0);
		sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS)/*-sprite.getWidth()/2*/,
				(body.getPosition().y * PIXELS_TO_METERS)/*-sprite.getWidth()/2*/);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		/*
		 * Mostrar el debug
		 */
		debugRender.render(world, debugMatrix);
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		img.dispose();
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
