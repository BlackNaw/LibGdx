package control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import comun.*;
import util.Constantes;

/**
 * Created by JAVI on 10/03/2017.
 */

public class Game {
    Stage stage;
    MyCamera mycamera;
    SpriteBatch batch;
    MyDebug myDebug;
    World world;
    GestorActores gestor;
    InputAdapter input;
    Contacto contacto;

    public Game(World world) {
        this.world = world;

        batch = new SpriteBatch();


        myDebug = new MyDebug();

        gestor = new GestorActores(world);
        mycamera = new MyCamera(gestor.obtenerTorreta());
        stage = new Stage(mycamera.viewport, batch);
        gestor.anadirActoresStage(stage);

        ///CAMBIAR A ANDROID//////////
        if(!Constantes.ANDROID) {
         input=new Teclado(gestor.obtenerTorreta());
        ((Teclado)input).addObserver(myDebug);
        }else {
            input = new comun.Movil(gestor.obtenerTorreta());
        }
        /////////////////////////////
        Gdx.input.setInputProcessor(input);
        contacto = new Contacto();
        this.world.setContactListener(contacto);
    }

    public void act() {
        stage.act();

        //Solo con ANDROID
        if(Constantes.ANDROID)
        ((comun.Movil) input).act();
    }

    public void render() {
        mycamera.update();

        stage.draw();
        mycamera.draw(batch);
        myDebug.draw(world, batch);
    }

    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
