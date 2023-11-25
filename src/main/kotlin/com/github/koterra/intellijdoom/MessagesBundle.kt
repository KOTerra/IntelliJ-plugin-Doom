package com.github.koterra.intellijdoom

import com.intellij.DynamicBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

@NonNls
private const val MESSAGES_BUNDLE = "messages.MyBundle"
private const val URLS_BUNDLE = "gameUrls.GameURLsBundle"

object MessagesBundle : DynamicBundle(MESSAGES_BUNDLE) {

    @JvmStatic
    fun message(@PropertyKey(resourceBundle = MESSAGES_BUNDLE) key: String, vararg params: Any) =
            getMessage(key, *params)

    @Suppress("unused")
    @JvmStatic
    fun messagePointer(@PropertyKey(resourceBundle = MESSAGES_BUNDLE) key: String, vararg params: Any) =
            getLazyMessage(key, *params)
}

object UrlsBundle : DynamicBundle(URLS_BUNDLE) {
    fun get(@PropertyKey(resourceBundle = URLS_BUNDLE) key: String) =
            getMessage(key, null);


    fun getUrl(name: String?): String {
        return when (name) {
            get("DOOM") -> get("DOOM_URL")
            get("PRINCE_OF_PERSIA") -> get("PRINCE_OF_PERSIA_URL")
            get("MORTAL_KOMBAT") -> get("MORTAL_KOMBAT_URL")
            get("GTA") -> get("GTA_URL")
            get("SIM_CITY") -> get("SIM_CITY_URL")
            get("NEED_FOR_SPEED") -> get("NEED_FOR_SPEED_URL")
            get("LANDING_PAGE") -> get("LANDING_URL")
            else -> {
                get("LANDING_URL")
            }
        }
    }
}
