package com.diplback.diplserver.controller

import com.diplback.diplserver.dto.PassportDto
import com.diplback.diplserver.model.Passport
import com.diplback.diplserver.repository.PassportRepo
import com.diplback.diplserver.repository.UserRepo
import com.diplback.diplserver.service.ElectronicSignatureService
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
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
    ): Passport? {
        return passportRepo.findPassport(userId)
    }

    @PostMapping("/{userId}/add")
    fun setPassport(
        @PathVariable userId: Int,
        @RequestBody passportDto: PassportDto,
    ): Passport {

        val existingPassport = passportRepo.findPassport(userId)
        val user = userRepo.findById(userId).orElseThrow { EntityNotFoundException("User not found") }


        val savedPassport = if (existingPassport != null) {
            existingPassport.apply {
                name = passportDto.name
                lastname = passportDto.lastname
                surname = passportDto.surname
                series = passportDto.series
                number = passportDto.number
                registration = passportDto.registration
                this.user = user //userRepo.findById(userId).get()


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
                    user = user //userRepo.findById(userId).get(),
                )
            )

        }

        user.updateElectronicSignature(user.generateElectronicSignature())
        userRepo.save(user)

        return savedPassport
    }

}