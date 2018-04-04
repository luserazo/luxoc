/*
 * File: SemanticActions.java
 *
 * Desc: contains the semantic actions of the compiler.
 *
 * - Semantic Actions 1 [x]
 * - Semantic Actions 2 [ ]
 * - Semantic Actions 3 [ ]
 *
 * Sem 1: actions 1, 2, 3, 4, 6, 7, 9, 13.
 *
 */
package main.java.semantics;

import main.java.lexer.Tokenizer;
import main.java.semantics.errors.SemanticError;
import main.java.table.*;
import main.java.table.errors.SymbolTableError;
import main.java.token.Token;
import main.java.token.TokenType;

import java.util.LinkedList;
import java.util.Stack;

/**
 * SemanticActions
 * @author Luis Serazo
 */
public class SemanticActions {

    /* represents a specific semantic action */
    class Action{ public void run(Token token){ }}

    /* flags (SEM 1) */
    private boolean insert; /* INSERT / SEARCH */
    private boolean global; /* GLOBAL / LOCAL */
    private boolean array;  /* ARRAY / SIMPLE */

    /* global memory and local memory sizes */
    private int globalMem;
    private int localMem;

    /* local and global Symbol Tables */
    private final int TABLE_SIZE = 1000;
    private SymbolTable localTable;
    private SymbolTable globalTable;

    // TODO: Find a better way of doing this.?
    // TODO: Fix this, not good practice to use object (determine correct parents).
    /* Stack used for controlling semantic action routines */
    private Stack<Object> semanticsStack;
    private Action[] actions;

    /**
     * SemanticAction constructor
     * @param tokenizer: the lexer.
     */
    public SemanticActions(Tokenizer tokenizer){
        semanticsStack = new Stack<>();
        localTable = new SymbolTable(TABLE_SIZE);
        globalTable = new SymbolTable(TABLE_SIZE);
        /* set the flags */
        this.insert = true; // = INSERT
        this.global = true; // = GLOBAL
        this.array = false; // = SIMPLE
        /* initialize memory sizes */
        this.globalMem = 0;
        this.localMem = 0;

        /* the actions */
        actions = new Action[58]; // THERE ARE 58 actions.
        init();
    }

    /**
     * init: initializes the list of actions
     *
     */
    private void init(){
        // 1
        this.actions[0] = new Action(){ @Override public void run(Token token) { action_1(token); } };
        // 2
        this.actions[1] = new Action(){ @Override public void run(Token token) { action_2(token); } };
        // 3
        this.actions[2] = new Action(){ @Override public void run(Token token) { action_3(token); } };
        // 4
        this.actions[3] = new Action(){ @Override public void run(Token token) { action_4(token); } };
        // 5
        this.actions[4] = new Action();
        // 6
        this.actions[5] = new Action(){ @Override public void run(Token token) { action_6(token); } };
        // 7
        this.actions[6] = new Action(){ @Override public void run(Token token) { action_7(token); } };
        // 8
        this.actions[7] = new Action();
        // 9
        this.actions[8] = new Action(){ @Override public void run(Token token) { action_9(token); } };
        // 10
        this.actions[9] = new Action();
        // 11
        this.actions[10] = new Action();
        // 12
        this.actions[11] = new Action();
        // 13
        this.actions[12] = new Action(){ @Override public void run(Token token) { action_13(token); } };
        // 14
        this.actions[13] = new Action();
        // 15
        this.actions[14] = new Action();
        // 16
        this.actions[15] = new Action();
        // 17
        this.actions[16] = new Action();
        // 18
        this.actions[17] = new Action();
        // 19
        this.actions[18] = new Action();
        // 20
        this.actions[19] = new Action();
        // 21
        this.actions[20] = new Action();
        // 22
        this.actions[21] = new Action();
        // 23
        this.actions[22] = new Action();
        // 24
        this.actions[23] = new Action();
        // 25
        this.actions[24] = new Action();
        // 26
        this.actions[25] = new Action();
        // 27
        this.actions[26] = new Action();
        // 28
        this.actions[27] = new Action();
        // 29
        this.actions[28] = new Action();
        // 30
        this.actions[29] = new Action();
        // 31
        this.actions[30] = new Action();
        // 32
        this.actions[31] = new Action();
        // 33
        this.actions[32] = new Action();
        // 34
        this.actions[33] = new Action();
        // 35
        this.actions[34] = new Action();
        // 36
        this.actions[35] = new Action();
        // 37
        this.actions[36] = new Action();
        // 38
        this.actions[37] = new Action();
        // 39
        this.actions[38] = new Action();
        // 40
        this.actions[39] = new Action();
        // 41
        this.actions[40] = new Action();
        // 42
        this.actions[41] = new Action();
        // 43
        this.actions[42] = new Action();
        // 44
        this.actions[43] = new Action();
        // 45
        this.actions[44] = new Action();
        // 46
        this.actions[45] = new Action();
        // 47
        this.actions[46] = new Action();
        // 48
        this.actions[47] = new Action();
        // 49
        this.actions[48] = new Action();
        // 50
        this.actions[49] = new Action();
        // 51
        this.actions[50] = new Action();
        // 52
        this.actions[51] = new Action();
        // 53
        this.actions[52] = new Action();
        // 54
        this.actions[53] = new Action();
        // 55
        this.actions[54] = new Action();
        // 56
        this.actions[55] = new Action();
        // 57
        this.actions[56] = new Action();
        // 58
        this.actions[57] = new Action();
    } // end of INIT

    /**
     * execute: runs the semantic analyzer routine.
     * @param actionID: the action number passed by the parser.
     * @param token: the current Token being considered.
     * @throws SemanticError when a semantic error occurs in one of the IDs.
     * @throws SymbolTableError when the Symbol Table encounters a problem.
     *
     * Note: pseudo code is delineated by ':::'
     */
    public void execute(int actionID, Token token) throws SemanticError, SymbolTableError{
        try{
            Action act = this.actions[actionID - 1];
            act.run(token);
        }
        catch (ArrayIndexOutOfBoundsException ex){
            throw SemanticError.ActionDoesNotExist(actionID); // catch fail for semantic action not implemented yet.
        }
    } /* end of execute method */

    /**
     * action_1: semantic action 1
     * @param token: the Token in question.
     */
    private void action_1(Token token){
        /* INSERT/SEARCH = INSERT */
        this.insert = true;
    }

    /**
     * action_2: semantic action 2
     * @param token: the Token in question.
     */
    private void action_2(Token token){
        /* INSERT/SEARCH = SEARCH */
        this.insert = false;
    }

    /**
     * action_3: semantic action 3
     * @param token: the Token in question.
     */
    private void action_3(Token token){
        System.out.println(token.getTokenType());
        semanticStackDump();
        /* ::: TYP = pop TYPE ::: */
        Token tok = (Token) semanticsStack.pop();
        System.out.println("TOK "+tok);
        /* obtain the type of the Token */
        TokenType TYP = tok.getTokenType();
        /* ::: if ARRAY/SIMPLE = ARRAY ::: */
        if(this.array){
            /* ::: UB (LB) = pop CONSTANT ::: (upper bound, lower bound) */
            ConstantEntry UB = (ConstantEntry) semanticsStack.pop();
            ConstantEntry LB = (ConstantEntry) semanticsStack.pop();
            /* ::: MSIZE = (UB - LB) + 1 ::: */
            int MEM_SIZE = intDistance(UB, LB);
            storeArray(UB, LB, MEM_SIZE, TYP);
        }
        else{
            System.out.println("IN VAR CASE");
            storeVariable(TYP);
        }
        /* ::: ARRAY/SIMPLE = SIMPLE ::: */
        this.array = false;
    }

    /**
     * action_4: semantic action 4
     * @param token: the Token in question.
     */
    private void action_4(Token token){
        /* ::: push TYPE ::: */
        semanticsStack.push(token);
    }

    /**
     * action_6: semantic action 6
     * @param token: the Token in question.
     */
    private void action_6(Token token){
        /* ::: ARRAY/SIMPLE = ARRAY ::: */
        this.array = true;
    }

    /**
     * action_7: semantic action 7
     * @param token: the Token in question.
     */
    private void action_7(Token token){
        /* ::: push constant (real or int constant) ::: */
        ConstantEntry constant = new ConstantEntry(token.getValue(), token.getTokenType());
        semanticsStack.push(constant);
    }

    /**
     * action_9: semantic action 9
     * @param token: the Token in question.
     */
    private void action_9(Token token){
        /* insert top two ids (identifiers) on semantic stack in the sym table mark as reserved */
        insertIO((Token) semanticsStack.pop()); // must pop but insert io can be handled in install builtins.
        insertIO((Token) semanticsStack.pop());
        /* insert bottom most id in the sym table (Procedure entry, with num param = 0), mark as reserved */
        insertProcedure((Token) semanticsStack.pop()); // third one, so this is fine.
        /* INSERT/SEARCH = SEARCH */
        // TODO: pop ids?
        // TODO: take procedure from bottom-most???
        this.insert = false;
    }

    /**
     * action_13: semantic action 13
     * @param token: the Token in question.
     */
    private void action_13(Token token){
        /* ::: push id (identifier) ::: */
        semanticsStack.push(token);
    }



    /* -------------------  HELPERS  ---------------------- */

    /**
     * intDistance: the intefer distance of two int constants
     * @param x: a ConstantEntry
     * @param y: a ConstantEntry
     */
    private int intDistance(ConstantEntry x, ConstantEntry y){
        return (Integer.parseInt(x.getName()) - Integer.parseInt(y.getName()) + 1);
    }

    /**
     * intValue: get the integer value of a constants
     * @param x: a ConstantEntry
     */
    private int intValue(ConstantEntry x){ return Integer.parseInt(x.getName()); }

    /**
     * storeArray: store the array in memory.
     * @param UB: upper bound, a ConstantEntry.
     * @param LB: lower bound, a ConstantEntry.
     * @param MEM_SIZE: the memory size.
     * @param TYP: the token type
     */
    private void storeArray(ConstantEntry UB, ConstantEntry LB, int MEM_SIZE, TokenType TYP){
        /* ::: For each id on the semantic stack ::: */
        while(!semanticsStack.isEmpty()){
            /* ::: ID = pop id ::: */
            Token ID = (Token) semanticsStack.pop();

            /* ::: if GLOBAL/LOCAL = GLOBAL ::: (store in global memory) */
            ArrayEntry arryEntry;
            if(global){
                /* ::: insert id in global symbol table (Array_entry) ::: */
                arryEntry = new ArrayEntry(ID.getValue(), globalMem, TYP, intValue(UB), intValue(LB));
                insertToGlobal(arryEntry, ID.getLineNum());
                globalMem += MEM_SIZE;
            }
            /* ::: else insert id in local symbol table (Array_entry) ::: */
            else{
                arryEntry = new ArrayEntry(ID.getValue(), localMem, TYP, intValue(UB), intValue(LB));
                insertToLocal(arryEntry, ID.getLineNum());
                localMem += MEM_SIZE;
            }
        }
    }

    /**
     * storeVariable: store the simple variable in memory.
     * @param TYP: the TokenType that we're going to store as a var.
     */
    private void storeVariable(TokenType TYP){
        /* ::: For each id on the semantic stack ::: */
        while(!semanticsStack.isEmpty()){ // maybe use the new pseudo code?
            /* ::: ID = pop id ::: */
            Token ID = (Token) semanticsStack.pop();

            /* ::: if GLOBAL/LOCAL = GLOBAL ::: (store in global memory) */
            VariableEntry var;
            if(global){
                /* ::: insert id in global symbol table (Variable_entry) */
                var = new VariableEntry(ID.getValue(), globalMem, TYP);
                System.out.println(var.getName());
                insertToGlobal(var, ID.getLineNum());
                globalMem += 1;
            }
            /* ::: else insert id in local symbol table (Variable_entry) */
            else{
                var = new VariableEntry(ID.getValue(), localMem, TYP);
                insertToLocal(var, ID.getLineNum());
                localMem += 1;
            }
        }
    }

    /**
     * insertGlobal: insert into a the global symbol table
     * @param entry: the entry we're inserting.
     * Wrapper for SymbolTable insert (specified).
     */
    private void insertToGlobal(SymbolTableEntry entry, int lineNum) throws SemanticError, SymbolTableError{
        entry.nameToUpperCase();
        if(globalTable.lookup(entry.getName()) != null){
            if(lookupEntry(true, entry.getName()).isReserved()){
                throw SemanticError.ReservedName(entry.getName(), lineNum);
            }
            else{ throw SemanticError.NameAlreadyDeclared(entry.getName(), lineNum); }
        }
        globalTable.insert(entry);
    }

    /**
     * insertLocal: insert into a the local symbol table
     * @param entry: the entry we're inserting.
     * Wrapper for SymbolTable insert (specified).
     */
    private void insertToLocal(SymbolTableEntry entry, int lineNum) throws SemanticError, SymbolTableError{
        entry.nameToUpperCase();
        if(localTable.lookup(entry.getName()) != null){
            if(lookupEntry(false, entry.getName()).isReserved()){
                throw SemanticError.ReservedName(entry.getName(), lineNum);
            }
            else{ throw SemanticError.NameAlreadyDeclared(entry.getName(), lineNum); }
        }
        localTable.insert(entry);
    }

    /**
     * lookupEntry: lookup wrapper for specified symbol tables.
     * @param globalEntry: boolean indicating if a global entry or local entry
     * @param name: the String name of the entry.
     * @return a corresponding SymbolTableEntry or null (if doesn't exist).
     */
    private SymbolTableEntry lookupEntry(boolean globalEntry, String name){
        if(globalEntry){
            return (SymbolTableEntry) globalTable.lookup(name.toUpperCase()); // maybe just get rid of interface and cast.
        }
        else{
            SymbolTableEntry value = (SymbolTableEntry) localTable.lookup(name.toUpperCase());
            if(value != null){ return value; }
            return (SymbolTableEntry) globalTable.lookup(name.toUpperCase());
        }
    }

    /**
     * insertIO: insert the reserved file IO
     * @param token: a Token
     */
    private void insertIO(Token token) throws SymbolTableError, SemanticError{
        IODeviceEntry entry = new IODeviceEntry(token.getValue());
        entry.setToReserved();
        insertToGlobal(entry, token.getLineNum());
    }

    /**
     * insertProcedure: insert any reserved procedures
     * @param token: a Token
     */
    private void insertProcedure(Token token) throws SymbolTableError, SemanticError{
        ProcedureEntry entry = new ProcedureEntry(token.getValue(), 0, new LinkedList<ParameterInfo>());
        entry.setToReserved();
        insertToGlobal(entry, token.getLineNum());
    }

    /**
     * semanticStackDump: routine to dump the semantic stack
     */
    private void semanticStackDump(){
        System.out.println("Dumping the semantic stack ...");
        for(int i = 0; i < semanticsStack.size(); i++){
            System.out.println("- "+semanticsStack.get(i));
        }
    }

} /* end of SemanticActions class */