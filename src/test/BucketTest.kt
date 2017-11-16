package test

import game.resource.Energy
import game.resource.Resource
import org.testng.Assert
import org.testng.annotations.Test

class BucketTest {

	@Test
	fun testSimpleBuy() {
		val energy = Energy()
		val bucketSize = energy.resourcesPerBucket

		Assert.assertEquals(energy.getNextBuyCost(), 1)

		buy(n = bucketSize, resources = energy)
		Assert.assertEquals(energy.getNextBuyCost(), 2)

		buy(n = bucketSize, resources = energy)
		Assert.assertEquals(energy.getNextBuyCost(), 3)

		buy(n = bucketSize, resources = energy)
		Assert.assertEquals(energy.getNextBuyCost(), 4)

		buy(n = bucketSize, resources = energy)
		Assert.assertEquals(energy.getNextBuyCost(), 5)

		buy(n = bucketSize, resources = energy)
		Assert.assertEquals(energy.getNextBuyCost(), 6)

		buy(n = bucketSize, resources = energy)
		Assert.assertEquals(energy.getNextBuyCost(), 6)
	}

	@Test
	fun testSimpleSell() {
		val energy = Energy()
		val bucketSize = energy.resourcesPerBucket

		Assert.assertEquals(energy.getNextSellCost(), 1)

		sell(n = bucketSize, resources = energy)
		Assert.assertEquals(energy.getNextBuyCost(), 1)

		sell(n = bucketSize, resources = energy)
		Assert.assertEquals(energy.getNextBuyCost(), 1)

		buy(n = bucketSize, resources = energy)
		// First bucket should be empty, but second bucket full
		// therefore we sell at the price of bucket 1
		Assert.assertEquals(energy.getNextSellCost(), 1)

		buy(n = 1, resources = energy)
		// First bucket should be empty and second bucket should contain one less than full
		// therefore we sell at the price of bucket 2
		Assert.assertEquals(energy.getNextSellCost(), 2)

		buy(n = bucketSize - 1, resources = energy)
		// Second bucket should be empty, but third bucket full
		Assert.assertEquals(energy.getNextSellCost(), 2)

		buy(n = 1, resources = energy)
		// Second bucket should be empty and third bucket should contain one less than full
		// therefore we sell at the price of bucket 3
		Assert.assertEquals(energy.getNextSellCost(), 3)

		buy(n = bucketSize - 1, resources = energy)
		// Third bucket should be empty, but fourth bucket full
		Assert.assertEquals(energy.getNextSellCost(), 3)

		buy(n = 1, resources = energy)
		// Third bucket should be empty and fourth bucket should contain one less than full
		// therefore we sell at the price of bucket 4
		Assert.assertEquals(energy.getNextSellCost(), 4)

		sell(n = 1, resources = energy)
		//Fourth bucket should now be full, therefore we sell at the price of bucket 3
		Assert.assertEquals(energy.getNextSellCost(), 3)
	}

	private fun buy(n: Int, resources: Resource) {
		repeat(times = n, action = { _ -> resources.buy() })
	}

	private fun sell(n: Int, resources: Resource) {
		repeat(times = n, action = { _ -> resources.sell() })
	}
}