package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;

import bodies.MyBody;

public class TableroBehavior extends MyBehavior {

	public TableroBehavior(MyBody myBody) {
		super(myBody);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		myBody.sprite.draw(batch);

	}

}
