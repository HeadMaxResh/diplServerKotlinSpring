package com.diplback.diplserver.service

import com.diplback.diplserver.controller.EinController
import com.diplback.diplserver.controller.PassportController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*


@Service
class ElectronicSignatureService {

    @Autowired
    lateinit var einController: EinController

    @Autowired
    lateinit var passportController: PassportController

    fun generateElectronicSignature(userId: Int): String {
        val ein = einController.getEin(userId)
        val passport = passportController.getPassport(userId)

        val passportData = "${passport?.name}${passport?.lastname}${passport?.surname}${passport?.series}${passport?.number}${passport?.registration}"
        val einData = ein?.einNumber.toString()

        val combinedData = "$passportData$einData"


        val electronicSignature = hash(combinedData)

        return electronicSignature
    }

    private fun hash(data: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val hashBytes = messageDigest.digest(data.toByteArray())
        return Base64.getEncoder().encodeToString(hashBytes)
    }
}


