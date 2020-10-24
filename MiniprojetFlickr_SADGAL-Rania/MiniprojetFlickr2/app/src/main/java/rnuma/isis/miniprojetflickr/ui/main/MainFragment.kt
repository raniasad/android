package rnuma.isis.miniprojetflickr.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_fragment.*
import rnuma.isis.miniprojetflickr.R
import rnuma.isis.miniprojetflickr.model.Photo
import rnuma.isis.miniprojetflickr.ui.liste.ListeFragmentDirections

class MainFragment : Fragment()  {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var imageview: ImageView
    private lateinit var next: TextView
    private lateinit var titre: TextView
    private lateinit var allimages: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var view=inflater.inflate(R.layout.main_fragment, container, false)
         next = view.findViewById<TextView>(R.id.next)
        titre = view.findViewById<TextView>(R.id.titre)
        next.setOnClickListener(View.OnClickListener{
            viewModel.nextPhoto()
        })
        allimages = view.findViewById<TextView>(R.id.allImages)
         imageview = view.findViewById<ImageView>(R.id.imageView)
        allimages.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(allimages).navigate(R.id.versListeFragment);
        })
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel = MainViewModel()
        viewModel.LPhoto.observe(requireActivity(), Observer<Photo> { photo ->
            val url =
                "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"
            Glide.with(requireActivity()).load(url).into(imageview)
            titre.text=photo.title
            imageview.setOnClickListener(View.OnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToFullFragment(url)
                findNavController().navigate(action)
            })


        })


    }



}