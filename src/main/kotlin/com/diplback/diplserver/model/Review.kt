package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Entity(name = Table.TABLE_REVIEW)
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    /*@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val date: LocalDateTime = LocalDateTime.MIN,*/
    var rate: Int,
    @ManyToOne
    val user: User,
    /*@ManyToOne
    val apartmentInfo: ApartmentInfo,*/
    //@Lob
    var dignityText: String,
    //@Lob
    var flawsText: String,
    //@Lob
    var commentText: String
)