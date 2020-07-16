package com.usacheow.di

import com.usacheow.coredata.CoreDataProvider
import com.usacheow.coremediator.MediatorProvider
import com.usacheow.coreui.CoreUiProvider

interface DiProvider : CoreUiProvider, MediatorProvider, CoreDataProvider