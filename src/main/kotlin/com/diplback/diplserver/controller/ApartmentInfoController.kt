package com.diplback.diplserver.controller

import com.diplback.diplserver.dto.ApartmentInfoDto
import com.diplback.diplserver.dto.ReviewDto
import com.diplback.diplserver.model.ApartmentInfo
import com.diplback.diplserver.model.Review
import com.diplback.diplserver.repository.ApartmentInfoRepo
import com.diplback.diplserver.repository.ReviewRepo
import com.diplback.diplserver.repository.UserRepo
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kotlin.math.roundToInt

@RestController
@RequestMapping("/apartments")
class ApartmentInfoController {

    @Autowired
    lateinit var apartmentInfoRepo: ApartmentInfoRepo

    @Autowired
    lateinit var userRepo: UserRepo

    @Autowired
    lateinit var reviewRepo: ReviewRepo

    @GetMapping("/all")
    fun getAllApartments(): List<ApartmentInfo>? = apartmentInfoRepo.findAllApartments()

    @GetMapping("/search/{name}")
    fun getByName(
        @PathVariable name: String
    ): List<ApartmentInfo>? = apartmentInfoRepo.findByName(name)

    @GetMapping("/filter/{city}/{minRent}/{maxRent}/{minArea}/{maxArea}/{countRooms}")
    fun getByFilter(
        @PathVariable city: String,
        @PathVariable minRent: Int,
        @PathVariable maxRent: Int,
        @PathVariable minArea: Float,
        @PathVariable maxArea: Float,
        @PathVariable countRooms: Int?
    ): List<ApartmentInfo>? = apartmentInfoRepo.findByFilter(
        city,
        minRent,
        maxRent,
        minArea,
        maxArea,
        countRooms
    )

    @GetMapping("/{id}")
    fun getByApartmentId(
        @PathVariable id: Int
    ): ApartmentInfo = apartmentInfoRepo.findByApartmentId(id)

    @GetMapping("/userapartments/{userOwnerId}")
    fun getByAllApartmentsByUser(
        @PathVariable userOwnerId: Int
    ): List<ApartmentInfo> = apartmentInfoRepo.findAllApartmentsByUser(userOwnerId)

    @PostMapping("/add")
    fun setApartment(
        //@PathVariable userId: Int,
        @RequestBody apartmentInfoDto: ApartmentInfoDto,
    ): ApartmentInfo {

        val user =
            userRepo.findById(apartmentInfoDto.userOwner.id).orElseThrow { EntityNotFoundException("User not found") }

        val apartmentInfo = ApartmentInfo(
            name = apartmentInfoDto.name,
            city = apartmentInfoDto.city,
            area = apartmentInfoDto.area,
            countRooms = apartmentInfoDto.countRooms,
            description = apartmentInfoDto.description,
            rent = apartmentInfoDto.rent,
            userOwner = user,
            listImages = apartmentInfoDto.listImages,
            rate = apartmentInfoDto.rate
        )
        /*return apartmentInfoRepo.save(
            ApartmentInfo(
                name = apartmentInfoDto.name,
                city = apartmentInfoDto.city,
                area = apartmentInfoDto.area,
                countRooms = apartmentInfoDto.countRooms,
                description = apartmentInfoDto.description,
                rent = apartmentInfoDto.rent,
                userOwner = userRepo.findById(userId).get(),
                listImages = apartmentInfoDto.listImages
            )
        )*/
        return apartmentInfoRepo.save(apartmentInfo)
    }




    @PostMapping("/{apartmentId}/review/add")
    fun addReviewToApartment(
        @PathVariable apartmentId: Int,
        @RequestBody reviewDto: ReviewDto
    ): Review {
        val apartmentInfo = apartmentInfoRepo.findByApartmentId(apartmentId)

        val user = userRepo.findById(reviewDto.user.id).orElseThrow { EntityNotFoundException("User not found") }

        val existingReview = apartmentInfo.reviewList.find { it.user.id == user.id }

        if (existingReview != null) {

            existingReview.rate = reviewDto.rate
            existingReview.dignityText = reviewDto.dignityText
            existingReview.flawsText = reviewDto.flawsText
            existingReview.commentText = reviewDto.commentText


            val updatedReview = reviewRepo.save(existingReview)
            updateApartmentRating(apartmentInfo)
            apartmentInfoRepo.save(apartmentInfo)
            return updatedReview
        }

        val review = Review(
            rate = reviewDto.rate,
            user = user,
            dignityText = reviewDto.dignityText,
            flawsText = reviewDto.flawsText,
            commentText = reviewDto.commentText,
            //apartmentInfo = apartmentInfo
        )
        /*val savedReview = reviewRepo.save(review)

        val updatedApartmentInfo = apartmentInfo.apply {
            reviewList.add(savedReview)
        }
        apartmentInfoRepo.save(updatedApartmentInfo)

        return savedReview*/

        apartmentInfo.reviewList.add(review)

        val savedReview = reviewRepo.save(review)
        updateApartmentRating(apartmentInfo)
        apartmentInfoRepo.save(apartmentInfo)

        return review
    }

    private fun updateApartmentRating(apartmentInfo: ApartmentInfo) {
        val totalReviews = apartmentInfo.reviewList.size
        if (totalReviews > 0) {
            val totalRating = apartmentInfo.reviewList.sumOf { it.rate }
            val averageRating = totalRating.toDouble() / totalReviews
            val roundedRating = (averageRating * 10).roundToInt() / 10.0
            apartmentInfo.rate = roundedRating
        } else {
            apartmentInfo.rate = 0.0 // Если нет отзывов, средняя оценка равна 0
        }
    }
}