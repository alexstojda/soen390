package org.wikipedia.wikilisteni;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.wikipedia.html.ParseException;
import org.wikipedia.page.PageSection;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PageParserTest {

    private String getPathFromResources(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return resource.getPath();
    }

    private String readWholeFile(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private static List<PageSection> buildPageSections() {
        ArrayList<PageSection> sections = new ArrayList<>();

        // Generated code
        sections.add(new PageSection("President of Kazakhstan", "The President of the Republic of Kazakhstan (Kazakh: Қазақстан Республикасының Президенті; Russian: Президент Республики Казахстан) is the head of state, commander-in-chief and holder of the highest office within the Republic of Kazakhstan. The powers of this position are described in a special section of the Constitution of Kazakhstan.  Quick facts : Residence, Appointer … Close   The position was established on 24 April 1990, a year before the dissolution of the Soviet Union. The first and, until March 2019, the only President of Kazakhstan was Nursultan Nazarbayev.  None of the presidential elections held in Kazakhstan have been considered free or fair by Western countries or international observers with issues noted including ballot tampering, multiple voting, harassment of opposition candidates and press censorship. "));
        sections.add(new PageSection("Symbols", " The President of Kazakhstan's decorations include a breast mark and a Presidential Standard. "));
        sections.add(new PageSection("Presidential Standard", " The Standard of the President of Kazakhstan is similar to the national flag in that it is rectangular in shape with a ratio of 1:2. In the center of the standard is the Emblem of Kazakhstan. It is bordered on three sides with golden fringe.  Presidential Standard (1995—2012)  The current presidential standard has been in service as recently as 2012. The former standard, which was used from 1995–2012, was a light blue rectangle there with a golden circle in which the figure of the young Kazakh leader Sakas riding a snow leopard. "));
        sections.add(new PageSection("Altyn Qyran Order", " The Order of the Golden Eagle (Kazakh: Алтын Қыран ордені or Altyn Qyran Order) is the highest civilian award that can be awarded by the President of Kazakhstan. Its purpose is to recognize outstanding service to the country by Kazakh and foreign citizens. As head of state, the President is de facto Commander special class of the Order of Altyn Kyran. "));
        sections.add(new PageSection("President in the Constitution", " Item 5 of Article 42 of the Constitution determines that no one can be elected president more than two terms in a row, but it also states that \"The present restriction shall not extend on the First President of the Republic of Kazakhstan.\"  Article 46 says that the President's \"honor and dignity shall be inviolable\" and that his expenses shall be paid by the state. Item 4 of the article outlines the special status and authority of the first president, and refers to a special constitutional act for definitions.According to this act, the first president possesses total, absolute and termless immunity for all actions he performs while in office, and that he remains a government official until his death. He also retains the ability to speak to the people of Kazakhstan, keeps guards, communication, transport, and state support of his activity, and that his official apartment and summer residence became his property with official maintenance. He is also provided with medical care, sanatorium, pensions and insurance.  On April 26, 2015 Nursultan Nazarbayev was re-elected for his 5th presidential term. The official ceremony of the inauguration took place at the Palace of Independence in Astana on April 29. At the inauguration ceremony the re-elected president assured the nation that he would continue the 5 institutional reforms that he had offered earlier, which would contribute to the consistent growth and development of the country.  On January 25, 2017 President Nursultan Nazarbayev laid the groundwork for reforms to the constitution that would redistribute executive powers to the parliament and ministries for the purpose of more open and efficient governance. "));
        sections.add(new PageSection("Presidential Administration", " The Presidential Administration of the Republic of Kazakhstan (Kazakh: Қазақстан Республикасы Президентінің Әкімшілігі/Russian: Администрация Президента Республики Казахстан) reports directly to the president and aids him/her in their everyday dates. It was established in accordance of Presidential Decree No. 2565 on October 20, 1995. It is currently based at the Ak-Orda Presidential Palace in the capital city of Astana. Prior to that, it was based in Almaty. "));
        sections.add(new PageSection("Heads of the Presidential Administration", " Nurtai Abykayev (1990–1995)  Saginbek Tursunov (1995–1996)  Akhmetzhan Yesimov (1996)  Oralbay Abdykarimov (1996–1997)  Sarybai Kalmurzaev (1997–1998)  Vladimir Shepel (1998)  Akhmetzhan Yesimov (1998)  Alikhan Baimenov (1998–1999)  Sarybai Kalmurzaev (1999—2002)  Nurtai Abykayev (January 29, 2002 – March 10, 2004)  Imangali Tasmagambetov (March 10 – December 9, 2004)  Adilbek Dzhaksybekov (December 19, 2004 – January 23, 2008)  Kairat Kelimbetov (January 23 – October 13, 2008)  Aslan Musin (October 13, 2008 – September 21, 2012)  Karim Massimov (September 24, 2012 – April 3, 2014)  Nurlan Nigmatulin (April 3, 2014 – June 21, 2016)  Adilbek Dzhaksybekov (June 21, 2016 – September 10, 2018)  Aset Issekeshev (September 10, 2018 – present) "));
        sections.add(new PageSection("Latest election", " Main article: Kazakhstani presidential election, 2015  Prior to the 2011 election, President Nazarbayev wrote an op-ed for the Washington Post titled \"Kazakhstan’s steady progress toward democracy\".  Kazakhstan's fifth presidential election was held on April 26, 2015. Nursultan Nazarbayev was re-elected with 97.7% of the vote. A total of 858 observers from 19 countries were present at the polling stations during the election.  The Organization for Security and Co-operation in Europe (OSCE) and other international monitors criticised the election as unfair, with issues noted including the closure of media outlets critical of the government and the jailing of opposition activists. OSCE spokesperson Cornelia Jonker criticised the lack of a \"genuine choice\" for voters and also argued that there were \"significant restrictions to the freedom of expression.\" "));
        sections.add(new PageSection("Presidents of Kazakhstan (1990–present)", " Main article: List of leaders of Kazakhstan  The \"No.\" column consecutively numbers the individuals who have served as president, while the \"Elected\" column consecutively numbers the Presidential terms or administrations.  More information : Political party, No. … Close "));
        sections.add(new PageSection("Rank by time in office", " More information : Rank, Time in office … Close "));
        sections.add(new PageSection("See also", " List of leaders of Kazakhstan "));
        sections.add(new PageSection("External links", "  Official website of Kazakhstan President Nazarbayev  Kazakhstan President Nazarbayev Biography "));
        sections.add(new PageSection("References", " More information : Tap to expand … Close   "));

        return sections;
    }

    @Test
    public void checkCorrectParsing() {
        String testHTML = readWholeFile(getPathFromResources("article.html"));

        List<PageSection> testSections = buildPageSections();
        List<PageSection> parsedSections = PageParser.getParsedPage(testHTML);

        for (int i = 0; i < testSections.size(); ++i) {
            assertTrue(testSections.get(i).equals(parsedSections.get(i)));
        }
    }

    @Test(expected = ParseException.class)
    public void failOnMissingMainTitle() {
        String testHTML = readWholeFile(getPathFromResources("article_missing_main_title.html"));
        PageParser.getParsedPage(testHTML);
    }

    @Test(expected = ParseException.class)
    public void failOnMissingMainSection() {
        String testHTML = readWholeFile(getPathFromResources("article_missing_main_content_section.html"));
        PageParser.getParsedPage(testHTML);
    }

    @Test(expected = ParseException.class)
    public void failOnMissingSection() {
        String testHTML = readWholeFile(getPathFromResources("article_missing_content_section.html"));
        PageParser.getParsedPage(testHTML);
    }
}


