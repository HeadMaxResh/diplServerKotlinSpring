package com.diplback.diplserver.repository

import com.diplback.diplserver.Table
import com.diplback.diplserver.model.ApartmentInfo
import com.diplback.diplserver.model.Review
import org.hibernate.internal.util.collections.CollectionHelper.listOf
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ApartmentInfoRepo : CrudRepository<ApartmentInfo, Int> {

    @Query("SELECT u FROM ${Table.TABLE_APARTMENT} u")
    fun findAllApartments(): List<ApartmentInfo>

    @Query("SELECT u FROM ${Table.TABLE_APARTMENT} u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :q, '%'))")
    fun findByName(@Param("q") query: String): List<ApartmentInfo>

    @Query("SELECT u FROM ${Table.TABLE_APARTMENT} u " +
            "WHERE (:city IS NULL OR u.city LIKE :city) AND " +
            "u.rent BETWEEN :min_rent AND :max_rent AND " +
            "u.area BETWEEN :min_area AND :max_area AND " +
            "(:count_rooms IS NULL OR u.countRooms = :count_rooms)")
    fun findByFilter(
        @Param("city") city: String?,
        @Param("min_rent") minRent: Int,
        @Param("max_rent") maxRent: Int,
        @Param("min_area") minArea: Float,
        @Param("max_area") maxArea: Float,
        @Param("count_rooms") countRooms: Int?
    ): List<ApartmentInfo>

    @Query("SELECT a FROM ${Table.TABLE_APARTMENT} a WHERE a.id = :id")
    fun findByApartmentId(
        @Param("id") id: Int
    ): ApartmentInfo

    @Query("SELECT a FROM ${Table.TABLE_APARTMENT} a WHERE a.userOwner.id = :userOwnerId")
    fun findAllApartmentsByUser(@Param("userOwnerId") userOwnerId: Int): List<ApartmentInfo>

    @Query("SELECT a.reviewList FROM ${Table.TABLE_APARTMENT} a WHERE a.id = :id")
    fun findReviewsByApartmentId(@Param("id") id: Int): List<Review>
}