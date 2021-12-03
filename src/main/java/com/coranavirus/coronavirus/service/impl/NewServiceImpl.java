package com.coranavirus.coronavirus.service.impl;

import com.coranavirus.coronavirus.DTO.news.NewResponse;
import com.coranavirus.coronavirus.entity.News;
import com.coranavirus.coronavirus.repository.NewRepository;
import com.coranavirus.coronavirus.service.NewService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class NewServiceImpl implements NewService {
    @Autowired
    private NewRepository newRepository;

    private static final String URL_WEBSITE_COVID_19_GOV = "https://covid19.gov.vn/ban-tin-covid-19.htm";
    private static final String URL_BAO_MOI = "https://baomoi.com/phong-chong-dich-covid-19/top/328.epi";

    private static final Logger logger = LoggerFactory.getLogger(NewServiceImpl.class);

    @Override
    public boolean isExistNewByNewId(String newId) {
        return newRepository.findById(newId).isPresent();
    }

    @Override
    public List<NewResponse> getAllNews() {
        return newRepository.findAll().stream().map(news -> mapToNewResponse(news)).collect(Collectors.toList());
    }

    @PostConstruct
    @Scheduled(cron="0 0/5 * * * ?")  //executed every 5 minutes
    public void crawlingCovid19GOV() {
        List<News> newsList = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "/home/vanket/Downloads/chromedriver_linux64/chromedriver");
        //Initiating your chromedriver
        WebDriver driver = new ChromeDriver();
        //Applied wait time
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //open browser with desried URL
        driver.get(URL_WEBSITE_COVID_19_GOV);

        // fetch data COVID-19 new case main-sub layout
        for (WebElement row : driver.findElements(By.cssSelector(".layout__main-sub"))) {
            String linkDetail = row.findElement(By.className("home__fp-viewmore")).getAttribute("href");
            for(WebElement item : row.findElements(By.cssSelector(".home__focus-list .swiper-wrapper .item"))) {
                String newId = item.getAttribute("data-id");
                String time = item.getAttribute("data-date");
                String title = item.findElement(By.className("box-focus-link-title")).getText();
                String shortDescription = item.findElement(By.className("box-focus-sapo")).getText();
                if (isValidNotNull(newId, time, title, shortDescription, linkDetail)
                        && !isExistNewByNewId(newId)) {
                    newsList.add(mapToEntity( URL_WEBSITE_COVID_19_GOV,null, title, newId,
                            linkDetail, shortDescription, time));
                }
            }
        }

        //fetch data COVID-19 main-content layout
        for (WebElement row : driver.findElements(By.className("layout__main-content"))) {
            // data focus banner
            for (WebElement itemFocus: row.findElements(By.className("box-list-focus-item "))) {
                String linkAvatar = itemFocus.findElement(By.className("box-list-focus-avatar"))
                        .getAttribute("src");
                String title = itemFocus.findElement(By.className("box-list-focus-link-title")).getText();
                String newId = itemFocus.findElement(By.className("box-list-focus-link-title"))
                        .getAttribute("data-id");
                String linkDetail = itemFocus.findElement(By.className("box-list-focus-link-title"))
                        .getAttribute("href");
                String shortDescription = itemFocus.findElement(By.className("box-list-focus-sapo")).getText();
                if (isValidNotNull(linkAvatar, title, newId, linkDetail, shortDescription)
                        && !isExistNewByNewId(newId)) {
                    newsList.add(mapToEntity( URL_WEBSITE_COVID_19_GOV, linkAvatar, title, newId,
                            linkDetail, shortDescription, null));
                }
            }
        }

        // fetch data following time
        for (WebElement row: driver.findElements(By.cssSelector(".list__listing .timeline_list"))) {
            for (WebElement item : row.findElements(By.className("box-stream-item"))) {
                String linkDetail = item.findElement(By.className("box-stream-link-with-avatar"))
                        .getAttribute("href");
                String linkAvatar = item.findElement(By.className("box-stream-avatar")).getAttribute("src");
                String title = item.findElement(By.className("box-stream-link-title")).getText();
                String newId = item.findElement(By.className("box-stream-link-title"))
                        .getAttribute("data-id");
                String shortDescription = item.findElement(By.className("box-stream-sapo")).getText();
                if (isValidNotNull(linkAvatar, title, newId, linkDetail, shortDescription)
                        && !isExistNewByNewId(newId)) {
                    newsList.add(mapToEntity(URL_WEBSITE_COVID_19_GOV, linkAvatar, title, newId,
                            linkDetail, shortDescription, null));
                }
            }
        }

        driver.close();
        // save all data
        newRepository.saveAll(newsList);
    }

    @PostConstruct
    @Scheduled(cron="0 0/5 * * * ?")  //executed every 5 minutes
    public void crawlingCovid19BaoMoi() {
        List<News> newsList = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "/home/vanket/Downloads/chromedriver_linux64/chromedriver");
        //Initiating your chromedriver
        WebDriver driver = new ChromeDriver();
        //Applied wait time
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //open browser with desried URL
        driver.get(URL_BAO_MOI);
        for (WebElement row: driver.findElements(By.cssSelector(".bm_AN .bm_E"))) {
            WebElement element = row.findElement(By.className("bm_M"));
            String linkDetail = element.findElement(By.cssSelector("span a")).getAttribute("href");
            String linkAvatar = element.findElement(By.cssSelector("figure.bm_Bg img")).getAttribute("src");
            element = row.findElement(By.className("bm_AP"));
            String title = element.findElement(By.cssSelector(".bm_P span a")).getText();
            String shortDescription = element.findElement(By.className("bm_AQ")).getText();
            String time = element.findElement(By.cssSelector(".bm_U time")).getAttribute("datetime");
            String[] splitLink = linkDetail.split("/c/");
            String newId = splitLink[splitLink.length - 1].split(".epi")[0];
            if (isValidNotNull(linkAvatar, title, newId, linkDetail, shortDescription, time)
                    && !isExistNewByNewId(newId)) {
                newsList.add(mapToEntity(URL_WEBSITE_COVID_19_GOV, linkAvatar, title, newId,
                        linkDetail, shortDescription, null));
            }
        }
        driver.close();
        newRepository.saveAll(newsList);
    }

    public boolean isValidNotNull(Object ...parameters) {
        for (Object parameter: parameters) {
            if (parameter == null || "".equals(parameter)) return false;
        }
        return true;
    }

    public News mapToEntity(String URL, String linkAvatar, String title, String newId, String linkDetail,
                            String shortDescription, String time) {
        News news = new News();
        news.setNewId(newId);
        news.setTitle(title);
        news.setLinkAvatar(linkAvatar);
        news.setLinkDetail(URL + linkDetail);
        news.setShortDescription(shortDescription);
        if (time != null) news.setTime(Timestamp.valueOf(LocalDateTime.parse(time)));
        return news;
    }

    public NewResponse mapToNewResponse(News news) {
        NewResponse newResponse = new NewResponse();
        newResponse.setNewId(news.getNewId());
        newResponse.setLinkAvatar(news.getLinkAvatar());
        newResponse.setLinkDetail(news.getLinkDetail());
        newResponse.setTitle(news.getTitle());
        newResponse.setTime(news.getTime());
        newResponse.setShortDescription(news.getShortDescription());
        return newResponse;
    }
}
