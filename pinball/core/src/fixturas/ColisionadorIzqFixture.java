package fixturas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import bodies.MyBody;
import comunes.BodyEditorLoader;

public class ColisionadorIzqFixture extends MyFixture {

	public ColisionadorIzqFixture(MyBody myBody) {
		super(myBody);
		FileHandle file = Gdx.files.internal("Imagenes/Colisionadores/colisionadorIzq.json");
		String cadena = file.readString();
		BodyEditorLoader loader = new BodyEditorLoader(cadena);
		fixtureDef.density = 1;
		fixtureDef.friction = 3f;
		fixtureDef.restitution = 0.0f;

		loader.attachFixture(myBody.body, "colisionadorIzq", fixtureDef, myBody.sprite.getWidth() / 100);

	}

}
