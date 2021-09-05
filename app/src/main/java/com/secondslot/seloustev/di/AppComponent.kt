package com.secondslot.seloustev.di

import com.secondslot.seloustev.data.db.di.DbModule
import com.secondslot.seloustev.data.repository.RepositoryImpl
import com.secondslot.seloustev.mainscreen.vm.PictureViewModel
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DbModule::class
    ]
)
interface AppComponent {

    fun injectPictureViewModel(viewModel: PictureViewModel)

    fun injectRepository(repository: RepositoryImpl)
}