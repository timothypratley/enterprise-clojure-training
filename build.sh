#!/bin/sh
set -e
FLAGS=--destination-dir=site

asciidoctor ${FLAGS} docs/index.adoc docs/manual.adoc docs/getting-help.adoc docs/setup.adoc
asciidoctor-pdf ${FLAGS} docs/manual.adoc
asciidoctor-revealjs ${FLAGS} docs/advanced-topics.adoc docs/advanced-topics.adoc docs/talk.adoc
asciidoctor-revealjs ${FLAGS} docs/slides/*.adoc
