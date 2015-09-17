/*
 * Created by Stephen Pheiffer.
 * Do not edit, distribute, modify or use without his permission.
 */
package com.pheiffware.lib.physics.testing.testScenarios;

import com.pheiffware.lib.geometry.Vec3D;
import com.pheiffware.lib.physics.PhysicsSystem;
import com.pheiffware.lib.physics.entity.DirectionalGravityEntity;
import com.pheiffware.lib.physics.entity.rigidBody.PolygonWallEntity;
import com.pheiffware.lib.physics.entity.rigidBody.SphereEntity;
import com.pheiffware.lib.physics.entity.rigidBody.WallEntity;
import com.pheiffware.lib.physics.testing.TestPhysicsScenario;

public class PolygonScenario extends TestPhysicsScenario
{
	public PolygonScenario(double scenarioRuntime, int numSteps)
	{
		super(scenarioRuntime, numSteps);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see physics.scenario.PhysicsScenario#generatePhysicsSystem()
	 */
	@Override
	public void setup(PhysicsSystem physicsSystem)
	{
		SphereEntity circle1 = new SphereEntity(new Vec3D(400, 450, 0), new Vec3D(0, 0, 0), 25, 1.0f, 50);
		physicsSystem.addEntity(circle1);

		// @formatter:off
		Vec3D[] points = new Vec3D[]
		{ new Vec3D(100.5f, 700, 0), new Vec3D(0.5f, 600, 0), new Vec3D(200.5f, 500, 0), new Vec3D(400.5f, 600, 0), new Vec3D(300.5f, 700, 0), };
		// @formatter:on
		PolygonWallEntity polygon = new PolygonWallEntity(new Vec3D(0, 0, 0), 0.7f, points);
		polygon.name = "Polygon1";
		physicsSystem.addEntity(polygon);
		PolygonWallEntity polygon2 = new PolygonWallEntity(new Vec3D(0, 0, 0), 0.7f, points);
		polygon2.name = "Polygon2";
		polygon2.move(new Vec3D(450, 0, 0));
		physicsSystem.addEntity(polygon2);

		physicsSystem.addEntity(new WallEntity(new Vec3D(0, 700, 0), new Vec3D(700, 700, 0), -1, new Vec3D(0, 0, 0), 0.9f));
		physicsSystem.addEntity(new DirectionalGravityEntity(new Vec3D(0, 500, 0)));
	}
}
