package com.vegasega.amnews.ui.main.test

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.TestBinding
import com.vegasega.amnews.utils.sticky.StickyHeaders
import com.vegasega.amnews.utils.sticky.StickyHeaders.ViewSetup
import com.vegasega.amnews.utils.sticky.StickyHeadersGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random


@AndroidEntryPoint
class Test : Fragment() {
    private var _binding: TestBinding? = null
    private val binding get() = _binding!!

    private val viewModel:TestVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TestBinding.inflate(inflater)
        return binding.root
    }



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = viewModel.dashboardAdapter
            viewModel.dashboardAdapter.submitList(viewModel.itemMain)


            val myAdapter = MyAdapter()
            binding.recyclerView2.setHasFixedSize(true)
            binding.recyclerView2.adapter = myAdapter

            val layoutManager: StickyHeadersGridLayoutManager<MyAdapter> =
                StickyHeadersGridLayoutManager(
                    requireContext(), 2
                )
            layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (myAdapter.isStickyHeader(position)) {
                        return 2
                    }
                    return 1
                }
            }
            binding.recyclerView2.setLayoutManager(layoutManager)
        }
    }


    internal class MyAdapter : RecyclerView.Adapter<MyViewHolder>(), StickyHeaders, ViewSetup {
        var datas: MutableList<String> = ArrayList()

        init {
            initData()
        }

        private fun initData() {
            for (i in 65 until 26 + 65) {
                datas.add(i.toChar().toString())
                for (j in 0..9) {
                    val itemText = getItemText(i.toChar())
                    datas.add(itemText)
                }
            }
        }

        private fun getItemText(prefix: Char): String {
            val length = createRandom(0, 10)
            val builder = StringBuilder()
            builder.append(prefix)
            for (i in 0 until length) {
                val random = createRandom(0, 51)
                builder.append(DICT[random])
            }
            return builder.toString()
        }

        private fun createRandom(min: Int, max: Int): Int {
            val random: Random = Random()
            return random.nextInt(max) % (max - min + 1) + min
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            if (viewType == HEADER_ITEM) {
                val inflate: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false)
                return MyViewHolder(inflate)
            } else {
                val inflate: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard_menus, parent, false)
                return MyViewHolder(inflate)
            }
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = datas[position]
//            val textView = holder.itemView.findViewById<TextView>(R.id.text2)
            //textView.text = item
        }

        override fun getItemCount(): Int {
            return datas.size
        }

        override fun getItemViewType(position: Int): Int {
            return if (position % 11 == 0) HEADER_ITEM else super.getItemViewType(position)
            Log.e("TAG" , "getItemViewType")
        }

        override fun isStickyHeader(position: Int): Boolean {
            return getItemViewType(position) == HEADER_ITEM
            Log.e("TAG" , "isStickyHeader")
        }

        override fun setupStickyHeaderView(stickyHeader: View) {
            ViewCompat.setElevation(stickyHeader, 10f)
            Log.e("TAG" , "setupStickyHeaderView")
        }

        override fun teardownStickyHeaderView(stickyHeader: View) {
            ViewCompat.setElevation(stickyHeader, 0f)
            Log.e("TAG" , "teardownStickyHeaderView")
        }

        override fun onViewAttachedToWindow(holder: MyViewHolder) {
            super.onViewAttachedToWindow(holder)
            val lp = holder.itemView.layoutParams
            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
                if (isStickyHeader(holder.layoutPosition)) {
                    lp.isFullSpan = true
                }
            }
        }

        companion object {
            private val DICT = arrayOf(
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z",
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z"
            )
            private const val HEADER_ITEM = 123
        }
    }


    internal class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}