/*
 * Created by Stephen Pheiffer.
 * Do not edit, distribute, modify or use without his permission.
 */
package com.pheiffware.lib.examples.physics.testScenarios;

import com.pheiffware.lib.examples.physics.TestPhysicsScenario;
import com.pheiffware.lib.geometry.Vec3D;
import com.pheiffware.lib.physics.PhysicsSystem;
import com.pheiffware.lib.physics.entity.DirectionalGravityEntity;
import com.pheiffware.lib.physics.entity.physicalEntity.entities.SphereEntity;
import com.pheiffware.lib.physics.entity.physicalEntity.entities.WallEntity;

/**
 *
 */
public class SingleBallSitGround extends TestPhysicsScenario
{
	public SingleBallSitGround()
	{
		super(1.0f, 1000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see physics.scenario.PhysicsScenario#generatePhysicsSystem()
	 */
	@Override
	public void setup(PhysicsSystem physicsSystem)
	{
		SphereEntity circle1 = new SphereEntity(new Vec3D(250, 450, 0), new Vec3D(0, 0, 0), 25, 0.9f, 50);
		physicsSystem.addEntity(circle1);

		physicsSystem.addEntity(new WallEntity(new Vec3D(0, 500, 0), new Vec3D(500, 500, 0), -1, new Vec3D(0, 0, 0), 1.0f));
		physicsSystem.addEntity(new DirectionalGravityEntity(new Vec3D(0, 500, 0)));
	}
}
