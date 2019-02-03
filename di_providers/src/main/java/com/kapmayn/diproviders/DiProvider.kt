package com.kapmayn.diproviders

import com.kapmayn.database.di.DataBaseProvider
import com.kapmayn.datanetwork.di.NetworkProvider

interface DiProvider : CoreProvider, FeatureApiProvider, NetworkProvider, DataBaseProvider