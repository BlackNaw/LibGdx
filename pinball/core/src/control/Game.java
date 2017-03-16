package control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import comunes.MyCamera;
import comunes.Teclado;
import debug.MyDebug;

public class Game implements Disposable {

	Stage stage;
	MyCamera mycamera;
	SpriteBatch batch;
	MyDebug myDebug;
	World world;
	GestorElementos gestor;
	Logica logica;
	Teclado teclado;

	public Game(World world) {
		this.world = world;

		batch = new SpriteBatch();
		mycamera = new MyCamera();
		stage = new Stage(mycamera.viewport, batch);

		myDebug = new MyDebug();

		gestor = new GestorElementos(world);
		gestor.anadirElementosStage(stage);
		teclado = new Teclado(stage);
		logica = new Logica(stage);
		Gdx.input.setInputProcessor(teclado);
		teclado.addObserver(myDebug);
		this.world.setContactListener(logica);

	}

	public void act() {

		logica.act();
		stage.act();

	}

	public void render() {
		// UPDATE
		mycamera.update();

		// DRAWS
		stage.draw();
		mycamera.draw(batch);
		myDebug.draw(world, batch);

	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();

	}

}
