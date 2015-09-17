package com.pheiffware.lib.swing.testing;

import javax.swing.JPanel;

import com.pheiffware.lib.swing.MainFrame;
import com.pheiffware.lib.swing.keylisten.PheiffKeyStrokeManager;

public class MainTestKey
{
	public static void main(String[] args)
	{
		PheiffKeyStrokeManager keyManager = new PheiffKeyStrokeManager()
		{
			@Override
			public void released(int keyCode)
			{
				System.out.println(keyCode + "released");
			}

			@Override
			public void pressed(int keyCode)
			{
				System.out.println(keyCode + "pressed");
			}
		};
		keyManager.install();
		new MainFrame("Test", new JPanel());
	}

}
