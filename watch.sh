#!/bin/sh

ls docs/slides.adoc docs/talk.adoc | entr bundler exec asciidoctor-revealjs --destination-dir=site docs/slides.adoc docs/talk.adoc
