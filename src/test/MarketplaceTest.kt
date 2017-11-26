package test

import game.Player
import game.resource.Marketplace
import game.resource.Resources
import org.testng.Assert
import org.testng.annotations.Test

class MarketplaceTest {

	@Test
	fun testInitialBuyValues() {
		Assert.assertEquals(Marketplace.buyCostOf(Resources.ENERGY), 1)
		Assert.assertEquals(Marketplace.buyCostOf(Resources.ALUMINUM), 2)
		Assert.assertEquals(Marketplace.buyCostOf(Resources.SILICON), 2)
		Assert.assertEquals(Marketplace.buyCostOf(Resources.CARBON), 2)
		Assert.assertEquals(Marketplace.buyCostOf(Resources.IRON), 2)
		Assert.assertEquals(Marketplace.buyCostOf(Resources.CHEMICALS), 7)
		Assert.assertEquals(Marketplace.buyCostOf(Resources.ELECTRONICS), 7)
		Assert.assertEquals(Marketplace.buyCostOf(Resources.GLASS), 7)
		Assert.assertEquals(Marketplace.buyCostOf(Resources.STEEL), 4)
	}

	@Test
	fun testBuyElectronics() {
		val player = Player()
		player.cash = Int.MAX_VALUE
		val electronics = Resources.ELECTRONICS

		var boughtElectronics = 0

		for (price in (7..10)) {
			for (i in (1..3)) {
				Assert.assertEquals(Marketplace.buyCostOf(electronics), price)
				Marketplace.buy(electronics, player)
				boughtElectronics++
			}
		}

		for (i in (1..100)) {
			Assert.assertEquals(Marketplace.buyCostOf(electronics), 11)
			Marketplace.buy(electronics, player)
		}

		for (i in (1..boughtElectronics)) {
			Marketplace.sell(electronics, player)
		}
	}

	@Test()
	fun testBuyCarbon() {
		val player = Player()
		player.cash = Int.MAX_VALUE
		val carbon = Resources.CARBON

		var boughtCarbon = 0

		for (i in (1..4)) {
			Assert.assertEquals(Marketplace.buyCostOf(carbon), 2)
			Marketplace.buy(carbon, player)
			boughtCarbon++
		}

		for (price in (3..5)) {
			for (i in (1..5)) {
				Assert.assertEquals(Marketplace.buyCostOf(carbon), price)
				Marketplace.buy(carbon, player)
				boughtCarbon++
			}
		}

		for (i in (1..100)) {
			Assert.assertEquals(Marketplace.buyCostOf(carbon), 6)
			Marketplace.buy(carbon, player)
		}

		for (i in (1..boughtCarbon)) {
			Marketplace.sell(carbon, player)
		}
	}

	@Test
	fun testInitialSellValues() {
		Assert.assertNull(Marketplace.sellValueOf(Resources.ENERGY))
		Assert.assertEquals(Marketplace.sellValueOf(Resources.ALUMINUM), 2)
		Assert.assertEquals(Marketplace.sellValueOf(Resources.SILICON), 2)
		Assert.assertEquals(Marketplace.sellValueOf(Resources.CARBON), 2)
		Assert.assertEquals(Marketplace.sellValueOf(Resources.IRON), 2)
		Assert.assertEquals(Marketplace.sellValueOf(Resources.CHEMICALS), 6)
		Assert.assertEquals(Marketplace.sellValueOf(Resources.ELECTRONICS), 6)
		Assert.assertEquals(Marketplace.sellValueOf(Resources.GLASS), 6)
		Assert.assertEquals(Marketplace.sellValueOf(Resources.STEEL), 3)
	}

	@Test
	fun testInitialCanSell() {
		Assert.assertFalse(Marketplace.canSell(Resources.ENERGY))
		Assert.assertTrue(Marketplace.canSell(Resources.ALUMINUM))
		Assert.assertTrue(Marketplace.canSell(Resources.SILICON))
		Assert.assertTrue(Marketplace.canSell(Resources.CARBON))
		Assert.assertTrue(Marketplace.canSell(Resources.IRON))
		Assert.assertTrue(Marketplace.canSell(Resources.CHEMICALS))
		Assert.assertTrue(Marketplace.canSell(Resources.ELECTRONICS))
		Assert.assertTrue(Marketplace.canSell(Resources.GLASS))
		Assert.assertTrue(Marketplace.canSell(Resources.STEEL))
	}
}