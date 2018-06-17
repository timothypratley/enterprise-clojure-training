#!/bin/sh

ls docs/slides.adoc | entr bundler exec asciidoctor-revealjs --destination-dir=site docs/slides.adoc
