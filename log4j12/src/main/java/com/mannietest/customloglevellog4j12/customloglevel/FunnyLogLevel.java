package com.mannietest.customloglevellogj12.customlevel;

import org.apache.log4j.Level;

public class FunnyLogLevel extends Level {
	
	public static final int FUNNY_INT = ERROR_INT + 10;

	public static final Level FUNNY = new FunnyLogLevel(FUNNY_INT, "FUNNY", 10);
	 
	protected FunnyLogLevel(int level, String levelStr, int syslogEquivalent) {
		super(level, levelStr, syslogEquivalent);
		// TODO Auto-generated constructor stub
	}

    public static Level toLevel(String logArgument) {
        if (logArgument != null && logArgument.toUpperCase().equals("FUNNY")) {
            return FUNNY;
        }
        return (Level) toLevel(logArgument, Level.DEBUG);
    }
 
    /**
     * Checks whether val is FUNNYLog4jLevel#FUNNY_INT. If yes then
     * returns FUNNYLog4jLevel#FUNNY, else calls
     * FUNNYLog4jLevel#toLevel(int, Level) passing it Level#DEBUG as the
     * defaultLevel
     * 
     */
    public static Level toLevel(int val) {
        if (val == FUNNY_INT) {
            return FUNNY;
        }
        return (Level) toLevel(val, Level.DEBUG);
    }
 
    /**
     * Checks whether val is FUNNYLog4jLevel#FUNNY_INT. If yes
     * then returns FUNNYLog4jLevel#FUNNY, else calls Level#toLevel(int, org.apache.log4j.Level)
     * 
     */
    public static Level toLevel(int val, Level defaultLevel) {
        if (val == FUNNY_INT) {
            return FUNNY;
        }
        return Level.toLevel(val, defaultLevel);
    }
 
    /**
     * Checks whether logArgument is "FUNNY" level. If yes then returns
     * FUNNYLog4jLevel#FUNNY, else calls
     * Level#toLevel(java.lang.String, org.apache.log4j.Level)
     * 
     */
    public static Level toLevel(String logArgument, Level defaultLevel) {
        if (logArgument != null && logArgument.toUpperCase().equals("FUNNY")) {
            return FUNNY;
        }
        return Level.toLevel(logArgument, defaultLevel);
    }
}
