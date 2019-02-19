#!/bin/sh
set -e

# get dependencies
if [ ! -d .bundle ]; then
  bundle --path=.bundle/gems --binstubs=.bundle/.bin
fi

# build with dependencies
bundle exec ./build.sh
