/*
 * File: ParameterInfo.java
 *
 * Desc: structure to contain parameter information
 *
 */
package main.java.table;

import main.java.token.Token;
import main.java.token.TokenType;

/**
 * ParameterInfo class
 * @author Luis Serazo
 */
public class ParameterInfo {

    /*
     * Common Parameter Info
     */
    private String name;
    private TokenType type;
    private int address;

    /*
     * If parameter is an Array
     */
    private boolean arrayParam;
    private int lowerBound;
    private int upperBound;

    /**
     * ParameterInfo constructor
     * @param name: the name of the parameter
     */
    public ParameterInfo(String name, TokenType type, int address){
        this.name = name;
        this.type = type;
        this.address = address;

        /* array info (default not an array) */
        this.arrayParam = false;
        this.lowerBound = -1;
        this.upperBound = -1;
    }

    /**
     * makeArray: sets the parameter to an array
     * @param lb: the lower bound
     * @param ub: the upper bound
     */
    public void makeArray(int lb, int ub){
        this.arrayParam = true;
        this.lowerBound = lb;
        this.upperBound = ub;
    }

    /**
     * isArray: boolean indicating if parameter is an array
     * @return arrayParam boolean.
     */
    public boolean isArray(){ return this.arrayParam; }

    /**
     * getType: getter method for the type of the parameter
     * @return the type of the parameter
     */
    public TokenType getType(){ return this.type; }

    /**
     * getName: getter method for the name of the param
     */
    public String getName(){ return this.name; }

    /**
     * getAddress: getter method for address
     * @return the parameter memory address
     */
    public int getAddress(){ return this.address; }

    /**
     * getLB: getter method for lower bound
     * @return lower bound of array, -1 if not an array
     */
    public int getLB(){ return this.lowerBound; }

    /**
     * getUB: getter method for upper bound
     * @return upper bound of array, -1 if not an array
     */
    public int getUB(){ return this.upperBound; }

    /**
     * toString: override the default toString method.
     */
    @Override
    public String toString(){
        String str = "Name: "+this.name+" Type: "+this.type+" Address: "+this.address;
        return str;
    }
}
