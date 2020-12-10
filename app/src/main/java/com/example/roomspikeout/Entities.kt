package com.example.roomspikeout

import androidx.room.*

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val age: Int
)

@Entity(
    tableName = "dog",
    foreignKeys = [ForeignKey(
        entity = Person::class,
        parentColumns = ["id"],
        childColumns = ["ownerId"]
    )]
)
data class Dog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val ownerId: Long,
    val age: Int
)

@Entity(
    tableName = "pup",
    foreignKeys = [ForeignKey(
        entity = Dog::class,
        parentColumns = ["id"],
        childColumns = ["parentId"]
    )]
)
data class Pup(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val parentId: Long,
    val name: String,
    val cuteness: Int
)

class DogAndPups {
    @Embedded var dog: Dog? = null

    @Relation(
        entity = Pup::class,
        parentColumn = "id",
        entityColumn = "parentId"
    )
    var pups: List<Pup>? = null
}

data class Owner(
    val person: Person,
    var dogs: List<DogAndPups>? = null
)
