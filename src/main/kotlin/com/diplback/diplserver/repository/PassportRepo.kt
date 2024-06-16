package com.diplback.diplserver.repository

import com.diplback.diplserver.Table
import com.diplback.diplserver.model.Passport
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface PassportRepo : CrudRepository<Passport, Int> {

    @Query("SELECT u FROM ${Table.TABLE_PASSPORT} u WHERE u.user.id = :userId")
    fun findPassport(@Param("userId") userId: Int): Passport?

}