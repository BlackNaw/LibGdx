package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import actores.HUD;
import bodies.BotonBody;
import bodies.MyBody;
import comunes.Constantes;

public class BotonBehaivor extends MyBehavior {
	float vecX, vecY;
	float fuerzaBumperA = Constantes.FUERZA_MAX_BUMPER - 0.4f;
	public boolean activado = false;

	public BotonBehaivor(MyBody myBody) {
		super(myBody);
	}

	@Override
	public void act(float delta) {

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		myBody.sprite.draw(batch);

	}

	@Override
	public void chocar(Body bolaBody) {
		if (!activado) {
			HUD.puntuacion += 50;
		}

		if (!activado) {
			vecX = bolaBody.getLinearVelocity().x * -0.08f;
			vecY = bolaBody.getLinearVelocity().y * -0.08f;

			vecX = (Math.abs(vecX) < fuerzaBumperA) ? vecX : fuerzaBumperA * (Math.abs(vecX) / vecX);
			vecY = (Math.abs(vecY) < fuerzaBumperA) ? vecY : fuerzaBumperA * (Math.abs(vecY) / vecY);

			if (myBody.sprite.getRotation() == 0) {
				vecX = Math.abs(vecX) - 0.2f;
			}
			bolaBody.applyLinearImpulse(new Vector2(vecX, vecY), myBody.body.getWorldCenter(), true);
		}
		activado = true;
		((BotonBody) myBody).desactivar();

	}

	@Override
	public void reiniciar() {
		activado = false;
		((BotonBody) myBody).activar();

	}

}
