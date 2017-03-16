package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import bodies.MyBody;

public class MuroBehaviors extends MyBehavior {

	public boolean impulso = false;
	public boolean desactivar = false;

	public MuroBehaviors(MyBody myBody) {
		super(myBody);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act(float delta) {
		// if(Estados.bolaEnJuego.getEstado()){
		// desactivar=false;
		// }

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (!desactivar) {
			myBody.sprite.draw(batch);
			myBody.body.setActive(true);
		} else {
			myBody.body.setActive(false);
		}

	}

	/**
	 * Si tiene impulso se le aplica el impulso(valga la redundancia) y se
	 * desactiva el muro
	 */
	@Override
	public void chocar(Body bolaBody) {
		super.chocar(bolaBody);
		if (impulso) {
			desactivar = true;
			bolaBody.applyLinearImpulse(new Vector2(0.1f, 0.9f), myBody.body.getWorldCenter(), true);
		}
	}

}
