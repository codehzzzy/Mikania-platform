package com.zjxz.mikaniaplatform;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zjxz.mikaniaplatform.util.WebClientUtils;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;


@SpringBootTest
class MikaniaPlatformApplicationTests {

    public static void main(String[] args) throws IOException {
        WebClient webClient = WebClientUtils.getWebClient();
        HtmlPage page = webClient.getPage("http://search.gd.gov.cn/search/local/174?keywords=%E8%96%87%E7%94%98%E8%8F%8A");
        Document document = Jsoup.parse(page.asXml());
        Elements elements = document
                .selectXpath("//*[@id=\"list-body\"]")
                .select(".list-item");
        for (Element element : elements) {
            // 链接
            String href = element.select("a").attr("href");
            // 标题
            String title = element.selectFirst("a.title").text();
            // 日期
            String date = document.select("div.url-date > span.date").get(0).text();
            System.out.println(href);
            System.out.println(title);
            System.out.println(date);
            System.out.println();
        }
    }
}