/**
 * 
 */
package com.kata.tennisGame.tennisGame;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.kata.tennisGame.tennisGame.Game;
import com.kata.tennisGame.tennisGame.Player;

/**
 * @author mezzine
 *
 *         On 4 févr. 2018
 */
public class TestTennisGame {

	/**
	 * SPRINT1 : 
	 * Manage a tennis GAME within a set of a tennis match.
	 * Manage the specific of the rule DEUCE at the end of a Game.
	 */

	private Game tennisGame;
	private Player player1;
	private Player player2;

	@Before
	public void setGameConfiguration() {
		player1 = new Player("Maha", "Tunisia");
		player2 = new Player("Computer", "England");
		tennisGame = new Game(player1, player2);

	}

	@Test
	public void testStartingScore() {
		assertEquals("ZERO", tennisGame.getInitialScore());

	}

	@Test
	public void testPlayer1Win1Point() {

		assertEquals("Fifteen", player1.winPoints(1));

	}

	@Test
	public void testPlayer2Win2Points() {

		player2.winPoints(2);

		assertEquals("Thirteen", player2.getFormattedScore(player2.getGameScore()));
	}

	@Test
	public void testPlayer1Win3Points() {

		player1.winPoints(3);

		assertEquals("Fourteen", player1.getFormattedScore(player1.getGameScore()));

	}

	@Test
	public void testPlayer1GetsAdvantage() {

		player1.winPoints(4);
		player2.winPoints(3);

		Assert.assertEquals(tennisGame.getGameScore(), "Advantage for Maha.");

	}

	@Test
	public void testDeuceRuleWhenTheScoresAreEqualToForteen() {

		player1.winPoints(3);
		player2.winPoints(3);

		Assert.assertEquals(tennisGame.getGameScore(), "Deuce");
		player1.winPoints(1);
		Assert.assertEquals(tennisGame.getGameScore(), "Advantage for Maha.");
		player2.winPoints(1);
		Assert.assertEquals(tennisGame.getGameScore(), "Deuce");

	}

	@Test
	public void testPlayer1WinTheGame() {

		player1.winPoints(4);
		player2.winPoints(3);

		Assert.assertEquals(tennisGame.getGameScore(), "Advantage for Maha.");

		player2.winPoints(1);
		Assert.assertNotEquals(tennisGame.getGameScore(), "Advantage for Computer.");
		Assert.assertEquals(tennisGame.getGameScore(), "Deuce");

		player1.winPoints(1);
		Assert.assertEquals(tennisGame.getGameScore(), "Advantage for Maha.");

		player1.winPoints(1);
		Assert.assertEquals(tennisGame.getGameScore(), "Maha wins the game.");
	}

}
