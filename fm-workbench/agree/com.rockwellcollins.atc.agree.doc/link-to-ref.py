#!/usr/bin/env python

"""
Pandoc filter to convert all links to cross-references.
"""

from pandocfilters import toJSONFilter, Link, Str, Cite


def links(key, value, format, meta):
    if key == 'Link':
        [_, _, target] = value
        [_, _, targetInternal] = target[0].rpartition('#')
        citation = [{"citationSuffix"  : [],
                     "citationNoteNum" : 0,
                     "citationMode"    : {"t":"NormalCitation"},
                     "citationPrefix"  : [],
                     "citationId"      : targetInternal,
                     "citationHash"    : 0}]
        return Cite(citation, [Str(u"[@{0}]".format(targetInternal))])

if __name__ == "__main__":
    toJSONFilter(links)