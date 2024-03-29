package by.andd3dfx.searchapp.search.model;

public class SearchResultItem {

    private final String url;
    private final String title;

    public SearchResultItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
