## Change log
----------------------

Version 2-SNAPSHOT
-------------

BREAKING:

- javax.mail is replaced by Jakarta Mail 2.1 (package jakarta.mail): EmailSendProperties builds
  its Authenticator from jakarta.mail, and email-tails 7 brings the Angus runtime. Every public
  signature that named a javax.mail type now names the jakarta one, which is why this is 2 and
  not 1.2
- the minimum Java version is 25 (was 1.8), the floor of email-tails 7
- SendMailTLS is removed, and with it decryptPassword and encryptPassword, its only members, which
  were protected and so open to subclasses. They decrypted a mail password stored in gmail.pw with
  three keys from emailsender.properties; both files lay in the repository, the keys also in the
  jar of version 1. The password was changed years ago, so the values were outdated (#11)
- EmailSendProperties.getEmailSendProperties is removed: it loaded emailsender.properties, the keys
  of SendMailTLS, and had no other use (#11)
- mystic-crypt is no longer a dependency: SendMailTLS was its only user. It was an implementation
  dependency, so a consumer that used it through this library has to declare it itself
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
- EmailSendProperties.getSmtpProperties(EmailConfiguration) and getSender(EmailConfiguration,
  username, password), for any SMTP server that wants a login; getGmailProperties and
  getGmailSender use them with smtp.gmail.com and port 587, unchanged
- tests that send through a GreenMail SMTP server inside the test JVM, with a login and a wrong
  password, and that render the three EmailComposer templates, instead of SendMailTLSTest, which
  needed a real Gmail account and was disabled. PIT kills 18 of 18 mutants; the floor in
  build.gradle is 94 (#11)
- Makefile with the family's targets: build, build-local (against a locally published email-tails
  snapshot), spotless, pitest, api-compatibility, publish-local, publish-snapshot and
  publish-central (the uploads need CONFIRM=yes), install-hooks; `make help` lists them

CHANGED:

- update gradle to version 9.7.0
- update of email-tails to 7 (released 2026-10-31), file-worker to 19.1, jobj-core to 9.1, resourcebundle-core to 6.1,
  lombok to 1.18.48, junit-jupiter to 6.1.3; xstream is lifted to 1.4.21
- email-tails and velocity are api dependencies, since this library's public signatures name
  their types; jakarta.mail-api is declared because EmailSendProperties uses it directly
- MessageComposer reads resources through ReadFileExtensions.toByteArray, the file-worker 19 form

REMOVED:

- the test resources gmail.pw and emailsender.properties, and SendMailTLSTest (#11)
- the direct dependencies on crypt-api, crypt-data and mystic-crypt, on
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
