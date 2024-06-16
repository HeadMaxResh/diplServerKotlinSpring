package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import jakarta.persistence.*
import java.util.Collections.emptyList


@Entity(name = Table.TABLE_APARTMENT)
data class ApartmentInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    val name: String,
    val city: String,
    val rent: Int,
    val area: Float,
    val listImages: List<String>,
    val countRooms: Int,
    var rate: Double,
    @ManyToOne
    val userOwner: User,
    //@Lob
    val description: String,
    @OneToMany
    val reviewList: MutableList<Review> = emptyList()
)