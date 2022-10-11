import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FlikTest {
    
    @Test
    public void testFlik(){
        for(int i=0;i<128;i++){
            assertTrue(Flik.isSameNumber(i,i));
        }
        for(int i = 128; i < 500 ; i++){
            assertTrue(Flik.isSameNumber(i,i));
        }
    }
}
