package com.pheiffware.lib.swing.testing;

import java.awt.Color;
import java.util.List;

import com.pheiffware.lib.physics.PhysicsSystemManager;
import com.pheiffware.lib.physics.entity.Entity;
import com.pheiffware.lib.physics.testing.TestPhysicsScenario;
import com.pheiffware.lib.physics.testing.TestingPhysicsSystemManager;
import com.pheiffware.lib.physics.testing.testScenarios.BouncingBall;
import com.pheiffware.lib.physics.testing.testScenarios.CompressedStackedObjects;
import com.pheiffware.lib.physics.testing.testScenarios.ConstrainedStackedObjects;
import com.pheiffware.lib.physics.testing.testScenarios.ConstrainedStackedObjectsDrop;
import com.pheiffware.lib.physics.testing.testScenarios.Elevator;
import com.pheiffware.lib.physics.testing.testScenarios.ElevatorWithLoad;
import com.pheiffware.lib.physics.testing.testScenarios.GeneralScenario1;
import com.pheiffware.lib.physics.testing.testScenarios.GeneralScenario2;
import com.pheiffware.lib.physics.testing.testScenarios.PolygonScenario;
import com.pheiffware.lib.physics.testing.testScenarios.PoolScenario;
import com.pheiffware.lib.physics.testing.testScenarios.SingleBallOnRamp;
import com.pheiffware.lib.physics.testing.testScenarios.SingleBallSitGround;
import com.pheiffware.lib.physics.testing.testScenarios.StackedObjects;
import com.pheiffware.lib.swing.graphics.G2D;
import com.pheiffware.lib.swing.graphics.G2DRender;
import com.pheiffware.lib.swing.renderPanel.RenderPanel;

@SuppressWarnings("serial")
public class PhysicsTestPanel extends RenderPanel
{
	private final PhysicsSystemManager physicsSystemManager;

	public PhysicsTestPanel()
	{
		super(800, 800, 0.01);
		// @formatter:off
		TestPhysicsScenario[] physicsScenarios = new TestPhysicsScenario[]
		{
				new PolygonScenario(5.0f),
				new StackedObjects(3.0f, 40.5f, 500.5f, 20, 5, 800, 0.9f),
				new CompressedStackedObjects(8.0f, 40.5f, 500.5f, 20, 5, 800,
						0.9f, 2500.0f, 300, 50),
				new ConstrainedStackedObjectsDrop(5.0f, 40.5f, 500.5f, 20, 8,
						800, 0.9f),
				new ConstrainedStackedObjects(3.0f, 40.5f, 500.5f, 20, 7, 800,
						0.9f), new PoolScenario(1.5f, 40, 500, 20, 5, 0.9f),
				new ElevatorWithLoad(1.5f), new BouncingBall(1.0f),
				new Elevator(1.0f), new SingleBallSitGround(),
				new GeneralScenario1(3.0f), new GeneralScenario2(8.0f),
				new SingleBallOnRamp(3.0f) };
		// @formatter:on

		// True gravity = 21147 pixels/s^2

		// Benchmark: time step of 0.00002f, drawing rate set high, real update
		// rate must be set low
		// fps = 175015.67

		// In tests, this allowed the stack of 10 to be stable at gravity of
		// 800. Anything higher did not work.
		// float MAX_STABLE_STACKED_OBJECT_TIME_STEP = 0.001f;
		// physicsSystemManager = new RealtimePhysicsSystemManager(0.0001f,
		// 0.00001f, 1.0f, new CompressedStackedObjects(8.0f, 40.5f, 500.5f, 20,
		// 5,
		// 800, 0.9f, 2500.0f, 10000, 5000));
		// physicsSystemManager = new RealtimePhysicsSystemManager(0.0001f,
		// 0.00001f, 1.0f, new SingleBallOnRamp());
		setView(0, 0, 800, 800);
		physicsSystemManager = new TestingPhysicsSystemManager(0.00002f, 0.1f,
				true, physicsScenarios);
		physicsSystemManager.start();
		startRender();
	}

	@Override
	public void render(G2D g2d)
	{
		g2d.setColor(new Color(100, 100, 200));
		g2d.fillRectAbsolute(0, 0, getWidth(), getHeight());

		g2d.setColor(new Color(255, 0, 0));
		List<Entity> entities = physicsSystemManager.copyForRender();
		for (Entity entity : entities)
		{
			G2DRender.render(g2d, entity);
		}
	}

}
