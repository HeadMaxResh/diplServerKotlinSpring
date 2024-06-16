package com.diplback.diplserver.controller


import com.diplback.diplserver.dto.InilaDto
import com.diplback.diplserver.model.Inila

import com.diplback.diplserver.repository.InilaRepo
import com.diplback.diplserver.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/inila")
class InilaController {

    @Autowired
    lateinit var inilaRepo: InilaRepo
    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping("/{userId}")
    fun getInila(
        @PathVariable userId: Int
    ): Inila? = inilaRepo.findInila(userId)

    @PostMapping("/{userId}/add")
    fun setInila(
        @PathVariable userId: Int,
        @RequestBody inilaDto: InilaDto,
    ): Inila = inilaRepo.save(
        Inila(inilaNumber = inilaDto.inilaNumber, user = userRepo.findById(userId).get())
    )
}