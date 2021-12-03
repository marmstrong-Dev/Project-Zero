package com.data

import org.mongodb.scala._

object DbCon {
  var mongoClient = MongoClient()
  var mongoDb = mongoClient.getDatabase("")


}
