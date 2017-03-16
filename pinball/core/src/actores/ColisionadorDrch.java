package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.TableroBehavior;
import bodies.ColisionadorDrchBody;
import fixturas.ColisionadorDrchFicture;

public class ColisionadorDrch extends MyActor {

	public ColisionadorDrch(World world, float posX, float posY) {
		super(world, posX, posY);
		myBody = new ColisionadorDrchBody(world, posX, posY);
		myBody.myBehavior = new TableroBehavior(myBody);
		myFixture = new ColisionadorDrchFicture(myBody);
	}

}
