package com.issa.animeshow.scraper;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SiteTools {

   /* public static List<List<String>> getAnimeList(String response) {

        Document document = Jsoup.parse(response);
        List<List<String>> animeList = new ArrayList<>();
        Elements links = document.select("ul.anime-list>li>a");
        for (Element link : links) {
            animeList.add(new ArrayList<String>(Arrays.asList(link.text(), link.attr("href"))));
        }
        return animeList;
    }*/

    public static List<List<String>> getOngoingAnimeList(String string_document) {
        Document document = Jsoup.parse(string_document);
        List<List<String>> ongoingAnimeList = new ArrayList<>();
        Elements anime_list = document.select("div.an-list>div");
        for (Element anime : anime_list) {
            String anime_url = "", image_url = "", title = "";
            for (Element animeDetail : anime.children()) {
                if (animeDetail.className().equals("an-image")) {
                    anime_url = animeDetail.child(0).attr("href");
                    image_url = animeDetail.child(0).child(0).attr("src");
                }
                if (animeDetail.className().equals("an-text")) {
                    title = animeDetail.child(0).text();
                }
            }
            ongoingAnimeList.add(new ArrayList<>(Arrays.asList(title, anime_url, image_url)));
        }
        return ongoingAnimeList;
    }


    /*public static List<List<String>> getGenreList(String response) {
        String url = "http://www.anime1.com/content/genre/";
        List<List<String>> genreList = new ArrayList<>();
        Document document = Jsoup.parse(response);
        Elements links = document.select("div.popularity-by-genre>ul>li>a");
        for (Element link : links) {
            genreList.add(new ArrayList<>(Arrays.asList(link.text(), link.attr("href"))));
        }
        return genreList;
    }*/

   /* public static List<List<String>> getAnimeByGenreElementParse(Element div) {
        List<List<String>> animeList = new ArrayList<>();
        for (Element div_child : div.children()) {
            System.out.println(div_child.html());
            String anime_name = "", anime_link = "", anime_cover = "";
            anime_link = div_child.child(1).child(0).attr("href"); //This is the a href link of the anime.Get the link of anime from here.
            anime_cover = div_child.child(1).child(0).child(0).attr("src");//This is the child of the previous element. This contains an image so extract it's source from here.
            anime_name = div_child.child(2).child(0).text();
            animeList.add(new ArrayList<>(Arrays.asList(anime_name, anime_link, anime_cover)));
        }
        return animeList;
    }

    /*public static String getVideoURL(String response) {
        String js = "";
        Document document = Jsoup.parse(response);
        Element divElement = document.getElementById("Movie");
        for (Element javascript : divElement.children()) {
            if (javascript.tagName().equals("script")) {
                js = javascript.html();
            }
        }
        int firstIndex = js.indexOf("\"", js.indexOf("file"));
        int secondIndex = js.indexOf("\"", firstIndex + 1);
        String result = js.substring(firstIndex + 1, secondIndex);
        return result;
    }*/
}
