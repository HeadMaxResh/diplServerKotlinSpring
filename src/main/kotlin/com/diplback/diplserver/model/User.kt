package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import com.diplback.diplserver.controller.EinController
import com.diplback.diplserver.controller.PassportController
import jakarta.persistence.*
import org.springframework.util.DigestUtils
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

@Entity(name = Table.TABLE_USER)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    var name: String,
    var surname: String,
    var email: String = "",
    //@Column(unique = true)
    var phone: String,
    //@Convert(converter = EncryptedAttributeConverter::class)
    var password: String,
    var photoUser: String?,
    val rate: Int = 5,
    @ManyToMany
    val favoriteApartments: MutableList<ApartmentInfo> = mutableListOf(),
    @OneToOne
    val passport: Passport? = null,
    @OneToOne
    val ein: Ein? = null,
    @Column(length = 512)
    var electronicSignature: String? = null
    /*@OneToOne
    val inila: Inila? = null,
    @OneToMany(mappedBy = "user")
    val apartmentList: List<ApartmentInfo> = emptyList()*/
){
    fun generateElectronicSignature(): String {
        /*val ein = einController.getEin(userId)
        val passport = passportController.getPassport(userId)*/

        /*val passportData = "${passport?.name}${passport?.lastname}${passport?.surname}${passport?.series}${passport?.number}${passport?.registration}"
        val einData = ein?.einNumber.toString()

        val combinedData = "$passportData$einData"

        val electronicSignature = hash(combinedData)

        return electronicSignature*/

        val passportData = "${passport?.name}${passport?.lastname}${passport?.surname}${passport?.series}${passport?.number}${passport?.registration}"
        val einData = ein?.einNumber.toString()
        val userData = "${name}${surname}${email}${phone}" // Include user-specific data

        val combinedData = "$passportData$einData$userData"

        val electronicSignature = hash(combinedData)

        return electronicSignature
    }

    private fun hash(data: String): String {
        /*val messageDigest = MessageDigest.getInstance("SHA-256")
        val hashBytes = messageDigest.digest(data.toByteArray())
        return Base64.getEncoder().encodeToString(hashBytes)*/

        val messageDigest = MessageDigest.getInstance("MD5")
        val hashBytes = messageDigest.digest(data.toByteArray())

        val hexString = BigInteger(1, hashBytes).toString(16)

        return hexString.padStart(32, '0')
    }

    fun updateElectronicSignature(electronicSignature: String) {
        this.electronicSignature = electronicSignature
    }
}
