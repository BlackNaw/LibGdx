package com.mygdx.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.scripts.IScript;

public class Base implements IScript {

	Entity entity;
	TransformComponent tc;
	PhysicsBodyComponent pc;
	
	@Override
	public void init(Entity entity) {
		this.entity=entity;
		tc=entity.getComponent(TransformComponent.class);
		pc=entity.getComponent(PhysicsBodyComponent.class);
	}

	@Override
	public void act(float delta) {
		Vector2 position = pc.body.getPosition();
		tc.x++;
		pc.body.setTransform(position, pc.body.getAngle());
	}

	@Override
	public void dispose() {

	}
}
