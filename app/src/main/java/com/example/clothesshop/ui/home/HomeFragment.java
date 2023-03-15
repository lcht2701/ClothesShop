package com.example.clothesshop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.adapters.PopularAdapter;
import com.example.clothesshop.databinding.FragmentHomeBinding;
import com.example.clothesshop.models.PopularModel;

import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView popularRec;
    private FragmentHomeBinding binding;
    //Popular items
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        popularRec = root.findViewById(R.id.popular_rec);
        return root;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}