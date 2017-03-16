package fixturas;

import com.badlogic.gdx.physics.box2d.CircleShape;

import bodies.MyBody;
import comunes.Constantes;

public class BumperAFixture extends MyFixture {

	public BumperAFixture(MyBody myBody) {
		super(myBody);
		shape = new CircleShape();
		shape.setRadius(myBody.sprite.getWidth() / 2 / Constantes.PIXELS_TO_METERS);
		fixtureDef.shape = shape;
		myBody.body.createFixture(fixtureDef);

		shape.dispose();
	}

}
