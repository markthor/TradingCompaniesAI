package game

import game.resource.Marketplace
import game.resource.Resources
import java.util.*

class Player {

	var cash = 6
	private val resources = HashMap<Resources, Int>()
	private val rng = Random()

	init {
		resources.put(Resources.ENERGY, 2)
		resources.put(Resources.STEEL, 4)
	}

	fun buyRandomResource() {
		if (Marketplace.cheapestResourceBuyCost() < cash) {
			val differentResources = Resources.values().size
			while (true) {
				val resource = Resources.values()[rng.nextInt(differentResources)]
				val cost = Marketplace.buyCostOf(resource)
				if (cost < cash) {
					Marketplace.buy(resource, this)
					increment(resource)
					break
				}
			}
		} else {
			throw IllegalStateException("Insufficient funds")
		}
	}

	fun sellRandomResource() {
		val sellableResources = resources.filter { it.value > 0 && Marketplace.canSell(it.key) }
		if (sellableResources.isEmpty()) {
			throw IllegalStateException("Nothing to sell")
		}
		val entry = sellableResources.entries.toTypedArray()[rng.nextInt(sellableResources.size)]
		Marketplace.sell(entry.key, this)
	}

	private fun increment(resource: Resources) {
		val amountOfResource = resources[resource]
		if (amountOfResource == null) {
			resources.put(resource, 1)
		} else {
			resources.put(resource, amountOfResource+1)
		}
	}
}