package com.pheiffware.lib.swing.testing;

import java.awt.Color;

import com.pheiffware.lib.geometry.shapes.BaseLineSegment;
import com.pheiffware.lib.geometry.shapes.Rect;
import com.pheiffware.lib.swing.MainFrame;
import com.pheiffware.lib.swing.renderPanel.graphicDebug2D.GraphicDebugPanel2D;

public class MainTestDebugPanel
{
	public static void main(String[] args)
	{
		GraphicDebugPanel2D panel = new GraphicDebugPanel2D(800, 800, 0.01, -1, -1, 2, 2);
		new MainFrame("Test Debug", panel);
		panel.startRender();
		try
		{
			panel.addRenderable(new Rect(-0.5, 0, 0.5, 0.5), new Color(255, 0, 0));
			Thread.sleep(400);
			panel.addRenderable(new Rect(0.5, 0.5, 0.5, 0.5), new Color(255, 255, 0));
			Thread.sleep(400);
			panel.addRenderable(new Rect(-0.5, -1, 1.0, 0.5), new Color(0, 0, 255));
			panel.addRenderable(new BaseLineSegment(-0.5, 0.5, 0.5, -0.5), new Color(0, 255, 0));

			Thread.sleep(400);
			panel.clearRenderables();
			panel.addRenderable(new BaseLineSegment(-0.5, -0.5, 0.5, 0.5), new Color(0, 255, 0));
			panel.addRenderable(new Rect(-0.5, 0, 0.5, 0.5), new Color(255, 0, 0));
			Thread.sleep(400);
			panel.addRenderable(new Rect(0.5, 0.5, 0.5, 0.5), new Color(255, 255, 0));
			Thread.sleep(400);
			panel.addRenderable(new Rect(-0.5, -1, 1.0, 0.5), new Color(0, 0, 255));
		}
		catch (InterruptedException e)
		{
		}
	}
}
