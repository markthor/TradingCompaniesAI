package game.resource

class Bucket {

	val resourceCost: Int
	private val maxNumberOfResourcesInBucket: Int
	private var numberOfResourcesInBucket: Int

	@Suppress("ConvertSecondaryConstructorToPrimary")
	constructor(resourceCost: Int, numberOfResourcesInBucket: Int) {
		this.resourceCost = resourceCost
		this.maxNumberOfResourcesInBucket = numberOfResourcesInBucket
		this.numberOfResourcesInBucket = numberOfResourcesInBucket
	}

	fun decrement() {
		if (numberOfResourcesInBucket < 1) {
			throw Exception("Trying to take resource from empty bucket")
		}
		numberOfResourcesInBucket = numberOfResourcesInBucket.dec()
	}

	fun increment() {
		if (numberOfResourcesInBucket >= maxNumberOfResourcesInBucket) {
			throw Exception("Trying to put resource in full bucket")
		}
		numberOfResourcesInBucket = numberOfResourcesInBucket.inc()
	}

	fun isFull(): Boolean {
		return numberOfResourcesInBucket == maxNumberOfResourcesInBucket
	}

	fun isEmpty(): Boolean {
		return numberOfResourcesInBucket <= 0
	}
}