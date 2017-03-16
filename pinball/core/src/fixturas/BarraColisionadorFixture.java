package fixturas;

import static comunes.Constantes.PIXELS_TO_METERS;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import bodies.MyBody;

public class BarraColisionadorFixture extends MyFixture {

	public BarraColisionadorFixture(MyBody myBody, float giro) {
		super(myBody);
		shape = new PolygonShape();
		((PolygonShape) shape).setAsBox(myBody.sprite.getWidth() / 2 / PIXELS_TO_METERS,
				myBody.sprite.getHeight() / 2 / PIXELS_TO_METERS,
				new Vector2((myBody.sprite.getWidth() - 47 * (giro / Math.abs(giro)) / 2) / PIXELS_TO_METERS,
						-5 / PIXELS_TO_METERS),
				(float) (giro * Math.PI) / 180);
		fixtureDef.shape = shape;
		myBody.body.createFixture(fixtureDef);
		myBody.body.setFixedRotation(false);
		shape.dispose();
	}

}
