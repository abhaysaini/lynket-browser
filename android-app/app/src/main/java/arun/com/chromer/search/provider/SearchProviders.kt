package arun.com.chromer.search.provider

import android.net.Uri
import androidx.core.net.toUri
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

data class SearchProvider(
        val name: String,
        val iconUri: Uri,
        val searchUrlPrefix: String
) {
    fun getSearchUrl(text: String) = searchUrlPrefix + text.replace(" ", "+")
}

@Singleton
class SearchProviders
@Inject constructor() {

    val availableProviders: Single<List<SearchProvider>> = Single.fromCallable {
        listOf(
                GOOGLE_SEARCH_PROVIDER,
                SearchProvider(
                        name = DUCKDUCKGO,
                        iconUri = "https://lh3.googleusercontent.com/8GiPaoaCopqI1AiBajxwx91ndKDeeAI-p2w7hDZlG7yi6KoXJ5bzWA0VteFpTAB5uhM=s192-rw".toUri(),
                        searchUrlPrefix = "https://duckduckgo.com/?q="
                ),
                SearchProvider(
                        name = BING,
                        iconUri = "https://lh3.googleusercontent.com/0aRIOVqPu3KKUh6FFSmo1jkQMIeTqgGvHNo4mHl_NUzJxGGd2m0jaUoRdhGcgaa-ug=s192-rw".toUri(),
                        searchUrlPrefix = "https://www.bing.com/search?q="
                ),
                SearchProvider(
                        name = QWANT,
                        iconUri = "https://lh3.googleusercontent.com/gZM93E0coPblwJysaGbAVgTRXPld0ZDRtrbmclDqWWrPJLKIjyVB9XKqOX8OM9_3GJI=s192-rw".toUri(),
                        searchUrlPrefix = "https://www.qwant.com/?q="
                )
        )
    }

    companion object {
        const val GOOGLE = "Google"
        const val DUCKDUCKGO = "Duck Duck Go"
        const val BING = "Bing"
        const val QWANT = "Qwant"

        val GOOGLE_SEARCH_PROVIDER = SearchProvider(
                name = GOOGLE,
                iconUri = "https://lh3.googleusercontent.com/DKoidc0T3T1KvYC2stChcX9zwmjKj1pgmg3hXzGBDQXM8RG_7JjgiuS0CLOh8DUa7as=s192-rw".toUri(),
                searchUrlPrefix = "https://www.google.com/search?q="
        )
    }
}