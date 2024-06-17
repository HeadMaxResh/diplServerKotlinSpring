package com.diplback.diplserver.controller

import com.diplback.diplserver.dto.EinDto
import com.diplback.diplserver.model.Ein
import com.diplback.diplserver.repository.EinRepo
import com.diplback.diplserver.repository.UserRepo
import com.diplback.diplserver.service.ElectronicSignatureService
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ein")
class EinController {

    @Autowired
    lateinit var einRepo: EinRepo
    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping("/{userId}")
    fun getEin(
        @PathVariable userId: Int,
    ): Ein? = einRepo.findEin(userId)

    @PostMapping("/{userId}/add")
    fun setEin(
        @PathVariable userId: Int,
        @RequestBody einDto: EinDto,
    ): Ein {
        val existingEin = einRepo.findEin(userId)
        val user = userRepo.findById(userId).orElseThrow { EntityNotFoundException("User not found") }

        val savedEin = if (existingEin != null) {
            existingEin.apply {
                einNumber = einDto.einNumber
                this.user = user //userRepo.findById(userId).get()
            }
            einRepo.save(existingEin)
        } else {
            einRepo.save(
                Ein(einNumber = einDto.einNumber, user = user /*userRepo.findById(userId).get()*/)
            )
        }

        user.updateElectronicSignature(user.generateElectronicSignature())
        userRepo.save(user)

        return savedEin
    }


/*= einRepo.save(
        Ein(einNumber = einDto.einNumber, user = userRepo.findById(userId).get())
    )*/
}