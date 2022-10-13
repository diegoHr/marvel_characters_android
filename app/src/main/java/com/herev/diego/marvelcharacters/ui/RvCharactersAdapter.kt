package com.herev.diego.marvelcharacters.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.herev.diego.marvelcharacters.R
import com.herev.diego.marvelcharacters.databinding.RvElementCharacterBinding
import com.herev.diego.marvelcharacters.databinding.RvElementLoadingBinding
import com.herev.diego.marvelcharacters.utils.images.ImageUrlGenerator
import com.herev.diego.marvelcharacters.viewModels.MainViewModel

class RvCharactersAdapter(
    private val inflater: LayoutInflater,
    private val context: Context, private val viewModel: MainViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val imageUrlGenerator = ImageUrlGenerator(context)

    override fun getItemViewType(position: Int): Int {
        if (!viewModel.isListCompleted && position + 1 == itemCount) {
            return ListItem.LOADING.ordinal
        } else {
            return ListItem.DATA.ordinal
        }
    }

    override fun getItemCount(): Int {
        val loadingOffset = if (!viewModel.isListCompleted) 1 else 0
        return viewModel.characters.size + loadingOffset

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ListItem.values()[viewType]) {
            ListItem.DATA -> CharacterViewHolder(
                RvElementCharacterBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> LoadingViewHolder(RvElementLoadingBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CharacterViewHolder){
            viewModel.characters.get(position).let{character ->
                character.thumbnail?.let { thumbnail ->
                    holder.binding.ivCharacter.apply {
                        Glide.with(context)
                            .load(imageUrlGenerator.getUrlImage(thumbnail))
                            .centerCrop()
                            .into(this)
                    }

                }

                holder.binding.tvName.text = context.getString(R.string.rv_character_name,
                    character.id?:"#" ,character.name)

            }
        }else if(holder is LoadingViewHolder){
            viewModel.getMoreCharacters()
        }
    }

    class CharacterViewHolder(val binding: RvElementCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    class LoadingViewHolder(val binding: RvElementLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

    enum class ListItem {
        DATA, LOADING
    }

}

