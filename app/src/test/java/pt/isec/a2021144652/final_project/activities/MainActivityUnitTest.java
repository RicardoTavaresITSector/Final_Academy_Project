package pt.isec.a2021144652.final_project.activities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class MainActivityUnitTest {

    private MainActivity mainActivity;

    @Mock
    private ConnectivityManager connectivityManager;

    @Mock
    private NetworkInfo networkInfo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainActivity = new MainActivity();
        mainActivity.setConnectivityManager(connectivityManager);
    }

    @Test
    public void testIsOnline_WithNetworkAvailable_ReturnsTrue() {
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        when(networkInfo.isConnected()).thenReturn(true);
        assertTrue(mainActivity.isOnline());
    }

    @Test
    public void testIsOnline_WithNoNetwork_ReturnsFalse() {
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(null);
        assertFalse(mainActivity.isOnline());
    }
}