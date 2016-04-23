package com.factweavers.NewsSkud;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.frontier.DocIDServer;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

public class Controller {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/home/vineeth/crawl";
        int numberOfCrawlers = 7;
        

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxConnectionsPerHost(1);
        config.setMaxDepthOfCrawling(1);
        config.setResumableCrawling(true);
        config.setFollowRedirects(true);

        
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        //controller.addSeed("https://www.yahoo.com/news/fitness-trainer-missy-bevers-killing-continues-to-perplex-texas-police-002259586.html");
        //controller.addSeed("https://www.yahoo.com/news/fitness-trainer-missy-bevers-killing-continues-to-perplex-texas-police-002259586.html");
        //controller.addSeed("https://www.yahoo.com/news/josh-norman-picks-a-new-team--172850215.html");
        //controller.addSeed("http://english.manoramaonline.com/sports/football/kerala-blasters-get-new-owners-as-sachin-halves-his-stake.html",-1,"vineeth");

        String urlString = "http://english.manoramaonline.com/sports/football/kerala-blasters-get-new-owners-as-sachin-halves-his-stake.html";
        
        WebURL url = new WebURL();
        url.setURL(urlString);
        url.setInfo("Factweavers");
        
        controller.getFrontier().schedule(url);

        
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
