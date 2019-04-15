package org.wikipedia.search;

import org.wikipedia.dataclient.ServiceFactory;
import org.wikipedia.dataclient.WikiSite;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchHandler {
    private CompositeDisposable disposables = new CompositeDisposable();
    private Callback callback;

    public interface Callback {
        void getArtist(SearchResult searchResult);
    }

    public SearchHandler(Callback callback) {
        this.callback = callback;
    }

    public void searchForArtist(String term) {
         disposables.add(ServiceFactory.get(WikiSite.forLanguageCode("en")).prefixSearch(term, 20,
                term).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response != null && response.query() != null && response.query().pages() != null) {
                        return new SearchResults(response.query().pages(),
                                WikiSite.forLanguageCode("en"), response.continuation(),
                                response.suggestion());
                    }
                    return new SearchResults();
                }).subscribe(results -> {
                    boolean found = false;
                    for (SearchResult result: results.getResults()
                         ) {
                        if(     result.getPageTitle().getDescription().contains("band")||
                                result.getPageTitle().getDescription().contains("artist")||
                                result.getPageTitle().getDescription().contains("rapper")||
                                result.getPageTitle().getDescription().contains("singer")){
                            callback.getArtist(result);
                            found = true;
                            break;
                        }
                    }
                        if (!found)
                            callback.getArtist(results.getResults().get(0));
                })
        );
    }


}