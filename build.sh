#!/bin/sh
set -e
FLAGS=--destination-dir=site

bundle exec asciidoctor ${FLAGS} docs/index.adoc docs/manual.adoc
bundle exec asciidoctor-pdf ${FLAGS} docs/manual.adoc
bundle exec asciidoctor-revealjs ${FLAGS} docs/slides.adoc docs/advanced-topics.adoc docs/advanced-topics.adoc
