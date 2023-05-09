package com.zjxz.mikaniaplatform;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zjxz.mikaniaplatform.util.WebClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadImages {
    public static void main(String[] args) throws Exception {
        List<String> urls = new ArrayList<>();
        urls.add("https://www.cfh.ac.cn/Data/2023/202304/20230401/Thumbnail/bce75f09-035e-4fa9-803a-b7cff8fc61a9.JPG");
        urls.add("https://www.cfh.ac.cn/Data/2023/202304/20230401/Thumbnail/bce75f09-035e-4fa9-803a-b7cff8fc61a9.JPG");
        urls.add("https://www.cfh.ac.cn/Data/2023/202304/20230401/Thumbnail/9b19697a-5ee7-469b-91cb-1cfe9c5de3f6.JPG");

        for (String url : urls) {
            String[] parts = url.split("https://www.cfh.ac.cn/Data/2023/202304/20230401/Thumbnail/");
            String fileName = parts[1];

            URL imageUrl = new URL(url);
            try (InputStream imageReader = new BufferedInputStream(imageUrl.openStream());
                 OutputStream imageWriter = new BufferedOutputStream(new FileOutputStream(fileName))) {
                byte[] buffer = new byte[1024];
                int readBytes;
                while ((readBytes = imageReader.read(buffer)) > 0) {
                    imageWriter.write(buffer, 0, readBytes);
                }
            }
        }
    }
}