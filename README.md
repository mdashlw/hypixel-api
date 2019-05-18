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

### API key

To use Hypixel Public API you must provide an API key.

You can get your key by doing `/api new` on Hypixel.

#### Setting

```kotlin
HypixelAPI.apiKey = UUID.fromString("API_KEY")
```

### Output Mode

Output mode is used in formatting display names. *(Player/Guild/etc)*

* Raw `(RAW)` — just plain text, without any formatting.
* Markdown `(MARKDOWN)` — with markdown formatting.
* Colorized `(COLORIZED)` — with minecraft color codes.

The default is **Raw**.

#### Setting

```kotlin
HypixelAPI.outputMode = HypixelAPI.OutputMode.COLORIZED
```

### Methods

**Note**: All return types are nullable.

**Note:** All UUIDs can be both dashed and undashed.

#### Getting a player information

Returns: **Player**.

```kotlin
HypixelAPI.getPlayerByUUUID("uuid")
HypixelAPI.getPlayerByName("nickname")
```

#### Getting player's session information

Returns: **Session**.

```kotlin
HypixelAPI.getSessionByUUID("uuid")
```

#### Getting a guild information by name/player

Returns: **Guild**.

```kotlin
HypixelAPI.getGuildByName("guild_name")
HypixelAPI.getGuildByPlayer("player_uuid")
```

### Entities

#### Player

Represents a Hypixel player.

*Soon...*

#### Session

Represents a session.

|   Property  	|                                                           Type                                                          	|           Description          	|
|:-----------:	|:-----------------------------------------------------------------------------------------------------------------------:	|:------------------------------:	|
|   **game**  	| [GameType](https://gitlab.com/mdashlw/hypixel-api/blob/master/src/main/kotlin/ru/mdashlw/hypixel/api/enums/GameType.kt) 	|              Game              	|
|  **server** 	|                                                          String                                                         	|             Server             	|
| **players** 	|                                                       List<String>                                                      	| UUIDs of players on the server 	|

### Guild

Represents a guild.

|      Property      	|      Type     	|        Description        	|
|:------------------:	|:-------------:	|:-------------------------:	|
|      **name**      	|     String    	|            Name           	|
|      **coins**     	|      Int      	|           Coins           	|
|     **members**    	|  List<Member> 	|          Members          	|
|    **joinable**    	|    Boolean    	|     Is guild joinable?    	|
| **publiclyListed** 	|    Boolean    	| Is guild publicly listed? 	|
|       **tag**      	|    String?    	|            Tag            	|
|  **achievements**  	| Achievements? 	|        Achievements       	|
|       **exp**      	|      Long     	|            Exp            	|
|  **legacyRanking** 	|      Int      	|        Legacy rank        	|
|   **description**  	|     String    	|        Description        	|

#### Member

Represents a guild member.

| Property 	|  Type  	| Description 	|
|:--------:	|:------:	|:-----------:	|
| **uuid** 	| String 	|     UUID    	|
| **rank** 	| String 	|     Rank    	|

#### Achievements

Represents guild achievements.

|       Property      	| Type 	|    Description   	|
|:-------------------:	|:----:	|:----------------:	|
|     **winners**     	|  Int 	|      Winners     	|
| **experienceKings** 	|  Int 	| Experience kings 	|
|  **onlinePlayers**  	|  Int 	|  Online players  	|

## License

The project is licensed under the **[MIT license](https://choosealicense.com/licenses/mit/)**.
