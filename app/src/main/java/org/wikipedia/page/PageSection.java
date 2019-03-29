package org.wikipedia.page;

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

}
