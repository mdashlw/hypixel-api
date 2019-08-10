[ ![Download](https://api.bintray.com/packages/mdashlw/maven/hypixel-api/images/download.svg) ](https://bintray.com/mdashlw/maven/hypixel-api/_latestVersion)
[![CircleCI](https://circleci.com/gh/mdashlw/hypixel-api.svg?style=svg)](https://circleci.com/gh/mdashlw/hypixel-api)

# Hypixel API

Java Hypixel API wrapper.

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

### Getting started

To use Hypixel Public API you must provide an API key.
You can obtain a key by doing `/api new` on the server.

```java
HypixelAPI api = new HypixelAPI("API_KEY");
HypixelAPI api = new HypixelAPI("API_KEY", new OkHttpClient());
HypixelAPI api = new HypixelAPI("API_KEY", new OkHttpClient(), new ObjectMapper());
```

### Methods

* All methods return **CompletableFuture**.
* All UUIDs can be both dashed and undashed.

#### Getting information about a player

Returns: **HypixelPlayer** (nullable).

```java
HypixelAPI#retrievePlayerByUuid("uuid");
HypixelAPI#retrievePlayerByName("nickname");
```

#### Getting information about a session

Returns: **Session** (nullable).

```java
HypixelAPI#retrieveSessionByUuid("uuid");
```

#### Getting information about a guild

Returns: **Guild** (nullable).

```java
HypixelAPI#retrieveGuildByName("guild_name");
HypixelAPI#retrieveGuildByPlayer("player_uuid");
```

#### Getting information about a set API key

Returns: **Key** (never-null).

```java
HypixelAPI#retrieveKey();
```

#### Getting friends of a player

Returns: **List\<Friendship>** (never-null).

```java
HypixelAPI#retrieveFriendsByUuid("uuid");
```

## License

The project is licensed under the **[MIT license](https://choosealicense.com/licenses/mit/)**.
