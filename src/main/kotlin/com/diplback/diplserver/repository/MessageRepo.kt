package com.diplback.diplserver.repository

import com.diplback.diplserver.model.Message
import com.diplback.diplserver.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MessageRepo : CrudRepository<Message, Int> {

    fun findBySenderIdOrReceiverId(senderId: Int, receiverId: Int): List<Message>

    @Query("SELECT DISTINCT m.senderId FROM Message m WHERE m.receiverId = :userId")
    fun findDistinctSenderIdsByUserId(@Param("userId") userId: Int): List<Int>

    @Query("SELECT DISTINCT m.receiverId FROM Message m WHERE m.senderId = :userId")
    fun findDistinctReceiverIdsByUserId(@Param("userId") userId: Int): List<Int>

    fun findBySenderIdAndReceiverId(senderId: Int, receiverId: Int): List<Message>
}