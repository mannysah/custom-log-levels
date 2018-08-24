package com.mannietest.customloglevellogj12.customlevel;

import org.apache.log4j.Level;

public class FunnyLogLevel extends Level 
{	
	public static final int FUNNY_INT = ERROR_INT + 10;

	public static final Level FUNNY = new FunnyLogLevel(FUNNY_INT, "FUNNY", 10);
	 
	protected FunnyLogLevel(int level, String levelStr, int syslogEquivalent) {
		super(level, levelStr, syslogEquivalent);
	}
}