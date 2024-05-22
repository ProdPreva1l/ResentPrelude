[![GitHub Release](https://img.shields.io/github/release/ProdPreva1l/ResentClientAPI.svg?style=flat)]()

## Usage for Server Owners
If you are a server owner, the usage for the ResentClient API is pretty simple.
1. Go to the latest release on GitHub https://github.com/ProdPreva1l/ResentClientAPI/releases/latest/
2. Download `ResentClientAPI-Bukkit-<latest-version>.jar` (where `<latest-version>` is the version found at the top of this page)
3. Upload to your servers `/plugins/` directory
4. (Optionally) Configure in `/plugins/BukkitResentAPI/config.yml`


## Usage for Bukkit Plugin Developers
If you are a plugin developer, the usage for the ResentClient API is as follows

For example usage and documentation see here: [Server-Implementation](Server-Implementation.md)

The following steps are to get the API into your project

1. Go to the latest release on GitHub https://github.com/ProdPreva1l/ResentClientAPI/releases/latest/
2. Download `ResentClientAPI-API-<latest-version>.jar` (where `<latest-version>` is the version found at the top of this page)
3. Create a `libs` directory in the root of your project
4. Drop the jar file into the newly created `libs` directory

Now the next step varies depending on what build tool you use
#### Using Gradle Groovy
In your `build.gradle` file under dependencies add the following (Replace `<latest-version>` with the version at the top of the page)
```gradle
compileOnly files("libs/ResentClientAPI-API-<latest-version>.jar")
```

#### Using Gradle Kotlin
In your `build.gradle.kts` file add the following (Replace `<latest-version>` with the version at the top of the page)
```kts
compileOnly(files("libs/ResentClientAPI-API-<latest-version>.jar"))
```

#### Using Maven
In your `maven.pom` file add the following (Replace `{latest-version}` with the version at the top of the page)
```xml
<dependency>
    <groupId>info.preva1l</groupId>
    <artifactId>ResentClientAPI-API</artifactId>
    <version>{latest-version}</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/ResentClientAPI-API-{latest-version}.jar</systemPath>
</dependency>
```

## Usage for Client Developers
See Here: [Client-Implementation](Client-Implementation.md)