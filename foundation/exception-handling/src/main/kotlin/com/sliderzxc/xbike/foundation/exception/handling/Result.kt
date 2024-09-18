package com.sliderzxc.xbike.foundation.exception.handling

interface ProjectException

sealed interface Result<out D, out E: ProjectException> {
    data class Success<out D, out E: ProjectException>(val data: D): Result<D, E>
    data class Error<out D, out E: ProjectException>(val error: E): Result<D, E>
}