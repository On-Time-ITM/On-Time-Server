package org.itm.ontime.infrastructure.external.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ResourceUtils

@Configuration
class FCMConfig(
    @Value("\${fcm.key.path}")
    private val key: String
){
    @Bean
    fun firebaseManager() : FirebaseApp {
        val account = ResourceUtils.getFile(key).inputStream()

        return try {
            FirebaseApp.getInstance()
        } catch (e: IllegalStateException) {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(account))
                .build()
            FirebaseApp.initializeApp(options)
        }
    }

}