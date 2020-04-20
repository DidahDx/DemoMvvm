package com.didahdx.mvvmsampleapp

import android.app.Application
import com.didahdx.mvvmsampleapp.data.db.AppDatabase
import com.didahdx.mvvmsampleapp.data.network.MyApi
import com.didahdx.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.didahdx.mvvmsampleapp.data.repositories.UserRepository
import com.didahdx.mvvmsampleapp.ui.auth.AuthViewModelFactory
import com.didahdx.mvvmsampleapp.ui.home.profile.ProfileViewModel
import com.didahdx.mvvmsampleapp.ui.home.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MvvmApplication() : Application(),KodeinAware {

    override val kodein=Kodein.lazy {
        import(androidXModule(this@MvvmApplication))

        bind() from singleton{ NetworkConnectionInterceptor(instance()) }
        bind() from singleton{ MyApi(instance()) }
        bind() from singleton{ AppDatabase(instance()) }
        bind() from singleton{ UserRepository(instance(),instance()) }
        bind() from provider{AuthViewModelFactory(instance())}
        bind() from provider { ProfileViewModelFactory(instance()) }
    }

}