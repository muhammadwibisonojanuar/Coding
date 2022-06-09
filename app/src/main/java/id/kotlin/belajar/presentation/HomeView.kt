package id.kotlin.belajar.presentation

interface HomeView {
    fun onShowLoading()
    fun onHideLoading()
    fun onResponse(results: List<Result>)
    fun onFailure(t: Throwable)
}