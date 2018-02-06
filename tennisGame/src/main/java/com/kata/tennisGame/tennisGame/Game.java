/**
 * 
 */
package com.kata.tennisGame.tennisGame;

import com.kata.tennisGame.tennisGame.utils.Constants;
import com.kata.tennisGame.tennisGame.utils.GameRules;

/**
 * @author mezzine
 *
 *         On 4 févr. 2018
 */
public class Game implements ITennisGame {

	private Player player1;
	private Player player2;

	/**
	 * @param player1
	 * @param player2
	 */
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	/*
	 * Gets the initial score of the game
	 * 
	 * @see com.kata.tennisGame.tennisGame.ITennisGame#getInitialScore()
	 */
	public String getInitialScore() {
		return GameRules.getScoreRulesByCode(0);

	}

	/*
	 * Gets the score of a Tennis Game within a tennis Set Manage DEUCE/ ADVANTAGE
	 * rules
	 * 
	 * @see com.kata.tennisGame.tennisGame.ITennisGame#getGameScore()
	 */
	@Override
	public String getGameScore() {

		if (finalGame()) {
			if (player1.getGameScore() == player2.getGameScore())
				return Constants.DEUCE_GAME;
			else if (Math.abs(player2.getGameScore() - player1.getGameScore()) >= Constants.MIN_DIFFERENCE_TO_WIN) {
				getCurrentGameWinner().setSetScore(getCurrentGameWinner().getSetScore() + 1);
				return getCurrentGameWinner().getName() + Constants.WIN_GAME;
			} else
				return Constants.ADVANTAGE_GAME + " for " + getCurrentGameWinner().getName() + ".";
		} else
			return "Current game score is: " + formatCurrentScore(player1.getGameScore(), player2.getGameScore());
	}

	/*
	 * Tests if it is the final game
	 */
	private boolean finalGame() {
		return player1.getGameScore() >= Constants.FOURTEEN && player2.getGameScore() >= Constants.FOURTEEN;
	}

	/**
	 * @param score1
	 * @param score1
	 * @return
	 */
	public String formatCurrentScore(int score1, int score2) {

		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append(String.format(Constants.SEPARATOR, player1.getName(), score1));
		sb.append(" | ");
		sb.append(String.format(Constants.SEPARATOR, player2.getName(), score2));
		sb.append("]");
		return sb.toString();
	}

	/*
	 * Gets the score of Tennis Sets within a tennis Match Manage TIE BREAK rule and
	 * Match winning
	 * 
	 * @see com.kata.tennisGame.tennisGame.entities.ITennisGame#getSetScore()
	 */
	@Override
	public String getSetScore() {
		if (finalSet()) {
			if (player1.getSetScore() == player2.getSetScore()) {
				return Constants.TIE_BREAK_RULE_ACTIVATED
						+ formatCurrentScore(player1.getSetScore(), player2.getSetScore());
			}
			if ((Math.abs(player2.getSetScore() - player1.getSetScore()) >= Constants.MIN_DIFFERENCE_TO_WIN)) {

				return getCurrentSetWinner().getName() + " from " + getCurrentSetWinner().getCountry()
						+ Constants.WIN_SET_AND_MATCH;
			}
		} else if (finalGame()) {
			if (Math.abs(player2.getGameScore() - player1.getGameScore()) >= Constants.MIN_DIFFERENCE_TO_WIN) {

				return getCurrentGameWinner().getName() + Constants.WIN_SET;
			}
		}
		return "Current set score is: " + formatCurrentScore(player1.getSetScore(), player2.getSetScore());
	}

	/*
	 * Manage TIE BREAK rule and match winning
	 * 
	 * @see com.kata.tennisGame.tennisGame.entities.ITennisGame#getTieBreakScore()
	 */
	@Override
	public String getTieBreakScore() {

		if (player1.getTieBreakScore() >= Constants.MAX_TIE_BREAK_NUMBER
				|| player2.getTieBreakScore() >= Constants.MAX_TIE_BREAK_NUMBER) {
			if (Math.abs(player2.getTieBreakScore() - player1.getTieBreakScore()) >= Constants.MIN_DIFFERENCE_TO_WIN) {

				return getCurrenTieBreakScoreWinner().getName() + " from " + getCurrenTieBreakScoreWinner().getCountry()
						+ Constants.WIN_SET_AND_MATCH;

			}
		}
		return "Current Tie Break score is: "
				+ formatCurrentScore(player1.getTieBreakScore(), player2.getTieBreakScore());

	}

	public Player getCurrentGameWinner() {
		return player1.getGameScore() > player2.getGameScore() ? player1 : player2;
	}

	private Player getCurrentSetWinner() {
		return (player1.getSetScore() > player2.getSetScore()) ? player1 : player2;
	}

	private Player getCurrenTieBreakScoreWinner() {
		return (player1.getTieBreakScore() > player2.getTieBreakScore()) ? player1 : player2;
	}

	private boolean finalSet() {
		return player1.getSetScore() >= Constants.MAX_SET_NUMBER || player2.getSetScore() >= Constants.MAX_SET_NUMBER;
	}

}
