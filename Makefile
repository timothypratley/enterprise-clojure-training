SOURCES := $(wildcard docs/*.adoc)
HTML := $(patsubst docs/%.adoc, site/%.html, $(SOURCES))
PDF := $(patsubst docs/%.adoc, site/%.pdf, $(SOURCES))

COURSE_SOURCES := $(wildcard docs/*/*.adoc)
SLIDES := $(patsubst docs/%.adoc, site/%.html, $(COURSE_SOURCES))
ARTICLES := $(patsubst docs/%.adoc, site/%.article.html, $(COURSE_SOURCES))

# Tasks

.PHONY: all clean setup

all: setup $(HTML) $(PDF) $(SLIDES) $(ARTICLES) # site/js/main.js

clean:
	rm -fr .bundle site/*.html site/*.pdf site/*/*.html

setup: .bundle

# Asciidoctor dependencies

.bundle:
	bundle --path=.bundle/gems --binstubs=.bundle/.bin

# Articles
site/%.article.html: docs/%.adoc
	bundle exec asciidoctor --out-file $@ $< 

# Pages
site/%.html: docs/%.adoc
	bundle exec asciidoctor --out-file $@ $< 

# Slides
site/%.html: docs/%.adoc
	bundle exec asciidoctor-revealjs --out-file $@ $<

# PDF
site/%.pdf: docs/%.adoc
	bundle exec asciidoctor-pdf --out-file $@ $<

#site/js/main.js: deps.edn cljs.edn src/enterprise_clojure_training/main.cljs
#	clojure -M --main cljs.main --compile-opts cljs.edn --compile
