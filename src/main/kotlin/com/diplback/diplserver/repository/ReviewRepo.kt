package com.diplback.diplserver.repository

import com.diplback.diplserver.Table.TABLE_REVIEW
import com.diplback.diplserver.model.Review
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ReviewRepo: CrudRepository<Review, Int> {

    /*@Query("SELECT r FROM $TABLE_REVIEW r WHERE r.apartmentInfo.id = :apartmentInfoId")
    fun findApartmentReviews(apartmentInfoId: Int): List<Review>?

    @Query("SELECT r FROM $TABLE_REVIEW r WHERE r.apartmentInfo.userOwner.id = :userId")
    fun findReviewsForApartmentOwner(userId: Int): List<Review>?*/


}