package com.maurrysonn.curling_tools.core.gui;

import java.awt.Color;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.UIDefaults;

public class GUIButtonFactory {

	public static JButton createDefaultButton(final String _text){
		return new JButton(_text);
	}

	public static JButton createPrimaryButton(final String _text){
		final JButton primaryBtn = new JButton(_text);
		UIDefaults primaryBtnDefaults = new UIDefaults();
		// primaryBtnDefaults.put("nimbusBorder", Color.red);
		primaryBtnDefaults.put("Button.background", new Color(66, 139, 202));
		for (Entry<Object, Object> item : primaryBtnDefaults.entrySet()) {
			// XXX amaury - Delete print
			System.out.println(item.getKey() + " = " + item.getValue());
		}
		primaryBtn.putClientProperty("Nimbus.Overrides", primaryBtnDefaults);
		primaryBtn.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
		return primaryBtn;
	}
	
}
