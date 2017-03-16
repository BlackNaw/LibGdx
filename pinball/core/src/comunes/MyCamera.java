package comunes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyCamera extends OrthographicCamera {
	public Viewport viewport;

	public MyCamera() {
		super(Gdx.graphics.getWidth() * Constantes.FactorZoomCamara,
				Gdx.graphics.getHeight() * Constantes.FactorZoomCamara);
		viewport = new ScreenViewport(this);
	}

	@Override
	public void update() {
		super.update();
	}

	public void draw(SpriteBatch batch) {
		batch.setProjectionMatrix(this.combined);
	}

}
