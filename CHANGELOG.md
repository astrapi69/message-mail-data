## Change log
----------------------

Version 2-SNAPSHOT
-------------

BREAKING:

- javax.mail is replaced by Jakarta Mail 2.1 (package jakarta.mail): EmailSendProperties builds
  its Authenticator from jakarta.mail, and email-tails 7 brings the Angus runtime. Every public
  signature that named a javax.mail type now names the jakarta one, which is why this is 2 and
  not 1.2
- the minimum Java version is 25 (was 1.8), the floor of mystic-crypt 13
- mystic-crypt 13.7 instead of 7.11: the encryptors and decryptors moved to the packages
  io.github.astrapi69.mystic.crypt.hex, .chainable and .core; crypt-api and crypt-data are no
  longer declared here, mystic-crypt exposes them
- xstream-extensions 1.1 instead of xml-extensions 7.6, whose XStream helpers it took over:
  MessageContent and MessageComposer call toObject and toXml instead of toObjectWithXStream and
  toXmlWithXStream

ADDED:

- gradle: version catalog (gradle/libs.versions.toml), build split into gradle/*.gradle files,
  spotless with the shared formatter, import order and MIT header, jacoco, PIT mutation testing
  with a floor in build.gradle, japicmp API compatibility gate against the last release
  (apiBaselineVersion in gradle.properties), publishing to the Central Portal through nmcp with
  signing that follows the key, mavenLocal only with -PuseMavenLocal
- github: CodeQL, commit provenance, closing references and release freeze workflows, a publish
  workflow for RELEASE-* tags, codecov configuration, SECURITY.md, a commit-msg hook under
  .githooks (install with `git config core.hooksPath .githooks`)
- Makefile with the family's targets: build, build-local (against a locally published email-tails
  snapshot), spotless, pitest, api-compatibility, publish-local, publish-snapshot and
  publish-central (the uploads need CONFIRM=yes), install-hooks; `make help` lists them

CHANGED:

- update gradle to version 9.7.0
- update of email-tails to 7 (released 2026-10-31), file-worker to 19.1, jobj-core to 9.1, resourcebundle-core to 6.1,
  lombok to 1.18.48, junit-jupiter to 6.1.3; xstream is lifted to 1.4.21
- email-tails and velocity are api dependencies, since this library's public signatures name
  their types; jakarta.mail-api is declared because EmailSendProperties uses it directly
- SendMailTLS writes the encrypted password through StoreFileExtensions.toFile and reads
  resources through ReadFileExtensions.toByteArray, the file-worker 19 forms

REMOVED:

- the direct dependencies on crypt-api and crypt-data (reached through mystic-crypt), on
  xml-extensions (replaced), and the unused test dependencies silly-io, throw-able, commons-text
  and meanbean
- the Travis CI configuration


Version 1.1
-------------

ADDED:

- new github-action workflow for 'Java CI with Gradle'
- new gradle plugin dependency of 'com.diffplug.spotless:spotless-plugin-gradle' to minor version 6.3.0

CHANGED:

- update gradle to new version 7.4
- moved all classes to new package 'io.github.astrapi69'

Version 1
-------------

ADDED:

- new CHANGELOG.md file created
- new view model classes for several messages

CHANGED:

- update gradle to new version 6.8.1
- moved and adapted utility classes from project message-system-data

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into
changelogs
