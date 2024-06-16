package com.diplback.diplserver.repository

import com.diplback.diplserver.Table
import com.diplback.diplserver.model.Inila
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface InilaRepo : CrudRepository<Inila, Int> {

    @Query("SELECT u FROM ${Table.TABLE_INILA} u WHERE u.user.id LIKE :userId")
    fun findInila(@Param("userId") userId: Int): Inila?
}