package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.TableroBehavior;
import bodies.ColisionadorIzqBody;
import fixturas.ColisionadorIzqFixture;

public class ColisionadorIzq extends MyActor {

	public ColisionadorIzq(World world, float posX, float posY) {
		super(world, posX, posY);
		myBody = new ColisionadorIzqBody(world, posX, posY);
		myBody.myBehavior = new TableroBehavior(myBody);
		myFixture = new ColisionadorIzqFixture(myBody);
	}

}
