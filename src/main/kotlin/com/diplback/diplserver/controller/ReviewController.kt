package com.diplback.diplserver.controller

import com.diplback.diplserver.dto.ReviewDto
import com.diplback.diplserver.model.Review
import com.diplback.diplserver.repository.ApartmentInfoRepo
import com.diplback.diplserver.repository.ReviewRepo
import com.diplback.diplserver.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reviews")
class ReviewController {

   /* @Autowired
    lateinit var reviewRepo: ReviewRepo
    @Autowired
    lateinit var apartmentInfoRepo: ApartmentInfoRepo
    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping("/review/{apartmentInfoId}")
    fun getApartmentReviews(
        @PathVariable apartmentInfoId: Int
    ): List<Review>? = reviewRepo.findApartmentReviews(apartmentInfoId)

    @GetMapping("/review/{userId}")
    fun getReviewsForApartmentOwner(
        @PathVariable userId: Int
    ): List<Review>? = reviewRepo.findReviewsForApartmentOwner(userId)

    @PostMapping("/review/{userId}/add")
    fun setReview(
        @PathVariable userId: Int,
        @PathVariable apartmentInfoId: Int,
        @RequestBody reviewDto: ReviewDto
    ): Review {
        return reviewRepo.save(
            Review(
                rate = reviewDto.rate,
                user = userRepo.findById(userId).get(),
                commentText = reviewDto.commentText,
                dignityText = reviewDto.dignityText,
                flawsText = reviewDto.flawsText,
                //apartmentInfo = apartmentInfoRepo.findByApartmentId(apartmentInfoId)
            )
        )
    }*/
}