package model.MARK_II;

import java.awt.Dimension;
import model.MARK_II.ConnectTypes.RegionToRegionRectangleConnect;
import model.MARK_II.ConnectTypes.RegionToRegionConnect;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version MARK II | June 29, 2013
 */
public class Test_Region extends junit.framework.TestCase {
    private Region region;

    public void setUp() {
	this.region = new Region("region", 5, 7, 4, 20, 3);
    }

    public void test_Region() {
	try {
	    this.region = new Region("V1", 0, 7, 4, 20, 3);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("numberOfColumnsAlongXAxis in Region constructor cannot be less than 1",
		    expected.getMessage());
	}

	try {
	    this.region = new Region("V1", 5, 7, 0, 20, 3);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("cellsPerColumn in Region constructor cannot be less than 1",
		    expected.getMessage());
	}

	try {
	    this.region = new Region("V1", 5, 7, 1, -20, 3);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("percentMinimumOverlapScore in Region constructor must be between 0 and 100",
		    expected.getMessage());
	}
    }

    public void test_getBottomLayerXYAxisLength() {
	Region bottomLayer = new Region("bottomLayer", 25, 35, 1, 50, 1);
	RegionToRegionConnect connectType = new RegionToRegionRectangleConnect();
	connectType.connect(bottomLayer, this.region, 0, 0);

	Dimension bottomLayerDimensions = this.region.getBottomLayerXYAxisLength();
	assertEquals(25, bottomLayerDimensions.width);
	assertEquals(35, bottomLayerDimensions.height);
    }

    public void test_toString() {
	Region region2 = new Region("region2", 5, 7, 4, 20, 3);
	Region region3 = new Region("region3", 5, 7, 4, 20, 3);

	this.region.addChildRegion(region2);
	this.region.addChildRegion(region3);

	System.out.println(this.region.toString());
    }
}