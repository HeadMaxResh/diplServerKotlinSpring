package com.diplback.diplserver.model

import com.diplback.diplserver.Table
import jakarta.persistence.*

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
    val favoriteApartments: MutableList<ApartmentInfo> = mutableListOf()
    /*@OneToOne
    val passport: Passport? = null,
    @OneToOne
    val ein: Ein? = null,
    @OneToOne
    val inila: Inila? = null,
    @OneToMany(mappedBy = "user")
    val apartmentList: List<ApartmentInfo> = emptyList()*/
)
