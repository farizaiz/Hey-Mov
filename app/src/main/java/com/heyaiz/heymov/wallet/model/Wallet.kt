package com.heyaiz.heymov.wallet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wallet(
    var tittle: String? = "",
    var date: String? = "",
    var money: Double,
    var status: String? = ""
): Parcelable