package actores;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import behaviors.BallBehavior;
import bodies.BallBody;
import fixturas.BallFixture;
import interfaces.IObservador;

public class Ball extends MyActor implements IObservador {
	Stage stage;
	World world;
	ArrayList<MyActor> e;

	public Ball(World world, float posX, float posY, ArrayList<MyActor> e) {
		super(world, posX, posY);
		this.world = world;
		this.e = e;
		myBody = new BallBody(world, posX, posY);
		myBody.myBehavior = new BallBehavior(myBody, this, world, e);
		myFixture = new BallFixture(myBody);
		e.add(this);
	}

	@Override
	public void update() {
		MyActor bola = new Ball(world, 200, 400, e);
		stage.addActor(bola);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		((BallBehavior) (myBody.myBehavior)).setStage(stage);
	}

}
