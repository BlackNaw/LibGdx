package fixturas;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

import bodies.MyBody;

public abstract class MyFixture {

	Shape shape;
	FixtureDef fixtureDef = new FixtureDef();

	public MyFixture(MyBody myBody) {
	}

}
