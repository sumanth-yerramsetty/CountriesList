package com.example.sampleproject.network.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for holding API response data schema
 */
data class Country(
    @SerializedName("capital") var capital: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("currency") var currency: Currency? = null,
    @SerializedName("flag") var flag: String? = null,
    @SerializedName("language") var language: Language? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("region") var region: String? = null,
)

data class Currency(
    @SerializedName("code") var code: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("symbol") var symbol: String? = null,
)

data class Language(
    @SerializedName("code") var code: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("symbol") var symbol: String? = null,
)