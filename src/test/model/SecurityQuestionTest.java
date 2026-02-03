package test.model;

import com.revpasswordmanager.model.SecurityQuestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityQuestionTest {

    @Test
    void testSecurityQuestion() {
        SecurityQuestion sq = new SecurityQuestion(1, "Q?", "AHash");
        assertEquals(1, sq.getUserId());
        assertEquals("Q?", sq.getQuestion());
        assertEquals("AHash", sq.getAnswerHash());

        sq.setId(5);
        assertEquals(5, sq.getId());

        sq.setQuestion("NewQ?");
        sq.setAnswerHash("NewHash");

        assertEquals("NewQ?", sq.getQuestion());
        assertEquals("NewHash", sq.getAnswerHash());
    }
}
