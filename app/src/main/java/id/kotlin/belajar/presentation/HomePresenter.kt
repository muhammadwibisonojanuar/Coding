package id.kotlin.belajar.presentation

import id.kotlin.belajar.data.HomeDatasource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

class HomePresenter(
    private val view: HomeView,
    private val datasource: HomeDatasource
    ) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun discoverMovie(){
        view.onShowLoading()
        datasource.discoverMovie()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {response-> view.onHideLoading(); view.onResponse(response)},
                { error -> view.onHideLoading(); view.onFailure(error)}
            ).addTo(disposables)
    }

    fun onDetach(){
        disposables.clear()
    }
}
