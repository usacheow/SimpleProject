package com.kapmayn.domain.di

import com.kapmayn.data.di.DataProvider
import com.kapmayn.network.di.NetworkProvider

interface DomainProvider : DataProvider, NetworkProvider