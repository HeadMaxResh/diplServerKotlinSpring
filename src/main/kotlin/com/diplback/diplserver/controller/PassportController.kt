package com.diplback.diplserver.controller

import com.diplback.diplserver.dto.PassportDto
import com.diplback.diplserver.model.Passport
import com.diplback.diplserver.repository.PassportRepo
import com.diplback.diplserver.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/passport")
class PassportController {

    @Autowired
    lateinit var passportRepo: PassportRepo
    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping("/{userId}")
    fun getPassport(
        @PathVariable userId: Int,
    ): ResponseEntity<Passport?>{
        val passport = passportRepo.findPassport(userId)
        return ResponseEntity.ok(passport)
    }

    @PostMapping("/{userId}/add")
    fun setPassport(
        @PathVariable userId: Int,
        @RequestBody passportDto: PassportDto,
    ): Passport {

        val existingPassport = passportRepo.findPassport(userId)

        return if (existingPassport != null) {
            existingPassport.apply {
                name = passportDto.name
                lastname = passportDto.lastname
                surname = passportDto.surname
                series = passportDto.series
                number = passportDto.number
                registration = passportDto.registration
                user = userRepo.findById(userId).get()
            }
            passportRepo.save(existingPassport)
        } else {
            passportRepo.save(
                Passport(
                    name = passportDto.name,
                    lastname = passportDto.lastname,
                    surname = passportDto.surname,
                    number = passportDto.number,
                    series = passportDto.series,
                    registration = passportDto.registration,
                    user = userRepo.findById(userId).get(),
                )
            )
        }
    }

}