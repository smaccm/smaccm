grammar SendsTo;

@header {
    package edu.umn.cs.crisys.tb.parse.antlr;
}

program: '{' '}' EOF 
	| '{' sends_to_tl (',' sends_to_tl)* '}'  EOF;

sends_to_tl: '{' '}'
	| '{'  INT ID (',' INT ID)* '}' ;
	   
    
REAL: INT '.' INT;

BOOL: 'true' | 'false';
INT: [0-9]+;
ID: [a-zA-Z_][a-zA-Z_0-9]*;

WS: [ \t\n\r\f]+ -> skip;

SL_COMMENT: '//' (~[%\n\r] ~[\n\r]* | /* empty */) ('\r'? '\n')? -> skip;
ML_COMMENT: '/*' .*? '*/' -> skip;

ERROR: .;
    