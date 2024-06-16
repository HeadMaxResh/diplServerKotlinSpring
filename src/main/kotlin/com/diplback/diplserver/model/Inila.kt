package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import jakarta.persistence.*

@Entity(name = Table.TABLE_INILA)
data class Inila(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    //@Convert(converter = EncryptedAttributeConverter::class)
    val inilaNumber: Int,
    @OneToOne
    val user: User,
)