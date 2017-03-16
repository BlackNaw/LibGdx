package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;

import bodies.FlipperBodyR;
import bodies.MyBody;
import comunes.BoxBodyBuilder;
import comunes.Constantes;

public class FlipperBehaviorR extends MyBehavior {

	public FlipperBehaviorR(MyBody myBody) {
		super(myBody);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act(float delta) {
		float x = myBody.body.getPosition().x * BoxBodyBuilder.BOX_TO_WORLD;
		float y = myBody.body.getPosition().y * BoxBodyBuilder.BOX_TO_WORLD;
		myBody.sprite.setPosition(x, y);
		myBody.sprite.setRotation((float) Math.toDegrees(((FlipperBodyR) myBody).cosa.getJointAngle()));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		myBody.sprite.draw(batch);
	}

	public void up() {
		((FlipperBodyR) myBody).cosa.setMotorSpeed(Constantes.UP_SPEED);
	}

	public void down() {
		((FlipperBodyR) myBody).cosa.setMotorSpeed(Constantes.DOWN_SPEED);
	}

}
