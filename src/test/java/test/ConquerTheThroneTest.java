package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mockito.Mock;
import solution.ConquerTheThrone;

@RunWith(Suite.class)
@SuiteClasses({ConquerTheThrone.class})
public class ConquerTheThroneTest {

  // @Mock
  // ConquerTheThrone conquerTheThrone;


  @Test
  public void testReadInput() {
    fail("Not yet implemented");
  }

  @Test
  public void testCleanInput() {
    List<String> testArray = new ArrayList<>();
    testArray.add("Air, \"Death is so terribly final, while life is full of possibilities.\"");
    testArray
        .add("Land, \"Crazy Fredrick bought many very exquisite pearl, emerald and diamond jewels.\"");
    String[][] response = ConquerTheThrone.cleanInput(testArray);
    assertEquals("Air", response[0][0]);
    System.out.println(response[0][1]);
    assertEquals("Land", response[1][0]);

  }

  @Test
  public void testGetAllies() {
    fail("Not yet implemented");
  }

  @Test
  public void testParseString() {
    fail("Not yet implemented");
  }

  @Test
  public void testMain() {
    fail("Not yet implemented");
  }
}
