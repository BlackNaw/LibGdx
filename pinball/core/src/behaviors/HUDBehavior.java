package behaviors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import actores.HUD;
import bodies.MyBody;

public class HUDBehavior extends MyBehavior {
	Texture textura;
	BitmapFont font2;
	BitmapFont font3;
	Texture imagen;

	public HUDBehavior(MyBody myBody) {
		super(myBody);
		textura = new Texture(Gdx.files.internal("Imagenes/HUD/paredPuntuacion.png"));
		font2 = new BitmapFont(Gdx.files.internal("Fuente/kk.fnt"));
		font3 = new BitmapFont(Gdx.files.internal("Fuente/dd.fnt"));
		imagen = new Texture(Gdx.files.internal("Imagenes/Botones/botonRosa.png"));
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		batch.draw(textura, 390, 0);
		myBody.sprite.draw(batch);
		myBody.font.draw(batch, "Vidas:" + HUD.vidas, myBody.sprite.getX() + 25, myBody.sprite.getY() + 140);
		if (HUD.puntuacion < 100000) {
			myBody.font.draw(batch, "Score: \n" + HUD.puntuacion, myBody.sprite.getX() + 25, myBody.sprite.getY() + 80);
		} else {

			myBody.font.draw(batch, "Score: \n"
					+ String.valueOf(HUD.puntuacion).substring(0, String.valueOf(HUD.puntuacion).length() - 3) + "K",
					myBody.sprite.getX() + 25, myBody.sprite.getY() + 80);
		}
		batch.draw(imagen, 420, 280, 150, 100);
		batch.draw(imagen, 420, 35, 130, 70);
		font2.draw(batch, "Instrucciones:\n Lanzar Bola: Space\n Mover Flipper: Z M\n Reiniciar Juego: R", 420, 365);
		font3.draw(batch, "Juego realizado por:\n Alumnos 2DAM\n IES Arroyo Harnina\n Febrero,2017", 420, 100);

	}

	@Override
	public void reiniciar() {
		HUD.puntuacion = 0;
		HUD.vidas = 3;

	}

}
