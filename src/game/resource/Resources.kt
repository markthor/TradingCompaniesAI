package game.resource

enum class Resources(val buckets: Int, val bucketSize: Int, val resourcesInFirstBucket: Int, val firstBucket: Int) {
	ENERGY      (buckets = 5,  bucketSize = 10, resourcesInFirstBucket = 10, firstBucket = 1),
	ALUMINUM    (buckets = 5,  bucketSize = 5,  resourcesInFirstBucket = 4,  firstBucket = 2),
	SILICON     (buckets = 5,  bucketSize = 5,  resourcesInFirstBucket = 4,  firstBucket = 2),
	CARBON      (buckets = 5,  bucketSize = 5,  resourcesInFirstBucket = 4,  firstBucket = 2),
	IRON        (buckets = 5,  bucketSize = 5,  resourcesInFirstBucket = 4,  firstBucket = 2),
	CHEMICALS   (buckets = 10, bucketSize = 3,  resourcesInFirstBucket = 3,  firstBucket = 7),
	ELECTRONICS (buckets = 10, bucketSize = 3,  resourcesInFirstBucket = 3,  firstBucket = 7),
	GLASS       (buckets = 10, bucketSize = 3,  resourcesInFirstBucket = 3,  firstBucket = 7),
	STEEL       (buckets = 10, bucketSize = 5,  resourcesInFirstBucket = 5,  firstBucket = 4),
}