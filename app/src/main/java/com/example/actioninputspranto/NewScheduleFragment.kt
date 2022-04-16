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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.actioninputspranto.customdialogs.DatePickerFragment
import com.example.actioninputspranto.customdialogs.TimePickerFragment
import com.example.actioninputspranto.databinding.FragmentNewScheduleBinding
import com.example.actioninputspranto.db.ScheduleDB
import com.example.actioninputspranto.viewmodels.ScheduleViewModel


class NewScheduleFragment : Fragment() {
    private val viewModel:ScheduleViewModel by activityViewModels()
    private lateinit var binding: FragmentNewScheduleBinding
    private var from = "Dhaka"
    private var to = "Dhaka"
    private var busType = "Economy"
    private var selectedDate = ""
    private var selectedTime = ""
    private var id:Long? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewScheduleBinding.inflate(inflater,container,false)
        initCitySpinner()
        initBusTypeRadioGroup()

        id = arguments?.getLong("id")
        if (id!=null){
            binding.saveBtn.setText("Update")
            viewModel.getScheduleById(id!!).observe(viewLifecycleOwner){
                binding.BusNameInputET.setText(it.name)
                binding.showDateTV.text = it.departureDate
                selectedDate = it.departureDate
                binding.showTimeTV.text = it.departureTime
                selectedTime = it.departureTime
                val fromIndex = cityList.indexOf(it.from)
                val toIndex = cityList.indexOf(it.to)
                binding.fromCitySpinner.setSelection(fromIndex)
                binding.toCitySpinner.setSelection(toIndex)
                if (it.busType == "Economy"){
                    binding.busTypeRG.check(R.id.economyRB)
                }
                else if(it.busType == "Business") {
                    binding.busTypeRG.check(R.id.businessRB)
                }
                binding.saveBtn.tag = it.favorite
            }
        }
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
            name = busName,
            from = from,
            to = to,
            departureDate = selectedDate,
            departureTime = selectedTime,
            busType = busType,

        )
        if (id != null){
            schedule.id = id!!
            schedule.favorite = binding.saveBtn.tag as Boolean
            viewModel.updateSchedule(schedule)
        }
        else{
            viewModel.addSchedule(schedule)
        }


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

val cityList = listOf("Dhaka","Chittagong","Rajshahi","Manda","Naogaon",
    "Khulna","Sylhet","Cox's Bazar","Comilla","Faridpur","Barishal")