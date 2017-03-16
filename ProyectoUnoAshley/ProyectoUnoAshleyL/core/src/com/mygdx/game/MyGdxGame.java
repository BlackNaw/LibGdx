package com.mygdx.game;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.PolygonComponent;
import com.uwsoft.editor.renderer.components.ScriptComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.systems.PhysicsSystem;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class MyGdxGame extends ApplicationAdapter {
	
	SceneLoader sl;
	Viewport viewport;
	Box2DDebugRenderer debugRender;
	OrthographicCamera camera;
//	Matrix4 debugMatrix;
	
	@Override
	public void create () {
		//Zona de creacion de objetos
		camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		sl = new SceneLoader();
		debugRender = new Box2DDebugRenderer();
		
		//Zona de carga
		sl.loadScene("MainScene", viewport);
		//Esot vale para poder obtener los elementos dle juego. El root engloba a todos los de la escena
		ItemWrapper root=new ItemWrapper(sl.getRoot());
		//Aqui obtenemos entity llamado base en overlap y le damos el comportamiento de Player
		ItemWrapper child=root.getChild("base");
		child.addScript(new Base());
		Entity base= child.getEntity();
		//Las entitdades son solo un contenedor de componetes
		ImmutableArray<Component> components = base.getComponents();
//		for (Component component : components) {
//			System.out.println(component.toString());
//		}
		//extraer un componente aislado
		PolygonComponent basepc=base.getComponent(PolygonComponent.class);
		TransformComponent basetc=base.getComponent(TransformComponent.class);
		PhysicsBodyComponent pbc=base.getComponent(PhysicsBodyComponent.class);
		// Ahora vamos a ver los sistemas
		ImmutableArray<EntitySystem> systems = sl.getEngine().getSystems();
//		for (EntitySystem entitySystem : systems) {
//			System.out.println(entitySystem.toString());
//		}
		PhysicsSystem pSystem=sl.getEngine().getSystem(PhysicsSystem.class);
		ScriptComponent sc=base.getComponent(ScriptComponent.class);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		debugRender.render(sl.world, camera.combined.scale(20, 20, 1));
		//actualizamos el engine de ashley
		sl.getEngine().update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		debugRender.dispose();
	}
}
