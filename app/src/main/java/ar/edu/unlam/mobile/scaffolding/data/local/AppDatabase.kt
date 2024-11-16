package ar.edu.unlam.mobile.scaffolding.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.product.local.ProductDao
import ar.edu.unlam.mobile.scaffolding.data.product.local.ProductEntity
import ar.edu.unlam.mobile.scaffolding.data.restaurants.local.RestaurantDao
import ar.edu.unlam.mobile.scaffolding.data.restaurants.local.RestaurantEntity

@Database(
    entities = [RestaurantEntity::class, ProductEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao

    abstract fun productDao(): ProductDao
}
