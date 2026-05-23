package by.andd3dfx.searchapp.search;

import by.andd3dfx.searchapp.search.model.SearchResultItem;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SearchHelper {

    /**
     * For search implementation next link was useful:
     */
    public List<SearchResultItem> search(String query, int maxResults) {
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String url = String.format("https://html.duckduckgo.com/html/?q=%s", encodedQuery);

            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                    .timeout(10000)
                    .get();

            // DuckDuckGo в HTML использует класс "result__url" для ссылок
            Elements links = doc.select("a.result__url");
            List<SearchResultItem> results = new ArrayList<>();

            for (int i = 0; i < Math.min(links.size(), maxResults); i++) {
                String href = links.get(i).absUrl("href");
                String title = doc.select("h2.result__title").get(i).text();
                if (!href.isEmpty() && href.startsWith("http")) {
                    results.add(new SearchResultItem(href, title));
                }
            }
            return results;
        } catch (Exception e) {
            return List.of();
        }
    }
}
