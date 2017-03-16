package comun;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import actores.Torreta;
import util.Constantes;

/**
 * Created by JAVI on 10/03/2017.
 */

public class MyCamera extends OrthographicCamera {
    public Viewport viewport;
    public Torreta actor;
    public double oldAngle = 0, newAngle, diferencia;

    public MyCamera(Torreta actor) {
        this.actor = actor;
        viewport = new ScreenViewport(this);
        //LA ROTACION EN CAMERA VA EN SEXAGESIMALES
        if (!Constantes.ANDROID)
            this.rotate(90);
        else this.rotate(180);
    }

    @Override
    public void update() {
        super.update();
        newAngle = obtenerAngulo(actor.body.getAngle());
        diferencia = Math.abs(newAngle) - Math.abs(oldAngle);
        if (diferencia != 0) {
            this.rotate((float) (diferencia * -(Math.abs(newAngle) / (newAngle))));
            oldAngle = newAngle;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.setProjectionMatrix(this.combined);
    }

    public double obtenerAngulo(float angle) {
        int sentido;
        float angulo = Math.abs(angle);
        sentido = angle != 0 ? (int) (angulo / angle) : 1;
        while (angulo > Math.PI * 2) {
            angulo -= Math.PI * 2;
        }
        return (angulo * sentido * 180 / Math.PI);
    }

}
