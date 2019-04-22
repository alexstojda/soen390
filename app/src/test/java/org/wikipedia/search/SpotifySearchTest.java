package org.wikipedia.search;

import org.junit.Test;
import org.wikipedia.dataclient.WikiSite;
import org.wikipedia.test.MockRetrofitTest;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;


public class SpotifySearchTest extends MockRetrofitTest {

    private static final WikiSite TESTWIKI = new WikiSite("test.wikimedia.org");
    private static final int BATCH_SIZE = 20;

    @Test
    public void testRequestSuccess() throws Throwable {
        enqueueFromFile("search_spotify_kiss_results.json");
        TestObserver<SearchResults> observer = new TestObserver<>();
        getObservable().subscribe(observer);

        observer.assertComplete().assertNoErrors()
                .assertValue(results -> SearchHandler.returnArtist(results).getPageTitle().getDisplayText().equals("kiss (band)"));
    }

    @Test
    public void testRequestNoDescriptionFound() throws Throwable {
        enqueueFromFile("search_spotify_no_desc.json");
        TestObserver<SearchResults> observer = new TestObserver<>();
        getObservable().subscribe(observer);

        observer.assertComplete().assertNoErrors()
                .assertValue(results -> SearchHandler.returnArtist(results) == null);
    }

    @Test
    public void testRequestNoResults() throws Throwable {
        enqueueFromFile("prefix_search_results_empty.json");
        TestObserver<SearchResults> observer = new TestObserver<>();
        getObservable().subscribe(observer);

        observer.assertComplete().assertNoErrors()
                .assertValue(results -> SearchHandler.returnArtist(results) == null);
    }

    private Observable<SearchResults> getObservable() {
        return getApiService().prefixSearch("foo", BATCH_SIZE, "foo")
                .map(response -> {
                    if (response != null && response.success() && response.query().pages() != null) {
                        // noinspection ConstantConditions
                        return new SearchResults(response.query().pages(), TESTWIKI, response.continuation(),
                                response.suggestion());
                    }
                    return new SearchResults();
                });
    }

}
