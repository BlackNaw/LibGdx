package actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import bodies.MyBody;
import fixturas.MyFixture;

public abstract class MyActor extends Actor {

	public MyBody myBody;
	public MyFixture myFixture;

	public MyActor(World world, float posX, float posY) {
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		myBody.myBehavior.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		myBody.myBehavior.draw(batch, parentAlpha);
	}

	public void removeActor() {
		this.addAction(Actions.removeActor(this));
	}

}
