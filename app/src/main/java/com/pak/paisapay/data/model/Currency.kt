package com.pak.paisapay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, val key: String, val value: BigDecimal
)
