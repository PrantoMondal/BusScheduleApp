package com.example.actioninputspranto

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.actioninputspranto.databinding.FragmentImplicitIntentExampleBinding


class ImplicitIntentExampleFragment : Fragment() {
    private lateinit var binding:FragmentImplicitIntentExampleBinding
    val phoneUri=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImplicitIntentExampleBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        binding.callBtn.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:123456789")
                }
                startActivity(intent)
            }catch (e:ActivityNotFoundException){
                Toast.makeText(requireActivity(),"Could Not launch any app",Toast.LENGTH_SHORT).show()
            }

            /*Without try catch
//                val intent = Intent(Intent.ACTION_DIAL).apply {
//                    data = Uri.parse("tel:123456789")
//                }
//                if (intent.resolveActivity(packageManager) != null) {
//                    startActivity(intent)
//                }
//            else{
//                Toast.makeText(requireActivity(),"Could Not launch any app",Toast.LENGTH_SHORT).show()
                }*/


        }

        binding.smsBtn.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    data = Uri.parse("smsto:123456789")
                    putExtra("sms_body", "Hello")
                }
                    startActivity(intent)

            }catch (e:ActivityNotFoundException){
                Toast.makeText(requireActivity(),"Could Not launch any app",Toast.LENGTH_SHORT).show()
            }
        }

        binding.emailBtn.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "*/*"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("prantomondal517@yahoo.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Test")
                    putExtra(Intent.EXTRA_STREAM, "Hello")
                }
                startActivity(intent)

            }catch (e:ActivityNotFoundException){
                Toast.makeText(requireActivity(),"Could Not launch any app",Toast.LENGTH_SHORT).show()
            }
        }
        binding.mapBtn.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("geo:0,0?q=EDB Trade Center,Kawran Bazar,Dhaka")
                }


                startActivity(intent)

            }catch (e:ActivityNotFoundException){
                Toast.makeText(requireActivity(),"Could Not launch any app",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}