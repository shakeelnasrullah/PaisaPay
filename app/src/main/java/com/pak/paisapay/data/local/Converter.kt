package com.pak.paisapay.data.local

import androidx.room.TypeConverter
import java.math.BigDecimal

object Converter {

  @TypeConverter
  fun fromBigDecimal(value: BigDecimal): String {
    return value.toString()
  }

  @TypeConverter
  fun stringToBigDecimal(value: String): BigDecimal {
    return BigDecimal(value)
  }
}