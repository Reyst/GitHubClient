package reyst.gsihome.client.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import reyst.gsihome.client.ui.details.RepoDetailsViewModel
import reyst.gsihome.client.ui.login.LoginViewModel
import reyst.gsihome.client.ui.user.UserViewModel

val loginModule = module {
    viewModel { LoginViewModel(get()) }
}

val userModule = module {
    viewModel { UserViewModel(get()) }
}

val detailsModule = module {
    viewModel { RepoDetailsViewModel(get()) }
}