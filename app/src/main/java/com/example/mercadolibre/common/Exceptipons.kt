package com.example.mercadolibre.common

import java.io.IOException
import java.lang.Exception

/**
 * @author Axel Sanchez
 */
open class RepositoryException(private val messageException: String, cause: Throwable? = null): Exception(messageException, cause)

class UiException(private val uiMessage: String): Exception(uiMessage)
class ServerException(private val serverMessage: String, cause: Throwable? = null): RepositoryException(serverMessage, cause)
class CacheException(private val cacheMessage: String, cause: Throwable? = null): RepositoryException(cacheMessage, cause)
class NoConnectivityException: IOException("No Internet Connection")