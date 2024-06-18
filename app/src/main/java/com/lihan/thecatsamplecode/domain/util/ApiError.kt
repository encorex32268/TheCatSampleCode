package com.lihan.thecatsamplecode.domain.util

enum class ApiError: Error {
    NETWORK_ERROR,
    TIMEOUT,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    INTERNAL_SERVER_ERROR,
    BAD_REQUEST,
    UNKNOWN_ERROR,
    IOEXCEPTION
}