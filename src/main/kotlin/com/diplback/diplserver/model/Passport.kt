package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import jakarta.persistence.*

@Entity(name = Table.TABLE_PASSPORT)
data class Passport(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    //@Convert(converter = EncryptedAttributeConverter::class)
    var name: String,
    //@Convert(converter = EncryptedAttributeConverter::class)
    var lastname: String,
    //@Convert(converter = EncryptedAttributeConverter::class)
    var surname: String,
    //Convert(converter = EncryptedAttributeConverter::class)
    var series: String,
    //@Convert(converter = EncryptedAttributeConverter::class)
    var number: String,
    //@Convert(converter = EncryptedAttributeConverter::class)
    var registration: String,
    @OneToOne
    var user: User
)