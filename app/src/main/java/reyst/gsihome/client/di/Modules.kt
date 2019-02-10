package reyst.gsihome.client.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import reyst.gsihome.client.data.DataSource
import reyst.gsihome.client.data.DefaultRepository
import reyst.gsihome.client.data.retrofit.ApiService
import reyst.gsihome.client.data.retrofit.RetrofitDataSource
import reyst.gsihome.client.domain.Repository

val retrofitModule = module {

    single<OkHttpClient> { OkHttpClient.Builder().build() }

    single<Gson> { GsonBuilder().create() }

    single {
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }
}

val apiModule = module {
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}

val dataModule = module {

    single<DataSource> {
        RetrofitDataSource(get())
    }
}

val repositoryModule = module {
    single<Repository> { DefaultRepository(get()) }
}
