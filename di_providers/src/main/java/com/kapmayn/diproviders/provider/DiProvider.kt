package com.kapmayn.diproviders.provider

import com.kapmayn.database.di.DataBaseProvider
import com.kapmayn.datanetwork.di.NetworkProvider

interface DiProvider : CoreProvider, MediatorProvider, NetworkProvider, DataBaseProvider