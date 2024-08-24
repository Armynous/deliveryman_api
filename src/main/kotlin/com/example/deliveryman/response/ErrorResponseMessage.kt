package com.example.deliveryman.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class ErrorResponseMessage (
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val timeStamp: Date = Date(),
    var errorMessage: String
)