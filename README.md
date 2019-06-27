[ ![Download](https://api.bintray.com/packages/mdashlw/maven/hypixel-api/images/download.svg) ](https://bintray.com/mdashlw/maven/hypixel-api/_latestVersion)
[![CircleCI](https://circleci.com/gh/mdashlw/hypixel-api.svg?style=svg)](https://circleci.com/gh/mdashlw/hypixel-api)

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

### Setup

#### API Key

To use Hypixel Public API you must provide an API key.
You can get your key by doing `/api new` on Hypixel.

You can provide many API keys, it will use random key.

```kotlin
HypixelApi.setup(listOf("key1", "key2"))
```

#### Output Mode

Output mode is used in formatting display names. *(Player/Guild/etc)*

The default is **Raw**.

* Raw `(RAW)` — just plain text, without any formatting.
* Markdown `(MARKDOWN)` — with markdown formatting.
* Colorized `(COLORIZED)` — with minecraft color codes.

```kotlin
HypixelApi.setup(listOf("key1", "key2"), HypixelApi.OutputMode.MARKDOWN)
```

### Methods

All return types are nullable.

UUIDs can be dashed and undashed.

#### Getting information about a player

Returns: **Player**.

```kotlin
HypixelApi.retrievePlayerByUuid("uuid")
HypixelApi.retrievePlayerByName("nickname")
```

#### Getting information about a player's session

Returns: **Session**.

```kotlin
HypixelApi.retrieveSessionByUuid("uuid")
```

#### Getting information about a guild by name/player

Returns: **Guild**.

```kotlin
HypixelApi.retrieveGuildByName("guild_name")
HypixelApi.retrieveGuildByPlayer("player_uuid")
```

#### Getting information about a Hypixel API key

Returns: **Key**.

```kotlin
HypixelApi.retrieveKey("key")
```

### Entities

#### Player

Represents a Hypixel player.

*Documentation is coming soon.*

#### Session

Represents a session.

|   Property  |                                                           Type                                                          |           Description          |
|:-----------:|:-----------------------------------------------------------------------------------------------------------------------:|:------------------------------:|
|   **game**  | [GameType](https://github.com/mdashlw/hypixel-api/blob/master/src/main/kotlin/ru/mdashlw/hypixel/api/enums/GameType.kt) |              Game              |
|  **server** |                                                          String                                                         |             Server             |
| **players** |                                                       List<String>                                                      | UUIDs of players on the server |

#### Guild

Represents a guild.

|      Property      |        Type       |        Description        |
|:------------------:|:-----------------:|:-------------------------:|
|      **name**      |       String      |            Name           |
|      **coins**     |        Int        |           Coins           |
|     **members**    |    List<Member>   |          Members          |
|    **joinable**    |      Boolean      |     Is guild joinable?    |
| **publiclyListed** |      Boolean      | Is guild publicly listed? |
|       **tag**      |      String?      |            Tag            |
|  **achievements**  | Map<String, Int>? |        Achievements       |
|       **exp**      |        Long       |            Exp            |
|  **legacyRanking** |        Int        |        Legacy rank        |
|   **description**  |       String      |        Description        |

##### Member

Represents a guild member.

| Property |  Type  |  Description  |
|:--------:|:------:|:-------------:|
| **uuid** | String | Undashed UUID |
| **rank** | String |      Rank     |

#### Key

Represents an api key.

|       Property       |  Type  |       Description      |
|:--------------------:|:------:|:----------------------:|
|     **ownerUuid**    | String |   Undashed owner UUID  |
|        **key**       | String |           Key          |
|   **totalQueries**   |   Int  |      Total queries     |
| **queriesInPastMin** |  Int?  | Queries in past minute |

## License

The project is licensed under the **[MIT license](https://choosealicense.com/licenses/mit/)**.
