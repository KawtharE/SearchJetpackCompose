# SearchJetpackCompose
Search module with Jetpack compose

## Key steps:

- In the ``Student`` data class the function ``interpretInputText`` define as much pattern as you need for your custom search.
- In ``MainViewModel`` 3 main stateFlow: searchInput, isSearching and students list, which are converted to compose state in MainActivity while collecting: ``collectAsState()``
- ``students`` list depend on ``searchInput`` and ``_students`` that's why we use ``combine`` operator which will be triggred whenver one of those are changed.
-  The logic behind adding ``.debounce(1000L)`` is to improve search performance when implementing search with a real remote API: it create a delay until next block is executed, meanwhile if the ``searchInput`` change value the previous call/emission (with previous value) is canceled and the new value will be used for search, and by doing this we reduce the API call.
-  In MainActivity to be able to get instance of MainViewModel like this: ``val mainViewModel = viewModel<MainViewModel>()`` you need first to add the dependency:
    ```
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    ```

<img src=https://github.com/KawtharE/SearchJetpackCompose/assets/19794865/8fd524fc-14e9-4498-843a-ae3cbc1ccb47 width="200" height="400" />




**Note:** there is no architecture followed here

