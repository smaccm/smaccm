EPSFILES=

TEXFILES = $(BASE).tex $(wildcard section_?.tex)

BIBFILES=

BASE=agree-tutorial

all: $(BASE).pdf

%.pdf: %.svg
	inkscape --export-pdf=$@ $<

%.pdf: %.png
	convert $< $@ 

$(BASE).pdf: $(TEXFILES) $(EPSFILES) $(BIBFILES)
	pdflatex $(BASE).tex
	pdflatex $(BASE).tex
#	bibtex $(BASE)
#	pdflatex $(BASE).tex
#	pdflatex $(BASE).tex

clean:
	rm -f *.aux *.log $(SOURCES) $(BASE).pdf \
	  *.bbl *.blg *~ *.bak *.out *.backup \
	  imgs/*.eps imgs/*.pdf *.toc
