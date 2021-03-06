/*
 * File: SemanticError.java
 *
 * Desc: describes a semantic action error.
 *
 */
package main.java.semantics.errors;

import main.java.token.TokenType;

/**
 * SemanticError
 * @author Luis Serazo
 */
public class SemanticError extends Error {

    /**
     * SemanticError constructor
     * @param msg: error message
     */
    private SemanticError(String msg){ super(msg); }

    public static SemanticError ReservedName(String name, int lineNumber){
        return new SemanticError("Attempted to use a reserved name: "+name+ " on line: "+lineNumber+". Not allowed to use reserved names.");
    }

    public static SemanticError NameAlreadyDeclared(String name, int lineNumber){
        return new SemanticError("The name: "+name+" on line: "+lineNumber+" has already been declared.");
    }

    public static SemanticError ReservedName(String name){
        return new SemanticError("Attempted to use a reserved name: "+name+ ". Not allowed to use reserved names.");
    }

    public static SemanticError NameAlreadyDeclared(String name){
        return new SemanticError("The name: "+name+" has already been declared.");
    }

    public static SemanticError ActionDoesNotExist(int num){
        return new SemanticError("The semantic action: "+num+" does not exist.");
    }

    public static SemanticError UnrecognizedTypes(TokenType tt1, TokenType tt2, int lineNum){
        return new SemanticError("The combination of Type: "+tt1+" and Type: "+tt2+" is unrecognized on line: "+lineNum+".");
    }

    public static SemanticError InputOutputNotSpecified(){
        return new SemanticError("Input and Output not specified in program identifier list.");
    }

    public static SemanticError UndeclaredVariable(String name, int lineNumber){
        return new SemanticError("Undeclared variable: "+name+" on line: "+lineNumber);
    }

    public static SemanticError UnmatchedTypes(String name1, String name2, int linNumber){
        return new SemanticError("The types of: "+name1+" and of: "+name2+" don't match on line: "+linNumber+".");
    }

    public static SemanticError BadMod(int lineNumber){
        return new SemanticError("Bad MOD setup on line: "+lineNumber+" operands of the MOD operator must both be of type: Integer.");
    }

    public static SemanticError BadDiv(int lineNumber){
        return new SemanticError("Bad DIV setup on line: "+lineNumber+" operands of the DIV operator must both be of type: Integer.");
    }

    public static SemanticError BadFunction(String name, int lineNumber){
        return new SemanticError("Bad function: "+name+" on line: "+lineNumber+".");
    }

    public static SemanticError BadProcedure(String name, int lineNumber){
        return new SemanticError("Bad procedure: "+name+" on line: "+lineNumber+".");
    }

    public static SemanticError ExpectedArray(int lineNumber){
        return new SemanticError("Expected array on line: "+lineNumber+".");
    }

    public static SemanticError InvalidArrayIndex(int lineNumber){
        return new SemanticError("Invalid array index on line: "+lineNumber+". Array indices must be of type: Integer.");
    }

    public static SemanticError InvalidArithmetic(int lineNumber){
        return new SemanticError("Invalid expression on line: "+lineNumber+". Expected an arithmetic expression but found a relational expression.");
    }

    public static SemanticError InvalidRelational(int lineNumber){
        return new SemanticError("Invalid expression on line: "+lineNumber+". Expected a relational expression but found an arithmetic expression.");
    }

    public static SemanticError NoSuchAction(int actionID){
        return new SemanticError("Semantic Action: "+actionID+" is not in use. No current implementation.");
    }

    public static SemanticError WrongNumberOfParameters(String routineName, int lineNumber){
        return new SemanticError("Invalid number of arguments given to: "+routineName+" on line: "+lineNumber+". The number of arguments must match the number of parameters for any function or procedure.");
    }

    public static SemanticError InvalidParameter(String paramName, int lineNumber){
        return new SemanticError("Invalid argument: "+paramName+" being passed to a function or procedure on line: "+lineNumber+". Parameters must be variables, constants, or arrays.");
    }

    public static SemanticError ParameterTypeMismatch(TokenType t1, TokenType t2, int lineNumber){
        return new SemanticError("Attempting to use an argument of type: "+t1+" when routine expected a type of: "+t2+" on line: "+lineNumber+".");
    }

    public static SemanticError InvalidArrayBound(boolean upper, int lineNumber){
        String bound = "upper";
        if(!upper){ bound = "lower"; }
        return new SemanticError("Array argument on line: "+lineNumber+" has an invalid "+bound+" bound. Parameter and argument bounds must match.");
    }

    public static SemanticError TypeMismatch(int lineNumber){
        return new SemanticError("Type mismatch on line: "+lineNumber+".");
    }

    public static SemanticError RoutineNotFound(int lineNumber){
        return new SemanticError("Routine Not Found On Line: "+lineNumber+".");
    }

    public static SemanticError ArrayNotFound(int lineNumber){
        return new SemanticError("Array Not Found On Line: "+lineNumber+".");
    }

    public static SemanticError OperandStringUnknown(String name){
        return new SemanticError("Internal Error. TVI operand string unknown for: "+name+".");
    }

}
