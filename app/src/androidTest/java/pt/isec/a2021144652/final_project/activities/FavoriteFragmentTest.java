package pt.isec.a2021144652.final_project.activities;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FavoriteFragmentTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testFavoriteFragmentNotNull() {
        FragmentScenario<FavoriteFragment> scenario = launchInContainer(FavoriteFragment.class);

        assertNotNull(scenario);
    }

    @Test
    public void testConfigurationChange_Landscape() {
        FragmentScenario<FavoriteFragment> scenario = launchInContainer(FavoriteFragment.class);

        scenario.onFragment(fragment -> {
            fragment.onConfigurationChanged(
                    InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getConfiguration()
            );
        });

        scenario.onFragment(fragment -> {
            assertTrue(fragment.recyclerView.getLayoutManager() instanceof GridLayoutManager);
        });
    }

    @Test
    public void testConfigurationChange_Portrait() {
        FragmentScenario<FavoriteFragment> scenario = launchInContainer(FavoriteFragment.class);

        scenario.onFragment(fragment -> {
            fragment.onConfigurationChanged(
                    InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getConfiguration()
            );
        });

        scenario.onFragment(fragment -> {
            assertTrue(fragment.recyclerView.getLayoutManager() instanceof LinearLayoutManager);
        });
    }
}