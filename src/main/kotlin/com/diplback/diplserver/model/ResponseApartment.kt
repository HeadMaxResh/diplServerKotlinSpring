package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import jakarta.persistence.*

@Entity(name = Table.TABLE_APARTMENT_RESPONSE)
data class ResponseApartment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    @ManyToOne
    val apartmentInfo: ApartmentInfo,
    @ManyToOne
    val user: User,
    var status: String = PENDING
) {
    companion object {
        const val PENDING = "В ожидании"
        const val REJECTED = "Одобрено"
        const val APPROVED = "Отклонено"

    }
}
