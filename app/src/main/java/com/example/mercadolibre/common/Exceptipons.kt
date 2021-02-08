package com.example.mercadolibre.common

import java.io.IOException
import java.lang.Exception

/**
 * @author Axel Sanchez
 */
open class RepositoryException(private val messageException: String = "Repository Exception", cause: Throwable? = null): Exception(messageException, cause)

class UiException(val uiMessage: String = "Ui Exception"): Exception(uiMessage)
class ServerException(private val serverMessage: String = "Server Exception", cause: Throwable? = null): RepositoryException(serverMessage, cause)
class CacheException(private val cacheMessage: String = "Cache Exception", cause: Throwable? = null): RepositoryException(cacheMessage, cause)
class NoConnectivityException: IOException("No Internet Connection")