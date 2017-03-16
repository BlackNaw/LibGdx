package fixturas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import bodies.MyBody;
import comunes.BodyEditorLoader;

public class TableroFixture extends MyFixture {
	public float ESCALA = 100f;

	public TableroFixture(MyBody myBody) {
		super(myBody);
		FileHandle file = Gdx.files.internal("tablero.json");
		String cadena = file.readString();
		BodyEditorLoader loader = new BodyEditorLoader(cadena);
		// fixtureDef.filter.maskBits=
		// fixtureDef.filter.categoryBits=
		fixtureDef.density = 1;
		fixtureDef.friction = 3f;
		fixtureDef.restitution = 0.0f;

		loader.attachFixture(myBody.body, "tablero", fixtureDef, myBody.sprite.getWidth() / ESCALA);

	}

}
