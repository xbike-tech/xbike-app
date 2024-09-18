package com.sliderzxc.xbike.sdk.google

import com.sliderzxc.xbike.foundation.exception.handling.ProjectException

enum class GoogleSignInException : ProjectException {
    GetCredentialException,
    GoogleIdTokenParsingException
}