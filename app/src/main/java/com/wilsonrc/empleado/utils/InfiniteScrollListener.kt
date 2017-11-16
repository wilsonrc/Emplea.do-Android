package com.wilsonrc.empleado.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by W on 13/11/2017.
 */
class InfiniteScrollListener(val func: () -> Unit, val layoutManager: LinearLayoutManager ) : RecyclerView.OnScrollListener(){

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if(dy > 0){
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()

            if(loading){
                if(totalItemCount > previousTotal){
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            if(!loading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)){

                Log.i("InfiniteScrollListener","End Reached")
                func()
                loading = true

            }

        }

    }
}