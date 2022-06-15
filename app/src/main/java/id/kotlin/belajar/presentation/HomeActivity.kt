package id.kotlin.belajar.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.android.support.DaggerAppCompatActivity
import id.kotlin.belajar.databinding.ActivityHomeBinding
import javax.inject.Inject
import id.kotlin.belajar.data.Result

class HomeActivity : DaggerAppCompatActivity(), HomeView {
    @Inject
    lateinit var presenter: HomePresenter

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        presenter.discoverMovie()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun onShowLoading() {
        binding.pbHome.visibility = View.VISIBLE
    }
    override fun onHideLoading() {
        binding.pbHome.visibility = View.GONE
        binding.rvHome.visibility = View.VISIBLE
    }

    override fun onResponse(results: List<Result>) {
        binding.rvHome.addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
        binding.rvHome.adapter = HomeAdapter(results)
    }

    override fun onFailure(t:Throwable) {
        Log.e(HomeActivity::class.java.simpleName, "${t.printStackTrace()}")
    }
}