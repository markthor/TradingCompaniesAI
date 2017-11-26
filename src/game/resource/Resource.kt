package game.resource

abstract class Resource(private val numberOfBuckets: Int) {

	private val resourceCostInterval = 1
	val resourcesPerBucket = 10
	private val buckets: Array<Bucket>

	init {
		buckets = Array(
				size = numberOfBuckets,
				init = { i -> Bucket(resourceCost = i + 1, bucketSize = resourcesPerBucket, resourcesInBucket = resourcesPerBucket) }
		)
	}

	fun getNextBuyCost(): Int {
		// There are an infinite amount of resources one cost step
		// more expensive than the last bucket
		return getNextBuyBucket()
				?.resourceCost
				?: resourceCostInterval * (numberOfBuckets + 1)
	}

	fun getNextSellCost(): Int {
		// You can keep selling even when the last bucket is full
		return getNextSellBucket()
				?.resourceCost
				?: resourceCostInterval
	}

	fun buy() {
		getNextBuyBucket()?.decrement()
	}

	fun sell() {
		getNextSellBucket()?.increment()
	}

	private fun getNextBuyBucket(): Bucket? {
		return buckets.firstOrNull(predicate = { !it.isEmpty() })
	}

	private fun getNextSellBucket(): Bucket? {
		return buckets.lastOrNull(predicate = { !it.isFull() })
	}
}