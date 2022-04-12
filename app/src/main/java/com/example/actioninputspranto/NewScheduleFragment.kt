package com.example.actioninputspranto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.actioninputspranto.customdialogs.DatePickerFragment
import com.example.actioninputspranto.customdialogs.TimePickerFragment
import com.example.actioninputspranto.databinding.FragmentNewScheduleBinding
import com.example.actioninputspranto.db.ScheduleDB


class NewScheduleFragment : Fragment() {
    private lateinit var binding: FragmentNewScheduleBinding
    private var from = "Dhaka"
    private var to = "Dhaka"
    private var busType = "Economy"
    private var selectedDate = ""
    private var selectedTime = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewScheduleBinding.inflate(inflater,container,false)
        initCitySpinner()
        initBusTypeRadioGroup()
        binding.dateBtn.setOnClickListener {
            DatePickerFragment {
                selectedDate = it
                binding.showDateTV.text = it
            }.show(childFragmentManager,null)
        }
        binding.timeBtn.setOnClickListener {
            TimePickerFragment{
                selectedTime = it
                binding.showTimeTV.text = it
            }.show(childFragmentManager,null)
        }

        binding.saveBtn.setOnClickListener {
            saveInfo()
        }

        return binding.root
    }
    private fun saveInfo() {
        val busName = binding.BusNameInputET.text.toString()
        //TODO: validate busName
        if (from == to){
            Toast.makeText(activity,"Origin and destination cannot be the same!",
                Toast.LENGTH_SHORT).show()
            return
        }
        val schedule = BusSchedule(
            id = System.currentTimeMillis(),
            name = busName,
            from = from,
            to = to,
            departureDate = selectedDate,
            departureTime = selectedTime,
            busType = busType
        )
        ScheduleDB
            .getDB(requireActivity())
            .getScheduleDao()
            .addSchedule(schedule)
        findNavController().navigate(R.id.action_newScheduleFragment_to_scheduleListFragment)

        Log.d("ScheduleInfoCheck","saveInfo: $schedule")
    }




    private fun initBusTypeRadioGroup() {
        binding.busTypeRG.setOnCheckedChangeListener { radioGroup, i ->
            val rb:RadioButton = radioGroup.findViewById(i)
            busType = rb.text.toString()
        }
    }

    private fun initCitySpinner(){
        val cityAdapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            cityList
        )
        binding.fromCitySpinner.adapter = cityAdapter
        binding.toCitySpinner.adapter = cityAdapter

        binding.fromCitySpinner.onItemSelectedListener =
            object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    from = p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        binding.toCitySpinner.onItemSelectedListener =
            object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    to = p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


    }

}

val cityList = listOf("Dhaka","Chittagong","Rajshahi",
    "Khulna","Sylhet","Cox's Bazar","Comilla","Faridpur","Barishal")