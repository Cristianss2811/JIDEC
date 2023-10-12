package jide;
import static jide.Tokens
%%
%class Lexer
%type Tokens
%line
%column
L=[a-zA-Z_]+
D=-?[0-9]+|-?(([0-9]+[.]?[0-9]*)|([.][0-9]+))(e[+-]?[0-9]+)?
espacio=[ \t\r\n]+

%{
public String lexeme;
Analisis c=new Analisis();
%}
%%

{espacio} {/*Ignore*/}


<YYINITIAL> "int" {c.linea=yyline; lexeme=yytext();return ent;}
<YYINITIAL> "float" {c.linea=yyline; lexeme=yytext();return flot;}
<YYINITIAL> "char" {c.linea=yyline; lexeme=yytext();return car;}

<YYINITIAL> "," {c.linea=yyline; lexeme=yytext();return coma;}
<YYINITIAL> ";" {c.linea=yyline; lexeme=yytext();return  punto_coma;}
<YYINITIAL> "(" {c.linea=yyline; lexeme=yytext();return parentesis_abre;}
<YYINITIAL> ")" {c.linea=yyline; lexeme=yytext();return parentesis_cierra;}
<YYINITIAL> "=" {c.linea=yyline; lexeme=yytext();return igual;}
<YYINITIAL> "+" {c.linea=yyline; lexeme=yytext();return mas;}
<YYINITIAL> "-" {c.linea=yyline; lexeme=yytext();return menos;}
<YYINITIAL> "/" {c.linea=yyline; lexeme=yytext();return division;}
<YYINITIAL> "*" {c.linea=yyline; lexeme=yytext();return multiplicacion;}

<YYINITIAL> {D} {c.linea=yyline; lexeme=yytext();return num;}
<YYINITIAL> {L}({L}{D})* {c.linea=yyline; lexeme=yytext();return id;}

. {c.linea=yyline; lexeme=yytext(); return Error;}