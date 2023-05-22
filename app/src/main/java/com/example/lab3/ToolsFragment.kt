package com.example.lab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.adapters.CardsListAdapter
import com.example.lab3.databinding.FragmentToolsBinding
import com.example.lab3.models.Card
import com.example.lab3.models.LoadingResult
import com.example.lab3.repository.Mock
import com.example.lab3.viewModels.ToolsFragmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToolsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToolsFragment : Fragment() {
    private var _binding: FragmentToolsBinding? = null
    private val toolsAdapter = CardsListAdapter()
    private val viewModel = ToolsFragmentViewModel()

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToolsBinding.inflate(inflater, container, false)
        init()
        viewModel.init()
        return binding.root
    }

    private fun init() {
        binding.tools.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.tools.adapter = toolsAdapter

        val observer = Observer<LoadingResult<List<Card>?>> { newValue ->
            when (newValue) {
                is LoadingResult.Success -> toolsAdapter.submitList(newValue.value)
                is LoadingResult.Failure -> toolsAdapter.submitList(listOf(newValue.message?.let { x ->
                    newValue.moreInfo?.let { y ->
                        Card.ErrorCard(
                            x, y
                        )
                    }
                }))
                else -> toolsAdapter.submitList(Mock().getData())
            }

        }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}