package com.example.actioninputspranto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actioninputspranto.customdialogs.CustomAlertDialog
import com.example.actioninputspranto.databinding.FragmentScheduleListBinding
import com.example.actioninputspranto.db.ScheduleDB
import com.example.actioninputspranto.viewmodels.ScheduleViewModel

class ScheduleListFragment : Fragment() {
    private lateinit var binding: FragmentScheduleListBinding
    private val viewModel:ScheduleViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleListBinding.inflate(inflater,container,false)
        val scheduleAdapter = ScheduleAdapter (::onMenuItemClicked)
        binding.scheduleRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.scheduleRV.adapter = scheduleAdapter

            viewModel.getAllSchedules().observe(viewLifecycleOwner){
                scheduleList->
                scheduleAdapter.submitList(scheduleList)
            }


        binding.scheduleRV.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d("ScheduleListFragment","dx: $dx, dy: $dy")
                if (dy>0){
                    binding.fab.hide()
                }
                if (dy<0){
                    binding.fab.show()
                }


            }
        })
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleListFragment_to_newScheduleFragment)
        }
        return binding.root
    }
    private fun onMenuItemClicked(busSchedule: BusSchedule,action: RowAction){
        when(action){
            RowAction.EDIT -> {

            }
            RowAction.DELETE -> {
                CustomAlertDialog(
                    icon = R.drawable.ic_baseline_delete_24,
                    title = "Delete ${busSchedule.name}?",
                    body = "Are you sure to delete this item ?",
                    positiveButtonText = "YES",
                    negativeButtonText = "CANCEL"
                ){
                    viewModel.deleteSchedule(busSchedule)
                }.show(childFragmentManager,null)

            }
        }
    }

}