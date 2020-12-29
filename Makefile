SOURCES := $(wildcard docs/*.adoc)
COURSE_SOURCES := $(wildcard docs/*/*.adoc)
ALL_SOURCES := $(SOURCES) $(COURSE_SOURCES)
HTML := $(patsubst docs/%.adoc, site/%.html, $(ALL_SOURCES))
PDF := $(patsubst docs/%.adoc, site/%.pdf, $(ALL_SOURCES))
ARTICLES := $(patsubst docs/%.adoc, site/%.html, $(COURSE_SOURCES))
SLIDES := $(patsubst docs/%.adoc, site/%.slides.html, $(COURSE_SOURCES))
ALL := $(HTML) $(PDF) $(ARTICLES) $(SLIDES)

# Tasks

.PHONY: all clean setup

all: setup $(ALL) # site/js/compiled/main.js

clean:
	rm -fr .bundle site/*.html site/*.pdf site/*/*.html

setup: .bundle

## Asciidoctor dependencies

.bundle: Gemfile
	bundle --path=.bundle/gems --binstubs=.bundle/.bin

# Rules

## Slides
site/%.slides.html: docs/%.adoc docs/docinfo.attrs .bundle
	bundle exec asciidoctor-revealjs --out-file $@ $<

## HTML
site/%.html: docs/%.adoc docs/docinfo.attrs .bundle
	bundle exec asciidoctor --out-file $@ $< 

## PDF
site/%.pdf: docs/%.adoc docs/docinfo.attrs .bundle
	bundle exec asciidoctor-pdf --out-file $@ $<

#site/js/compiled/main.js: deps.edn cljs.edn src/enterprise_clojure_training/main.cljs
#	clojure -M --main cljs.main --compile-opts cljs.edn --compile

pdfslides:
	docker run --rm -t -v `pwd`:/slides -v ~:/home/user astefanutti/decktape site/extra/term-rewriting-with-meander-examples.slides.html slides.pdf

