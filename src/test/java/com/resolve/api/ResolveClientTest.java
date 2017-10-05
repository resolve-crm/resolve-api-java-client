package com.resolve.api;

import com.resolve.api.resource.Developments;
import com.resolve.api.resource.Objects;
import com.resolve.api.response.ItemResponse;
import com.resolve.api.response.ItemResponseItem;
import com.resolve.api.response.ItemsResponseItem;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple ResolveClient.
 */
public class ResolveClientTest extends TestCase
{
    protected ResolveClient client;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ResolveClientTest(String testName )
    {
        super( testName );
        this.client = new ResolveClient("djI=@NPnHvWT3pwmgQ5Tr");
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ResolveClientTest.class );
    }

    /**
     * Test if we can get resource
     */
    public void testGetResource() throws Exception
    {
        Objects objects = (Objects) this.client.resource("objects");
        assertThat(objects, instanceOf(Objects.class));
    }

    /**
     * Test hasResource
     */
    public void testHasResource()
    {
        // Existing resource
        assertTrue(this.client.hasResource("objects"));

        // Non-existant resource
        assertFalse(this.client.hasResource("nosuchresource"));
    }

    /**
     * Test if we can get resource items
     */
    public void testGetResourceItems() throws Exception
    {
        Objects objects = null;
        objects = (Objects)this.client.resource("objects");

        List<ItemsResponseItem> items = objects.getMany().getItems();

        assertEquals(2, items.size());
    }

    /**
     * Test if we can get developments resource items
     */
    public void testGetDevelopmentsResourceItems() throws Exception
    {
        Developments developments = null;
        developments = (Developments)this.client.resource("developments");

        List<ItemsResponseItem> items = developments.getMany().getItems();

        assertEquals(1, items.size());
    }

    /**
     * Test if we can get resource items by status
     */
    public void testGetResourceItemsByStatus() throws Exception
    {
        Objects objects = null;
        objects = (Objects)this.client.resource("objects");

        // Get active items
        List<ItemsResponseItem> activeItems = objects.getMany("active").getItems();
        assertEquals(2, activeItems.size());

        // Get archived items
        List<ItemsResponseItem> archivedItems = objects.getMany("archived").getItems();
        assertEquals(0, archivedItems.size());

        // Get deleted items
        List<ItemsResponseItem> deletedItems = objects.getMany("archived").getItems();
        assertEquals(0, deletedItems.size());
    }

    /**
     * Test if we can get resource items by date
     */
    public void testGetResourceItemsByDate() throws Exception
    {
        Objects objects = null;
        objects = (Objects)this.client.resource("objects");

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-01-01 00:00:00");

        // Get items by last modified date
        List<ItemsResponseItem> dateItems = objects.getMany(date).getItems();
        assertEquals(2, dateItems.size());

        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-09-01 00:00:00");

        // Get items by last modified date
        List<ItemsResponseItem> date1Items = objects.getMany(date1).getItems();
        assertEquals(0, date1Items.size());
    }

    /**
     * Test if we can get resource item
     */
    public void testGetResourceItem() throws Exception
    {
        Objects objects = null;
        objects = (Objects)this.client.resource("objects");

        ItemResponse itemResponse = objects.getOne(1);
        ItemResponseItem item = itemResponse.getItem();

        assertEquals(1, item.getId());
    }

    /**
     * Test if we can set new version
     */
    public void testCanSetVersion() throws Exception
    {
        ResolveClient client = this.client.setVersion("2.0.1");

        // Changed version
        assertEquals("2.0.1", this.client.getVersion());

        // Returned client
        assertEquals(this.client, client);
    }
}
