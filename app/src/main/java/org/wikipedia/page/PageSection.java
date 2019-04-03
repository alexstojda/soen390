package org.wikipedia.page;

import java.util.Objects;

public class PageSection {

    private String sectionTitle;
    private String sectionContents;

    public PageSection(String title, String content) {
        sectionTitle = title;
        sectionContents = content;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public String getSectionContents() {
        return sectionContents;
    }

    public String[] getSectionContentsSplit() {
        return sectionContents.split(" ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PageSection that = (PageSection) o;
        return Objects.equals(sectionTitle, that.sectionTitle)
                && Objects.equals(sectionContents, that.sectionContents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionTitle, sectionContents);
    }
}
