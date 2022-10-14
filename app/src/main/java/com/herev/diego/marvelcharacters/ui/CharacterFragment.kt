package com.herev.diego.marvelcharacters.ui


import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.herev.diego.marvelcharacters.databinding.ElementLinkBinding
import com.herev.diego.marvelcharacters.databinding.FragmentCharacterBinding

class CharacterFragment : Fragment() {

    val args: CharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCharacterBinding.bind(view)

        binding.toolbar.title = args.name
        val imageUrl : String = args.image
        Glide.with(requireContext())
            .load(imageUrl)
            .centerCrop()
            .into(binding.ivCharacter)

        binding.tvDescription.text = args.description

        args.urls.forEach {
            val link = ElementLinkBinding.inflate(layoutInflater, binding.llContent, false)
            val text = "<a href=\"${it.url}\">${it.type}</>"
            link.root.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
            link.root.setMovementMethod(LinkMovementMethod.getInstance())

            binding.llContent.addView(link.root)
        }



    }
}