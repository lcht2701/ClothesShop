package com.example.clothesshop.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.adapters.HomeCategoryAdapter;
import com.example.clothesshop.adapters.HomePopularAdapter;
import com.example.clothesshop.adapters.RecommendedAdapter;
import com.example.clothesshop.adapters.ViewAllAdapter;
import com.example.clothesshop.databinding.FragmentHomeBinding;
import com.example.clothesshop.models.CategoryModel;
import com.example.clothesshop.models.PopularModel;
import com.example.clothesshop.models.RecommendedModel;
import com.example.clothesshop.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView popularRec, homeCategoryRec, recommendedRec;
    FirebaseFirestore db;
    private FragmentHomeBinding binding;
    ProgressBar progressBar;
    ScrollView scrollView;
    //Search Recycle View
    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private RecyclerView recyclerViewSearch;
    private ViewAllAdapter viewAllAdapter;

    //Popular items
    List<PopularModel> popularModelList;
    HomePopularAdapter popularAdapter;
    //Home Categories
    List<CategoryModel> categoryModelList;
    HomeCategoryAdapter homeCategoryAdapter;
    //Recommended
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        popularRec = root.findViewById(R.id.popular_rec);
        homeCategoryRec = root.findViewById(R.id.explore_rec);
        recommendedRec = root.findViewById(R.id.recommended_rec);
        progressBar = root.findViewById(R.id.progress_bar);
        scrollView = root.findViewById(R.id.scroll_view);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        db = FirebaseFirestore.getInstance();
        //Popular Items
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularModelList = new ArrayList<>();
        popularAdapter = new HomePopularAdapter(getActivity(), popularModelList);
        popularRec.setAdapter(popularAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Explore Categories
        homeCategoryRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryModelList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(getActivity(), categoryModelList);
        homeCategoryRec.setAdapter(homeCategoryAdapter);

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                CategoryModel homeCategoryModel = documentSnapshot.toObject(CategoryModel.class);
                                categoryModelList.add(homeCategoryModel);
                                homeCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Recommended
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(), recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapter);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                RecommendedModel recommendedModel = documentSnapshot.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        ////////////////////Search View
        recyclerViewSearch = root.findViewById(R.id.search_rec);
        search_box = root.findViewById(R.id.search_box);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getContext(), viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                } else {
                    searchProduct(s.toString());
                }
            }
        });

        return root;
    }

    private void searchProduct(String type) {
        if (!type.isEmpty()) {
            db.collection("AllProducts").whereEqualTo("type", type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}