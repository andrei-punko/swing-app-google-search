package by.andd3dfx.searchapp.search;

import by.andd3dfx.searchapp.search.model.SearchResult;
import by.andd3dfx.searchapp.search.model.SearchResultItem;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchHelper {

    private final String SEARCH_URL = "https://www.google.com/search?q=%s&start=%d";
    private final String CHARSET = "UTF-8";
    private final String USER_AGENT = "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:99.0) Gecko/20100101 Firefox/99.0";

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchHelper.class);

    /**
     * For search implementation next link was useful:
     * <p>
     * http://stackoverflow.com/questions/3727662/how-can-you-search-google-programmatically-java-api
     */
    public SearchResult search(String searchString, int maxResults) {
        List<SearchResultItem> resultItems = new ArrayList<>();
        try {
            int offset = 0;
            while (resultItems.size() < maxResults) {
                Elements elements = searchLinks(searchString, offset);
                if (elements.isEmpty()) {
                    break;
                }
                List<SearchResultItem> searchResultItems = batchSearch(elements);
                resultItems.addAll(searchResultItems);
                offset += 10;
            }
            while (resultItems.size() > maxResults) {
                resultItems.remove(resultItems.size() - 1);
            }
        } catch (Exception e) {
            LOGGER.error("Error during search occurs", e);
        }

        return new SearchResult(resultItems);
    }

    private List<SearchResultItem> batchSearch(Elements elements) throws Exception {
        List<SearchResultItem> result = new ArrayList<>();
        for (Element link : elements) {
            SearchResultItem searchResultItem = extractSearchResultItemFromLink(link);
            if (searchResultItem != null) {
                result.add(searchResultItem);
            }
        }
        return result;
    }

    private Elements searchLinks(String searchString, int offset) throws Exception {
        String keyword = URLEncoder.encode(searchString, CHARSET);
        String url = String.format(SEARCH_URL, keyword, offset);
        return Jsoup
                .connect(url)
                .userAgent(USER_AGENT)
                .get().select(".g a");
    }

    private SearchResultItem extractSearchResultItemFromLink(Element link) throws Exception {
        String title = link.text();

        String absoluteUrl = link.absUrl("href");
        if (absoluteUrl.startsWith("https://translate.google.com")) {
            return null;
        }

        return new SearchResultItem(absoluteUrl, title);
    }
}
