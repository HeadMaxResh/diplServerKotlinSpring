package com.diplback.diplserver.controller

import com.diplback.diplserver.model.ApartmentInfo
import com.diplback.diplserver.model.ResponseApartment
import com.diplback.diplserver.repository.ApartmentInfoRepo
import com.diplback.diplserver.repository.ResponseApartmentRepo
import com.diplback.diplserver.repository.UserRepo
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/responses")
class ResponseApartmentController {

    @Autowired
    lateinit var responseApartmentRepo: ResponseApartmentRepo

    @Autowired
    lateinit var apartmentInfoRepo: ApartmentInfoRepo

    @Autowired
    lateinit var userRepo: UserRepo

    @PostMapping("/add/{apartmentId}/user/{userId}")
    fun addResponseToApartment(
        @PathVariable apartmentId: Int,
        @PathVariable userId: Int
    ): ResponseApartment {
        val apartment = apartmentInfoRepo.findByApartmentId(apartmentId)
        val user = userRepo.findById(userId).orElseThrow { EntityNotFoundException("Пользователь не найден") }

        val response = ResponseApartment(apartmentInfo = apartment, user = user)
        return responseApartmentRepo.save(response)
    }

    @GetMapping("/owner/{userId}")
    fun getResponsesForApartmentOwner(@PathVariable userId: Int): List<ResponseApartment> {
        return responseApartmentRepo.findAllByApartmentInfoUserOwner(userId)
    }

    @GetMapping("/user/{userId}")
    fun getSentResponsesForUser(@PathVariable userId: Int): List<ResponseApartment> {
        return responseApartmentRepo.findAllByUser(userId)
    }

    @PutMapping("/{responseId}/status/{status}")
    fun updateResponseStatus(
        @PathVariable responseId: Int,
        @PathVariable status: String
    ): ResponseApartment {
        val response = responseApartmentRepo.findById(responseId)
            .orElseThrow { EntityNotFoundException("Отклик не найден") }

        response.status = status
        return responseApartmentRepo.save(response)
    }

    @GetMapping("/{responseId}/status")
    fun getResponseStatus(@PathVariable responseId: Int): String {
        val response = responseApartmentRepo.findById(responseId)
            .orElseThrow { EntityNotFoundException("Отклик не найден") }

        return response.status
    }

    @GetMapping("/status/{status}")
    fun getResponsesByStatus(@PathVariable status: String): List<ResponseApartment> {
        return responseApartmentRepo.findAllByStatus(status)
    }

}