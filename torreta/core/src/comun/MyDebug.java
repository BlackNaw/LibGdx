package comun;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import interfaz.IObservador;
import util.Constantes;

/**
 * Created by JAVI on 10/03/2017.
 */

public class MyDebug extends Box2DDebugRenderer implements IObservador {
    private boolean debug=true;

    public MyDebug() {

    }

    public void draw(World world, SpriteBatch batch) {
        if(debug)
            this.render(world, batch.getProjectionMatrix().cpy().scale(Constantes.PIXELS_TO_METERS, Constantes.PIXELS_TO_METERS, 0));

    }

    @Override
    public void update() {
        debug=!debug;
    }
}
