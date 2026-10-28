# Security Policy

`message-mail-data` composes mails from templates and sends them through email-tails, and its
SendMailTLS helper decrypts a stored mail password. A defect here can deliver a message to the
wrong recipient, render untrusted text into a template or expose a credential, so the reporting
path below is deliberately not the public issue tracker.

## Supported Versions

Only the current minor line receives security fixes. This is a single-maintainer project, and
supporting several lines at once is a promise it could not keep.

| Version | Supported          | Required JDK | Mail API                 |
|---------|--------------------|--------------|--------------------------|
| 2.x     | :white_check_mark: | 25 and above | jakarta.mail 2.1 (Angus) |
| 1.x     | :x:                | 8 and above  | javax.mail 1.4           |

The current release is the latest one on the
[releases page](https://github.com/astrapi69/message-mail-data/releases) and on Maven Central. If
you are on an older line, the fix for a reported vulnerability will be an upgrade to the current
one rather than a backport.

## Reporting a Vulnerability

**Do not open a public issue.** Use GitHub's private vulnerability reporting:

[Report a vulnerability](https://github.com/astrapi69/message-mail-data/security/advisories/new)

That keeps the report between you and the maintainer until a fix exists, and it creates the draft
advisory a CVE can later be issued from.

Please include, as far as you can:

- the version you found it in, and whether the current release is affected
- which class or method is involved
- what an attacker gains - a message delivered elsewhere, template code executed, a credential
  read
- a way to reproduce it: a failing test is ideal, since it becomes the regression guard

### What happens next

This is a spare-time project, so no response time is promised that could not be met. What is
promised instead:

- a reply acknowledging the report, and whether it is reproducible
- if it is: a fix on the current line, a release, and an advisory crediting you unless you prefer
  otherwise
- if it is not, or it turns out to be intended behaviour: an explanation of why, rather than
  silence

### What belongs elsewhere

Template rendering comes from Apache Velocity, XML binding from XStream, mail transport from
Jakarta Mail and Eclipse Angus through email-tails, and the encryption of the stored password
from mystic-crypt. A flaw inside one of them belongs there:

- Apache Velocity: <https://www.apache.org/security/>
- XStream: <https://x-stream.github.io/security.html>
- email-tails: <https://github.com/lightblueseas/email-tails/security/policy>
- mystic-crypt: <https://github.com/astrapi69/mystic-crypt/security/policy>

A flaw in *how this library uses* them - a template merged with unescaped user input, an XML
alias that lets an unexpected class in, a key read from the wrong place - is squarely in scope
here.

## Known limitations

- The templates are rendered with Velocity 1.7, the version velocity-extensions 1.5 is built
  against (lightblueseas/velocity-extensions#6 asks for a release on velocity-engine-core 2.4.1).
  Velocity 1.7 is affected by CVE-2020-13936 when a template is written by an attacker;
  this library renders templates from its own classpath, never from user input, which is why that
  advisory does not apply to it as shipped. Do not pass user-written templates through
  MessageComposer.
- `src/test/resources` holds an encrypted test password together with the keys that encrypt it.
  Treat anything stored that way as public.

## What this project already does

- **CodeQL** runs on every push and pull request to `master` and `develop`, plus weekly.
- **Dependabot security updates** are enabled.
- The build gate `apiCompatibility` compares every build with the last release, so a breaking
  change cannot slip into a minor version.
- PIT mutation testing runs in CI with the floor recorded in `build.gradle`. The floor is 0
  because the only test needs a mail account and is disabled; that is a documented gap, not an
  accident.
