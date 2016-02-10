/*
 * Created by Stephen Pheiffer.
 * Do not edit, distribute, modify or use without his permission.
 */
package com.pheiffware.lib.examples.physics;

import com.pheiffware.lib.physics.PhysicsSystem;

/**
 * Describes a physics scenario and how long it should run. Used for testing.
 */
public abstract class TestPhysicsScenario
{
	private final double runTime;
	private final int numSteps;

	public TestPhysicsScenario(double scenarioRuntime, int numSteps)
	{
		this.runTime = scenarioRuntime;
		this.numSteps = numSteps;
	}

	void resetPhysicsSystem(PhysicsSystem physicsSystem)
	{
		physicsSystem.reset();
		setup(physicsSystem);
	}

	public abstract void setup(PhysicsSystem physicsSystem);

	public final double getRuntime()
	{
		return runTime;
	}

	public final int getNumSteps()
	{
		return numSteps;
	}

}
