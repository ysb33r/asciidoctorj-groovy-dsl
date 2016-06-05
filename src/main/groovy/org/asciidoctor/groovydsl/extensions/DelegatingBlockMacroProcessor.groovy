/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asciidoctor.groovydsl.extensions

import org.asciidoctor.ast.AbstractBlock
import org.asciidoctor.extension.BlockMacroProcessor

class DelegatingBlockMacroProcessor extends BlockMacroProcessor {

    private final Closure cl

    DelegatingBlockMacroProcessor(String name, Map options, @DelegatesTo(BlockMacroProcessor) Closure cl) {
        super(name, options)
        this.cl = cl
        cl.delegate = this
    }
    
    Object process(AbstractBlock parent, String target, Map<String, Object> attributes) {
        cl.call(parent, target, attributes)
    }

}

