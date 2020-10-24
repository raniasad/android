package rnuma.isis.miniprojetflickr.ui.liste

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rnuma.isis.miniprojetflickr.R
import rnuma.isis.miniprojetflickr.model.Photo
import rnuma.isis.miniprojetflickr.ui.main.MainViewModel

class ListeFragment : Fragment() {

    companion object {
        fun newInstance() = ListeFragment()
    }

    private lateinit var viewModel: ListeViewModel
    private lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view=inflater.inflate(R.layout.liste_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ListeViewModel()
        viewModel.Lphotos.observe(requireActivity(), Observer<List<Photo>> {
            recycler = view!!.findViewById<RecyclerView>(R.id.recyclerview)
            recycler.layoutManager = GridLayoutManager(requireContext(),2)
            val adapter=MyAdapter(viewModel.Lphotos, { position ->
                var photo = viewModel.Lphotos.value!!.get(position)
                val url =
                    "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"
                val action = ListeFragmentDirections.actionListeFragmentToFullFragment(url)
                findNavController().navigate(action)
            })
            recycler.adapter=adapter


        })

    }


}