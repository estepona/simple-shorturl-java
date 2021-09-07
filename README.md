# Simple ShortUrl Server

A simple ShortUrl server implemented in Java.

## Overview

This ShortUrl server accepts an url (i.e. `https://www.bing.com`) and returns a short url of a hostname and six characters such as `http://localhost:8000/CeqohK` (when it runs locally). And when user makes a request to that address the browser is redirected to the original url registered under the short url `CeqohK`.

The server generates a short url by hashing the passed original url with a MD5 hash algorithm, encode it with a base62 encoder, and then take the first 6 characters as the short url. With 6 characters produced there can be ~57 billion possible short urls, enough for this simple demonstration.

As a simple storage solution, the original url, its MD5 hashed string, and encoded short url are all stored in a local **SQLite 3** database.

## Getting Started

**Gradle**, **JDK 11**, and **SQLite 3** are required to run the code.

Start the server by issue `./gradlew run`.

## APIs

### Create Short Url

Request  
`GET http://localhost:8000/create?url=<string>`

Response  
```
Status Code: 200

{
  "shortUrl": "http://localhost:8000/CeqohK"
}
```

### Get Url

Request  
`GET http://localhost:8000/<string>`

Response  
```
Status Code: 302
Header: Location=https://www.google.com
```

## Limitations

As a simple demonstration, there are several limitations with this implementation:
1. There can be key collisions by taking the first 6 characters of a base62 encoded MD5 string. To prevent key collision from happening, the duplicated key can be slightly modified such as appending new characters or changing one or two characters.
2. Since the final short url is not guaranteed to be unique, index of it in the database cannot be created to make the url lookup faster.
3. This implementation does not take user into account, thus unable to have any user-related functionalities such as register a short url under a user's account or define a custom unused short url.
4. Expiration of the short url is not implemented.

## Author

[Binghuan Zhang](https://github.com/estepona) - esteponawondering@gmail.com
