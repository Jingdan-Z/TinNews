package com.laioffer.tinnews.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.tinnews.ui.home.HomeViewModel;
import com.laioffer.tinnews.ui.save.SaveViewModel;
import com.laioffer.tinnews.ui.search.SearchViewModel;

public class NewsViewModelFactory implements ViewModelProvider.Factory {
    //ViewModels are kept alive across configuration changes,
    // so if you rotate the phone, you're not supposed to create a new ViewModel.
    //And you need the factory because you typically don't just have a no-arg constructor, your ViewModel has constructor arguments,
    // and the ViewModelProvider must know how to fill out the constructor arguments when you're using a non-default constructor

        private final NewsRepository repository;

        public NewsViewModelFactory(NewsRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(HomeViewModel.class)) {
                return (T) new HomeViewModel(repository);
            } else if (modelClass.isAssignableFrom(SaveViewModel.class)) {
                return (T) new SaveViewModel(repository);
            } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
                return (T) new SearchViewModel(repository);
            } else {
                throw new IllegalStateException("Unknown ViewModel");
            }
        }
    }

