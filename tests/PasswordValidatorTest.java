import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class PasswordValidatorTest {
    @Test void shouldProcessValid() {
        PasswordValidator obj = new PasswordValidator();
        assertNotNull(obj.process(Map.of("key", "val")));
    }
    @Test void shouldHandleNull() {
        PasswordValidator obj = new PasswordValidator();
        assertNull(obj.process(null));
    }
    @Test void shouldTrackStats() {
        PasswordValidator obj = new PasswordValidator();
        obj.process(Map.of("x", 1));
        assertEquals(1, obj.getStats().get("processed"));
    }
    @Test void supportShouldWork() {
        PolicyConfig obj = new PolicyConfig();
        assertNotNull(obj.process(Map.of("data", "test")));
    }
}
