package com.diplback.diplserver.repository

import com.diplback.diplserver.Table
import com.diplback.diplserver.model.ResponseApartment
import com.diplback.diplserver.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ResponseApartmentRepo : CrudRepository<ResponseApartment, Int> {

    @Query("SELECT r FROM ${Table.TABLE_APARTMENT_RESPONSE} r WHERE r.apartmentInfo.userOwner.id = :userOwnerId")
    fun findAllByApartmentInfoUserOwner(userOwnerId: Int): List<ResponseApartment>

    @Query("SELECT r FROM ${Table.TABLE_APARTMENT_RESPONSE} r WHERE r.user.id = :userId")
    fun findAllByUser(userId: Int): List<ResponseApartment>

    fun findAllByStatus(status: String): List<ResponseApartment>
}