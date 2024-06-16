package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import jakarta.persistence.*

@Entity(name = Table.TABLE_CONTRACT)
class Contract(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    @ManyToOne
    val apartmentInfo: ApartmentInfo,
    @ManyToOne
    val userOwner: User,
    @ManyToOne
    val passportOwner: Passport,
    @ManyToOne
    val userSender: User,
    @ManyToOne
    val passportSender: Passport,
    val date: String?,
)