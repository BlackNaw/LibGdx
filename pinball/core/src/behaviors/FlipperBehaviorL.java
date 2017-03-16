package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;

import bodies.FlipperBodyL;
import bodies.MyBody;
import comunes.BoxBodyBuilder;
import comunes.Constantes;

public class FlipperBehaviorL extends MyBehavior {

	public FlipperBehaviorL(MyBody myBody) {
		super(myBody);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act(float delta) {
		float x = myBody.body.getPosition().x * BoxBodyBuilder.BOX_TO_WORLD;
		float y = myBody.body.getPosition().y * BoxBodyBuilder.BOX_TO_WORLD;
		myBody.sprite.setPosition(x, y);
		myBody.sprite.setRotation((float) Math.toDegrees(((FlipperBodyL) myBody).cosa.getJointAngle()));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		myBody.sprite.draw(batch);
	}

	public void up() {
		((FlipperBodyL) myBody).cosa.setMotorSpeed(Constantes.UP_SPEED);
	}

	public void down() {
		((FlipperBodyL) myBody).cosa.setMotorSpeed(Constantes.DOWN_SPEED);
	}

}
