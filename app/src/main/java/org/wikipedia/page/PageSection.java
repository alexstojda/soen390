package org.wikipedia.page;

import java.util.Objects;

public class PageSection {

    private String title;
    private String contents;

    /**
     * Ctor for the PageSection object.
     * @param title The title of the page section.
     * @param content The contents of the page section.
     */
    public PageSection(String title, String content) {
        this.title = title;
        contents = content;
    }

    /**
     * @return The title of the section.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The contents of the section.
     */
    public String getContents() {
        return contents;
    }

    /**
     * @return The contents of the section split into an array by space.
     */
    public String[] getContentsSplit() {
        return contents.split(" ");
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
        return Objects.equals(title, that.title)
                && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents);
    }
}
