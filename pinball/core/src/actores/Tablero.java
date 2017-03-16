package actores;

import com.badlogic.gdx.physics.box2d.World;

import behaviors.TableroBehavior;
import bodies.TableroBody;
import fixturas.TableroFixture;

public class Tablero extends MyActor {

	public Tablero(World world, float posX, float posY) {
		super(world, posX, posY);
		myBody = new TableroBody(world, posX, posY);
		myBody.myBehavior = new TableroBehavior(myBody);
		myFixture = new TableroFixture(myBody);

	}

}
