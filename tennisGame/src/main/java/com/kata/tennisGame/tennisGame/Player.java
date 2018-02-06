/**
 * 
 */
package com.kata.tennisGame.tennisGame;

import java.util.stream.IntStream;

import com.kata.tennisGame.tennisGame.utils.Constants;
import com.kata.tennisGame.tennisGame.utils.GameRules;

/**
 * @author mezzine
 *
 *         On 4 févr. 2018
 */
public class Player {

	private String name;
	private String country;
	private int gameScore;
	private int setScore;
	private int tieBreakScore;

	/**
	 * @param string
	 * @param string2
	 */
	public Player(String name, String country) {
		this.name = name;
		this.country = country;
	}

	/**
	 * Updates player score
	 */
	public String winPoints(int goal) {
		IntStream.rangeClosed(1, goal).forEach((Integer) -> {
			this.setGameScore(gameScore + 1);

		});
		if (this.getSetScore() == Constants.MAX_SET_NUMBER)
			this.tieBreakScore = getTieBreakScore() + goal;

		return this.getFormattedScore(this.gameScore);

	}

	/**
	 * @param i
	 */
	public void winSets(int i) {
		IntStream.rangeClosed(1, i).forEach((Integer) -> {
			this.setSetScore(setScore + 1);
		});

	}

	/**
	 * Increment set score
	 */
	public void winASet() {

		this.setSetScore(setScore + 1);

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the gameScore
	 */
	public int getGameScore() {
		return this.gameScore;
	}

	/**
	 * @param gameScore
	 *            the gameScore to set
	 */
	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}

	/**
	 * @return the setScore
	 */
	public int getSetScore() {
		return this.setScore;
	}

	/**
	 * @param setScore
	 *            the setScore to set
	 */
	public void setSetScore(int setScore) {
		this.setScore = setScore;
	}

	/**
	 * @return the tieBreakScore
	 */
	public int getTieBreakScore() {
		return this.tieBreakScore;
	}

	/**
	 * @param tieBreakScore
	 *            the tieBreakScore to set
	 */
	public void setTieBreakScore(int tieBreakScore) {
		this.tieBreakScore = tieBreakScore;
	}

	/**
	 * @return the tieBreakScore
	 */
	public String getFormattedScore(int score) {
		return GameRules.getScoreRulesByCode(score);
	}

}
