package com.vikalpchaurasia.customLauncher

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikalpchaurasia.customLauncher.adapter.CalendarAdapter
import com.vikalpchaurasia.customLauncher.databinding.FragmentHomeScreenBinding
import com.vikalpchaurasia.customLauncher.viewModel.HomeScreenViewModel


class HomeScreenFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var binding: FragmentHomeScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]

        observeViewModel()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.btnEnter.setOnClickListener(this)

    }

    private fun observeViewModel() {
        val currentDay =
            viewModel.getCurrentDateAndDay().second + "," + viewModel.getCurrentDateAndDay().first
        binding.daystv.text = currentDay

        viewModel.currentTime.observe(viewLifecycleOwner) { time ->
            binding.topTextView.text = time
            Log.d("time", time)

        }

        viewModel.quote.observe(viewLifecycleOwner) { quote ->
            binding.quTv.text = quote
            Log.d("quote", quote)
        }

        viewModel.getCurrentDateAndDay().first
        Log.d("quote", viewModel.getCurrentDateAndDay().first)
        Log.d("quote", viewModel.getCurrentDateAndDay().second)


        val adapter = CalendarAdapter(viewModel.weekDays, viewModel.getCurrentDateAndDay().second)
        binding.recyclerView.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnEnter -> {
                startActivity(Intent(requireActivity(), SecondActivity::class.java))
            }
        }
    }


}