SOURCEDIR := docs
BUILDDIR := site
FLAGS := --destination-dir=$(BUILDDIR)
SOURCES := $(wildcard $(SOURCEDIR)/*.adoc)
HTMLS := $(patsubst $(SOURCEDIR)/%.adoc, $(BUILDDIR)/%.html, $(SOURCES))
PDFS := $(patsubst $(SOURCEDIR)/%.adoc, $(BUILDDIR)/%.pdf, $(SOURCES))
SLIDESOURCES := $(wildcard $(SOURCEDIR)/slides/*.adoc)
SLIDES := $(patsubst $(SOURCEDIR)/slides/%.adoc, $(BUILDDIR)/%.html, $(SLIDESOURCES))

.PHONY: all clean setup

all: setup $(HTMLS) $(PDFS) $(SLIDES)

clean:
	rm $(BUILDDIR)/*.html $(BUILDDIR)/*.pdf

setup: .bundle

.bundle:
	bundle --path=.bundle/gems --binstubs=.bundle/.bin

$(BUILDDIR)/%.html: $(SOURCEDIR)/%.adoc
	bundle exec asciidoctor $(FLAGS) $< 

$(BUILDDIR)/%.pdf: %.adoc
	bundle exec asciidoctor-pdf $(FLAGS) $<

$(BUILDDIR)/slides/%.html: $(SOURCEDIR)/slides/%.adoc
	bundle exec asciidoctor-revealjs $(FLAGS) $<
