# Resolve API Java client

## Simple usage

````java
import com.resolve.api.ResolveClient;
import com.resolve.api.response.ItemResponse;
import com.resolve.api.response.ItemsResponse;
import com.resolve.api.response.ItemResponseItem;
import com.resolve.api.response.ItemsResponseItem;

// Init client
ResolveClient client = new ResolveClient("YOUR_API_KEY");

// Get resource
Objects objectsResource = client.getResource("objects");

// Get items
ItemsResponse itemsResponse = objectsResource.getMany();
List<ItemsResponseItem> items = itemsResponse.getItems();

// Get one item by ID
ItemResponse itemResponse = objectsResource.getOne(1);
ItemResponseItem item = itemResponse.getItem();
````

## TODO
- [X] Add support to get by date, status for `getMany()`.
- [X] Support all resources
- [ ] Feature to get tags, files and clients related to item (`includeTags`, `includeFiles`, `includeClients`)
- [ ] Feature to add custom query params
- [ ] Cache support