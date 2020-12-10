package com.example.roomspikeout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class PersonDao {

    @Query("SELECT * FROM dog")
    abstract suspend fun getAllDogs(): List<DogAndPups>

    @Query("SELECT * FROM dog WHERE dog.ownerId = :ownerId")
    abstract suspend fun getDogsByOwnerId(ownerId: Long): List<DogAndPups>

    @Insert
    abstract suspend fun addDog(dog: Dog): Long

    @Insert
    abstract suspend fun addPup(pup: Pup): Long

    @Insert
    abstract suspend fun addPerson(person: Person): Long

    @Query("SELECT * FROM person")
    abstract suspend fun getAllPersons(): List<Person>

    @Transaction
    open suspend fun getAllOwners(): List<Owner> {
        val owners = getAllPersons()
        return owners.map { person ->
            Owner(
                person,
                getDogsByOwnerId(person.id)
            )
        }
    }
}