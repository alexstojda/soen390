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
        sections.add(new PageSection("President of Kazakhstan", "The President of the Republic of Kazakhstan (Kazakh: Қазақстан Республикасының Президенті; Russian: Президент Республики Казахстан) is the head of state, commander-in-chief and holder of the highest office within the Republic of Kazakhstan. The powers of this position are described in a special section of the Constitution of Kazakhstan.  Quick facts : Residence, Appointer … President of the Republic of Kazakhstan Қазақстан Республикасының Президенті Presidential standard Incumbent Kassym-Jomart Tokayev since 20 March 2019 Residence Ak Orda Presidential Palace Appointer Direct popular vote Term length 5 years, renewable Inaugural holder Nursultan Nazarbayev Formation 24 April 1990 Succession Chairwoman of the Senate of Kazakhstan Website (in Kazakh) akorda.kz (in English) www.akorda.kz/en Close  Kazakhstan This article is part of a series on the politics and government of Kazakhstan  Constitution Human rights  President (list) Kassym-Jomart Tokayev  Prime Minister Askar Mamin  Parliament Senate Chairman Dariga Nazarbayeva  Mazhilis Chairman Nurlan Nigmatulin  Political parties  Recent elections Presidential 2005 2011 2015  Parliamentary 2007 2012 2016  Administrative divisions Regions (oblıstar)  Districts (audandar)  Foreign relations Visa policy    Other countries  Atlas  v t e  The position was established on 24 April 1990, a year before the dissolution of the Soviet Union. The first and, until March 2019, the only President of Kazakhstan was Nursultan Nazarbayev.  None of the presidential elections held in Kazakhstan have been considered free or fair by Western countries or international observers with issues noted including ballot tampering, multiple voting, harassment of opposition candidates and press censorship. "));
        sections.add(new PageSection("Symbols", " The President of Kazakhstan's decorations include a breast mark and a Presidential Standard. "));
        sections.add(new PageSection("Presidential Standard", " The Standard of the President of Kazakhstan is similar to the national flag in that it is rectangular in shape with a ratio of 1:2. In the center of the standard is the Emblem of Kazakhstan. It is bordered on three sides with golden fringe.  Presidential Standard (1995—2012)  The current presidential standard has been in service as recently as 2012. The former standard, which was used from 1995–2012, was a light blue rectangle there with a golden circle in which the figure of the young Kazakh leader Sakas riding a snow leopard. "));
        sections.add(new PageSection("Altyn Qyran Order", " The Order of the Golden Eagle (Kazakh: Алтын Қыран ордені or Altyn Qyran Order) is the highest civilian award that can be awarded by the President of Kazakhstan. Its purpose is to recognize outstanding service to the country by Kazakh and foreign citizens. As head of state, the President is de facto Commander special class of the Order of Altyn Kyran. "));
        sections.add(new PageSection("President in the Constitution", " Item 5 of Article 42 of the Constitution determines that no one can be elected president more than two terms in a row, but it also states that \"The present restriction shall not extend on the First President of the Republic of Kazakhstan.\"  Article 46 says that the President's \"honor and dignity shall be inviolable\" and that his expenses shall be paid by the state. Item 4 of the article outlines the special status and authority of the first president, and refers to a special constitutional act for definitions.According to this act, the first president possesses total, absolute and termless immunity for all actions he performs while in office, and that he remains a government official until his death. He also retains the ability to speak to the people of Kazakhstan, keeps guards, communication, transport, and state support of his activity, and that his official apartment and summer residence became his property with official maintenance. He is also provided with medical care, sanatorium, pensions and insurance.  On April 26, 2015 Nursultan Nazarbayev was re-elected for his 5th presidential term. The official ceremony of the inauguration took place at the Palace of Independence in Astana on April 29. At the inauguration ceremony the re-elected president assured the nation that he would continue the 5 institutional reforms that he had offered earlier, which would contribute to the consistent growth and development of the country.  On January 25, 2017 President Nursultan Nazarbayev laid the groundwork for reforms to the constitution that would redistribute executive powers to the parliament and ministries for the purpose of more open and efficient governance. "));
        sections.add(new PageSection("Presidential Administration", " The Presidential Administration of the Republic of Kazakhstan (Kazakh: Қазақстан Республикасы Президентінің Әкімшілігі/Russian: Администрация Президента Республики Казахстан) reports directly to the president and aids him/her in their everyday dates. It was established in accordance of Presidential Decree No. 2565 on October 20, 1995. It is currently based at the Ak-Orda Presidential Palace in the capital city of Astana. Prior to that, it was based in Almaty. "));
        sections.add(new PageSection("Heads of the Presidential Administration", " Nurtai Abykayev (1990–1995)  Saginbek Tursunov (1995–1996)  Akhmetzhan Yesimov (1996)  Oralbay Abdykarimov (1996–1997)  Sarybai Kalmurzaev (1997–1998)  Vladimir Shepel (1998)  Akhmetzhan Yesimov (1998)  Alikhan Baimenov (1998–1999)  Sarybai Kalmurzaev (1999—2002)  Nurtai Abykayev (January 29, 2002 – March 10, 2004)  Imangali Tasmagambetov (March 10 – December 9, 2004)  Adilbek Dzhaksybekov (December 19, 2004 – January 23, 2008)  Kairat Kelimbetov (January 23 – October 13, 2008)  Aslan Musin (October 13, 2008 – September 21, 2012)  Karim Massimov (September 24, 2012 – April 3, 2014)  Nurlan Nigmatulin (April 3, 2014 – June 21, 2016)  Adilbek Dzhaksybekov (June 21, 2016 – September 10, 2018)  Aset Issekeshev (September 10, 2018 – present) "));
        sections.add(new PageSection("Latest election", " Main article: Kazakhstani presidential election, 2015  Prior to the 2011 election, President Nazarbayev wrote an op-ed for the Washington Post titled \"Kazakhstan’s steady progress toward democracy\".  Kazakhstan's fifth presidential election was held on April 26, 2015. Nursultan Nazarbayev was re-elected with 97.7% of the vote. A total of 858 observers from 19 countries were present at the polling stations during the election.  The Organization for Security and Co-operation in Europe (OSCE) and other international monitors criticised the election as unfair, with issues noted including the closure of media outlets critical of the government and the jailing of opposition activists. OSCE spokesperson Cornelia Jonker criticised the lack of a \"genuine choice\" for voters and also argued that there were \"significant restrictions to the freedom of expression.\" "));
        sections.add(new PageSection("Presidents of Kazakhstan (1990–present)", " Main article: List of leaders of Kazakhstan  The \"No.\" column consecutively numbers the individuals who have served as president, while the \"Elected\" column consecutively numbers the Presidential terms or administrations.  More information : Political party, No. …  No.  Name  Portrait  Elected  Term of office  Political party   #D50000;\"}]]}\">1  Nursultan Nazarbayev Нұрсұлтан Назарбаев (1940–)   #D50000;\"}]]}\">—  24 April 1990  1 December 1991  Communist Party of Kazakhstan   #D50000;\"}]]}\">1 1991  1 December 1991  14 December 1991   (1)  14 December 1991  20 January 1999  Independent   2 1999  20 January 1999  1 March 1999   (1)  1 March 1999  11 January 2006  Otan   3 2005  11 January 2006  1 December 2006   (1)  1 December 2006  8 April 2011  Nur Otan   4 2011  8 April 2011  29 April 2015   5 2015  29 April 2015  20 March 2019 (resigned from office)   2  Kassym-Jomart Tokayev Қасым-Жомарт Тоқаев (1953–)   20 March 2019  Incumbent  Nur Otan   Close "));
        sections.add(new PageSection("Rank by time in office", " More information : Rank, Time in office …   Rank  President  Time in office   1  Nursultan Nazarbayev  27 years, 94 days   2  Kassym-Jomart Tokayev  13 days  Close "));
        sections.add(new PageSection("See also", " List of leaders of Kazakhstan "));
        sections.add(new PageSection("External links", "    Wikimedia Commons has media related to Nursultan Nazarbayev.  Official website of Kazakhstan President Nazarbayev  Kazakhstan President Nazarbayev Biography "));
        sections.add(new PageSection("References", " More information : Tap to expand … Tap to expand  Kazakhstan President Embassy of Kazakhstan Kazakhstan's long term president to run in snap election – again, The Guardian, 11 March 2015 Указ Президента Республики Казахстан от 29 декабря 1995 г. № 2736 Символы власти Президента Казахстана \"Symbols of power\". Akorda. Retrieved 25 March 2019. \"Awards and orders of the Republic of Kazakhstan\". Government of Kazakhstan. Retrieved 25 March 2019. Constitution of the Republic of Kazakhstan Archived 2007-10-20 at the Wayback Machine Constitutional Council of the Republic of Kazakhstan \"Re-elected President Inaugurated, Announces Five Reform Efforts\". astanatimes.com. \"Kazakh President Unveils Plans of Constitutional Reforms\". The Astana Times. Электронное правительство Республики Казахстан Kazakhstan’s steady progress toward democracy Washington Post \"Nearly 10 mn voters to head to polls to elect Kazakh president\". latino.foxnews.com. Archived from the original on 2016-01-01. \"Kazakhstan strongman leader re-elected with 97.7% amid record voter turnout\". rt.com. \"Kazakh strongman says sorry for landslide election victory\". telegraph.co.uk. 27 April 2015. Kazakhstan president Nursultan Nazarbayev wins re-election with 97.7 per cent of vote, ABC Australia, 28 April 2015 \"Kazakh President Nazarbaev Abruptly Announces Resignation\". RadioFreeEurope/RadioLiberty. Retrieved 2019-03-19. Обязанности президента Казахстана будет исполнять спикер Сената Токаев Counting from the declaration of independence on 16 December 1991. Close  v t e Kazakhstan articles History More information : Prehistory, Early history … Prehistory  Saka  Kangju  Wusun  Huns  Early history  Tele  Rouran Khaganate  Göktürks  Kangar union  Kimek Khanate  Karluks  Oghuz Yabgu State  Xueyantuo Khaganate  Uyghur Khaganate  Kyrgyz Khaganate  Kara-Khanid Khanate  Mongol Empire  Chagatai Khanate  Golden Horde  White Horde  Since 1456  Kazakh Khanate  List of Kazakh khans  Jüz  Russian Turkestan  Alash Autonomy  Kazakh ASSR  Kazakh SSR  Republic of Kazakhstan  By topic  Postal  Close Geography More information : Subdivisions …  Altay (Altai) Mountains  Aral Karakum Desert  Caspian Sea  Sharyn Canyon  Kazakh Steppe  Khan Tangiri Shyngy  Kyzylkum Desert  Lake Balkhash  Syr Darya  Tien (Tian) Shan  Ural River  Zhetysu  Subdivisions  Cities and towns  Districts  Regions  Close Politics  Constitution  Elections  Foreign relations  Government President  Prime Minister  Human rights LGBT  LGBT history  Law enforcement  Military  Parliament Mazhilis  Senate  Political parties  Economy  Agriculture  Energy policy  National Bank  Stock Exchange  Telecommunications  Tenge (currency)  Transport  Culture More information : Demographics, Peoples …  Alphabet  Anthem  Clothing  Coat of arms  Cuisine  Flag  Kaznet (Internet)  Kazakhs  Media  Music  Sport  Television  Wedding ceremony  Demographics  Education  Health  Language  Peoples  Armenians  Azerbaijanis  Dungan  Germans  Greeks  Jews  Kazakhs  Koreans  Poles  Russians  Tatars  Turks  Ukrainians  Uyghurs  Religion  Islam  Christianity  Atheism  Hinduism  Tengrism  Religious freedom  Close Outline Index  Category  Portal  v t e Heads of state and government of Europe Heads of state More information : UN members and observers, Partially recognised2 … UN members and observers  Albania  Andorra  Armenia1  Austria  Azerbaijan1  Belarus  Belgium  Bosnia and Herzegovina  Bulgaria  Croatia  Cyprus1  Czech Republic  Denmark  Estonia  Finland  France  Georgia1  Germany  Greece  Hungary  Iceland  Ireland  Italy  Kazakhstan1  Latvia  Liechtenstein  Lithuania  Luxembourg  Malta  Moldova  Monaco  Montenegro  Netherlands  North Macedonia  Norway  Poland  Portugal  Romania  Russian Federation1  San Marino  Serbia  Slovakia  Slovenia  Sovereign Military Order of Malta  Spain  Sweden  Switzerland  Turkey1  Ukraine  United Kingdom  Vatican City  Partially recognised2  Abkhazia1  Kosovo  Northern Cyprus1  South Ossetia1  Unrecognised states3  Artsakh1  Transnistria  Former countries  Czechoslovakia  East Germany  Serbia and Montenegro  Soviet Union1  Yugoslavia  Close Heads of government More information : UN members and observers, Partially recognised2 … UN members and observers  Albania  Andorra  Armenia1  Austria  Azerbaijan1  Belarus  Belgium  Bosnia and Herzegovina  Bulgaria  Croatia  Cyprus1  Czech Republic  Denmark  Estonia  Finland  France  Georgia1  Germany  Greece  Hungary  Iceland  Ireland  Italy  Kazakhstan1  Latvia  Liechtenstein  Lithuania  Luxembourg  Malta  Moldova  Monaco  Montenegro  Netherlands  North Macedonia  Norway  Poland  Portugal  Romania  Russian Federation1  San Marino  Serbia  Slovakia  Slovenia  Sovereign Military Order of Malta  Spain  Sweden  Switzerland  Turkey1  Ukraine  United Kingdom  Vatican City  Partially recognised2  Abkhazia1  Kosovo  Northern Cyprus1  South Ossetia1  Unrecognised states3  Artsakh1  Transnistria  Former countries  Czechoslovakia  East Germany  Serbia and Montenegro  Soviet Union1  Yugoslavia  Close  1. Partially or entirely in Asia, depending on geographical definition.  2. Recognised by at least one United Nations member.  3. Not recognised by any United Nations members.  "));

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


