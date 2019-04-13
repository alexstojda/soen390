package org.wikipedia.wikilisteni;

import android.webkit.ValueCallback;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wikipedia.html.ParseException;
import org.wikipedia.page.PageSection;

import java.util.ArrayList;
import java.util.List;

public final class PageParser {

    private PageParser() {
    }

    /**
     * Gets the HTML from a WebView (You can get one from PageFragment)
     *
     * @param webView  The WebView to get the HTML from.
     * @param callback The callback that will handle receiving the HTML (This is an async action.)
     */
    public static void getPageHTML(WebView webView, ValueCallback<String> callback) {
        webView.evaluateJavascript("(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
                (String value) -> {
                    value = value.replace("\\u003C", "<");
                    callback.onReceiveValue(value);
                });
    }

    /**
     * Extracts a list of PageSection objects from the HTML of a Wikipedia Page.
     *
     * @param pageHTML The HTML of said Wikipedia page.
     * @return The extracted list of page sections.
     */
    public static List<PageSection> getParsedPage(String pageHTML) {

        ArrayList<PageSection> sections = new ArrayList<>();
        pageHTML = pageHTML.replace("\\&quot;", "");
        Document parsedPage = Jsoup.parse(pageHTML);

        // Remove reference links
        parsedPage.select("span[class*=mw-reflink-text]").remove();

        // Handle title separately
        Element title = parsedPage.selectFirst("h1");
        Element firstParagraph = parsedPage.selectFirst("div[id*=content_block_0]");

        if (title == null) {
            throw new ParseException("Missing main title.");
        }

        if (firstParagraph == null) {
            throw new ParseException("Missing main content section.");
        }

        // Remove tables, they are annoying...
        Elements tables = firstParagraph.select("table");
        tables.remove();

        String titleStr = filterNewlines(title.text());
        String firstParagraphStr = filterNewlines(firstParagraph.text());
        sections.add(new PageSection(titleStr, firstParagraphStr));

        // Handle sections
        for (Element e : parsedPage.select("h2,h3,h4,h5,h6[class*=pagelib_edit_section_title]")) {

            int sectionID = Integer.parseInt(e.attr("data-id").replace("\\\"", ""));
            String sectionTitle = filterNewlines(e.text());

            Element paragraphElement = parsedPage.selectFirst("div[id*=content_block_" + sectionID + "]");
            if (paragraphElement == null) {
                throw new ParseException("Missing content section #" + Integer.toString(sectionID) + ".");
            }

            paragraphElement.select("table").remove();
            String paragraph = filterNewlines(paragraphElement.text());

            sections.add(new PageSection(sectionTitle, paragraph));
        }
        return sections;
    }

    /**
     * Helper to remove annoying "\n"s and \" found throughout the html page from WebViews.
     * @param str The string to clean.
     * @return The clean string.
     */
    private static String filterNewlines(String str) {
        return str.replace("\\n", "").replace("\\\"", "\"");
    }

}
