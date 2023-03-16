package com.dashboardk.backend.di

import org.koin.core.context.GlobalContext

inline fun <reified T> inject(): T = GlobalContext.get().get()
