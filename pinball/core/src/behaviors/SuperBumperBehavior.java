package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import actores.HUD;
import bodies.MyBody;
import bodies.SuperBumperBody;
import comunes.Constantes;
import interfaces.IObservador;

public class SuperBumperBehavior extends MyBehavior implements IObservador {
	int puntuacion = Constantes.PUNTOS_SUPERBUMPER;
	float vecX, vecY;
	float fuerzaBumperA = Constantes.FUERZA_MAX_BUMPER;
	boolean activo = false;
	boolean fuera = false;

	public SuperBumperBehavior(MyBody myBody) {
		super(myBody);
		myBody.body.setActive(activo);
	}

	@Override
	public void act(float delta) {

		if (activo && !fuera) {
			myBody.body.setAwake(activo);
			myBody.body.setActive(activo);

			Thread bumper = new Thread(new Runnable() {

				public void run() {
					try {
						fuera = true;
						Thread.sleep((Constantes.TIEMPO_SUPERBUMPER * 1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					activo = false;
					fuera = false;
					myBody.body.setAwake(activo);
					myBody.body.setActive(activo);
					((SuperBumperBody) myBody).texturaOff();
				}
			});
			bumper.start();
		}

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		myBody.sprite.draw(batch);

	}

	@Override
	public void chocar(Body bolaBody) {

		Thread hilo = new Thread(new Runnable() {

			public void run() {
				((SuperBumperBody) myBody).iluminar();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				((SuperBumperBody) myBody).cambiarTexture();
			}
		});
		hilo.start();

		vecX = bolaBody.getLinearVelocity().x * -0.08f;
		vecY = bolaBody.getLinearVelocity().y * -0.08f;

		vecX = (Math.abs(vecX) < fuerzaBumperA) ? vecX : fuerzaBumperA * (Math.abs(vecX) / vecX);
		vecY = (Math.abs(vecY) < fuerzaBumperA) ? vecY : fuerzaBumperA * (Math.abs(vecY) / vecY);

		vecY = (vecY < (-0.4f)) ? -0.4f : vecY;

		HUD.puntuacion += puntuacion;

		bolaBody.applyLinearImpulse(new Vector2(vecX, vecY), myBody.body.getWorldCenter(), true);

	}

	@Override
	public void update() {
		activo = true;
		((SuperBumperBody) myBody).texturaOn();

	}

	@Override
	public void reiniciar() {
		activo = false;
	}

}
