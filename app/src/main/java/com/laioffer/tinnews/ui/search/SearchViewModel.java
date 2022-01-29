package com.laioffer.tinnews.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

public class SearchViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<String> searchInput = new MutableLiveData<>();
    public SearchViewModel(NewsRepository repository) {
        this.repository = repository;
    }
    public void setSearchInput(String query) {
        searchInput.setValue(query);
    }
    //put the response of search news into the livedata
    public LiveData<NewsResponse> searchNews() {
        //pass searchinput and repository to the searchNews method in repo and put the response
        //in livedata instead
        return Transformations.switchMap(searchInput,repository::searchNews);
    }
}
