package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

import bodies.MyBody;
import interfaces.IChocable;
import interfaces.IReiniciable;

public abstract class MyBehavior implements IChocable, IReiniciable {
	MyBody myBody;

	public MyBehavior(MyBody myBody) {
		this.myBody = myBody;
	}

	public abstract void act(float delta);

	public abstract void draw(Batch batch, float parentAlpha);

	@Override
	public void chocar(Body bolaBody) {
		// TODO los hijos sobrescriben al padre

	}

	@Override
	public void finalizarChoque() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reiniciar() {

	}
}
