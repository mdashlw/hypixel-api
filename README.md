[ ![Download](https://api.bintray.com/packages/mdashlw/maven/hypixel-api/images/download.svg) ](https://bintray.com/mdashlw/maven/hypixel-api/_latestVersion)
[![pipeline status](https://gitlab.com/mdashlw/hypixel-api/badges/master/pipeline.svg)](https://gitlab.com/mdashlw/hypixel-api/commits/master)

# Hypixel API

Kotlin Hypixel API wrapper

## Importing

Replace `VERSION` with the latest version above.

### Gradle Groovy DSL

<details><summary>build.gradle</summary>
<p>

```gradle
repositories {
    jcenter()
}

dependencies {
    implementation 'ru.mdashlw.hypixel:hypixel-api:VERSION'
}
```

</p>
</details>

### Gradle Kotlin DSL

<details><summary>build.gradle.kts</summary>
<p>

```kotlin
repositories {
    jcenter()
}

dependencies {
    implementation("ru.mdashlw.hypixel:hypixel-api:VERSION")
}
```

</p>
</details>

### Maven

<details><summary>pom.xml</summary>
<p>

```xml
<depedencies>
    <dependency>
        <groupId>ru.mdashlw.hypixel</groupId>
        <artifactId>hypixel-api</artifactId>
        <version>VERSION</version>
  </dependency>
</depedencies>

<repositories>
    <repository>
      <id>jcenter</id>
      <name>JCenter</name>
      <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
```

</p>
</details>

## Usage

### Getting Started

To start working with Hypixel API, you need to private an API key.

You can get your api key via `/api new` on Hypixel Network.

```kotlin
HypixelAPI.apiKey = UUID.fromString("API_KEY_HERE")
```

You can customize the mode of outputs:

* Raw - just plain text, without any formatting.
* Markdown - with markdown formatting.
* Colorized - with minecraft color codes.

The default is **Raw**.

Changing the mode:

```kotlin
HypixelAPI.outputMode = HypixelAPI.OutputMode.COLORIZED
```

### Player

```kotlin
HypixelAPI.getPlayerByUUUID("uuid_here") // Can be both dashed and undashed
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
