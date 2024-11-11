package team1.be.seamless.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import team1.be.seamless.util.page.SingleResult;

class PageTest {

    @Test
    void singleResult() {
//        given
        String[] test = "test".split("");

//        when
        SingleResult<String[]> singleResult = new SingleResult<>(test);

//        then
        assertEquals(test, singleResult.getResultData());
    }

    @Test
    void page의_데이터는_페이지여야_한다() {
//        given
        String test = "test";

//        when
        Page<String> page = new PageImpl<>(Arrays.stream(test.split("")).toList());

//        then
        assertEquals(test, String.join("", page.stream().toList()));

    }
}