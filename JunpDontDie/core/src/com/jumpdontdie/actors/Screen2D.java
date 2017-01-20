package com.jumpdontdie.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jumpdontdie.BaseScreen;
import com.jumpdontdie.MainGame;

/**
 * Created by Yo on 04/01/2017.
 */

public class Screen2D extends BaseScreen {

    private ActorJugador jugador;
    private ActorPinchos pinchos;
    private Stage stage;
    private Texture texturaJugador;
    private Texture texturaPinchos;

    public Screen2D(MainGame game) {
        super(game);
        texturaJugador= new Texture("player.png");
        texturaPinchos=new Texture("spike.png");
    }
    @Override
    public void show() {

        stage = new Stage();
        stage.setDebugAll(true);
        jugador =new ActorJugador(texturaJugador);
        pinchos=new ActorPinchos(texturaPinchos);
        stage.addActor(jugador);
        stage.addActor(pinchos);
        jugador.setPosition(20,100);
        pinchos.setPosition(400,100);
    }

    @Override
    public void hide() {
        stage.dispose();
        texturaJugador.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        comprobarColisiones();

        stage.draw();
    }

    private void comprobarColisiones() {
        if(jugador.isAlive()&&jugador.getX()+jugador.getWidth()>pinchos.getX()){
            System.out.println("colision");
            jugador.setAlive(false);
        }

    }

    @Override
    public void dispose() {
        texturaJugador.dispose();
    }
}
