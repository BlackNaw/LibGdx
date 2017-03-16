package behaviors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import actores.MyActor;
import bodies.AgujeroNegroBody;
import bodies.MyBody;
import comunes.Constantes;
import comunes.Estados;

public class AgujeroNegroBehaviors extends MyBehavior {

	private float elapsedTime = 0;
	ArrayList<MyActor> actores;
	boolean parado = false;

	public AgujeroNegroBehaviors(MyBody myBody, ArrayList<MyActor> actores) {
		super(myBody);
		this.actores = actores;
	}

	@Override
	public void act(float delta) {
		if (Estados.bolaEnJuego.getEstado())
			for (MyActor myActor : actores) {
				double distanciaObjetos = obtenerDistancia(myBody.body, myActor);
				if (distanciaObjetos <= Constantes.DISTANCIA_PARAR_OBJETO_AGUJERO_NEGRO) {
					paralizarObjeto(myActor, distanciaObjetos);
				}
				if (distanciaObjetos <= (Constantes.DISTANCIA_AGUJERO_NEGRO[0] + 0.15f)) {
					myActor.myBody.body.setGravityScale(0f);
					atraerObjetos(myActor, distanciaObjetos);
				} else {
					myActor.myBody.body.setGravityScale(1f);
				}

			}
	}

	private double obtenerDistancia(Body actorOne, MyActor actorTwo) {
		return Math.sqrt(Math.pow(actorOne.getPosition().x - actorTwo.myBody.body.getPosition().x, 2)
				+ Math.pow(actorOne.getPosition().y - actorTwo.myBody.body.getPosition().y, 2));
	}

	private void paralizarObjeto(MyActor myActor, double distanciaObjetos) {
		if (distanciaObjetos <= (this.myBody.sprite.getHeight())) {
			lanzarObjeto(myActor);
		} else {
			myActor.myBody.sprite.setPosition(
					(myBody.body.getPosition().x - myActor.myBody.body.getPosition().x)
							* Constantes.ATRACCION_AGUJERO_NEGRO[Constantes.ATRACCION_AGUJERO_NEGRO.length],
					(myBody.body.getPosition().y - myActor.myBody.body.getPosition().y)
							* Constantes.DISTANCIA_AGUJERO_NEGRO[Constantes.ATRACCION_AGUJERO_NEGRO.length]);
		}
	}

	private void atraerObjetos(MyActor myActor, double distanciaObjetos) {
		for (int i = Constantes.DISTANCIA_AGUJERO_NEGRO.length - 1; i >= 0; i--) {
			if (distanciaObjetos <= Constantes.DISTANCIA_AGUJERO_NEGRO[i]) {
				myActor.myBody.body.applyForce(
						(myBody.body.getPosition().x - myActor.myBody.body.getPosition().x)
								* Constantes.ATRACCION_AGUJERO_NEGRO[i],
						(float) ((myBody.body.getPosition().y - myActor.myBody.body.getPosition().y)
								* Constantes.DISTANCIA_AGUJERO_NEGRO[i]),
						myActor.myBody.body.getPosition().x, myActor.myBody.body.getPosition().y, true);
				return;
			}
		}
	}

	private void lanzarObjeto(MyActor myActor) {
		float numero = obtenerLanzamientoObjetoX(myActor);
		myActor.myBody.body.applyForceToCenter(numero, Constantes.VELOCIDAD_Y_LANZAMIENTO_OBJETO_AGUJERO_NEGRO, true);
		/*
		 * myActor.myBody.body.applyLinearImpulse( new Vector2(numero,
		 * Constantes.VELOCIDAD_Y_LANZAMIENTO_OBJETO_AGUJERO_NEGRO),
		 * myActor.myBody.body.getWorldCenter(), true);
		 */
	}

	private float obtenerLanzamientoObjetoX(MyActor myActor) {
		float numero = 3.2f;
		if ((myActor.myBody.body.getPosition().x * Constantes.PIXELS_TO_METERS) > (Constantes.ANCHO_PANTALLA / 2)) {
			numero = -numero;
		}
		return numero;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Animation<?> animation = ((AgujeroNegroBody) myBody).animacion;
		float x = myBody.sprite.getX();
		float y = myBody.sprite.getY();
		elapsedTime += Gdx.graphics.getDeltaTime();
		TextureRegion textureRegion = (TextureRegion) animation.getKeyFrame(elapsedTime, true);
		batch.draw(textureRegion, x, y);
	}

}
