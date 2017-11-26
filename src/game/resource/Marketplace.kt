package game.resource

import game.Player

object Marketplace {

	private val marketplaceResources = HashMap<Resources, Array<Bucket>>()

	init {
		for (resource in Resources.values()) {
			marketplaceResources[resource] = Array(
					size = resource.buckets,
					init = { i ->
						Bucket(
								resourceCost = i + 1,
								bucketSize = resource.bucketSize,
								resourcesInBucket = initValueOf(i, resource)
						)
					}
			)
		}
	}

	/**
	 * Determine the number of resources in a given bucket at game start
	 */
	private fun initValueOf(bucketIndex: Int, resource: Resources): Int {
		val bucketNumber = bucketIndex + 1
		return when {
			bucketNumber < resource.firstBucket -> 0
			bucketNumber == resource.firstBucket -> resource.resourcesInFirstBucket
			else -> resource.bucketSize
		}
	}

	fun cheapestResourceBuyCost(): Int {
		return Resources.values()
				.map { buyCostOf(it) }
				.min() ?: throw IllegalStateException("No cheapest resource found")
	}

	fun buyCostOf(resource: Resources): Int {
		// Can we find a non-empty bucket? Otherwise the price is buckets + 1
		return marketplaceResources[resource]?.firstOrNull { !it.isEmpty() }?.resourceCost ?: resource.buckets+1
	}

	fun sellValueOf(resource: Resources): Int? {
		return marketplaceResources[resource]?.lastOrNull { !it.isFull() }?.resourceCost
	}

	fun canSell(resource: Resources): Boolean {
		return marketplaceResources[resource]?.firstOrNull { !it.isFull() } != null
	}

	fun buy(resource: Resources, player: Player) {
		val buyCost = buyCostOf(resource)
		if (buyCost < player.cash) {
			marketplaceResources[resource]?.firstOrNull { !it.isEmpty() }?.decrement()
			player.cash -= buyCost
		} else {
			throw IllegalStateException("Insufficient funds")
		}
	}

	fun sell(resource: Resources, player: Player) {
		val sellValue = sellValueOf(resource)
		if (sellValue != null) {
			marketplaceResources[resource]?.lastOrNull { !it.isFull() }?.increment()
			player.cash += sellValue
		}
	}
}