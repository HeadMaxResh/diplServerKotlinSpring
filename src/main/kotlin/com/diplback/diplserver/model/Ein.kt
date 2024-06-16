package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import jakarta.persistence.*

@Entity(name = Table.TABLE_EIN)
data class Ein(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    //@Convert(converter = EncryptedAttributeConverter::class)
    val einNumber: Long,
    @OneToOne
    val user: User
)