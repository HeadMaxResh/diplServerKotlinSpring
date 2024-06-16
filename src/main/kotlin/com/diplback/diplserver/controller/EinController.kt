package com.diplback.diplserver.controller

import com.diplback.diplserver.dto.EinDto
import com.diplback.diplserver.model.Ein
import com.diplback.diplserver.repository.EinRepo
import com.diplback.diplserver.repository.UserRepo
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
    ): Ein = einRepo.save(
        Ein(einNumber = einDto.einNumber, user = userRepo.findById(userId).get())
    )
}