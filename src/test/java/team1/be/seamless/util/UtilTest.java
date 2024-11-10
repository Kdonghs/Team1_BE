package team1.be.seamless.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void 빈칸_검사(){
//        given
        String text1 = " ";
        String text2 = "";
        String text3 = " test";

//        when

        Boolean isnull1 = Util.isNull(text1);
        Boolean isnull2 = Util.isNull(text2);
        Boolean isnull3 = Util.isNull(text3);

//        then

        assertEquals(isnull1, true);
        assertEquals(isnull2, true);
        assertEquals(isnull3, false);
    }

    @Test
    void 로컬데이트타임_파싱_성공(){
//        given
        String date1 = LocalDate.now().toString();

//        when
        LocalDateTime parse = Util.parseDate(date1);

//        then
        assertNotNull(parse);
    }

    @Test
    void 로컬데이트타임_파싱_실패_형식오류(){
//        given
        String date1 = "2024-05-24T27:66:10";

//        when
        LocalDateTime parse = Util.parseDate(date1);

//        then
        assertThrows(DateTimeParseException.class, () -> {
            Util.parseDate(date1);  // 파싱 시도, 실패 시 예외 발생
        });
    }
}