package com.zjxz.mikaniaplatform.job;

import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zjxz.mikaniaplatform.enums.BusinessFailCode;
import com.zjxz.mikaniaplatform.exception.GlobalException;
import com.zjxz.mikaniaplatform.model.entity.PopularizationScience;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.service.PopularizationScienceService;
import com.zjxz.mikaniaplatform.util.WebClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hzzzzzy
 * @date 2023/4/22
 * @description 定时爬取有关薇甘菊政府政策信息
 */
@Component
@Slf4j
public class CrawlGovernmentPolicy {
    @Autowired
    private PopularizationScienceService popularizationScienceService;
    /**
     * 爬取网站的url
     */
    @Value("crawler.url")
    private String url;

    /**
     * 开启线程池
     */
    private static final ExecutorService CACHE_PREHEAT_EXECUTOR = Executors.newFixedThreadPool(10);

    /**
     * 每隔10条存储数据，然后清理list，方便内存回收
     */
    private static final int BATCH_COUNT = 10;

    /**
     * 缓存数据
     */
    private List<PopularizationScience> cacheList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     * 开启定时任务, 每个月爬取一次
     */
    @Scheduled(cron = "0 0 0 1 1-12 ?")
    private void doCrawl(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        CACHE_PREHEAT_EXECUTOR.submit(()->{
            WebClient webClient = WebClientUtils.getWebClient();
            HtmlPage page = null;
            try {
                page = webClient.getPage(url);
            } catch (IOException e) {
                throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("获取链接失败"));
            }
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
                log.info("链接为：{}", href);
                log.info("标题为：{}", title);
                log.info("日期为：{}", date);
                PopularizationScience item;
                var queryWrapper = new LambdaQueryWrapper<>();

                try {
                    item = PopularizationScience.builder()
                            .title(title)
                            .url(href)
                            .time(format.parse(date))
                            .build();
                } catch (ParseException e) {
                    throw new GlobalException(new Result<>().error(BusinessFailCode.OBJECT_TYPE_ERROR).message("类型转换失败"));
                }
                cacheList.add(item);
                if (cacheList.size() >= BATCH_COUNT){
                    saveData();
                    // 存储完成清理 list
                    cacheList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }
            // 保存剩余的数据
            saveData();
        });
    }

    /**
     * 保存数据
     */
    private void saveData() {
        boolean flag = popularizationScienceService.saveBatch(cacheList);
        if (flag){
            throw new GlobalException(new Result<>().error(BusinessFailCode.OBJECT_TYPE_ERROR).message("插入文章信息失败"));
        }
    }
}
