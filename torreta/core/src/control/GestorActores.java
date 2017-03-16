package control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

import actores.Enemigo;
import actores.Torreta;
import util.Constantes;

/**
 * Created by JAVI on 10/03/2017.
 */

public class GestorActores {
    Actor torreta;
    ArrayList<Actor> enemigos=new ArrayList<Actor>();

    public GestorActores(World world) {
        torreta = new Torreta(world, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        for (int i = 0; i < Constantes.NUMERO_INICIAL_ENEMIGOS; i++) {
            enemigos.add(new Enemigo(world));
        }

    }


    public void anadirActoresStage(Stage stage) {
        stage.addActor(torreta);
        for (Actor enemigo: enemigos ) {
            stage.addActor(enemigo);
        }
    }


    public Torreta obtenerTorreta() {
        return (Torreta) torreta;
    }
}
