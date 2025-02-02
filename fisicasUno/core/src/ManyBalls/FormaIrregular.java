package ManyBalls;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.physics.box2d.World;

public class FormaIrregular extends ApplicationAdapter {
	private static final float GRAVEDAD_Y = -9.8f;
	public static final int FactorZoomCamara = 2;
	SpriteBatch batch;
	World world;
	Sprite sprite;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	Ground ground;
	// cosas pal avion
	Body avion;
	static float ANCHO_AVION = 0;
	static final float ESCALA = 100f;

	@Override
	public void create() {
		batch = new SpriteBatch();
		//Si quitais el mundo no se pinta el avion
		world = new World(new Vector2(0, GRAVEDAD_Y), true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() * FactorZoomCamara, Gdx.graphics.getHeight()
				* FactorZoomCamara);
		sprite=new Sprite(new Texture(Gdx.files.internal("014.png")));
		ANCHO_AVION=sprite.getWidth();
		createAvion();
	}

	private void createAvion() {
		FileHandle file=Gdx.files.internal("avion.json");		
		String cadena=file.readString();
		BodyEditorLoader loader = new BodyEditorLoader(cadena);
		BodyDef bd = new BodyDef();
		bd.position.set(0, 0);
		bd.type = BodyDef.BodyType.StaticBody;
		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = 3f;
		fd.restitution = 0.0f;
		avion = world.createBody(bd);
		// 4. Create the body fixture automatically by using the loader.
		loader.attachFixture(avion, "avion", fd, ANCHO_AVION/ESCALA);
		
	}

	@Override
	public void render() {
		world.step(1f / 60f, 6, 2);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		debugMatrix = batch.getProjectionMatrix().cpy().scale(Utiles.PIXELS_TO_METERS, Utiles.PIXELS_TO_METERS, 0);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.end();

		debugRenderer.render(world, debugMatrix);

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
