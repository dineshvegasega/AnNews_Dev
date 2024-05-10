package com.vegasega.amnews.ui.main.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.SearchBinding
import com.vegasega.amnews.utils.onRightDrawableClicked
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Search : Fragment() {
    private val viewModel: SearchVM by viewModels()
    private var _binding: SearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            inclideHeaderSearch.textHeaderTxt.text = getString(R.string.search)

        }
        searchHandler()
    }





    @SuppressLint("NotifyDataSetChanged")
    private fun searchHandler() {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = viewModel.searchAdapter
            inclideHeaderSearch.editTextSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //loadFirstPage()
                    viewModel.searchAdapter.submitList(viewModel.itemMain)
                    viewModel.searchAdapter.notifyDataSetChanged()
                }
                true
            }

            inclideHeaderSearch.editTextSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    inclideHeaderSearch.editTextSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, if(count >= 1) R.drawable.ic_cross_white else R.drawable.ic_search, 0);
                }
            })

            inclideHeaderSearch.editTextSearch.onRightDrawableClicked {
                it.text.clear()
               // loadFirstPage()
            }
        }
    }

}