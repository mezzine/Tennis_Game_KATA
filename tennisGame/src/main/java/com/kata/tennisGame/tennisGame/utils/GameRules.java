/**
 * 
 */
package com.kata.tennisGame.tennisGame.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mezzine
 *
 *         On 4 févr. 2018
 */
public enum GameRules {

	ZERO(0), Fifteen(1), Thirteen(2), Fourteen(3), Advantage(4);

	private final int code;
	private static Map<Integer, String> mMap;

	private GameRules(int code) {
		this.code = code;

	}

	public static String getScoreRulesByCode(int code) {
		if (mMap == null) {
			initializeMapping();
		}
		if (mMap.containsKey(code)) {
			return mMap.get(code);
		}
		return "The rule is not defined !!";

	}

	private static void initializeMapping() {
		mMap = new HashMap<Integer, String>();
		for (GameRules s : GameRules.values()) {
			mMap.put(s.code, s.name());
		}
	}

}
