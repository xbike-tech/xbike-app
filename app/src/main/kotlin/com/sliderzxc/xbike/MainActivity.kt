package com.sliderzxc.xbike

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.sliderzxc.xbike.style.system.thema.AppTheme
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val coroutineScope = rememberCoroutineScope()

                    GoogleSignInButton(innerPadding)
                }
            }
        }
    }

    @Composable
    fun GoogleSignInButton(paddings: PaddingValues) {
        val coroutineScope = rememberCoroutineScope()
        val context = LocalContext.current

        val onClick: () -> Unit = {
            val credentialManager = CredentialManager.create(context)

            // Generate a nonce and hash it with sha-256
            // Providing a nonce is optional but recommended
            val rawNonce = UUID.randomUUID().toString() // Generate a random String. UUID should be sufficient, but can also be any other random string.
            val bytes = rawNonce.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) } // Hashed nonce to be passed to Google sign-in


            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(BuildConfig.GOOGLE_AUTH_SERVER_CLIENT_ID)
                .setNonce(hashedNonce) // Provide the nonce if you have one
                .build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            coroutineScope.launch {
                try {
                    val result = credentialManager.getCredential(
                        request = request,
                        context = context,
                    )

                    val googleIdTokenCredential = GoogleIdTokenCredential
                        .createFrom(result.credential.data)

                    val googleIdToken = googleIdTokenCredential.idToken

                    Log.d("MyLog", "SUCCESS: $googleIdToken")

                    // Handle successful sign-in
                } catch (e: GetCredentialException) {
                    Log.d("MyLog", "GetCredentialException: ${e.message}")
                    // Handle GetCredentialException thrown by `credentialManager.getCredential()`
                } catch (e: GoogleIdTokenParsingException) {
                    Log.d("MyLog", "GoogleIdTokenParsingException: ${e.message}")
//                } catch (e: RestException) {
//                    // Handle RestException thrown by Supabase
//                } catch (e: Exception) {
                    // Handle unknown exceptions
                }
            }
        }

        Button(
            onClick = onClick,
            modifier = Modifier.padding(paddings)
        ) {
            Text("Sign in with Google")
        }
    }
}