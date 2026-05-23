package by.andd3dfx.searchapp.search;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchHelperTest {

    private SearchHelper searchHelper;

    @Before
    public void setUp() {
        searchHelper = new SearchHelper();
    }

    @Test
    public void search() {
        var result = searchHelper.search("Andrei Punko", 10);

        assertThat(result.size(), is(10));
    }
}
