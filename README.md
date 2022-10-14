# marvel_characters_android
Android app that lists the marvel characters


## INITIAL CONFIGURATION

Create text file `marvel_characters_android/api.properties` with the API credentials:

	PUBLIC_KEY="Your public api key"
	PRIVATE_KEY="Your private api key"


You can get this information at https://developer.marvel.com/

## CODE ARCHITECTURE

This project applies the MVVM architecture pattern, with certain tints of hexagonal architecture.

The androidConnectors and the logger package contain interceptors to abstract the SDK dependencies from the business logic.

The business logic is encapsulated in domain package.

There are tests wich check api connection and pagination.


## LIBRARIES

This project uses the following libraries:


- Retrofit : Library to implement requests to Marvel API REST.
- Glide : Library that downloads and caches images from the internet.
- Hilt : Dependency injection library to abstract business logic from android SDK.
- AndroidX Navigation : Library to navigate between fragments, making it possible to send arguments
- Junit : Library that execute tests.
- Hamcrest : Assertions library to use in tests.
- Mockk : Library to mock test dependencies, kotlin friendly.
- AndroidX libraries.