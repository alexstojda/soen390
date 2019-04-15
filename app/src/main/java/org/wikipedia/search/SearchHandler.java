package org.wikipedia.search;

import org.wikipedia.dataclient.ServiceFactory;
import org.wikipedia.dataclient.WikiSite;

import java.util.regex.Matcher;
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
            if (hasMusicDescription(result,term)) {
                value = result;
                break;
            }
        }
        return value;
    }

    private static boolean isContain(String source, String subItem) {
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    //works for bands that redirect such as K/DA -> League of legends
    //but breaks bands such as KISS, since kiss redirects to kiss(disambiguation)
    private static boolean isRedirect(SearchResult result, String term){
        return result.getRedirectFrom() != null && result.getRedirectFrom().contentEquals(term);
    }
    private  static boolean hasMusicDescription(SearchResult result, String term){
       return result.getPageTitle().getDescription() != null && (
                (isContain(result.getPageTitle().getDescription(), "band") ||
                        isContain(result.getPageTitle().getDescription(), "artist") ||
                        isContain(result.getPageTitle().getDescription(), "rapper") ||
                        isContain(result.getPageTitle().getDescription(), "singer") ||
                        isContain(result.getPageTitle().getDescription(), "musician") ||
                        isContain(result.getPageTitle().getDescription(), "DJ")));
    }

    //can be used to check if the title is an exact match of the search criteria
    //filterTerm is required since page titles use underscores '_' instead of spaces ' '

    //breaks things such as kiss, but allows for people who aren't known as singers
    //such as League of Legends and Dwayne "The rock" Johnson
    private static boolean isNameExact(SearchResult result, String term) {
        return result.getPageTitle().toString().contentEquals(filerTerm(term));
    }
    private static String filerTerm(String term){
        return term.replaceAll(" ","_");
    }
}