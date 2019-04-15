package org.wikipedia.search;

import org.wikipedia.dataclient.ServiceFactory;
import org.wikipedia.dataclient.WikiSite;

import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchHandler {
    private CompositeDisposable disposables = new CompositeDisposable();
    private Callback callback;

    public interface Callback {
        void getArtist(SearchResult searchResult, String term);
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
                    callback.getArtist(returnArtist(results, term), term);
                })
        );
    }

    public SearchResult returnArtist(SearchResults results, String term) {
        SearchResult value = null;
        for (SearchResult result : results.getResults()
        ) {
            if (hasMusicDescription(result)) {
                value = result;
                break;
            }
        }
        return value;
    }

    private boolean hasMusicDescription(SearchResult result) {
        return result.getPageTitle().getDescription() != null && (
                isContain(result.getPageTitle().getDescription(), "band", "artist", "rapper",
                        "singer", "musician", "DJ", "group"));
    }

    private static boolean isContain(String source, String... subItems) {
        for (String subItem : subItems) {
            Pattern p = Pattern.compile("\\b" + subItem + "\\b");
            if (p.matcher(source).find()) {
                return true;
            }
        }
        return false;
    }

}
