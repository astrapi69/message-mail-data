# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/message-mail-data/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/message-mail-data/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/message-mail-data/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/message-mail-data)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/message-mail-data.svg?style=flat)](https://github.com/astrapi69/message-mail-data/issues)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/message-mail-data.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.astrapi69/message-mail-data)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/message-mail-data.svg)](http://www.javadoc.io/doc/io.github.astrapi69/message-mail-data)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/message-mail-data?branch=develop)](https://hitsofcode.com/github/astrapi69/message-mail-data/view?branch=develop)
[![Lines Of Code](https://tokei.rs/b1/github/astrapi69/message-mail-data)](https://github.com/astrapi69/message-mail-data)

</div>

This project holds pojo classes for messages and email messages, composes localized mails from
XML templates with Velocity, and sends them through email-tails on Jakarta Mail 2.1.

Version 2 requires Java 25 or newer and replaces javax.mail with jakarta.mail; a project still on
javax.mail stays on version 1.1.

> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
<a class="github-button" href="https://github.com/astrapi69/message-mail-data" data-icon="octicon-star" aria-label="Star astrapi69/message-mail-data on GitHub">Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

## Note

No animals were harmed in the making of this library.


## Maven dependency

Maven dependency is now on Maven Central. Check out
[Maven Central](https://central.sonatype.com/artifact/io.github.astrapi69/message-mail-data) for the
latest releases, and the
[Central Portal snapshots](https://central.sonatype.com/repository/maven-snapshots/) for snapshots.
Replace the variable ${latestVersion} with the current latest version:
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/message-mail-data.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.astrapi69/message-mail-data)

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of message-mail-data:

Than you can add the dependency to your dependencies:

    <properties>
            ...
        <!-- message-mail-data version -->
        <message-mail-data.version>${latestVersion}</message-mail-data.version>
            ...
    </properties>
            ...
        <dependencies>
            ...
            <!-- message-mail-data DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>message-mail-data</artifactId>
                <version>${message-mail-data.version}</version>
            </dependency>
            ...
        </dependencies>

## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of message-mail-data:

define version in file gradle.properties

```
messageMailDataVersion=${latestVersion}
```

or in build.gradle ext area

```
    messageMailDataVersion = "${latestVersion}"
```

and than add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:message-mail-data:$messageMailDataVersion")
```

or, with a version catalog, in gradle/libs.versions.toml

```
[versions]
message-mail-data-version = "${latestVersion}"

[libraries]
message-mail-data = { module = "io.github.astrapi69:message-mail-data", version.ref = "message-mail-data-version" }
```

## Development

The build is Gradle 9 with a version catalog in `gradle/libs.versions.toml`; the build logic sits in
`gradle/*.gradle`, listed in `gradle/gradle-files.list`. `make help` lists the make targets that wrap
the everyday Gradle calls.

```
./gradlew build            # compile, test, jacoco, spotless check, API compatibility against the last release
./gradlew spotlessApply    # format, order imports, add the MIT header
./gradlew pitest           # mutation testing; the floor stands in build.gradle
./gradlew dependencyUpdates versionCatalogUpdate --interactive   # see and stage dependency updates
```

While email-tails 7 is a snapshot, build against a locally published one with
`./gradlew build -PuseMavenLocal`; without the flag the build resolves it from the Central Portal
snapshot repository.

Install the commit hook once after cloning; it refuses a commit that credits a non-human
collaborator, the same rule the commit-provenance workflow enforces on pull requests:

```
git config core.hooksPath .githooks
```

Releasing is a manual step: push a `RELEASE-*` tag (`./gradlew tagRelease`), and the publish
workflow uploads the signed artifacts to the Central Portal. A push to develop publishes a
snapshot. Security reports go through [SECURITY.md](SECURITY.md), not the issue tracker.

# Donations

This project is kept as an open source product and relies on contributions to remain being
developed. If you like this library, please consider a donation

over paypal:
<br>
<br>
<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MJ7V43GU2H386" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif"
alt="PayPal this"
title="PayPal – The safer, easier way to pay online!"
style="border: none" />
</a>
<br>
<br>
or over bitcoin(BTC) with this address:

bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy.png"
alt="Donation Bitcoin Wallet" width="250"/>

or over FIO with this address:

FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop.png"
alt="Donation FIO Wallet" width="250"/>

or over Ethereum(ETH) with:

0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D.png"
alt="Donation Ethereum Wallet" width="250"/>

or over Ethereum Classic(ETC) with:

0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD.png"
alt="Donation Ethereum Classic Wallet" width="250"/>

or over Dogecoin(DOGE) with:

D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1.png"
alt="Donation Dogecoin Wallet" width="250"/>

or over Monero(XMR) with:

49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw.png"
alt="Donation Monero Wallet" width="250"/>

or over flattr:

<a href="https://flattr.com/submit/auto?user_id=astrapi69&url=https://github.com/astrapi69/message-mail-data" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" style="border: none" />
</a>

## License

The source code comes under the liberal MIT License, making message-mail-data great for all types of
applications.

## Semantic Versioning

The versions of message-mail-data are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit
the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## Want to Help and improve it? ###

The source code for message-mail-data are on GitHub. Please feel free to fork and send pull
requests!

Create your own fork
of [astrapi69/message-mail-data/fork](https://github.com/astrapi69/message-mail-data/fork)

To share your
changes, [submit a pull request](https://github.com/astrapi69/message-mail-data/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the message-mail-data developers with your questions, concerns, comments,
bug reports, or feature requests.

- Feature requests, questions and bug reports can be reported at
  the [issues page](https://github.com/astrapi69/message-mail-data/issues).

## Similar projects

## Credits
