package org.wikipedia.wikilisteni;

import android.webkit.ValueCallback;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wikipedia.page.PageSection;

import java.util.ArrayList;
import java.util.List;

public final class PageParser {

    private PageParser() { }

    public static void getPageHTML(WebView webView, ValueCallback<String> callback) {
        webView.evaluateJavascript("(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
                (String value) -> {
                    value = value.replace("\\u003C", "<");
                    callback.onReceiveValue(value);
                });
    }

    public static void getParsedPage(String pageHTML, ValueCallback<List<PageSection>> callback) {

        ArrayList<PageSection> sections = new ArrayList<>();
        Document parsedPage = Jsoup.parse(pageHTML);

        Element title = parsedPage.selectFirst("h1");
        Element firstParagraph = parsedPage.selectFirst("div[id*=content_block_0]");

        String titleStr = filterNewlines(title.text());
        String firstParagraphStr = filterNewlines(firstParagraph.text());
        sections.add(new PageSection(titleStr, firstParagraphStr));

        for (Element e : parsedPage.select("h2,h3,h4,h5,h6[class*=pagelib_edit_section_title]")) {

            int sectionID = Integer.parseInt(e.attr("data-id").replace("\\\"", ""));
            String sectionTitle = filterNewlines(e.text());

            Element paragraphElement = parsedPage.selectFirst("div[id*=content_block_" + sectionID + "]");
            String paragraph = filterNewlines(paragraphElement.text());

            sections.add(new PageSection(sectionTitle, paragraph));
        }
        callback.onReceiveValue(sections);
    }

    private static String filterNewlines(String str) {
        return str.replace("\\n", "");
    }

}
