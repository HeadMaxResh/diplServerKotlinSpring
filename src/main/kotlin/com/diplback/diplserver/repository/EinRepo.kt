package com.diplback.diplserver.repository

import com.diplback.diplserver.Table
import com.diplback.diplserver.model.Ein
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface EinRepo : CrudRepository<Ein, Int> {

    @Query("SELECT u FROM ${Table.TABLE_EIN} u WHERE u.user.id = :userId")
    fun findEin(@Param("userId") userId: Int): Ein?
}