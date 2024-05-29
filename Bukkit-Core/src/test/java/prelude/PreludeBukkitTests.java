package prelude;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.junit.jupiter.api.*;

@DisplayName("Plugin Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PreludeBukkitTests {

    private static ServerMock server;
    private static PreludePlugin plugin;

    // ERM WTF
    // ADDING THE PRIVATE CONSTRUCTOR BREAKS THE PLUGIN
    // NOT ADDING IT BREAKS THE TESTS?!?!


//    @BeforeAll
//    @DisplayName("Test Plugin Initialization")
//    public static void setUpPlugin() {
//        server = MockBukkit.mock();
//        server.addPlayer("Test_Player");
//        plugin = MockBukkit.load(PreludePlugin.class);
//    }
//
//    @AfterAll
//    @DisplayName("Tear down Plugin")
//    public static void tearDownPlugin() {
//        MockBukkit.unmock();
//    }
//
//    @Order(1)
//    @Test
//    @DisplayName("Test Adapter")
//    public void testVersionAdapter() {
//        Assertions.assertTrue(plugin.getAdapter().isPresent());
//    }
}