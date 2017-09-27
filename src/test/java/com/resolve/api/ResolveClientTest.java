package com.resolve.api;

import com.resolve.api.resource.Objects;
import com.resolve.api.response.ItemResponse;
import com.resolve.api.response.ItemResponseItem;
import com.resolve.api.response.ItemsResponseItem;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

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
        assertEquals("2.0.1", this.client.version);

        // Returned client
        assertEquals(this.client, client);
    }
}
