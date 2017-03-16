package comun;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MyDebug extends Box2DDebugRenderer {

	public boolean debug=Constantes.STAR_WITH_DEBUG;
	
	public MyDebug() {
	}
	
	public void draw(World world,SpriteBatch batch){
		if(debug)
			//Esto es religion se creee que funcione y se pone
			this.render(world, batch.getProjectionMatrix().cpy().scale(Constantes.PIXELS_TO_METERS, Constantes.PIXELS_TO_METERS, 0));
	}
}