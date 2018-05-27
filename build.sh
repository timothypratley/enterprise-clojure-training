#!/bin/sh

set -e

asciidoctor --destination-dir=site index.adoc manual/*.adoc slides/*.adoc
