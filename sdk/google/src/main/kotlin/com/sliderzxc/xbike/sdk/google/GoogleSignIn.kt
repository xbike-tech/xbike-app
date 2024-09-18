package com.sliderzxc.xbike.sdk.google

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.sliderzxc.xbike.foundation.exception.handling.Result
import java.security.MessageDigest
import java.util.UUID

class GoogleSignIn(
    private val context: Context,
    private val clientId: String
) {

    suspend fun signInWithGoogle() {
        val credentialManager = CredentialManager.create(context)

        val rawNonce = UUID.randomUUID().toString()
        val hashedNonce = hashNonce(rawNonce)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(clientId)
            .setNonce(hashedNonce)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(context, request)
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
            val googleIdToken = googleIdTokenCredential.idToken

            Log.d("MyLog", "SUCCESS: $googleIdToken")

            // Handle successful sign-in
        } catch (e: GetCredentialException) {
            Log.e("MyLog", "GetCredentialException: ${e.message}")
        } catch (e: GoogleIdTokenParsingException) {
            Log.e("MyLog", "GoogleIdTokenParsingException: ${e.message}")
        }
    }

    private fun hashNonce(nonce: String): String {
        val bytes = nonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}