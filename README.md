 [![Bintray](https://img.shields.io/bintray/v/mdashlw/maven/hypixel-api.svg?label=Hypixel%20API&style=flat-square)](https://bintray.com/mdashlw/maven/hypixel-api/_latestVersion)
 [![Travis (.com)](https://img.shields.io/travis/com/mdashl/hypixel-api.svg?style=flat-square)](http://travis-ci.com/mdashl/hypixel-api)
 [![GitHub](https://img.shields.io/github/license/mdashl/hypixel-api.svg?style=flat-square)](https://choosealicense.com/licenses/mit/)

# Hypixel API

Kotlin Hypixel API wrapper

## Importing

Replace `VERSION` with the latest version above.

### Gradle

```gradle
repositories {
    jcenter()
}

dependencies {
    implementation 'com.github.mdashl:hypixel-api:VERSION'
}
```

### Maven

```xml
<depedencies>
    <dependency>
        <artifactId>hypixel-api</artifactId>
        <groupId>com.github.mdashl</groupId>
        <scope>compile</scope>
        <version>VERSION</version>
  </dependency>
</depedencies>

<repositories>
    <repository>
      <id>jcenter</id>
      <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
```

## Usage

### Getting Started

To start working with Hypixel API, you need to private an API key.

You can get your api key via `/api new` on Hypixel Network.

```kotlin
HypixelAPI.apiKey = UUID.fromString("API_KEY_HERE")
```

You can customize the mode of outputs (uncolorized, colorized).

Default is `uncolorized`.

```kotlin
HypixelAPI.mode = HypixelAPI.Mode.COLORIZED
```

### Player

```kotlin
HypixelAPI.getPlayerByUUID("uuid_here") // Can be both dashed and undashed
HypixelAPI.getPlayerByName("name_here")
```

### Session

```kotlin
HypixelAPI.getSessionByUUID("uuid_here") // Can be both dashed and undashed
```

### Guild

```kotlin
HypixelAPI.getGuildByName("guild_name_here")
HypixelAPI.getGuildByPlayer("player_uuid_here") // Can be both dashed and undashed
```

## License

The project is licensed under the **[MIT license](https://choosealicense.com/licenses/mit/)**.
