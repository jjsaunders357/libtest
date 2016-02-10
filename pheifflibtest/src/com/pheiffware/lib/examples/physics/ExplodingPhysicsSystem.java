package com.pheiffware.lib.examples.physics;

import com.pheiffware.lib.geometry.Vec3D;
import com.pheiffware.lib.physics.PhysicsSystem;
import com.pheiffware.lib.physics.entity.physicalEntity.PhysicalEntity;
import com.pheiffware.lib.physics.entity.rigidBody.SphereEntity;

public class ExplodingPhysicsSystem extends PhysicsSystem
{
	/**
	 * Added to mess with simulation while running to test this mechanism.
	 */
	@Override
	public void applyExternalInput(String key, Object value)
	{
		if (key.equals("explode"))
		{
			Vec3D center = new Vec3D(0, 0, 0);
			for (PhysicalEntity entity : getPhysicalEntities())
			{
				if (entity instanceof SphereEntity)
				{
					SphereEntity sphere = (SphereEntity) entity;
					center.addTo(sphere.getCenter());
				}
			}
			for (PhysicalEntity entity : getPhysicalEntities())
			{
				if (entity instanceof SphereEntity)
				{
					SphereEntity sphere = (SphereEntity) entity;
					Vec3D impulse = Vec3D.sub(sphere.getCenter(), center);
					impulse.scaleBy(0.1);
					sphere.applyImpulse(impulse);
				}
			}
		}
	}
}
