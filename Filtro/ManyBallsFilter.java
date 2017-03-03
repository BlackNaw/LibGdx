package Filtro;

import java.util.ArrayList;

import javafx.collections.SetChangeListener;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ManyBallsFilter extends ApplicationAdapter implements InputProcessor, ContactListener {

	private static final float GRAVEDAD_Y = -9.8f;
	public static final int FactorZoomCamara = 3;
	SpriteBatch batch;
	World world;
	MakingACage cage;
	Stage stage;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	ArrayList<Ball> balls;
	Viewport viewport;

	@Override
	public void create() {
		batch = new SpriteBatch();
		world = new World(new Vector2(0, GRAVEDAD_Y), true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() * FactorZoomCamara, Gdx.graphics.getHeight()
				* FactorZoomCamara);
		balls = new ArrayList<Ball>();
		Gdx.input.setInputProcessor(this);
		for (int i = 0; i < 20; i++) {
			switch (i % 3) {
			default:
			case 0:
				balls.add(new Ball(world, BallType.azul));
				break;
			case 1:
				balls.add(new Ball(world, BallType.roja));
				break;
			case 2:
				balls.add(new Ball(world, BallType.verde));
				break;
			}
		}

		cage = new MakingACage(world, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Utiles.PIXELS_TO_METERS);
		viewport=new ScreenViewport(camera);
		stage=new Stage(viewport, batch);
		for (Ball ball : balls) {
			stage.addActor(ball);
		}
		world.setContactListener(this);
	}

	@Override
	public void render() {
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(Utiles.PIXELS_TO_METERS, Utiles.PIXELS_TO_METERS, 0);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		batch.begin();
		stage.act();
		stage.draw();
//		batch.end();
		debugRenderer.render(world, debugMatrix);

	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return true;
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

	@Override
	public void beginContact(Contact contact) {
//		Vector2 linearVelocity = contact.getFixtureA().getBody().getLinearVelocity();
//		contact.getFixtureA().getBody().setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity());
//		contact.getFixtureB().getBody().setLinearVelocity(linearVelocity);
		contact.getFixtureA().getBody().applyForceToCenter(new Vector2(1, 1), true);
		contact.getFixtureB().getBody().applyForceToCenter(new Vector2(-1, -1), true);

	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}
}
