#!/usr/bin/env python

"""
Pandoc filter to convert all links to cross-references.
"""

from pandocfilters import attributes, toJSONFilter, Cite, Link, Space, Span, Str
import urlparse

def is_absolute(url):
    return bool(urlparse.urlparse(url).netloc)

def links(key, value, format, meta):
    if key == 'Link':
        [_, title, target] = value
        if (is_absolute(target[0])):
            # citation = [{"citationSuffix"  : [],
            #              "citationNoteNum" : 0,
            #              "citationMode"    : {"t":"NormalCitation"},
            #              "citationPrefix"  : [],
            #              "citationId"      : target[0],
            #              "citationHash"    : 0}]
            # return Cite(citation, title)
            return Span(attributes({}),
                        [Str(u'\u201c'), Span(attributes({}), title), Str(u'\u201d'),
                         Space(), Str('('), Str(target[0]), Str(')')])
        else:
            [_, _, targetInternal] = target[0].rpartition('#')
            citation = [{"citationSuffix"  : [],
                         "citationNoteNum" : 0,
                         "citationMode"    : {"t":"NormalCitation"},
                         "citationPrefix"  : [],
                         "citationId"      : targetInternal,
                         "citationHash"    : 0}]
            return Cite(citation, [Str("[@{0}]".format(targetInternal))])

if __name__ == "__main__":
    toJSONFilter(links)