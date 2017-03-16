package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;

import bodies.MyBody;

public class RejillaBehaviors extends MyBehavior {

	public RejillaBehaviors(MyBody myBody) {
		super(myBody);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act(float delta) {

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		myBody.sprite.draw(batch);
	}

}
