/*
 * File: KeywordMap.java
 *
 * Desc: a wrapper class for a HashMap.
 *
 */
package main.java.lexer;

import main.java.token.Token;
import main.java.token.TokenType;
import java.util.HashMap;

/**
 * KeywordMap class
 * @author Luis Serazo
 */
public class KeywordMap {

    private HashMap<String, Token> keywords;

    /**
     * Keyword map constructor.
     * Note: constructor is package-private.
     */
    KeywordMap(){
        this.keywords = new HashMap<>();

        /* populate the hashmap */
        keywords.put("program", new Token(TokenType.PROGRAM, null));
        keywords.put("begin", new Token(TokenType.BEGIN, null));
        keywords.put("end", new Token(TokenType.END, null));
        keywords.put("var", new Token(TokenType.VAR, null));
        keywords.put("function", new Token(TokenType.FUNCTION, null));
        keywords.put("procedure", new Token(TokenType.PROCEDURE, null));
        keywords.put("result", new Token(TokenType.RESULT, null));
        keywords.put("integer", new Token(TokenType.INTEGER, null));
        keywords.put("real", new Token(TokenType.REAL, null));
        keywords.put("array", new Token(TokenType.ARRAY, null));
        keywords.put("of", new Token(TokenType.OF, null));
        keywords.put("if", new Token(TokenType.IF, null));
        keywords.put("then", new Token(TokenType.THEN, null));
        keywords.put("else", new Token(TokenType.ELSE, null));
        keywords.put("while", new Token(TokenType.WHILE, null));
        keywords.put("do", new Token(TokenType.DO, null));
        keywords.put("not", new Token(TokenType.NOT, null));

        /* special operators */
        keywords.put("or", new Token(TokenType.ADDOP, "3"));
        keywords.put("div", new Token(TokenType.MULOP, "3"));
        keywords.put("mod", new Token(TokenType.MULOP, "4"));
        keywords.put("and", new Token(TokenType.MULOP, "5"));
    }

    /**
     * isKeyword: determines if the buffer is a keyword or not.
     * @param buffer: a collection of characters in the file.
     * @return True, if the buffer is a keyword, False otherwise.
     */
    public boolean isKeyword(String buffer){
        String lowerCase = buffer.toLowerCase();
        return keywords.containsKey(lowerCase);
    }

    /**
     * getKeyword: returns the correct keyword from the hashmap.
     * @param buffer: the accumulated keyword as a string.
     * @param lineNum: the line number where the keyword began in the file.
     * @return a keyword Token matching the buffer.
     */
    public Token getKeyword(String buffer, int lineNum){
        buffer = buffer.toLowerCase();
        Token keywordToken = keywords.get(buffer);
        keywordToken.setLineNum(lineNum);
        return keywordToken;
    }

} /* end of KeywordMap class */