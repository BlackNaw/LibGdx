package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;

import bodies.MyBody;
import comunes.Estados;

public class PuertaBehaior extends MyBehavior {

	public boolean cerrar = false;

	public PuertaBehaior(MyBody myBody) {
		super(myBody);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act(float delta) {
		if (Estados.bolaEnJuego.getEstado()) {
			cerrar = true;
		} else {
			cerrar = false;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (cerrar) {
			myBody.sprite.draw(batch);
			myBody.body.setActive(true);
		} else {
			myBody.body.setActive(false);
		}
	}

}
