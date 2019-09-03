package com.kotlinandroidstarter.app.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_b.*
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.adapters.FragBAdapter
import com.kotlinandroidstarter.app.models.Thing
import com.kotlinandroidstarter.app.utils.ViewHelper
import com.kotlinandroidstarter.app.utils.toast
import com.kotlinandroidstarter.app.viewmodels.FragBViewModel
import com.vicpin.krealmextensions.save
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class BFragment : Fragment() {

    private val vm: FragBViewModel by viewModel()
    private val adapter by lazy { FragBAdapter(mutableListOf()) }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_b, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
        
        ViewHelper.setupRecyclerView(activity as Context, listItemsB)
        listItemsB.adapter = adapter
        
        vm.things.observe(this, Observer { 
            adapter.apply {
                things.clear()
                toast("added ${it.size} items to list")
                things.addAll(it.toList())
                notifyDataSetChanged()
            }
        })

        checkFilterItems.setOnCheckedChangeListener { buttonView, isChecked -> 
            vm.updateFilter(isChecked)
        }
        
        buttonDeleteAll.setOnClickListener {
            Realm.getDefaultInstance().use { realm ->
                val events = realm.where<Thing>().findAll()
                realm.executeTransaction { events.deleteAllFromRealm() }
            }
        }
        
        buttonAddMore.setOnClickListener { _ ->
            val lastOffset = Realm.getDefaultInstance().where<Thing>().count().toInt()
            val nextOffset = lastOffset + 10
            GlobalScope.launch(Dispatchers.Main) {
                (lastOffset..nextOffset).forEach {
                    
                    val fuckyou = Date()
                    val dtf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    val dname = dtf.format(fuckyou)
                    
                    /*Thing(
                        id = Date().time.toInt(),
                        name = "Thing $dname"
                    ).save()*/
                    Thing(
                        id = it,
                        name = "Thing #$it"
                    ).save()
                    
                    delay(500)
                    
                }
            }
        }
        
    }
    
}
