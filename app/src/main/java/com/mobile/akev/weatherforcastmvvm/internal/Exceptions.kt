package com.mobile.akev.weatherforcastmvvm.internal

import java.io.IOException

class NoConnectivityException(message: String?) : IOException(message)
class LocationPermissionNotGrantedException() : Exception()
class DateNotFoundException():java.lang.Exception()