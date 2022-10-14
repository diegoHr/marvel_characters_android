package com.herev.diego.marvelcharacters.ui

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.herev.diego.marvelcharacters.databinding.FragmentCharactersListBinding
import com.herev.diego.marvelcharacters.viewModels.CharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private val viewModel by viewModels<CharactersListViewModel> ()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharactersListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCharactersListBinding.bind(view)
        val adapter = RvCharactersAdapter(layoutInflater, requireContext(), viewModel, this)
        binding.rvCharacters.adapter = adapter

        viewModel.loadingMoreCharacters.onEach {
            adapter.notifyDataSetChanged()
            /*if(true){
                adapter.notifyItemChanged(viewModel.characters.size)
            }else{
                adapter.notifyItemRangeInserted(viewModel.characters.size-viewModel.page, viewModel.page)
            }*/
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.attributionData.onEach {
            binding.tvMarvelAttribution.text = HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT)
            binding.tvMarvelAttribution.setMovementMethod(LinkMovementMethod.getInstance())
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.errorMessage.onEach {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.getAttributionData()
        //viewModel.getMoreCharacters()
    }

}