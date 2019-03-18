package org.wikipedia.dataclient.restbase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.wikipedia.dataclient.restbase.page.RbPageSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RbRelatedPages {
    @SuppressWarnings("unused") @Nullable private List<RbPageSummary> pages;

    @Nullable
    public List<RbPageSummary> getPages() {
        return pages;
    }

    @NonNull
    public List<RbPageSummary> getPages(int limit) {
        List<RbPageSummary> list = new ArrayList<>();
        if (getPages() != null) {
            for (RbPageSummary page : getPages()) {
                list.add(page);
                if (limit == list.size()) {
                    break;
                }
            }
        }

        return list;
    }

    @NonNull
    public RbPageSummary getRelatedPage() {
        RbPageSummary pageSummary = new RbPageSummary();
        if (getPages() != null) {
            Random rand = new Random();
            int pageIndex = rand.nextInt(getPages().size());
            pageSummary = getPages().get(pageIndex);
        }

        return pageSummary;
    }
}
