package behaviors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import actores.HUD;
import bodies.BumperABody;
import bodies.MyBody;
import comunes.Constantes;
import interfaces.IObservador;

public class BumperABehavior extends MyBehavior implements IObservador {
	int puntuacion = Constantes.PUNTOS_BUMPERA;
	float vecX, vecY;
	float fuerzaBumperA = Constantes.FUERZA_MAX_BUMPER;
	int nivel = 0;

	public BumperABehavior(MyBody myBody) {
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

	@Override
	public void chocar(Body bolaBody) {

		Thread hilo = new Thread(new Runnable() {

			public void run() {
				((BumperABody) myBody).iluminar(nivel);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				((BumperABody) myBody).cambiarTexture(nivel);
			}
		});
		hilo.start();

		vecX = bolaBody.getLinearVelocity().x * -0.08f;
		vecY = bolaBody.getLinearVelocity().y * -0.08f;

		vecX = (Math.abs(vecX) < fuerzaBumperA) ? vecX : fuerzaBumperA * (Math.abs(vecX) / vecX);
		vecY = (Math.abs(vecY) < fuerzaBumperA) ? vecY : fuerzaBumperA * (Math.abs(vecY) / vecY);

		vecY = (vecY < (-0.4f)) ? -0.4f : vecY;

		vecX = (Math.abs(vecX) < 0.05f) ? vecX = 0.5f : vecX;
		vecY = (Math.abs(vecY) < 0.05f) ? vecY = 0.5f : vecY;

		HUD.puntuacion += puntuacion;

		bolaBody.applyLinearImpulse(new Vector2(vecX, vecY), myBody.body.getWorldCenter(), true);

	}

	@Override
	public void update() {
		puntuacion *= 2;
		if (nivel < 4)
			((BumperABody) myBody).cambiarTexture(++nivel);
	}

	@Override
	public void reiniciar() {
		nivel = 0;
		((BumperABody) myBody).cambiarTexture(nivel);
		puntuacion = Constantes.PUNTOS_BUMPERA;
	}

}
