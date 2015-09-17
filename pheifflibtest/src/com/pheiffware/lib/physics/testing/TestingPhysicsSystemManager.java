/*
 * Created by Stephen Pheiffer.
 * Do not edit, distribute, modify or use without his permission.
 */
package com.pheiffware.lib.physics.testing;

import java.util.List;
import java.util.Random;

import com.pheiffware.lib.Utils;
import com.pheiffware.lib.log.Log;
import com.pheiffware.lib.physics.PhysicsSystem;
import com.pheiffware.lib.physics.entity.Entity;
import com.pheiffware.lib.simulation.SimulationManager;

/**
 * Designed to run the simulator in a background thread in a precise and
 * consistent way. Allows multiple test scenarios to be setup and run.
 */
public class TestingPhysicsSystemManager
{
	// Randomizes the order in which entities appear in the PhysicSystem list
	// for each scenario
	private final boolean randomizeEntityOrder;

	// The physics scenarios to run through
	private final TestPhysicsScenario[] physicsScenarios;

	// Use this ratio to delay updates to the simulation so that it flows with
	// this ratio to real time
	private final double minRealSimTimeRatio;

	private final SimulationManager<List<Entity>> simulationManager;
	private final PhysicsSystem physicsSystem;

	public TestingPhysicsSystemManager(double minRealSimTimeRatio, boolean randomizeEntityOrder, TestPhysicsScenario[] physicsScenarios)
	{
		this.randomizeEntityOrder = randomizeEntityOrder;
		this.physicsScenarios = physicsScenarios;
		this.minRealSimTimeRatio = minRealSimTimeRatio;
		physicsSystem = new PhysicsSystem();
		simulationManager = new SimulationManager<List<Entity>>(physicsSystem);
	}

	public void start()
	{
		new Thread()
		{
			public void run()
			{
				for (int i = 0; i < physicsScenarios.length; i++)
				{
					physicsSystem.reset();
					physicsScenarios[i].setup(physicsSystem);
					if (randomizeEntityOrder)
					{
						physicsSystem.randomizeEntityProcessingOrder_TESTING_ONLY(new Random());
					}
					long scenarioStart = System.nanoTime();
					simulationManager.runDeterministicSimulationInBackground(physicsScenarios[i].getRuntime(), physicsScenarios[i].getNumSteps(),
							minRealSimTimeRatio);
					simulationManager.awaitCompletion();
					Log.info("Ups : " + physicsScenarios[i].getNumSteps() / Utils.getTimeElapsed(scenarioStart));
				}
			}
		}.start();

	}

	public List<Entity> getState()
	{
		return simulationManager.getState();
	}
}
