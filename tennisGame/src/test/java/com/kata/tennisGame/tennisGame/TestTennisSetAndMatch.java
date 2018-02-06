/**
 * 
 */
package com.kata.tennisGame.tennisGame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.kata.tennisGame.tennisGame.Game;
import com.kata.tennisGame.tennisGame.Player;

/**
 * @author mezzine
 *
 *         On 5 févr. 2018
 */

public class TestTennisSetAndMatch {

	/**
	 * SPRINT2 : Manage a tennis SET within a tennis match Manage the specific of
	 * the rule of Tie-Break at the end of the Set
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
	public void testWinningASetWithinAMatch() {

		player1.winPoints(3);
		player2.winPoints(3);

		Assert.assertEquals(tennisGame.getGameScore(), "Deuce");

		player1.winPoints(1);
		Assert.assertEquals(tennisGame.getGameScore(), "Advantage for Maha.");

		player1.winPoints(1);
		Assert.assertEquals(tennisGame.getGameScore(), "Maha wins the game.");
		Assert.assertEquals(tennisGame.getSetScore(), "Maha wins the set.");

	}

	@Test
	public void testDisplayCurrentSetScoreWithinAMatch() {

		player1.winSets(4);
		player2.winSets(2);

		Assert.assertEquals(tennisGame.getSetScore(), "Current set score is: [ Maha: 4 | Computer: 2]");

	}

	@Test
	public void testWinningAMatchWithTwoSetsDifferenceBetweenPlayers() {

		player1.winSets(5);
		player2.winSets(4);
		Assert.assertEquals(tennisGame.getSetScore(), "Current set score is: [ Maha: 5 | Computer: 4]");
		player1.winSets(1);
		Assert.assertEquals(tennisGame.getSetScore(), "Maha from Tunisia wins the set and the match! :D");

	}

	@Test
	public void testWinningAMatchWithOneSetDifferenceBetweenPlayers() {

		player1.winSets(6);
		player2.winSets(5);
		Assert.assertEquals(tennisGame.getSetScore(), "Current set score is: [ Maha: 6 | Computer: 5]");

		player1.winSets(7);
		Assert.assertEquals(tennisGame.getSetScore(), "Maha from Tunisia wins the set and the match! :D");

	}

	@Test
	public void testTieBreakRuleActivationWithinAMatch() {

		player1.winSets(6);
		player2.winSets(5);
		Assert.assertEquals(tennisGame.getSetScore(), "Current set score is: [ Maha: 6 | Computer: 5]");
		player2.winSets(1);
		Assert.assertEquals(tennisGame.getSetScore(), "Tie Break rule is activated:[ Maha: 6 | Computer: 6]");

	}

	@Test
	public void testWinningAMatchWithTieBreakRuleActivated() {

		player1.winSets(6);
		player2.winSets(6);
		Assert.assertEquals(tennisGame.getSetScore(), "Tie Break rule is activated:[ Maha: 6 | Computer: 6]");

		player1.winPoints(2);
		Assert.assertEquals(tennisGame.getGameScore(), "Current game score is: [ Maha: 2 | Computer: 0]");
		Assert.assertEquals(tennisGame.getSetScore(), "Tie Break rule is activated:[ Maha: 6 | Computer: 6]");
		Assert.assertEquals(tennisGame.getTieBreakScore(), "Current Tie Break score is: [ Maha: 2 | Computer: 0]");

		player2.winPoints(1);
		Assert.assertEquals(tennisGame.getGameScore(), "Current game score is: [ Maha: 2 | Computer: 1]");
		Assert.assertEquals(tennisGame.getSetScore(), "Tie Break rule is activated:[ Maha: 6 | Computer: 6]");
		Assert.assertEquals(tennisGame.getTieBreakScore(), "Current Tie Break score is: [ Maha: 2 | Computer: 1]");

		player1.winPoints(4);
		player2.winPoints(4);
		Assert.assertEquals(tennisGame.getTieBreakScore(), "Current Tie Break score is: [ Maha: 6 | Computer: 5]");

		player1.winPoints(1);
		Assert.assertEquals(tennisGame.getTieBreakScore(), "Maha from Tunisia wins the set and the match! :D");

	}
}
