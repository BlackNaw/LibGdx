package PinBall;


import Collision.MakingACage;
import ManyBalls.Ground;
import ManyBalls.Utiles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LimpiaFlipper extends ApplicationAdapter {
	private static final float GRAVEDAD_Y = -9.8f;
	public static final int FactorZoomCamara =9;
	SpriteBatch batch;
	World world;
	Sprite sprite;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	Ground ground;
	Body body;
	MakingACage caja;
	Stage escenario;
	Viewport viewport;
	Ball ball;

	@Override
	public void create() {
		batch = new SpriteBatch();
		world = new World(new Vector2(0, 0), true);
		ball=new Ball(world);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()
				);
		viewport = new ScreenViewport(camera);
		escenario = new Stage(viewport, batch);
		caja=new MakingACage(world, 0, Gdx.graphics.getWidth()/Utiles.PIXELS_TO_METERS, Gdx.graphics.getHeight()/Utiles.PIXELS_TO_METERS,1);
	}

	@Override
	public void render() {
		world.step(1f / 60f, 6, 2);
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			System.out.println("aqui");
			world.setGravity(new Vector2(0, -10f));
			ball.body.setAwake(true);
		}
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		debugMatrix = batch.getProjectionMatrix().cpy().scale(Utiles.PIXELS_TO_METERS, Utiles.PIXELS_TO_METERS, 0);
		escenario.act();
		ball.update();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		ball.draw(batch);
		
		batch.end();
		escenario.draw();	

		debugRenderer.render(world, debugMatrix);
		

	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
	}

}
