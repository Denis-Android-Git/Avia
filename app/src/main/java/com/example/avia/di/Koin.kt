package com.example.avia.di

import com.example.feature_tickets.data.jsonrepo.JsonRepo
import com.example.feature_tickets.data.repoImpl.Repoimpl
import com.example.feature_tickets.domain.repo.Repo
import com.example.feature_tickets.domain.usecase.GetAllTicketsUseCase
import com.example.feature_tickets.domain.usecase.GetListUseCase
import com.example.feature_tickets.domain.usecase.GetTicketsUseCase
import com.example.feature_tickets.viewmodel.LastTicketsVM
import com.example.feature_tickets.viewmodel.OfferViewModel
import com.example.feature_tickets.viewmodel.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

    single<Repo> { Repoimpl(get()) }

    factory { GetListUseCase(get()) }
    factory { GetTicketsUseCase(get()) }
    factory { GetAllTicketsUseCase(get()) }

    factory { JsonRepo() }

    viewModel { OfferViewModel(get()) }
    viewModel { TicketsViewModel(get()) }
    viewModel { LastTicketsVM(get()) }
}