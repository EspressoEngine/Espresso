package io.github.espressoengine;

import java.io.File;

class Resource extends File {
    // Now I can add any extra utility functions should I need to in the future. Adding this class right now ensures
    // that when that does happen users don't have to rewrite all their programs to replace File instances with this.
    public Resource(String path) {
        super(path);
    }
}