package com.diplback.diplserver.repository

import com.diplback.diplserver.Table
import com.diplback.diplserver.model.Contract
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ContractRepo : CrudRepository<Contract, Int> {

    @Query("SELECT c FROM ${Table.TABLE_CONTRACT} c WHERE c.userSender.id = :userId")
    fun findByUserSenderId(userId: Int): List<Contract>

    @Query("SELECT c FROM ${Table.TABLE_CONTRACT} c WHERE c.userOwner.id = :userId")
    fun findByUserOwnerId(userId: Int): List<Contract>

    @Query("SELECT c FROM ${Table.TABLE_CONTRACT} c WHERE c.apartmentInfo.id = :apartmentInfoId")
    fun findByApartmentInfoId(apartmentInfoId: Int): List<Contract>

    @Query("SELECT c FROM ${Table.TABLE_CONTRACT} c WHERE c.userSender.id = :userSenderId AND c.userOwner.id = :userOwnerId AND c.apartmentInfo.id = :apartmentInfoId")
    fun findByUserSenderAndUserOwnerAndApartmentInfo(userSenderId: Int, userOwnerId: Int, apartmentInfoId: Int): Contract?

    @Query("SELECT c FROM ${Table.TABLE_CONTRACT} c WHERE c.userSender.id = :userId OR c.userOwner.id = :userId")
    fun findContractsByUserSenderIdOrUserOwnerId(userId: Int): List<Contract>
}
